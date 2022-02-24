package decorator.odev.decorator;

import decorator.odev.base.IMilkTea;
import decorator.odev.base.MilkTeaDecorator;

public class BlackSugar extends MilkTeaDecorator {
	public BlackSugar(IMilkTea iMilkTea) {
		super(iMilkTea);
	}

	@Override
	public Double cost() {
		return super.cost() + 2d;
	}
}
