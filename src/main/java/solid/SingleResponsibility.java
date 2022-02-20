package solid;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/*
* Single class,method single responsibility
* */
public class SingleResponsibility {

	public static void main(String[] args) throws IOException {
		Journal journal = new Journal();
		journal.addEntry("It's a human sight!");
		journal.addEntry("When the things go wrong!");
		System.out.println(journal);

		Persistence persistence = new Persistence();
		String filename = "\\journal.txt";
		persistence.saveToFile(journal, filename, true);

		Runtime.getRuntime().exec("notepad.exe " + filename);
	}
}

/*SOLUTION*/
class Persistence {
	public void saveToFile(Journal journal, String filename, boolean override) throws FileNotFoundException {
		if (override || new File(filename).exists()) {
			try (PrintStream out = new PrintStream(filename)) {
				out.println(journal.toString());
			}
		}
	}
}

class Journal {
	private static final List<String> entries = new ArrayList<>();
	private static int counter = 0;

	public void addEntry(String entry) {
		entries.add("" + (++counter) + ": " + entry);
	}

	public void removeEntry(int index) {
		entries.remove(index);
	}

	public void saveEntry(String filename) throws FileNotFoundException {
		try (PrintStream out = new PrintStream(filename)) {
			out.println(this);
		}
	}

	public void loadEntry(String filename) {
	}

	public void load(URL url) {
	}

	@Override
	public String toString() {
		return String.join(System.lineSeparator(), entries);
	}
}
