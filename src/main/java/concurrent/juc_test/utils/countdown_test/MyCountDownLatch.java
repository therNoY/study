package concurrent.juc_test.utils.countdown_test;

import concurrent.juc_test.lock.aqs.MyAbstractQueuedSynchronizer;

public class MyCountDownLatch {


    private Sync sync;


    public MyCountDownLatch(int num) {
        sync = new Sync(num);
    }


    public void countdown(){
        sync.releaseShared(1);
    }

    public void await(){
        sync.acquireShared(1);
    }

    private class Sync extends MyAbstractQueuedSynchronizer{

        public Sync(int s) {
            setState(s);
        }

        @Override
        protected int tryAcquireShared(int acquires) {
            return (getState() == 0) ? 1 : -1;
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            for (;;){
                int state = getState();
                if (state > 0) {
                    int newCount = state - arg;
                    if (compareAndSetState(state, newCount)) {
                        return newCount == 0;
                    }
                }
            }
        }
    }

}
