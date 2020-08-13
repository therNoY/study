package concurrent.juc_test.utils.cyclicBarrier;

import util.log.Logger;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest2 {

    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier1 = new CyclicBarrier(2);


        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, ()->{

            Logger.info("最后一个完成的线程会执行 所有的线程都完成了");
            try {
                cyclicBarrier1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

            Logger.info("END");
        });


        for (int i = 0; i < 5; i++) {

            new Thread(()->{
                try {

                    Thread.sleep(1000);
                    Logger.info("我完成了");
                    cyclicBarrier.await();
                    Logger.info("END");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();

        }

        try {
            cyclicBarrier1.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

        Logger.info("END");

    }
}
