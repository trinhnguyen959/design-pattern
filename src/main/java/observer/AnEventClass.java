package observer;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class AnEventClass {
	public static void main(String[] args) {
		APerson person = new APerson();
		Event<PropertyChangedEventArg>.Subscription subscription = person.propertyChanged.addHandler(x -> {
			System.out.println("Person's " + x.propertyName + " has changed!");
		});
		person.setAge(18);
		person.setAge(19);
		subscription.close();
		person.setAge(20);
	}
}

class Event<TArgs> {
	private final Map<Integer, Consumer<TArgs>> handlers = new HashMap<>();
	private int count = 0;

	public Subscription addHandler(Consumer<TArgs> handler) {
		int i = count;
		handlers.put(count++, handler);
		return new Subscription(this, i);
	}

	public void fire(TArgs args) {
		for (Consumer<TArgs> handler : handlers.values()) {
			handler.accept(args);
		}
	}

	public class Subscription implements AutoCloseable {
		private final Event<TArgs> event;
		private final int id;

		public Subscription(Event<TArgs> event, int id) {
			this.event = event;
			this.id = id;
		}

		@Override
		public void close() {
			event.handlers.remove(id);
		}
	}
}

class PropertyChangedEventArg {
	public Object source;
	public String propertyName;

	public PropertyChangedEventArg(Object source, String propertyName) {
		this.source = source;
		this.propertyName = propertyName;
	}
}

class APerson {
	public Event<PropertyChangedEventArg> propertyChanged = new Event<>();
	private int age;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		if (this.age == age) return;
		boolean oldCanVote = getCanVote();

		this.age = age;

		propertyChanged.fire(new PropertyChangedEventArg(
				this, "age"
		));

		if (oldCanVote != getCanVote()) {
			propertyChanged.fire(new PropertyChangedEventArg(
					this, "canVote"
			));
		}
	}

	public boolean getCanVote() {
		return age >= 18;
	}
}
