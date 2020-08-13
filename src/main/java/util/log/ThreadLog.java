package util.log;

/**
 * 关心线程的log
 */
public class ThreadLog extends Log {


    @Override
    public void info(String mes) {
        String name = Thread.currentThread().getName();
        System.out.println("[ " + name + " ] [info]: " + mes);
    }

    @Override
    public void error(String s, Throwable e) {
        String name = Thread.currentThread().getName();
        System.out.println("[ " + name + " ] [error]: " + s);
        e.printStackTrace();
    }

    @Override
    public void error(String s) {
        String name = Thread.currentThread().getName();
        System.out.println("[ " + name + " ] [error]: " + s);
    }

    @Override
    public void warn(String message) {
        String name = Thread.currentThread().getName();
        System.out.println("[ " + name + " ] [warn]: " + message);
    }

}
