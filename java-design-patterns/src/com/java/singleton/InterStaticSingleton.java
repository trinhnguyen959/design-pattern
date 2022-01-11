package com.java.singleton;

// make sure thread safe
public class InterStaticSingleton {
	private InterStaticSingleton() {
		System.out.println("InterStaticSingleton initializing...");
	}

	private static class Implementation {
		private static final InterStaticSingleton INSTANCE = new InterStaticSingleton();

	}

	public static InterStaticSingleton getInstance() {
		return Implementation.INSTANCE;
	}
}

class InterStaticSingletonApplication {
	public static void main(String[] args) {
		InterStaticSingleton.getInstance();
	}
}
