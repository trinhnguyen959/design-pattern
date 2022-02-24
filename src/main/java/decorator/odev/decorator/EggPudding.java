package decorator.odev.decorator;

import decorator.odev.base.IMilkTea;
import decorator.odev.base.MilkTeaDecorator;

public class EggPudding extends MilkTeaDecorator {
	public EggPudding(IMilkTea iMilkTea) {
		super(iMilkTea);
	}

	@Override
	public Double cost() {
		return super.cost() + 3d;
	}
}
