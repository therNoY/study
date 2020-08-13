package design_patterns.creater_type.builder;

public class Client {
    public static void main(String[] args) {
        Director director = new Director(new ThinkPadBuilder());

        Pc pc = director.build();
    }
}
