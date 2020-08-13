package java_base.jdk8;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * consumer 可以理解为一个没有返回值的function
 * 这个可以接受两个参数{@link BiConsumer}.
 */
public class ConsumerTest {

    public static void main(String[] args) {

        Consumer<ParaObj> paraObjConsumer = p->{
            System.out.println(p.getName());
        };

        accpectConsumer(paraObjConsumer, new ParaObj("hahah"));
        accpectConsumer(ConsumerTest::myFunction, new ParaObj("hahah"));
    }

    public static void myFunction(ParaObj obj) {
        System.out.println(obj.getName());
    }


    public static void accpectConsumer(Consumer<ParaObj> paraObjConsumer, ParaObj paraObj) {

        paraObjConsumer.accept(paraObj);

    }

}
