package design_patterns.structure_type.decorator;


/**
 * 具体装饰类 对比 BufferedInputStream DataInputStream
 */
public class ConcreteDecorator extends Decorator{


    public ConcreteDecorator(Component component) {
        super(component);
    }


    @Override
    public void getDesc() {
        super.getDesc();
        System.out.println("加一个鸡蛋");
    }

    @Override
    public int cost() {
        return super.cost() + 2;
    }
}
