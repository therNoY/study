package design_patterns.structure_type.decorator;


/**
 * 装饰者基类 其他装饰者需要继承该类 对比InputStream
 */
public class Decorator implements Component {

    private Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void getDesc() {
        this.component.getDesc();
    }

    @Override
    public int cost() {
        return this.component.cost();
    }
}
