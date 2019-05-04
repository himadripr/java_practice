package org.himadri.practice.java_practice.hashing;

import java.util.HashMap;

// https://www.geeksforgeeks.org/find-top-k-or-most-frequent-numbers-in-a-stream/

public class FindTopKNumbers {
	public static void main(String[] args) {
		int[]arr = {5, 2, 1, 3, 2};
		findAndPrint(arr,4);
	}
	
	private static void findAndPrint(int arr[], int k){
		int len  = arr.length;
		int []top = new int[k+1];
		for (int i=0; i<k+1; i++){
			top[i]=Integer.MIN_VALUE;
		}
		HashMap<Integer, Integer> hash = new HashMap<>();
		for (int index=0; index<len; index++){
			int item = arr[index];
			if (hash.containsKey(item)){
				hash.put(item, hash.get(item)+1);
			} else {
				hash.put(item, 1);
			}
			top[k]=item;
			int tin = getPosition(top, item);
			//now we will find the exact position of the element to be inserted.
			for (int j=tin-1; j>=0; j--){
				if (top[j]==Integer.MIN_VALUE){
					swapElements(top, j, j+1);
				} else {
					if (hash.get(top[j])<hash.get(top[j+1])){
						swapElements(top, j, j+1);
					} else if ((hash.get(top[j])==hash.get(top[j+1]))&&
							top[j]>top[j+1]){
						swapElements(top, j, j+1);
					} else {
						break;
					}
				}
			}
			//print the first k elements.
			for (int i=0; i<k; i++){
				if (top[i]==Integer.MIN_VALUE){
					break;
				}
				System.out.print(top[i]+", ");
			}
		}
	}
	
	private static int getPosition(int arr[], int ele){
		for (int i=0; i<arr.length; i++){
			if (arr[i]==ele){
				return i;
			}
		}
		return -1;
	}
	
	private static void swapElements(int arr[], int i, int j){
		int temp = arr[i];
		arr[i]=arr[j];
		arr[j]=temp;
	}
}
