package decorator.odev.decorator;

import decorator.odev.base.IMilkTea;
import decorator.odev.base.MilkTeaDecorator;

public class FruitPudding extends MilkTeaDecorator {
	public FruitPudding(IMilkTea iMilkTea) {
		super(iMilkTea);
	}

	@Override
	public Double cost() {
		return super.cost() + 4d;
	}
}
