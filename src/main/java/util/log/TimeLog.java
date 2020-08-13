package util.log;

import java.time.LocalDateTime;

public class TimeLog extends Log {


    @Override
    public void info(String mes) {
        System.out.println("[" + LocalDateTime.now() + "] :" + " " + mes);
    }

    @Override
    public void error(String s, Throwable e) {

    }

    @Override
    public void error(String s) {

    }

    @Override
    public void warn(String message) {

    }
}
