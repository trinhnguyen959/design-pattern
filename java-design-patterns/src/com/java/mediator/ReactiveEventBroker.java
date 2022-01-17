package com.java.mediator;

import io.reactivex.Observable;
import io.reactivex.Observer;

import java.util.ArrayList;
import java.util.List;

public class ReactiveEventBroker {
	public static void main(String[] args) {
		EventBroker eventBroker = new EventBroker();
		FootballPlayer john = new FootballPlayer(eventBroker, "john");
		FootballCoach footballCoach = new FootballCoach(eventBroker);
		john.score();
		john.score();
		john.score();
	}
}

class EventBroker extends Observable<Integer> {
	private final List<Observer<? super Integer>> observers = new ArrayList<>();

	@Override
	protected void subscribeActual(Observer<? super Integer> observer) {
		observers.add(observer);
	}

	public void publish(int value) {
		for (Observer<? super Integer> observer : observers) {
			observer.onNext(value);
		}
	}
}

class FootballPlayer {
	private int goalsScore = 0;
	private final EventBroker eventBroker;
	public String name;

	public FootballPlayer(EventBroker eventBroker, String name) {
		this.eventBroker = eventBroker;
		this.name = name;
	}

	public void score() {
		eventBroker.publish(++goalsScore);
	}
}

class FootballCoach {
	public FootballCoach(EventBroker eventBroker) {
		eventBroker.subscribe(i -> {
			System.out.println("Hey, you score " + i + " goal");
		});
	}
}
