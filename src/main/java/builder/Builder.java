package builder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Builder {
	public static void main(String[] args) {
		HtmlBuilder builder = new HtmlBuilder("ul");
		builder
				.addChild("li", "hello")
				.addChild("li", "world");
		System.out.println(builder);
	}

	static class HtmlElement {
		public String name, text;
		public List<HtmlElement> elements = new ArrayList<>();
		private final String newLine = System.lineSeparator();

		public HtmlElement() {
		}

		public HtmlElement(String name, String text) {
			this.name = name;
			this.text = text;
		}

		private String toStringImpl(int indent) {
			StringBuilder builder = new StringBuilder();
			int indentSize = 2;
			String join = String.join("", Collections.nCopies(indent * indentSize, ""));
			builder.append(String.format("%s<%s>%s", join, name, newLine));
			if (text != null && !text.isEmpty()) {
				builder.append(String.join("", Collections.nCopies(indentSize * (indent + 1), "")))
						.append(text)
						.append(newLine);
			}

			for (HtmlElement e : elements) {
				builder.append(e.toStringImpl(indent + 1));
			}

			builder.append(String.format("%s</%s>%s", join, name, newLine));
			return builder.toString();
		}

		@Override
		public String toString() {
			return toStringImpl(0);
		}
	}

	static class HtmlBuilder {
		private final String rootName;
		private HtmlElement root = new HtmlElement();

		public HtmlBuilder(String rootName) {
			this.rootName = rootName;
			root.name = rootName;
		}

		public HtmlBuilder addChild(String childName, String childText) {
			HtmlElement element = new HtmlElement(childName, childText);
			root.elements.add(element);
			return this;
		}

		public void clear() {
			root = new HtmlElement();
			root.name = rootName;
		}

		@Override
		public String toString() {
			return root.toString();
		}
	}
}
