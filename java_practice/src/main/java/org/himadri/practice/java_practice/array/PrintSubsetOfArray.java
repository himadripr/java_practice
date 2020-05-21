package org.himadri.practice.java_practice.array;

import java.util.Arrays;

public class PrintSubsetOfArray {
	public static void main(String [] args) {
		int[] arr = {1,2,3,4};
		int len = arr.length;
		for (int i=0; i< (1<<len); i++){
			for (int j=0; j<len; j++){
				int t = 1<<j;
				if ((i&t)!=0){
					System.out.print(arr[j]+", ");
				}
			}
			System.out.println("\n");
		}
		
	}

}
