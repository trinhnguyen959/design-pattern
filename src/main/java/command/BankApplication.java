package command;

import java.util.List;

public class BankApplication {
	public static void main(String[] args) {
		BankAccount bankAccount = new BankAccount();
		System.out.println("Bank Account: " + bankAccount);

		List<BankAccountCommand> commands = List.of(
				new BankAccountCommand(bankAccount, BankAccountCommand.Action.DEPOSIT, 100),
				new BankAccountCommand(bankAccount, BankAccountCommand.Action.WITHDRAW, 1000)
		);

		for (BankAccountCommand command : commands) {
			command.call();
			System.out.println(bankAccount);
		}
	}
}

class BankAccount {
	private int balance;
	private final int overdraftLimit = -500;

	public void deposit(int amount) {
		balance += amount;
		System.out.println("Deposit " + amount + " balance is now " + balance);
	}

	public void withdraw(int amount) {
		if (balance - amount >= overdraftLimit) {
			balance -= amount;
			System.out.println("Withdraw " + amount + " balance is now " + balance);
		}
	}

	@Override
	public String toString() {
		return "BankAccount{" +
				"balance=" + balance +
				'}';
	}
}

interface Command {
	void call();
}

class BankAccountCommand implements Command {
	private final BankAccount bankAccount;

	@Override
	public void call() {
		switch (action) {
			case DEPOSIT:
				bankAccount.deposit(amount);
				break;
			case WITHDRAW:
				bankAccount.withdraw(amount);
				break;
		}
	}

	public enum Action {
		DEPOSIT, WITHDRAW
	}

	private final Action action;
	private final int amount;

	public BankAccountCommand(BankAccount bankAccount, Action action, int amount) {
		this.bankAccount = bankAccount;
		this.action = action;
		this.amount = amount;
	}
}