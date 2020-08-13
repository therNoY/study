package design_patterns.active_type.strategy;

public class Dis9Discount extends Discount{

    @Override
    public void discount(double price) {
        System.out.println("打九折");
        System.out.println("实际价格" + price * 0.9);
    }
}
