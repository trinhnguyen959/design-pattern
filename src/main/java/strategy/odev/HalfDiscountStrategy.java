package strategy.odev;

public class HalfDiscountStrategy implements IPromotedStrategy {
    @Override
    public double doDiscount(Double price) {
        return price * 0.5;
    }
}
