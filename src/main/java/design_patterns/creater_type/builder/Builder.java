package design_patterns.creater_type.builder;

public interface Builder {

    Object buildCpu();

    Object buildScream();

    Object buildKey();

    Pc build();

}
