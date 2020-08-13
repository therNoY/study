package util.log;

public abstract class Log {

    public static Log threadLog(){
        return new ThreadLog();
    }

    public static Log timeLog(){
        return new TimeLog();
    }

    public abstract void info(String mes);

    public abstract void error(String s, Throwable e);
    public abstract void error(String s);

    public abstract void warn(String message);
}
