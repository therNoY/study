package design_patterns.active_type.command;


/**
 * 具体实现
 */
public class EndCommand extends Command{
    @Override
    public void execute() {
        audioPlay.stop();
    }
}
