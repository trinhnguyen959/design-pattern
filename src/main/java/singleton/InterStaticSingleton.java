package singleton;

// make sure thread safe
public class InterStaticSingleton {
	private InterStaticSingleton() {
		System.out.println("InterStaticSingleton initializing...");
	}

	public static InterStaticSingleton getInstance() {
		return Implementation.INSTANCE;
	}

	private static class Implementation {
		private static final InterStaticSingleton INSTANCE = new InterStaticSingleton();

	}
}

class InterStaticSingletonApplication {
	public static void main(String[] args) {
		InterStaticSingleton.getInstance();
	}
}
