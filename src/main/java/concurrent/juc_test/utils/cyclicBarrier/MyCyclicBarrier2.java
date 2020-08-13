package concurrent.juc_test.utils.cyclicBarrier;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyCyclicBarrier2 {

    Runnable callback = null;
    private final int ss;
    private int index;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();


    public MyCyclicBarrier2(int ss) {
        this.ss = ss;
        this.index = ss;
    }

    public MyCyclicBarrier2(int ss,  Runnable callback) {
        this.callback = callback;
        this.index = ss;
        this.ss = ss;
    }


    public void await(){
        lock.lock();

        if (index > 0) {
            index--;
            if (index == 0) {
                if (callback != null)
                    callback.run();
                index = ss;
                condition.signalAll();
            }else {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        lock.unlock();
    }
}
