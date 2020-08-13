package design_patterns.active_type.command;

public class PlayCommand extends Command {
    @Override
    public void execute() {
        audioPlay.play();
    }
}
