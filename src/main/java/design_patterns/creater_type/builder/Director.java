package design_patterns.creater_type.builder;

/**
 * 导演类
 */
public class Director {

    Builder builder = null;

    public Director(Builder builder) {
        this.builder = builder;
    }

    Pc build() {
       return builder.build();
    }
}
