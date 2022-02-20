package facade;

import java.util.ArrayList;
import java.util.List;

public class FacadeApplication {
	public static void main(String[] args) {
		// old way
		Buffer buffer = new Buffer(30, 20);
		ViewPort viewPort = new ViewPort(buffer, 30, 30, 0, 0);
		Console console = new Console(30, 20);
		console.addViewPort(viewPort);
		console.render();

		// facade
		Console newConsole = Console.newConsole(30, 20);
		newConsole.render();
	}
}

class Buffer {
	private final char[] characters;
	private final int lineWidth;

	public Buffer(int lineHeight, int lineWidth) {
		characters = new char[lineHeight * lineWidth];
		this.lineWidth = lineWidth;
	}

	public char charAt(int x, int y) {
		return characters[y * lineWidth + x];
	}
}

class ViewPort {
	private final Buffer buffer;
	private final int width;
	private final int height;
	private final int offsetX;
	private final int offsetY;

	public ViewPort(Buffer buffer, int width, int height, int offsetX, int offsetY) {
		this.buffer = buffer;
		this.width = width;
		this.height = height;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
	}

	public char charAt(int x, int y) {
		return buffer.charAt(x + offsetX, y + offsetY);
	}
}

class Console {
	int width, height;
	private final List<ViewPort> viewPorts = new ArrayList<>();

	public Console(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public static Console newConsole(int width, int height) {
		Buffer buffer = new Buffer(width, height);
		ViewPort viewPort = new ViewPort(buffer, width, height, 0, 0);
		Console console = new Console(width, height);
		console.addViewPort(viewPort);
		return console;

	}

	public void addViewPort(ViewPort viewPort) {
		viewPorts.add(viewPort);
	}

	public void render() {
		for (int y = 0; y < height; ++y) {
			for (int x = 0; x < width; ++x) {
				for (ViewPort viewPort : viewPorts) {
					System.out.println(viewPort.charAt(x, y));
				}
				System.out.println();
			}
		}
	}
}
