package decorator.odev;

import decorator.odev.base.MilkTea;
import decorator.odev.decorator.BlackSugar;
import decorator.odev.decorator.Bubble;
import decorator.odev.decorator.EggPudding;
import decorator.odev.decorator.FruitPudding;

public class DecoratorMain {
	public static void main(String[] args) {
		EggPudding eggPudding = new EggPudding(new FruitPudding(new BlackSugar(new Bubble(new MilkTea()))));
		System.out.println(eggPudding.cost());
	}
}
