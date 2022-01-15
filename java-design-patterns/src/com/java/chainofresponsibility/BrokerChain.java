package com.java.chainofresponsibility;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class BrokerChain {
	public static void main(String[] args) {
		Game game = new Game();
		GameCreature goblin = new GameCreature(game, "Strong Goblin", 2, 2);
		System.out.println(goblin);

		IncreaseGameDefenseModifier gameDefenseModifier = new IncreaseGameDefenseModifier(game, goblin);
		DoubleGameAttackModifier gameAttackModifier = new DoubleGameAttackModifier(game, goblin);
		try (gameAttackModifier) {
			System.out.println(goblin);
		}

		System.out.println(goblin);
	}
}


class Event<Args> {
	private int index = 0;
	private Map<Integer, Consumer<Args>> handlers = new HashMap<>();

	public int subcribe(Consumer<Args> handler) {
		int i = index;
		handlers.put(index++, handler);
		return i;
	}

	public void unsubscribe(int key) {
		handlers.remove(key);
	}

	public void fire(Args args) {
		for (Consumer<Args> handler : handlers.values()) {
			handler.accept(args);
		}
	}
}

class Query {
	public String creatureName;
	enum Argument {
		ATTACK, DEFENSE
	}

	public Argument argument;
	public int result;

	public Query(String creatureName, Argument argument, int result) {
		this.creatureName = creatureName;
		this.argument = argument;
		this.result = result;
	}

}

class Game {
	public Event<Query> queries = new Event<>();
}

class GameCreature {
	private Game game;
	public String name;
	public int baseAttack, baseDefense;

	public GameCreature(Game game, String name, int baseAttack, int baseDefense) {
		this.game = game;
		this.name = name;
		this.baseAttack = baseAttack;
		this.baseDefense = baseDefense;
	}

	int getAttack() {
		Query queryAttack = new Query(name, Query.Argument.ATTACK, baseAttack);
		game.queries.fire(queryAttack);
		return queryAttack.result;
	}

	int getDefense() {
		Query queryDefense = new Query(name, Query.Argument.DEFENSE, baseDefense);
		game.queries.fire(queryDefense);
		return queryDefense.result;
	}

	@Override
	public String toString() {
		return "GameCreature{" +
				"name='" + name + '\'' +
				", attack=" + getAttack() +
				", defense=" + getDefense() +
				'}';
	}
}

class GameCreatureModifier {
	protected Game game;
	protected GameCreature creature;

	public GameCreatureModifier(Game game, GameCreature creature) {
		this.game = game;
		this.creature = creature;
	}
}

class DoubleGameAttackModifier extends GameCreatureModifier implements AutoCloseable {
	private int token;

	public DoubleGameAttackModifier(Game game, GameCreature creature) {
		super(game, creature);

		game.queries.subcribe(q -> {
			if (q.creatureName.equals(creature.name) && q.argument == Query.Argument.ATTACK) {
				q.result *= 2;
			}
		});
	}

	@Override
	public void close() {
		game.queries.unsubscribe(token);
	}
}

class IncreaseGameDefenseModifier extends GameCreatureModifier {

	public IncreaseGameDefenseModifier(Game game, GameCreature creature) {
		super(game, creature);

		game.queries.subcribe(q -> {
			if (q.creatureName.equals(creature.name) && q.argument == Query.Argument.DEFENSE) {
				q.result += 3;
			}
		});
	}
}