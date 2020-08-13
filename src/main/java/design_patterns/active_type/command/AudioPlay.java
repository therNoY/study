package design_patterns.active_type.command;

/**
 * 命令执行者
 */
public class AudioPlay {

    public void play(){
        System.out.println("播放...");
    }

    public void pause(){
        System.out.println("倒带...");
    }

    public void stop(){
        System.out.println("停止...");
    }
}
