package design_patterns.structure_type.decorator;


/**
 * 具体装饰类 对比 BufferedInputStream DataInputStream
 */
public class ConcreteDecorator2 extends Decorator {


    public ConcreteDecorator2(Component component) {
        super(component);
    }


    @Override
    public void getDesc() {
        super.getDesc();
        System.out.println("加一个香肠");
    }

    @Override
    public int cost() {
        return super.cost() + 3;
    }
}
