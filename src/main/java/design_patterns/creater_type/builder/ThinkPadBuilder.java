package design_patterns.creater_type.builder;

public class ThinkPadBuilder implements Builder{

    Pc pc;

    public ThinkPadBuilder() {
        pc = new Pc();
    }

    @Override
    public Object buildCpu() {
        return new Object();
    }

    @Override
    public Object buildScream() {
        return new Object();
    }

    @Override
    public Object buildKey() {
        return new Object();
    }

    @Override
    public Pc build() {
        pc.setCpu(buildCpu());
        pc.setKey(buildKey());
        pc.setScream(buildScream());
        return pc;
    }
}
