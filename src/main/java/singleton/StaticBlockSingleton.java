package singleton;

import java.io.IOException;

public class StaticBlockSingleton {
	private static StaticBlockSingleton instance;

	static {
		try {
			instance = new StaticBlockSingleton();
		} catch (Exception e) {
			System.err.println("Fail to create singleton");
		}
		System.out.println("Singleton create successful!");
	}

	private StaticBlockSingleton() throws IOException {
		System.out.println("Singleton is initializing");
//		File.createTempFile(".", ".");
	}

	public static StaticBlockSingleton getInstance() {
		return instance;
	}
}

class StaticApplication {
	public static void main(String[] args) {
		StaticBlockSingleton.getInstance();
	}
}