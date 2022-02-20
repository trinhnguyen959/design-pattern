package visitor.reflective;

public class ReflectiveVisitor {
	public static void main(String[] args) {
		AdditionExpression additionExpression = new AdditionExpression(
				new DoubleExpression(1),
				new AdditionExpression(
						new DoubleExpression(2),
						new DoubleExpression(3)
				)
		);

		StringBuilder builder = new StringBuilder();
		ExpressionPrinter.print(additionExpression, builder);
		System.out.println(builder);
	}
}

abstract class Expression {

}

class DoubleExpression extends Expression {
	public double value;

	public DoubleExpression(double value) {
		this.value = value;
	}
}

class AdditionExpression extends Expression {
	public Expression left;
	public Expression right;

	public AdditionExpression(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}
}

class ExpressionPrinter {
	public static void print(Expression expression, StringBuilder builder) {
		if (expression.getClass() == DoubleExpression.class) {
			builder.append(((DoubleExpression) expression).value);
		} else if (expression.getClass() == AdditionExpression.class) {
			AdditionExpression ae = (AdditionExpression) expression;
			builder.append("(");
			print(ae.left, builder);
			builder.append("+");
			print(ae.right, builder);
			builder.append(")");
		}
	}
}