package org.himadri.practice.java_practice.array;

import java.util.ArrayList;

//Find the first missing positive integer in O(n) tc and O(1) sc.

public class FirstMissingPositiveInteger {
    public int firstMissingPositive(ArrayList<Integer> A) {
        int min = Integer.MIN_VALUE;
        int len = A.size();
        for (int i=0; i<len; i++){
            if (A.get(i)==min || A.get(i)<=0 || A.get(i)>len){
                continue;
                
            }
            int x = A.get(i);
            //A.add(i, min);
            while (A.get(x-1)!=min){
                int y = A.get(x-1);
                A.set(x-1, min);
                if (y==min || y<=0 || y>len){
                     break;
                
                }
                x=y;
            }
        }
        int index = 0;
        for (; index<len; index++){
            if (A.get(index)!=min){
                break;
            }
        }
        return index+1;
    }
    
    public static void main(String[] args) {
		FirstMissingPositiveInteger solution = new FirstMissingPositiveInteger();
		ArrayList<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		System.out.println(solution.firstMissingPositive(list));
	}
}