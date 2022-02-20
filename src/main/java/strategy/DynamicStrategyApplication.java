package strategy;

import java.util.List;

public class DynamicStrategyApplication {
	public static void main(String[] args) {
		TextProcessor processor = new TextProcessor(OutputFormat.MARK_DOWN);
		processor.appendList(List.of("liberte", "egalite", "fraternite"));
		System.out.println(processor);

		processor.clear();

		processor.setOutputFormat(OutputFormat.HTML);
		processor.appendList(List.of("inheritance", "encapsulation", "polymorphism"));
		System.out.println(processor);
	}
}

enum OutputFormat {
	MARK_DOWN,
	HTML,
}

interface ListStrategy {
	default void start(StringBuilder builder) {

	}

	void addListItem(StringBuilder builder, String item);

	default void end(StringBuilder builder) {

	}
}

class MarkDownStrategy implements ListStrategy {

	@Override
	public void addListItem(StringBuilder builder, String item) {
		builder
				.append(" * ")
				.append(item)
				.append(System.lineSeparator());
	}
}

class HtmlListStrategy implements ListStrategy {

	@Override
	public void start(StringBuilder builder) {
		builder
				.append("<ul>")
				.append(System.lineSeparator());
	}

	@Override
	public void addListItem(StringBuilder builder, String item) {
		builder
				.append(" <li>")
				.append(item)
				.append("</li>")
				.append(System.lineSeparator());
	}

	@Override
	public void end(StringBuilder builder) {
		builder
				.append("</ul>")
				.append(System.lineSeparator());
	}
}

class TextProcessor {
	private StringBuilder builder = new StringBuilder();
	private ListStrategy strategy;

	public TextProcessor(OutputFormat format) {
		setOutputFormat(format);
	}

	public void setOutputFormat(OutputFormat format) {
		switch (format) {
			case MARK_DOWN:
				strategy = new MarkDownStrategy();
				break;
			case HTML:
				strategy = new HtmlListStrategy();
				break;
		}
	}

	public void appendList(List<String> items) {
		strategy.start(builder);
		for (String item : items) {
			strategy.addListItem(builder, item);
		}
		strategy.end(builder);
	}

	public void clear() {
		builder.setLength(0);
	}

	@Override
	public String toString() {
		return builder.toString();
	}
}