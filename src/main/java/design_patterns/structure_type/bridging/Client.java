package design_patterns.structure_type.bridging;

public class Client {

    public static void main(String[] args) {
        Pen pen = new BigPen();
        pen.setColor(new Red());


        pen.makePen();
    }


}
