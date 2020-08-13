package design_patterns.active_type.command;


/**
 * 相当于invoke  命令的发起者
 *
 */
public class KeyPad {

    private AudioPlay audioPlay = new AudioPlay();

    /**
     * 可以用if else 判断
     * @param command
     */
    void sendCommand(Class<? extends Command> command) {
        try {
            Command instance = command.newInstance();
            instance.setAudioPlay(audioPlay);
            instance.execute();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
