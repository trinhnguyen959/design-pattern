package com.java.factory;

import javafx.util.Pair;
import org.reflections.Reflections;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AbstractDrinkFactory {
	public static void main(String[] args) throws Exception {
		HotDrinkMachine machine = new HotDrinkMachine();
		HotDrink drink = machine.makeDrink();
		drink.consume();
	}
}

interface HotDrink {
	void consume();
}

class Tea implements HotDrink {
	@Override
	public void consume() {
		System.out.println("This tea is delicious");
	}
}

class Coffee implements HotDrink {
	@Override
	public void consume() {
		System.out.println("This coffee is delicious");
	}
}

interface HotDrinkFactory {
	HotDrink prepare(int amount);
}

class TeaFactory implements HotDrinkFactory {
	@Override
	public HotDrink prepare(int amount) {
		System.out.println(
				"Put in a bag, boil water, pour "
						+ amount + "ml, add lemon, enjoy the moment!"
		);
		return new Tea();
	}
}

class CoffeeFactory implements HotDrinkFactory {
	@Override
	public HotDrink prepare(int amount) {
		System.out.println(
				"Grind some beans, boil water, pour "
						+ amount + "ml, add cream and sugar, enjoy!"
		);
		return new Coffee();
	}
}

class HotDrinkMachine {
	private final List<Pair<String, HotDrinkFactory>> drinkFactories = new ArrayList<>();

	public HotDrinkMachine() throws Exception {
		Set<Class<? extends HotDrinkFactory>> types = new Reflections("com.java.factory")
				.getSubTypesOf(HotDrinkFactory.class);

		for (Class<? extends HotDrinkFactory> type : types) {
			drinkFactories.add(new Pair<>(
					type.getSimpleName().replace("Factory", ""),
					type.getDeclaredConstructor().newInstance()
			));
		}
	}

	public HotDrink makeDrink() throws Exception {
		System.out.println("Available Drink");
		for (int i = 0; i < drinkFactories.size(); i++) {
			Pair<String, HotDrinkFactory> item = drinkFactories.get(i);
			System.out.println("" + i + ": " + item.getKey());
		}

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			String s;
			int i, amount;
			if ((s = reader.readLine()) != null && (i = Integer.parseInt(s)) >= 0 && i < drinkFactories.size()) {
				System.out.println("Specific amount: ");
				s = reader.readLine();

				if (s != null && (amount = Integer.parseInt(s)) > 0) {
					return drinkFactories.get(i).getValue().prepare(amount);
				}
			}
			System.out.println("Incorrect input, try again!");
		}
	}
}