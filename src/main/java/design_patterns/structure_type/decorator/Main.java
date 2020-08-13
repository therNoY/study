package design_patterns.structure_type.decorator;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * 抽象构件(Component)角色：给出一个抽象接口，以规范准备接收附加责任的对象。
 * <p>
 * 具体构件(ConcreteComponent)角色：定义一个将要接收附加责任的类。
 * <p>
 * 装饰(Decorator)角色：持有一个构件(Component)对象的实例，并定义一个与抽象构件接口一致的接口。
 * <p>
 * 具体装饰(ConcreteDecorator)角色：负责给构件对象“贴上”附加的责任。
 */
public class Main {


    public static void main(String[] args) {

        Component component = new ConcreteComponent();

        component = new ConcreteDecorator(component);
        component = new ConcreteDecorator2(component);

        component.getDesc();
        System.out.println("价格" + component.cost());
    }
}
