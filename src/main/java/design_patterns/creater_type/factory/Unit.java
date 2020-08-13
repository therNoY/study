package design_patterns.creater_type.factory;

public abstract class Unit {

    private Integer level;

    private Boolean isDone;

    public abstract void disPlay();

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Boolean getDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }
}
