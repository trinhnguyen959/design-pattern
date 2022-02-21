package singleton.odev;

import java.util.Random;

public class JustOneSingleton {
	private int index;
	private static JustOneSingleton justOneSingleton;

	private JustOneSingleton(int index) {
		this.index = index;
	}

	public static synchronized JustOneSingleton getInstance() {
		if (justOneSingleton == null) {
			Random random = new Random();
			justOneSingleton = new JustOneSingleton(random.nextInt(1, 4));
		}
		return justOneSingleton;
	}

	public void SayHello() {
		System.out.println("Hi, I'm unique " + index);
	}
}
