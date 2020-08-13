package design_patterns.active_type.strategy;

public class BuyBook {
    private Discount discount;


    public BuyBook(Discount discount) {
        this.discount = discount;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public void buyBook(double price) {
        this.discount.discount(price);
    }


    public static void main(String[] args) {
        BuyBook buyBook = new BuyBook(new Dis8Discount());

        buyBook.buyBook(100);
        buyBook.setDiscount(new Dis9Discount());
        buyBook.buyBook(100);
    }
}
