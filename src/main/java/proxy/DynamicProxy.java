package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class DynamicProxy {
	@SuppressWarnings("unchecked")
	public static <T> T withLogging(T target, Class<T> itf) {
		return (T) Proxy.newProxyInstance(
				itf.getClassLoader(),
				new Class<?>[]{itf},
				new LoggingHandler(target)
		);
	}

	public static void main(String[] args) {
		Person person = new Person();
		Human logging = withLogging(person, Human.class);
		logging.talk();
		logging.work();
		System.out.println(logging);
	}
}

interface Human {
	void work();

	void talk();
}

class Person implements Human {

	@Override
	public void work() {
		System.out.println("I am working!");
	}

	@Override
	public void talk() {
		System.out.println("I am talking!");
	}
}

class LoggingHandler implements InvocationHandler {
	private final Object target;
	private final Map<String, Integer> calls = new HashMap<>();

	public LoggingHandler(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		String name = method.getName();
		if (name.contains("toString")) {
			return calls.toString();
		}
		calls.merge(name, 1, Integer::sum);
		return method.invoke(target, args);
	}
}
