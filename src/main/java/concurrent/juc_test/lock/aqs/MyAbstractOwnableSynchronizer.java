package concurrent.juc_test.lock.aqs;

/**
 * 线程专有的同步器。 这个
 * 类提供了创建锁和相关同步器的基础
 * 这可能需要所有权的概念。 的
 * {@code AbstractOwnableSynchronizer}类本身无法管理或
 * 使用此信息。 但是，子类和工具可能会使用
 * 适当维护的值有助于控制和监视访问
 * 并提供诊断信息。
 */
public class MyAbstractOwnableSynchronizer {

    /**
     * Empty constructor for use by subclasses.
     */
    protected MyAbstractOwnableSynchronizer() { }

    /**
     * The current owner of exclusive mode synchronization.
     */
    private transient Thread exclusiveOwnerThread;

    /**
     * Sets the thread that currently owns exclusive access.
     * A {@code null} argument indicates that no thread owns access.
     * This method does not otherwise impose any synchronization or
     * {@code volatile} field accesses.
     * @param thread the owner thread
     */
    protected final void setExclusiveOwnerThread(Thread thread) {
        exclusiveOwnerThread = thread;
    }

    /**
     * Returns the thread last set by {@code setExclusiveOwnerThread},
     * or {@code null} if never set.  This method does not otherwise
     * impose any synchronization or {@code volatile} field accesses.
     * @return the owner thread
     */
    protected final Thread getExclusiveOwnerThread() {
        return exclusiveOwnerThread;
    }
}
