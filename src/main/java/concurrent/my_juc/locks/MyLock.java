package concurrent.my_juc.locks;

/**
 * 锁需要实现的接口
 * 原版 {@link java.util.concurrent.locks.Lock}
 */
public interface MyLock {

    /**
     * 获取锁对象的锁 获取不到会加入等待队列
     */
    void lock();

    /**
     * 尝试快速获取锁, 获取成功返回true失败返回false不会加入等待队列
     */
    boolean tryLock();

    /**
     * 释放所对象的锁
     */
    void unlock();
}
