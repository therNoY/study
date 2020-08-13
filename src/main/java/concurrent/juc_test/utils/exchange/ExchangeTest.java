package concurrent.juc_test.utils.exchange;

import util.log.Logger;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Exchanger;

public class ExchangeTest {

//    static MyExchanger<String> exchanger = new MyExchanger<>();
    static Exchanger<String> exchanger = new Exchanger<>();
    static CyclicBarrier  cyclicBarrier = new CyclicBarrier(3);

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {



        for (int i = 0; i < 5; i++) {
            test();
            cyclicBarrier.await();

            System.out.println();
        }



    }

    private static void test() {
        new Thread(()->{

            String res = "A";
            Logger.info("我是审核员A 我的审核结果是" + res);
            try {
                String bR = exchanger.exchange(res);
                Logger.info("收到另一个审核员的结果" + bR);
                cyclicBarrier.await();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

        }).start();

        new Thread(()->{
            String res = "B";
            Logger.info("我是审核员B 我的审核结果是" + res);


            try {
                Thread.sleep(1000);
                String aR = exchanger.exchange(res);
                cyclicBarrier.await();
                Logger.info("收到另一个审核员的结果" + aR);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
            }

        }).start();
    }

}
