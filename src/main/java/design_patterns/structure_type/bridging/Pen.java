package design_patterns.structure_type.bridging;

/**
 * 桥接模式的抽象类 持有另外一个维度的接口对象
 */
public abstract class Pen {

    Color color;

    abstract void makePen();

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
