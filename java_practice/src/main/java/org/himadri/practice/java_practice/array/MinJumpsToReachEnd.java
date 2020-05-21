//package org.himadri.practice.java_practice.array;

import java.util.*;

public class PrintMedianOfRunningIntegers {
	public static void main(String [] args) {
		int[] arr = {5, 15, 1, 3, 2, 8, 7, 9, 10, 6, 11, 4}; 
		printMedian(arr);
	}

	static void printMedian(int[] arr){
		int len = arr.length;
		if (len==0){
			return;
		}
		if (len==1){
			System.out.println(arr[0]);
			return;
		}
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

		maxHeap.add(arr[0]);
		System.out.println(arr[0]);

		for (int i=1; i<len; i++){
			int v = arr[i];
			if (maxHeap.peek()>=v){
				maxHeap.add(v);
			} else {
				minHeap.add(v);
			}
			if (Math.abs(maxHeap.size()-minHeap.size())>1){
				//it is not balanced
				if (maxHeap.size()>minHeap.size()){
					minHeap.add(maxHeap.poll());
				} else{
					maxHeap.add(minHeap.poll());
				}
			}
			if (maxHeap.size()==minHeap.size()){
				System.out.println((maxHeap.peek()+minHeap.peek())/2.0);
			} else {
				if (maxHeap.size()>minHeap.size()){
					System.out.println(maxHeap.peek());
				} else {
					System.out.println(minHeap.peek());
				}
			}
		}

		

	}

}
