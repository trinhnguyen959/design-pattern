package com.java.bridge;

public class Bridge {
	public static void main(String[] args) {
		RasterRenderer rasterRenderer = new RasterRenderer();
		VectorRenderer vectorRenderer = new VectorRenderer();

		Circle circle = new Circle(vectorRenderer, 5);
		circle.draw();
		circle.resize(2);
		circle.draw();
	}
}

interface Render {
	void renderCircle(float radius);
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