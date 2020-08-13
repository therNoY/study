package design_patterns.structure_type.bridging;

public class BigPen extends Pen{
    @Override
    void makePen() {
        System.out.println(this.color + "BigPen");
    }
}
