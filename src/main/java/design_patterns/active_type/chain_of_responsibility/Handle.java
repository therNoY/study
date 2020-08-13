package design_patterns.active_type.chain_of_responsibility;

public abstract class Handle {

    protected String name;
    protected Handle nextHandel;

    public void next(Handle nextHandel) {
        this.nextHandel = nextHandel;
    }


    public void process(LevelRequest levelRequest) {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected abstract void handel(LevelRequest levelRequest);

}
