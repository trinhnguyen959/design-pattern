package strategy.odev;

public class NoDiscountStrategy implements IPromotedStrategy {
	@Override
	public double doDiscount(Double price) {
		return price;
	}
}
