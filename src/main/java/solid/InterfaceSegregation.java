package solid;

interface Machine {
	void print(Document document);

	void fax(Document document);

	void scan(Document document);
}

/*SOLUTION*/
interface Printer {
	void print(Document document);
}

interface Scanner {
	void scan(Document document);
}

interface MultiFunctionDevice extends Printer, Scanner {
}

public class InterfaceSegregation {
}

class Document {

}

class MultiFunctionPrinter implements Machine {

	@Override
	public void print(Document document) {

	}

	@Override
	public void fax(Document document) {

	}

	@Override
	public void scan(Document document) {

	}
}

class OldFashionPrinter implements Machine {
	@Override
	public void print(Document document) {

	}

	@Override
	public void fax(Document document) {
		// thow vi old fashion khong co fax
	}

	@Override
	public void scan(Document document) {
		// thow vi old fashion khong co scan
	}
}

class JustAPrinter implements Printer {

	@Override
	public void print(Document document) {

	}
}

class Photocopier implements Printer, Scanner {
	@Override
	public void print(Document document) {

	}

	@Override
	public void scan(Document document) {

	}
}

class MultiFunctionMachine implements MultiFunctionDevice {
	private Printer printer;
	private Scanner scanner;

	@Override
	public void print(Document document) {
		printer.print(document);
	}

	@Override
	public void scan(Document document) {
		scanner.scan(document);
	}
}


