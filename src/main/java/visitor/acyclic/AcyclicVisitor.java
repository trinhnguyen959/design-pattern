package visitor.acyclic;

public class AcyclicVisitor {
	public static void main(String[] args) {
		AdditionExpression additionExpression = new AdditionExpression(
				new DoubleExpression(2),
				new AdditionExpression(
						new DoubleExpression(3),
						new DoubleExpression(5)
				)
		);

		ExpressionPrinter expressionPrinter = new ExpressionPrinter();
		expressionPrinter.visit(additionExpression);
		System.out.println(expressionPrinter);

		ExpressionCalculator expressionCalculator = new ExpressionCalculator();
		expressionCalculator.visit(additionExpression);
		System.out.println(expressionPrinter + " = " + expressionCalculator.result);
	}
}

interface Visitor {
}

interface ExpressionVisitor extends Visitor {
	void visit(Expression expression);
}

interface DoubleExpressionVisitor extends Visitor {
	void visit(DoubleExpression doubleExpression);
}

interface AdditionExpressionVisitor extends Visitor {
	void visit(AdditionExpression additionExpression);
}

abstract class Expression {
	public void accept(Visitor visitor) {
		if (visitor instanceof ExpressionVisitor) {
			((ExpressionVisitor) visitor).visit(this);
		}
	}
}

class DoubleExpression extends Expression {
	public double value;

	public DoubleExpression(double value) {
		this.value = value;
	}

	@Override
	public void accept(Visitor visitor) {
		if (visitor instanceof DoubleExpressionVisitor) {
			((DoubleExpressionVisitor) visitor).visit(this);
		}
	}
}

class AdditionExpression extends Expression {
	public Expression left;
	public Expression right;

	public AdditionExpression(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public void accept(Visitor visitor) {
		if (visitor instanceof AdditionExpressionVisitor) {
			((AdditionExpressionVisitor) visitor).visit(this);
		}
	}
}

class ExpressionPrinter implements DoubleExpressionVisitor, AdditionExpressionVisitor {
	private final StringBuilder builder = new StringBuilder();

	@Override
	public void visit(DoubleExpression expression) {
		builder.append(expression.value);
	}

	@Override
	public void visit(AdditionExpression additionExpression) {
		builder.append("(");
		additionExpression.left.accept(this);
		builder.append(" + ");
		additionExpression.right.accept(this);
		builder.append(")");
	}

	@Override
	public String toString() {
		return builder.toString();
	}
}

class ExpressionCalculator implements DoubleExpressionVisitor, AdditionExpressionVisitor {
	public double result;

	@Override
	public void visit(DoubleExpression expression) {
		result = expression.value;
	}

	@Override
	public void visit(AdditionExpression additionExpression) {
		additionExpression.left.accept(this);
		double a = result;
		additionExpression.right.accept(this);
		double b = result;
		result = a + b;
	}
}