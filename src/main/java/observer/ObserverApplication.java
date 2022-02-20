package observer;

import java.util.ArrayList;
import java.util.List;

interface Observer<T> {
	void handle(PropertyChangedEventArgs<T> args);
}

public class ObserverApplication implements Observer<Person> {
	public ObserverApplication() {
		Person person = new Person();
		person.subscribe(this);
		for (int i = 20; i < 24; ++i) {
			person.setAge(i);
		}
	}

	public static void main(String[] args) {
		new ObserverApplication();
	}

	@Override
	public void handle(PropertyChangedEventArgs<Person> args) {
		System.out.println("Person's " + args.propertyName + " has changed to " + args.newValue);
	}
}

class PropertyChangedEventArgs<T> {
	public T source;
	public String propertyName;
	public Object newValue;

	public PropertyChangedEventArgs(T source, String propertyName, Object newValue) {
		this.source = source;
		this.propertyName = propertyName;
		this.newValue = newValue;
	}
}

class Observable<T> {
	private final List<Observer<T>> observers = new ArrayList<>();

	public void subscribe(Observer<T> observer) {
		observers.add(observer);
	}

	protected void propertyChanged(T source, Object newValue) {
		for (Observer<T> o : observers) {
			o.handle(new PropertyChangedEventArgs<T>(source, "age", newValue));
		}
	}
}

class Person extends Observable<Person> {
	private int age;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		if (this.age == age) return;
		this.age = age;
		propertyChanged(this, age);
	}
}

