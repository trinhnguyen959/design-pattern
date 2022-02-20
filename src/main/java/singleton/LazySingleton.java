package singleton;

public class LazySingleton {
	private static LazySingleton instance;

	private LazySingleton() {
		System.out.println("Initializing LazySingleton...");
	}

	// fix synchronized for multi-thread
	public static synchronized LazySingleton getInstance() {
		if (instance == null) {
			instance = new LazySingleton();
		}
		return instance;
	}

	// double check block
	public static LazySingleton getInstanceDoubleChecked() {
		if (instance == null) {
			synchronized (LazySingleton.class) {
				if (instance == null) {
					instance = new LazySingleton();
				}
			}
		}

		return instance;
	}
}

class LazySingletonApplication {
	public static void main(String[] args) {
		LazySingleton.getInstance();
		LazySingleton.getInstanceDoubleChecked();
	}
}
