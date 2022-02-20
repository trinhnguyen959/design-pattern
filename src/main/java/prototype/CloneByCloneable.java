package prototype;

import java.util.Arrays;

// don't use cloneable
class Address implements Cloneable {
	public String streetName;
	public int houseNumber;

	public Address(String streetName, int houseNumber) {
		this.streetName = streetName;
		this.houseNumber = houseNumber;
	}

	@Override
	public String toString() {
		return "Address{" +
				"streetName='" + streetName + '\'' +
				", houseNumber='" + houseNumber + '\'' +
				'}';
	}

	// not recommend
	@Override
	public Object clone() {
		return new Address(streetName, houseNumber);
	}
}

class Person implements Cloneable{
	public String[] names;
	public Address address;

	public Person(String[] names, Address address) {
		this.names = names;
		this.address = address;
	}

	@Override
	public String toString() {
		return "Person{" +
				"names=" + Arrays.toString(names) +
				", address=" + address +
				'}';
	}

	@Override
	protected Object clone() {
		return new Person(names.clone(), (Address) address.clone());
	}
}

class PrototypeApplication {
	public static void main(String[] args) {
		Person john = new Person(new String[] {"John", "Smith"}, new Address("London Road", 123));
		Person jane = (Person) john.clone();
		jane.names[0] = "Jane";
		jane.address.houseNumber = 456;

		System.out.println(jane);
		System.out.println(john);
	}
}