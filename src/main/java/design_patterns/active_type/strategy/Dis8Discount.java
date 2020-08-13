package design_patterns.active_type.strategy;

public class Dis8Discount extends Discount{
    @Override
    public void discount(double price) {
        System.out.println("打八折");
        System.out.println("实际价格" + price * 0.8);
    }
}
