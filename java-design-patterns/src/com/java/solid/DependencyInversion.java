package com.java.solid;

import org.javatuples.Triplet;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

enum Relationship {
	PARENT,
	CHILD,
	SIBLING
}

/*SOLUTION*/
interface RelationshipBrowser {
	List<Person> findAllChildrenOf(String name);
}

/*
 * A. High-level modules should not depend on low-level modules
 * Both should depend on abstractions
 *
 * B. Abstractions shout not depend on details
 * Details should not depend on abstractions
 * */
public class DependencyInversion {
	public static void main(String[] args) {
		Person john = new Person("John");
		Person chris = new Person("Chris");
		Person matt = new Person("Matt");

		Relationships relationships = new Relationships();
		relationships.addParentAndChild(john, chris);
		relationships.addParentAndChild(john, matt);

		new Research(relationships);

	}
}

class Person {
	public String name;

	public Person(String name) {
		this.name = name;
	}
}

// low level
class Relationships implements RelationshipBrowser {
	private final List<Triplet<Person, Relationship, Person>> relations = new ArrayList<>();

	public void addParentAndChild(Person parent, Person child) {
		relations.add(new Triplet<>(parent, Relationship.PARENT, child));
		relations.add(new Triplet<>(child, Relationship.CHILD, parent));
	}

	public List<Triplet<Person, Relationship, Person>> getRelations() {
		return relations;
	}

	@Override
	public List<Person> findAllChildrenOf(String name) {
		return relations.stream()
				.filter(x -> Objects.equals(x.getValue0().name, name)
						&& x.getValue1() == Relationship.PARENT)
				.map(Triplet::getValue2)
				.collect(Collectors.toList());
	}
}

// high level
class Research {
//	public Research(Relationships relationships) {
//		List<Triplet<Person, Relationship, Person>> relations = relationships.getRelations();
//		relations.stream()
//				.filter(x -> x.getValue0().name.equals("John")
//						&& x.getValue1() == Relationship.PARENT)
//				.forEach(ch -> System.out.println(
//						"John has a child called " + ch.getValue2().name
//				));
//	}

	public Research(RelationshipBrowser browser) {
		List<Person> children = browser.findAllChildrenOf("John");
		for (Person child : children) {
			System.out.println("John has a child called " + child.name);
		}
	}
}