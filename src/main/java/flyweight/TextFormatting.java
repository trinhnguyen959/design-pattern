package flyweight;

import java.util.ArrayList;
import java.util.List;

public class TextFormatting {
	public static void main(String[] args) {
		FormattedText text = new FormattedText("This is a brave new world");
		text.capitalize(10, 15);
		System.out.println(text);

		// Better format
		BetterTextFormatting betterTextFormatting = new BetterTextFormatting("Make America Great Again!");
		betterTextFormatting.getRange(13, 18).capitalize = true;

		System.out.println(betterTextFormatting);
	}
}

class FormattedText {
	private final String plainText;
	private final boolean[] capitalize;

	public FormattedText(String plainText) {
		this.plainText = plainText;
		capitalize = new boolean[plainText.length()];
	}

	public void capitalize(int start, int end) {
		for (int i = start; i < end; ++i) {
			capitalize[i] = true;
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < plainText.length(); ++i) {
			char c = plainText.charAt(i);
			builder.append(capitalize[i] ? Character.toUpperCase(c) : c);
		}

		return builder.toString();
	}
}

class BetterTextFormatting {
	private final String plainText;
	private final List<TextRange> formatting = new ArrayList<>();

	public BetterTextFormatting(String plainText) {
		this.plainText = plainText;
	}

	public TextRange getRange(int start, int end) {
		TextRange textRange = new TextRange(start, end);
		formatting.add(textRange);
		return textRange;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < plainText.length(); ++i) {
			char c = plainText.charAt(i);
			for (TextRange range : formatting) {
				if (range.covers(i) && range.capitalize) {
					c = Character.toUpperCase(c);
				}
				builder.append(c);
			}
		}
		return builder.toString();
	}

	public static class TextRange {
		public int start, end;
		public boolean capitalize, bold, italic;

		public TextRange(int start, int end) {
			this.start = start;
			this.end = end;
		}

		public boolean covers(int position) {
			return position >= start && position <= end;
		}
	}
}
