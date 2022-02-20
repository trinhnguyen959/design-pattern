package strategy.odev;

public class EightyPercentDiscountStrategy implements IPromotedStrategy {
	@Override
	public double doDiscount(Double price) {
		return price * 0.2;
	}
}
