package decorator.odev.base;

public abstract class MilkTeaDecorator implements IMilkTea {
	private IMilkTea iMilkTea;

	public MilkTeaDecorator(IMilkTea iMilkTea) {
		this.iMilkTea = iMilkTea;
	}

	@Override
	public Double cost() {
		return iMilkTea.cost();
	}
}
