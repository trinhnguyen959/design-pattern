package com.java.singleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class BasicSingleton implements Serializable {
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

	// fix issue

	protected Object readResolve() {
		return INSTANCE;
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

class SingletonIssue {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		//1. Reflection
		//2. Serializations

		BasicSingleton instance = BasicSingleton.getInstance();
		instance.setValue(111);

		String fileName = "singleton.bin";
		saveToFile(instance, fileName);

		instance.setValue(222);

		BasicSingleton readFromFile = readFromFile(fileName);

		System.out.println(instance == readFromFile); // false

		System.out.println(instance.getValue()); // 222
		System.out.println(readFromFile.getValue()); // 111
	}

	static void saveToFile(BasicSingleton singleton, String filename) throws IOException {
		try (FileOutputStream fos = new FileOutputStream(filename);
			 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(singleton);
		}
	}

	static BasicSingleton readFromFile(String filename) throws IOException, ClassNotFoundException {
		try (FileInputStream fis = new FileInputStream(filename);
			 ObjectInputStream ios = new ObjectInputStream(fis)) {
			return (BasicSingleton) ios.readObject();
		}
	}
}
