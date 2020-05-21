package org.himadri.practice.java_practice.hashing;

import java.util.HashSet;
import java.util.Iterator;

public class Puzzle100Guns {
	
	public static void main(String[] args) {
		HashSet<Integer> hash = new HashSet<>();
		for (int i=1;i<=100; i++) {
			hash.add(i);
		}
		System.out.println("The survivor is :"+ getSurvivor(hash));
		
	}
	
	private static int getSurvivor(HashSet<Integer> hash) {
		int i=1;
		while (hash.size()>1) {
			//to find next survivor
			while (!hash.contains(i)) {
				i++;
			}
			i++;
			
			//to find who gets killed
			while (!hash.contains(i)) {
				i++;
			}
			hash.remove(i);
			
		}
		Iterator<Integer> it = hash.iterator();
		return it.next();
	}

}
