package com.java.prototype;

import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;

public class CloneBySerializable {
	public static void main(String[] args) {
		Foo foo = new Foo(42, "ever");
		Foo rounder = SerializationUtils.roundtrip(foo);
		rounder.whatever = "xyz";

		System.out.println(foo);
		System.out.println(rounder);
	}
}

class Foo implements Serializable {
	public int stuff;
	public String whatever;

	public Foo(int stuff, String whatever) {
		this.stuff = stuff;
		this.whatever = whatever;
	}

	@Override
	public String toString() {
		return "Foo{" +
				"stuff=" + stuff +
				", whatever='" + whatever + '\'' +
				'}';
	}
}
