package prototype;

public class CloneConstructor {
	public static void main(String[] args) {
		Employee john = new Employee("John", new CAddress("123 London Road", "London", "UK"));
		Employee chris = new Employee(john);
		chris.name = "Chris";
		System.out.println(john);
		System.out.println(chris);
	}
}

class CAddress {
	String streetAddress, city, country;

	public CAddress(String streetAddress, String city, String country) {
		this.streetAddress = streetAddress;
		this.city = city;
		this.country = country;
	}

	public CAddress(CAddress other) {
		this(other.streetAddress, other.city, other.country);
	}

	@Override
	public String toString() {
		return "CAddress{" +
				"streetAddress='" + streetAddress + '\'' +
				", city='" + city + '\'' +
				", country='" + country + '\'' +
				'}';
	}
}

class Employee {
	public String name;
	public CAddress address;

	public Employee(String name, CAddress address) {
		this.name = name;
		this.address = address;
	}

	public Employee(Employee other) {
		name = other.name;
		address = new CAddress(other.address);
	}

	@Override
	public String toString() {
		return "CEmployee{" +
				"name='" + name + '\'' +
				", address=" + address +
				'}';
	}
}
