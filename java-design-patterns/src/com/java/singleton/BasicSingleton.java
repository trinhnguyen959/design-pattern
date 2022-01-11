package com.java.singleton;

public class BasicSingleton {
	private BasicSingleton() {
	}

	private static final BasicSingleton INSTANCE = new BasicSingleton();

	public static BasicSingleton getInstance() {
		return INSTANCE;
	}

	private int value;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}

class Application {
	public static void main(String[] args) {
		BasicSingleton instance = BasicSingleton.getInstance();
		instance.setValue(123);
		System.out.println(instance.getValue());

		BasicSingleton instances = BasicSingleton.getInstance();
		System.out.println(instances.getValue());
	}
}


