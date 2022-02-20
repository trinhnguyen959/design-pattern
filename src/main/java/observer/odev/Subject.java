package observer.odev;

import java.util.ArrayList;
import java.util.List;

public class Subject {
	private final List<Observer> observers = new ArrayList<>();

	public void attackObserver(Observer iObserver) {
		observers.add(iObserver);
	}

	public void detachObserver(Observer iObserver) {
		observers.remove(iObserver);
	}

	public void notifyObserver(Subject subject, Object o) {
		observers.forEach(observer -> observer.notify(subject, o));
	}
}
