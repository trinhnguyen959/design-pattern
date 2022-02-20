package nullobject;

import java.lang.reflect.Proxy;

interface Log {
	void info(String message);

	void warn(String message);
}

public class NullObjectApplication {
	public static void main(String[] args) {
		Log log = noOp(Log.class);
		BankAccount bankAccount = new BankAccount(log);
		bankAccount.deposit(100);
		bankAccount.withdraw(50);
	}

	@SuppressWarnings("unchecked")
	public static <T> T noOp(Class<T> itf) {
		return (T) Proxy.newProxyInstance(
				itf.getClassLoader(),
				new Class<?>[]{itf},
				(proxy, method, args) -> {
					if (method.getReturnType().equals(Void.TYPE)) {
						return null;
					} else {
						return method.getReturnType().getConstructor().newInstance();
					}
				}
		);
	}
}

class ConsoleLog implements Log {
	@Override
	public void info(String message) {
		System.out.println(message);
	}

	@Override
	public void warn(String message) {
		System.out.println("WARNING: " + message);
	}
}

final class NullLog implements Log {

	@Override
	public void info(String message) {

	}

	@Override
	public void warn(String message) {

	}
}

class BankAccount {
	private final Log log;
	private int balance;

	public BankAccount(Log log) {
		this.log = log;
	}

	public void deposit(int amount) {
		balance += amount;
		if (log != null) {
			log.info("Deposited " + amount
					+ ", balance is now " + balance);
		}
	}

	public void withdraw(int amount) {
		if (balance >= amount) {
			balance -= amount;
			if (log != null) {
				System.out.println("Withdrew " + amount
						+ ", we have " + balance + " left");
			}
		} else {
			if (log != null) {
				System.out.println("Could not withdraw "
						+ amount + " because balance is only " + balance);
			}
		}
	}
}
