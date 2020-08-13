package design_patterns.structure_type.decorator;


/**
 * 具体构建类
 * 对比FileInputStream PipedInputStream
 */
public class ConcreteComponent implements Component {
    @Override
    public void getDesc() {
        System.out.println("卖煎饼");
    }

    @Override
    public int cost() {
        return 8;
    }
}
