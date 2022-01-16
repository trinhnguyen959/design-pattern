package com.java.interpreter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LexingInterpreter {
	static List<Token> lex(String input) {
		ArrayList<Token> result = new ArrayList<>();
		for (int i = 0; i < input.length(); i++) {
			switch (input.charAt(i)) {
				case '+':
					result.add(new Token(Token.Type.PLUS, "+"));
					break;
				case '-':
					result.add(new Token(Token.Type.MINUS, "-"));
					break;
				case '(':
					result.add(new Token(Token.Type.LPAREN, "("));
					break;
				case ')':
					result.add(new Token(Token.Type.RPAREN, ")"));
					break;
				default:
					StringBuilder builder = new StringBuilder("" + input.charAt(i));
					for (int j = 0; j < input.length(); j++) {
						if (Character.isDigit(input.charAt(j))) {
							builder.append(input.charAt(j));
							++i;
						} else {
							result.add(new Token(Token.Type.INTEGER, builder.toString()));
							break;
						}
					}
					break;
			}
		}
		return result;
	}

	static Element parse(List<Token> tokens) {
		BinaryOperator result = new BinaryOperator();
		boolean hasLHS = false;
		for (int i = 0; i < tokens.size(); ++i) {
			Token token = tokens.get(i);

			switch (token.type) {
				case INTEGER:
					Integer integer = new Integer(java.lang.Integer.parseInt(token.text));
					if (!hasLHS) {
						result.left = integer;
						hasLHS = true;
					} else {
						result.right = integer;
					}
					break;
				case PLUS:
					result.type = BinaryOperator.Type.ADDITION;
					break;
				case MINUS:
					result.type = BinaryOperator.Type.SUBTRACTION;
					break;
				case LPAREN:
					int j = 0;
					for (; j < tokens.size(); ++j)
						if (tokens.get(j).type == Token.Type.RPAREN)
							break;
					List<Token> subExpression = tokens.stream()
							.skip(i + 1)
							.limit(j - i - 1)
							.collect(Collectors.toList());
					Element element = parse(subExpression);
					if (!hasLHS) {
						result.left = element;
						hasLHS = true;
					} else
						result.right = element;
					i = j;
					break;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		String input = "(13+4)-(12+1)";
		List<Token> tokens = lex(input);
		System.out.println(tokens.stream().map(Token::toString).collect(Collectors.joining("\t")));

		Element parse = parse(tokens);
		System.out.println(input + " = " + parse.eval());
	}
}

class Token {
	public enum Type {
		INTEGER,
		PLUS,
		MINUS,
		LPAREN,
		RPAREN
	}

	public Type type;
	public String text;

	public Token(Type type, String text) {
		this.type = type;
		this.text = text;
	}

	@Override
	public String toString() {
		return "`" + text + "`";
	}
}

interface Element {
	int eval();
}

class Integer implements Element {
	private final int value;

	public Integer(int value) {
		this.value = value;
	}

	@Override
	public int eval() {
		return value;
	}
}

class BinaryOperator implements Element {
	public enum Type {
		ADDITION,
		SUBTRACTION,
	}

	public Type type;
	public Element left, right;

	@Override
	public int eval() {
		switch (type) {
			case ADDITION:
				return left.eval() + right.eval();
			case SUBTRACTION:
				return left.eval() - right.eval();
			default:
				return 0;
		}
	}
}