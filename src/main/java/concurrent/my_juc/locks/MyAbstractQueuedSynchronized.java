package concurrent.my_juc.locks;


import concurrent.my_juc.util.UnsafeUtil;
import sun.misc.Unsafe;


/**
 * 队列同步器 是juc包中其他类的基础
 * 原版 {@link java.util.concurrent.locks.AbstractQueuedSynchronizer}
 * <p>
 * 原理：维护一个同步队列(Node链表)和一个同步状态(int status) 通过CAS获取该状态 当线程获取不到这个状态
 * 就加入等待队列中, 并且进行自选的状态, 知道自己是队列的头结点并且获得同步状态时退出循环 这个过程叫做 `自旋`
 */
public abstract class MyAbstractQueuedSynchronized {

    // 同步状态 设置成volatile 保证可见性
    private volatile int state;
    // 队列的头结点
    private transient volatile Node head;
    // 队列的尾结点
    private transient volatile Node tail;
    // 通过unsafe 获取偏移量
    private static final Unsafe unsafe;
    private static final long stateOffset;
    private static final long headOffset;
    private static final long tailOffset;

    /**
     * 使用静态代码块初始化重要变量的偏移量 方便使用unsafe CAS修改
     */
    static {
        try {
            unsafe = UnsafeUtil.getUnsafe();
            stateOffset = unsafe.objectFieldOffset
                    (MyAbstractQueuedSynchronized.class.getDeclaredField("state"));
            headOffset = unsafe.objectFieldOffset
                    (MyAbstractQueuedSynchronized.class.getDeclaredField("head"));
            tailOffset = unsafe.objectFieldOffset
                    (MyAbstractQueuedSynchronized.class.getDeclaredField("tail"));
        } catch (NoSuchFieldException e) {
            throw new Error(e);
        }
    }

    /**
     * 通过CAS 设置 state 变量预期是空才能设置成功
     *
     * @param expect 预期的状态值
     * @param update 需要更新的值
     * @return 成功设置返回 true
     */
    protected final boolean compareAndSetState(int expect, int update) {
        return unsafe.compareAndSwapInt(this, stateOffset, expect, update);
    }

    /**
     * 通过CAS 设置head 变量预期是空才能设置成功
     */
    private final boolean compareAndSetHead(Node update) {
        return unsafe.compareAndSwapObject(this, headOffset, null, update);
    }

    /**
     * 通过CAS 设置tail 变量 设置预期值
     */
    private final boolean compareAndSetTail(Node expect, Node update) {
        return unsafe.compareAndSwapObject(this, tailOffset, expect, update);
    }

    public int getState() {
        return state;
    }

    /**
     * 设置头结点 此时已经获取状态
     * @param node
     */
    private final void setHead(Node node){
        this.head = node;
        node.thread = null;
        node.preNode = null;
    }

    /**
     * 储存封装线程的节点
     */
    private static class Node {

        static final Node SHARED = new Node();
        static final Node EXCLUSIVE = null;

        // 当前线程信息
        Thread thread;

        // 该节点的前驱节点
        Node preNode;

        // 该节点的后继节点
        Node nextNode;

        public Node() {
        }

        public Node(Thread thread) {
            this.thread = thread;
        }

        public Node(Thread thread, Node mode) {
            this.thread = thread;
        }
    }

    /**
     * 获取独占锁
     *
     * @param state
     */
    public final void acquire(int state) {
        /* 首先通过子类方法获取锁, 如果获取成功就直接返回 否则加入等待队列 在队列中进行自旋 */
        if (!tryAcquire(state) && acquireQueued(addWaiter(null), state)) {
            selfInterrupt();
        }
    }

    /**
     * 子类如果要使用独占锁必须实现这个接口
     * 主要是改变当前锁的状态 设置当前线程变量为当前线程 并返回是否设置成功
     * 如果返回true 说明该线程成功获取锁状态 否则加入等待队列自旋
     *
     * @param state 希望改变的同步状态的变化
     * @return
     */
    protected boolean tryAcquire(int state) {
        throw new UnsupportedOperationException("使用独占锁必须实现tryAcquire");
    }

    /**
     * 尝试将当前线程加入等待队列
     *
     * @param node
     * @param state
     * @return
     */
    final boolean acquireQueued(final Node node, int state) {
        for (;;){
            // 进入自旋 如果该节点的头结点是头部 并且成功获取状态 退出自旋
            Node preNode = node.preNode;
            if (preNode == head && tryAcquire(state)) {
                setHead(head);
                preNode.nextNode = null;
            }
        }
    }

    /**
     * 创建一个节点加入队列
     *
     * @param mode
     * @return
     */
    private Node addWaiter(Node mode) {
        // 1.构建节点
        Node node = new Node(Thread.currentThread(), mode);
        // 2.获取当前的尾节点
        Node currentTail = tail;
        // 3. 尝试快速将节点加入尾部
        if (currentTail != null) {
            node.preNode = currentTail;
            if (compareAndSetTail(currentTail, node)) {
                currentTail.nextNode = node;
                return node;
            }
        }
        // 4. 快速尝试失败 进入自旋设置
        enq(node);
        return node;
    }

    /**
     * Inserts node into queue, initializing if necessary. See picture above.
     *
     * @param node the node to insert
     * @return node's predecessor
     */
    private Node enq(final Node node) {
        for (; ; ) {
            Node currentTail = tail;
            // 1.同快速获取锁
            if (currentTail == null) {
                // 2.如果为空 需要进行初始化
                if (compareAndSetHead(new Node())){
                    tail = head;
                }
            }else {
                node.preNode = currentTail;
                if (compareAndSetTail(currentTail, node)) {
                    currentTail.nextNode = node;
                    return node;
                }
            }
        }
    }

    public String printlnNodeList(){
        StringBuilder stringBuilder = new StringBuilder();
        if (head == null){
            return null;
        }
        stringBuilder.append("head ->");
        Node node = head;
        while (node.nextNode != null) {
            stringBuilder.append(node.thread.getName() + " ->");
        }
        stringBuilder.append("null");
        stringBuilder.append("& tail ->" + tail.nextNode);
        return stringBuilder.toString();
    }

    /**
     * 中断当前线程
     */
    static void selfInterrupt() {
        Thread.currentThread().interrupt();
    }
}
