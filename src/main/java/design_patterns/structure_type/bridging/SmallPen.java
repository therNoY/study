package design_patterns.structure_type.bridging;

public class SmallPen extends Pen{

    @Override
    void makePen() {
        System.out.println(this.color + "smallPen");
    }
}
