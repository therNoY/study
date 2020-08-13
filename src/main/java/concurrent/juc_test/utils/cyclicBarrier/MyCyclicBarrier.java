package concurrent.juc_test.utils.cyclicBarrier;

import concurrent.juc_test.lock.aqs.MyAbstractQueuedSynchronizer;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class MyCyclicBarrier {

    private Sync sync;
    Runnable callback = null;


    public MyCyclicBarrier(int s, Runnable runnable) {
        sync = new Sync(s);
        this.callback = runnable;
    }

    public void await(){

        // 执行await 首先释放锁
        if (!sync.releaseShared(1)) {
            sync.acquireShared(1);
        }
    }

    private class Sync extends AbstractQueuedSynchronizer {

        private final int ss;
        private AtomicInteger sb = new AtomicInteger();

        public Sync(int s) {
            setState(s);
            this.ss = s;
        }

        public Sync(int s, Runnable runnable, int ss) {
            this.ss = ss;
            setState(s);
        }



        @Override
        protected int tryAcquireShared(int acquires) {
            if (getState() == 0) {
                if (sb.incrementAndGet()== ss)
                    setState(ss);
                return 1;
            }else {
                return -1;
            }
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            for (;;){
                int state = getState();
                if (state > 0) {
                    int newCount = state - arg;
                    if (compareAndSetState(state, newCount)) {
                        if (newCount == 0) {
                            // 说明此事我是最后一个到达节点的
                            if (callback != null) {
                                callback.run();
                            }
                        }
                        // 返回现在是不是全部释放完毕 即最后一个线程达到栅栏
                        return newCount == 0;
                    }
                }else {
                    return true;
                }
            }
        }
    }
}
