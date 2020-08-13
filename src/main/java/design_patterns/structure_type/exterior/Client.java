package design_patterns.structure_type.exterior;

/**
 * 客户端
 */
public class Client {

    public static void main(String[] args) {
        Facade facade = new Facade();
        facade.connect("mysql");
    }
}
