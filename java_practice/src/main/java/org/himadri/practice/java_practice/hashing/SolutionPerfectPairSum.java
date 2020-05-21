package org.himadri.practice.java_practice.hashing;

/**
 * Two elements 'a' and 'b' are said to be a perfect pair if sum of digits of 'a' is equal to sum of digits of 'b'. Find the perfect pair with the maximum sum (a+b). 

Input:
First line of input contains number of testcases T. For each testcase, there will be 2 lines. First line contains N which denoted the number of elements in the array. Second line contains N space separated integers.

Output:
Print the maximum sum. If it is not possible print -1.

Your Task:
Complete the function PerfectMatch() which takes the array and N as inputs and returns the maximum perfect match. Return -1 if no such match is possible. 

Constraints:
1 <= T <= 100
1 <= N <= 10^5
1 <= arr[i] <= 10^9

Example:
Sample Input:
2
5
55 23 32 46 88
4
18 19 23 15

Sample Output:
101
-1 

Explanation:
Testcase 1:
Possible perfect match with equal digit sum are - 
(23, 32) with digit sum 5 
(55, 46) with digit sum 10 
The pair with maximum sum is (55, 46) i.e. 55 + 46 = 101

Testcase 2:
There are no elements that form a perfect match. 

 


 */


import java.util.*;

public class SolutionPerfectPairSum 

{ 
	public static void main(String[] args) {
		int [] arr = {55, 23, 32, 46,88};
	}
	
    static class Pair{
        int fir = Integer.MIN_VALUE;
        int sec = Integer.MIN_VALUE;
    }
	static int PerfectMatch(int arr[], int n) { 
        // your code goes here 	
        HashMap<Integer, Pair> map = new HashMap<>();
        int ms = -1; //maxSum
        
        for (int v: arr){
            String s = String.valueOf(v);
            int ds = 0;
            for (int i=0;i<s.length();i++){
                ds+=(s.charAt(i)-'0');
            }
            
            if (map.get(ds)!=null){
                System.out.println(s+", sum = "+ds);
                Pair p = map.get(ds);
                //update fir and sec
                if (v>=p.fir){
                    p.sec = p.fir;
                    p.fir = v;
                } else if(v>p.sec){
                    p.sec = v;
                }
                int t = p.fir+p.sec;
                if (ms>t){  //see the mistake here, it should t>ms
                    ms=t;
                }
                map.put(ds, p);
            } else {
                System.out.println(s+", sum = "+ds);
                Pair p = new Pair();
                p.fir = v;
                map.put(ds, p);
            }
        }
        return ms;
	}    
} 