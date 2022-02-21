package singleton.odev;

public class SingletonMain {
	public static void main(String[] args) {
		Thread threadOne = new Thread(() -> JustOneSingleton.getInstance().SayHello());
		Thread threadTwo = new Thread(() -> JustOneSingleton.getInstance().SayHello());

		threadOne.start();
		threadTwo.start();

	}
}
