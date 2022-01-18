package com.java.visitor;

public class Visitor {
	public static void main(String[] args) {
		AdditionExpression additionExpression = new AdditionExpression(
				new DoubleExpression(1),
				new AdditionExpression(
						new DoubleExpression(2),
						new DoubleExpression(3)
				)
		);

		StringBuilder builder = new StringBuilder();
		additionExpression.print(builder);
		System.out.println(builder);
	}
}

abstract class Expression {
	public abstract void print(StringBuilder builder);
}

class DoubleExpression extends Expression {
	private final double value;

	public DoubleExpression(double value) {
		this.value = value;
	}

	@Override
	public void print(StringBuilder builder) {
		builder.append(value);
	}
}

class AdditionExpression extends Expression {
	private final Expression left;
	private final Expression right;

	public AdditionExpression(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public void print(StringBuilder builder) {
		builder.append("(");
		left.print(builder);
		builder.append(" + ");
		right.print(builder);
		builder.append(")");
	}
}