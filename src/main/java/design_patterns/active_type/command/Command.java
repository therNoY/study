package design_patterns.active_type.command;

/**
 * 抽象命令
 */
public abstract  class Command {

    protected AudioPlay audioPlay;

    public AudioPlay getAudioPlay() {
        return audioPlay;
    }

    public void setAudioPlay(AudioPlay audioPlay) {
        this.audioPlay = audioPlay;
    }

    abstract void execute();
}
