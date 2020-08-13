package design_patterns.active_type.command;

public class PauseCommand extends Command{
    @Override
    public void execute() {
        audioPlay.pause();
    }
}
