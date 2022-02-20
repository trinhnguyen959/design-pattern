package memento;

public class MementoApplication {
	public static void main(String[] args) {
		BankAccount account = new BankAccount(100);
		Memento depositFirst = account.deposit(50);
		Memento depositSecond = account.deposit(25);
		System.out.println(account);

		// restore to first
		account.restore(depositFirst);
		System.out.println(account);

		// restore to second
		account.restore(depositSecond);
		System.out.println(account);
	}
}

class BankAccount {
	private int balance;

	public BankAccount(int balance) {
		this.balance = balance;
	}

	public Memento deposit(int amount) {
		balance += amount;
		return new Memento(balance);
	}

	public void restore(Memento memento) {
		balance = memento.balance;
	}

	@Override
	public String toString() {
		return "BankAccount{" +
				"balance=" + balance +
				'}';
	}
}

class Memento {
	public int balance;

	public Memento(int balance) {
		this.balance = balance;
	}
}
