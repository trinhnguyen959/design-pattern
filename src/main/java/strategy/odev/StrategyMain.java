package strategy.odev;

import java.util.Random;

public class StrategyMain {
	public static void main(String[] args) {
		System.out.println("Starting get tickets");
		Random random = new Random();
		for (int i = 1; i < 5; i++) {
			Ticket ticket = new Ticket();
			ticket.setName("Ticket " + i);
			ticket.setPrice(50d * i);

			generatePromotedStrategy(random, ticket);
			logPromotedStrategy(ticket);

			generatePromotedStrategy(random, ticket);
			logPromotedStrategy(ticket);
		}
	}

	private static void logPromotedStrategy(Ticket ticket) {
		Double promotedPrice = ticket.getPromotedPrice();
		System.out.println("Promoted price of " + ticket.getName() + " is " + promotedPrice +
				" and strategy is " + ticket.getPromotedStrategy().getClass().getSimpleName());
	}

	private static void generatePromotedStrategy(Random random, Ticket ticket) {
		int strategyIndex = random.nextInt(0, 4);
		switch (strategyIndex) {
			case 0:
				ticket.setPromotedStrategy(new NoDiscountStrategy());
				break;
			case 1:
				ticket.setPromotedStrategy(new QuarterDiscountStrategy());
				break;
			case 2:
				ticket.setPromotedStrategy(new HalfDiscountStrategy());
				break;
			default:
				ticket.setPromotedStrategy(new EightyPercentDiscountStrategy());
				break;
		}
	}
}
