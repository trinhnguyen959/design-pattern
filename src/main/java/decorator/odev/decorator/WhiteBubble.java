package decorator.odev.decorator;

import decorator.odev.base.IMilkTea;
import decorator.odev.base.MilkTeaDecorator;

public class WhiteBubble extends MilkTeaDecorator {
	public WhiteBubble(IMilkTea iMilkTea) {
		super(iMilkTea);
	}

	@Override
	public Double cost() {
		return super.cost() + 5d;
	}
}
