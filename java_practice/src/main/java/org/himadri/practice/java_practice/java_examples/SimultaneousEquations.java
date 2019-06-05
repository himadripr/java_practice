package org.himadri.practice.java_practice.java_examples;

import java.awt.Point;

public class SimultaneousEquations {
	public static void main(String[] args) {
		solveSimultaneousEquations(0.6, -0.8, 0.8, 0.6, 3, 4);
	}
	
	/**
	 * equations are :- a1x+b1y = c1 and a2x+b2y = c2
	 * 
	 * @param a1
	 * @param b1
	 * @param a2
	 * @param b2
	 * @param c1
	 * @param c2
	 */
	private static Point solveSimultaneousEquations(double a1, double b1, double a2, double b2, double c1, double c2) {
		double det = ((a1) * (b2) - (b1) * (a2)); // instead of 1/
		double x = ((b2) * (c1) - (b1) * (c2)) / det;
		double y = ((a1) * (c2) - (a2) * (c1)) / det;
		Point p = new Point();
		p.x = (int) x;
		p.y = (int) y;
		System.out.print("x=" + p.x + " y=" + p.y);
		return p;
	}

}
