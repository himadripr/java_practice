package org.himadri.practice.java_practice.vending_machine;

/**
 * * A parameterized utility class to hold two different object. * @author Javin
 * Paul
 */
public class Bucket<E1, E2> {
	private E1 first;
	private E2 second;

	public Bucket(E1 first, E2 second) {
		this.first = first;
		this.second = second;
	}

	public E1 getFirst() {
		return first;
	}

	public E2 getSecond() {
		return second;
	}
}

// Read more:
// https://javarevisited.blogspot.com/2016/06/design-vending-machine-in-java.html#ixzz5nQjHYOWl