package com.java.singleton;

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

public class TestabilityIssues {
	@Test
	void singletonTotalPopulationTest() {
		SingletonRecordFinders finders = new SingletonRecordFinders();
		List<String> names = List.of("Seoul", "Mexico City");
		int totalPopulation = finders.getTotalPopulation(names);
		assertEquals(17500000 + 17400000, totalPopulation);
	}
}

class SingletonDatabase {
	private Dictionary<String, Integer> capitals = new Hashtable<>();
	private static int instanceCount = 0;

	public static int getCount() {
		return instanceCount;
	}

	private SingletonDatabase() {
		instanceCount++;
		System.out.println("Initializing Database...");

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

