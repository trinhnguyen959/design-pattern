package observer.odev;

public abstract class Observer {
	protected Subject subject;

	protected abstract void notify(Subject subject, Object o);
}
