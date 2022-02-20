package singleton;

import com.google.common.collect.Iterables;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

interface Database {
	int getPopulation(String name);
}

public class TestabilityIssues {
	@Test
	void singletonTotalPopulationTest() {
		SingletonRecordFinders finders = new SingletonRecordFinders();
		List<String> names = List.of("Seoul", "Mexico City");
		int totalPopulation = finders.getTotalPopulation(names);
		assertEquals(17500000 + 17400000, totalPopulation);
	}

	@Test
	void dependentPopulationsTest() {
		DummyDatabase database = new DummyDatabase();
		ConfigurableRecordFinders finders = new ConfigurableRecordFinders(database);
		assertEquals(4, finders.getTotalPopulation(List.of("alpha", "gamma")));
	}
}

class SingletonDatabase implements Database {
	private final Dictionary<String, Integer> capitals = new Hashtable<>();
	private static int instanceCount = 0;

	public static int getCount() {
		return instanceCount;
	}

	private SingletonDatabase() {
		instanceCount++;
		System.out.println("Initializing Database... " + instanceCount);

		try {
			File file = new File(SingletonDatabase.class.getProtectionDomain().getCodeSource().getLocation().getPath());
			Path path = Paths.get(file.getPath(), "data.txt");
			List<String> lines = Files.readAllLines(path);

			Iterables.partition(lines, 2)
					.forEach(kv -> capitals.put(
							kv.get(0).trim(), Integer.parseInt(kv.get(1))
					));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static final SingletonDatabase INSTANCE = new SingletonDatabase();

	public static SingletonDatabase getInstance() {
		return INSTANCE;
	}

	@Override
	public int getPopulation(String name) {
		return capitals.get(name);
	}
}

class SingletonRecordFinders {
	public int getTotalPopulation(List<String> names) {
		int result = 0;
		for (String name : names) {
			result += SingletonDatabase.getInstance().getPopulation(name);
		}
		return result;
	}
}

class ConfigurableRecordFinders {
	private final Database database;

	public ConfigurableRecordFinders(Database database) {
		this.database = database;
	}

	public int getTotalPopulation(List<String> names) {
		int result = 0;
		for (String name : names) {
			result += database.getPopulation(name);
		}
		return result;
	}
}

class DummyDatabase implements Database {
	private final Dictionary<String, Integer> data = new Hashtable<>();

	public DummyDatabase() {
		data.put("alpha", 1);
		data.put("beta", 2);
		data.put("gamma", 3);
	}

	@Override
	public int getPopulation(String name) {
		return data.get(name);
	}
}

