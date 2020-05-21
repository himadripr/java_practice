package org.himadri.practice.java_practice.java_examples;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date date = new Date();
		long daysFactor = 24*60*60*1000;
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("Today's date is: "+formatter.format(date));
		System.out.println("After 10 days date will be: "+formatter.format(date.getTime()+10*daysFactor));
		System.out.println("Before 10 days date was: "+formatter.format(date.getTime()-10*daysFactor));
		System.out.println("After 20 days date will be: "+formatter.format(date.getTime()+20*daysFactor));
		System.out.println("Before 20 days date was: "+formatter.format(date.getTime()-20*daysFactor));

	}

}
