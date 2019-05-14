package org.himadri.practice.java_practice.java_examples;

/**
 * Given a list of non negative integers, arrange them such that they form the largest number.

For example:

Given [3, 30, 34, 5, 9], the largest formed number is 9534330.
Given [0, 0, 0, 0], the largest number is 0

Note: The result may be very large, so you need to return a string instead of an integer.
 */


import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LargestNumber {
    // DO NOT MODIFY THE LIST. IT IS READ ONLY
    public String largestNumber(final List<Integer> A) {
        int len = A.size();
        String[] arr = new String[len];
        int i=0;
        for (int x: A){
            arr[i]=String.valueOf(x);
            i++;
        }
        Arrays.sort(arr, new Comparator<String>(){
            public int compare(String s1, String s2){
                int i = 0;
                int j=0;
                int len1 = s1.length();
                int len2 = s2.length();
                while (i<len1 || j<len2){
                    if (i==len1){
                        i=0;
                    }
                    if (j==len2){
                        j=0;
                    }
                    if (s1.charAt(i)>s2.charAt(j)){
                        return -1;
                    } else if (s1.charAt(i)<s2.charAt(j)){
                        return 1;
                    }
                    i++;
                    j++;
                }
                return 0;
            }
        });
        StringBuilder sb = new StringBuilder("");
        int index=0;
        while(index<len && arr[index].equals("0")){
            index++;
        }
        for (; index<len; index++){
            sb.append(arr[index]);
        }
        if (sb.toString().equals("")){
            sb.append("0");
        }
        return sb.toString();
    }
}