package strategy.statics;

import java.util.List;
import java.util.function.Supplier;

public class StaticStrategyApplication {
	public static void main(String[] args) {
		TextProcessor<MarkDownStrategy> mark = new TextProcessor<>(MarkDownStrategy::new);
		mark.appendList(List.of("alpha", "beta", "gamma"));
		System.out.println(mark);

		TextProcessor<HtmlListStrategy> html = new TextProcessor<>(HtmlListStrategy::new);
		html.appendList(List.of("alpha", "beta", "gamma"));
		System.out.println(html);
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

class TextProcessor<LS extends ListStrategy> {
	private StringBuilder builder = new StringBuilder();
	private LS strategy;

	public TextProcessor(Supplier<? extends LS> supplier) {
		strategy = supplier.get();
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