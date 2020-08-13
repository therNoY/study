package util.log;

public class Logger {

    static Log threadLog = new ThreadLog();

    public static void info(Object m){

        if (m == null) {
            threadLog.info("null");
        }else
            threadLog.info(m.toString());
    }

    public static void error(Object m){
        threadLog.error(m.toString());
    }
}
