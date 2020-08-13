package concurrent.Thread.application;

import util.log.Logger;

/**
 * 开发人员经常会遇到这样的方法调用场景：调用一个方法时等待一段时间（一般来说是给 定一个时间段），
 * 如果该方法能够在给定的时间段之内得到结果，那么将结果立刻返回，反之， 超时返回默认结果
 */
public class TimerWait {

    static String res = null;

    public static void main(String[] args) {


        Thread execTask = new Thread(()->{

        });

        Thread cThread = new Thread(()->{

            synchronized (TimerWait.class) {

                Logger.info("请求过来");

                try {
                    TimerWait.class.wait(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (res == null) {
                    Logger.info("请求超时");
                }else {
                    Logger.info("请求结果。。" + res);
                }
            }

        });



    }

}
