package decorator.odev.decorator;

import decorator.odev.base.IMilkTea;
import decorator.odev.base.MilkTeaDecorator;

public class Bubble extends MilkTeaDecorator {
	public Bubble(IMilkTea iMilkTea) {
		super(iMilkTea);
	}

	@Override
	public Double cost() {
		return super.cost() + 1d;
	}
}
