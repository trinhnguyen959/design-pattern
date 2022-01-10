package com.java.builder;

class ComplexPerson {
	public String streetAddress, postcode, city;
	public String companyName, position;
	public int annualIncome;

	@Override
	public String toString() {
		return "ComplexPerson{" +
				"streetAddress='" + streetAddress + '\'' +
				", postcode='" + postcode + '\'' +
				", city='" + city + '\'' +
				", companyName='" + companyName + '\'' +
				", position='" + position + '\'' +
				", annualIncome=" + annualIncome +
				'}';
	}
}

class ComplexPersonBuilder {
	ComplexPerson person = new ComplexPerson();

	public PersonAddressBuilder lives() {
		return new PersonAddressBuilder(person);
	}

	public PersonJobBuilder works() {
		return new PersonJobBuilder(person);
	}

	public ComplexPerson build() {
		return person;
	}
}

class PersonAddressBuilder extends ComplexPersonBuilder {
	public PersonAddressBuilder(ComplexPerson person) {
		this.person = person;
	}

	public PersonAddressBuilder at(String streetAddress) {
		person.streetAddress = streetAddress;
		return this;
	}

	public PersonAddressBuilder in(String city) {
		person.city = city;
		return this;
	}

	public PersonAddressBuilder withPostCode(String postCode) {
		person.postcode = postCode;
		return this;
	}
}

class PersonJobBuilder extends ComplexPersonBuilder {
	public PersonJobBuilder(ComplexPerson person) {
		this.person = person;
	}

	public PersonJobBuilder at(String companyName) {
		person.companyName = companyName;
		return this;
	}

	public PersonJobBuilder asA(String position) {
		person.position = position;
		return this;
	}

	public PersonJobBuilder earning(int annualIncome)
	{
		person.annualIncome = annualIncome;
		return this;
	}
}

class ApplicationComplexPersonBuilder {
	public static void main(String[] args) {
		ComplexPersonBuilder builder = new ComplexPersonBuilder();
		ComplexPerson person = builder
				.lives()
				.at("Philadelphia")
				.in("Pennsylvania")
				.withPostCode("21093")
				.works()
				.at("Apple")
				.asA("Engineer")
				.earning(1000)
				.build();

		System.out.println(person);
	}
}