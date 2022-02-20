package flyweight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class RepeatingUser {
	public static void main(String[] args) {
		User johnSmith = new User("John Smith");
		User janeSmith = new User("Jane Smith");

	}
}

class User {
	public String fullName;

	public User(String fullName) {
		this.fullName = fullName;
	}
}

class SecondUser {
	static List<String> strings = new ArrayList<>();
	private int[] names;

	public SecondUser(String fullName) {
		Function<String, Integer> getOrAdd = (String s) -> {
			int index = strings.indexOf(s);
			if (index != -1) {
				return index;
			} else {
				strings.add(s);
				return strings.size() - 1;
			}
		};
		names = Arrays.stream(fullName.split(" "))
				.mapToInt(getOrAdd::apply)
				.toArray();
	}
}