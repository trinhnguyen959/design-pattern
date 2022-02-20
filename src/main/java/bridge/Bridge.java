package bridge;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

interface Render {
	void renderCircle(float radius);
}

public class Bridge {
	public static void main(String[] args) {
		RasterRenderer rasterRenderer = new RasterRenderer();
		VectorRenderer vectorRenderer = new VectorRenderer();

		Circle circle = new Circle(vectorRenderer, 5);
		circle.draw();
		circle.resize(2);
		circle.draw();

		System.out.println("---------------------------");

		Injector injector = Guice.createInjector(new ShapeModule());
		Circle instance = injector.getInstance(Circle.class);
		instance.radius = 3;
		instance.draw();
		instance.resize(2);
		instance.draw();
	}
}

class VectorRenderer implements Render {
	@Override
	public void renderCircle(float radius) {
		System.out.println("Drawing a circle of radius " + radius);
	}
}

class RasterRenderer implements Render {
	@Override
	public void renderCircle(float radius) {
		System.out.println("Drawing pixel for a circle of radius " + radius);
	}
}

abstract class Shape {
	protected Render render;

	public Shape(Render render) {
		this.render = render;
	}

	public abstract void draw();

	public abstract void resize(float factor);
}

class Circle extends Shape {
	public float radius;

	@Inject
	public Circle(Render render) {
		super(render);
	}

	public Circle(Render render, float radius) {
		super(render);
		this.radius = radius;
	}

	@Override
	public void draw() {
		render.renderCircle(radius);
	}

	@Override
	public void resize(float factor) {
		radius *= factor;
	}
}

class ShapeModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(Render.class).to(VectorRenderer.class);
	}
}