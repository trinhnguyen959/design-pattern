package template;

public class TemplateMethodApplication {
	public static void main(String[] args) {
		new Chess().run();
	}
}

abstract class Game {
	protected int current;
	public final int numberOfPlayers;

	public Game(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}

	public void run() {
		start();
		while (!hasWinner()) {
			takeTurn();
			System.out.println("Player " + getWinningPlayer() + " wins");
		}
	}

	protected abstract void start();

	protected abstract boolean hasWinner();

	protected abstract void takeTurn();

	protected abstract int getWinningPlayer();
}

class Chess extends Game {
	private final int maxTurn = 10;
	private int turn = 1;

	public Chess() {
		super(2);
	}

	@Override
	protected void start() {
		System.out.println("Starting a game of chess");
	}

	@Override
	protected boolean hasWinner() {
		return turn == maxTurn;
	}

	@Override
	protected void takeTurn() {
		System.out.println("Turn " + (turn++) + " taken by player " + current);
		current = (current + 1) % numberOfPlayers;
	}

	@Override
	protected int getWinningPlayer() {
		return 0;
	}
}