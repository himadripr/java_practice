package org.himadri.practice.java_practice.vending_machine;

import java.util.List;

/** * Decleare public API for Vending Machine * @author Javin Paul */
public interface VendingMachine {
	public long selectItemAndGetPrice(Item item);

	public void insertCoin(Coin coin);

	public List<Coin> refund();

	public Bucket<Item, List<Coin>> collectItemAndChange();

	public void reset();
}

// Read more:
// https://javarevisited.blogspot.com/2016/06/design-vending-machine-in-java.html#ixzz5nQk80t59