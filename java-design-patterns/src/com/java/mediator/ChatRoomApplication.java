package com.java.mediator;

import java.util.ArrayList;
import java.util.List;

class ChatRoom {
	private final List<Person> people = new ArrayList<>();

	public void join(Person person) {
		String joinMessage = person.name + " join the room";
		broadcast("room", joinMessage);
		person.room = this;
		people.add(person);
	}

	public void broadcast(String source, String message) {
		for(Person person : people) {
			if (!person.name.equals(source)) {
				person.receive(source,message);
			}
		}
	}

	public void message(String name, String who, String message) {
		people.stream()
				.filter(person -> person.name.equals(who))
				.findFirst()
				.ifPresent(person -> person.receive(name, message));
	}
}

class Person {
	public String name;
	public ChatRoom room;
	private final List<String> chatLog = new ArrayList<>();

	public Person(String name) {
		this.name = name;
	}

	public void receive(String sender, String message) {
		String s = sender + ": '" + message + "'";
		System.out.println("[" + name + "'s chat session]" + s);
	}

	public void say(String message) {
		room.broadcast(name, message);
	}

	public void privateMessage(String who, String message) {
		room.message(name, who, message);
	}
}

public class ChatRoomApplication {
	public static void main(String[] args) {
		ChatRoom chatRoom = new ChatRoom();
		Person john = new Person("John");
		Person jane = new Person("Jane");

		chatRoom.join(john);
		chatRoom.join(jane);

		john.say("hi room");
		jane.say("oh hey john");

		Person simon = new Person("simon");
		chatRoom.join(simon);
		simon.say("hi everyone");

		jane.privateMessage("simon", "glad you should join us");
	}
}