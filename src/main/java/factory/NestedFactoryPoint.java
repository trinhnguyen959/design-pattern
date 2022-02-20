package factory;

public class NestedFactoryPoint {
	private double x, y;

	private NestedFactoryPoint(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public static class Factory {
		public static NestedFactoryPoint newCartesianPoint(double x, double y) {
			return new NestedFactoryPoint(x, y);
		}

		public static NestedFactoryPoint newPolarPoint(double rho, double theta) {
			return new NestedFactoryPoint(rho * Math.cos(theta), rho * Math.sin(theta));
		}
	}
}

class NestedFactoryPointApp {
	public static void main(String[] args) {
		NestedFactoryPoint.Factory.newCartesianPoint(4, 5);
	}
}