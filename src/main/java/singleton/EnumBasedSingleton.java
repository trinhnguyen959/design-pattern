package singleton;

import java.io.*;

// ban than enum la singleton
public enum EnumBasedSingleton {
	INSTANCE;

	private int value;

	EnumBasedSingleton() {
		value = 42;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}

class EnumBasedSingletonApp {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		String fileName = "enum.bin";
		EnumBasedSingleton instance = EnumBasedSingleton.INSTANCE;
		instance.setValue(111);
		saveToFile(instance, fileName);

		EnumBasedSingleton readFromFile = readFromFile(fileName);
		System.out.println(readFromFile.getValue());
	}

	static void saveToFile(EnumBasedSingleton singleton, String filename) throws IOException {
		try (FileOutputStream fos = new FileOutputStream(filename);
		     ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(singleton);
		}
	}

	static EnumBasedSingleton readFromFile(String filename) throws IOException, ClassNotFoundException {
		try (FileInputStream fis = new FileInputStream(filename);
		     ObjectInputStream ios = new ObjectInputStream(fis)) {
			return (EnumBasedSingleton) ios.readObject();
		}
	}
}

