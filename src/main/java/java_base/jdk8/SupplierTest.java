package java_base.jdk8;

import java.util.function.Supplier;


/**
 * Supplier 是一个数据提供商
 */
public class SupplierTest {

    public static void main(String[] args) {


        show(()-> "112131");

    }

    public static void show(Supplier<String> s) {

        System.out.println(s.get());

        s.get();
    }
}
