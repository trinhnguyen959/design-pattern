package composite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CompositeCommand {
	public static void main(String[] args) {
		GraphicObject graphicObject = new GraphicObject();
		graphicObject.setName("GraphicObject drawing");
		graphicObject.children.add(new Circle("Yellow"));
		graphicObject.children.add(new Square("Red"));

		GraphicObject group = new GraphicObject();
		group.children.add(new Circle("Blue"));
		group.children.add(new Square("Blue"));
		graphicObject.children.add(group);

		System.out.println(graphicObject);
	}
}

class GraphicObject {
	protected String name = "Group";

	public GraphicObject() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String color;
	public List<GraphicObject> children = new ArrayList<>();

	private void print(StringBuilder stringBuilder, int depth) {
		stringBuilder.append(String.join("", Collections.nCopies(depth, "*")))
				.append(depth > 0 ? " " : "")
				.append((color == null || color.isEmpty()) ? "" : color + " ")
				.append(getName())
				.append(System.lineSeparator());
		for (GraphicObject child : children)
			child.print(stringBuilder, depth + 1);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		print(builder, 0);
		return builder.toString();
	}
}

class Circle extends GraphicObject {
	public Circle(String color) {
		name = "Circle";
		this.color = color;
	}
}

class Square extends GraphicObject {
	public Square(String color) {
		name = "Square";
		this.color = color;
	}
}