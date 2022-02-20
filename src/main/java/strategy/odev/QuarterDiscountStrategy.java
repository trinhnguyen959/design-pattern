package strategy.odev;

public class QuarterDiscountStrategy implements IPromotedStrategy {
	@Override
	public double doDiscount(Double price) {
		return price * 0.75;
	}
}
