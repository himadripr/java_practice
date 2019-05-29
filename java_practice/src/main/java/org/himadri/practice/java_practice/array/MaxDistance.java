package org.himadri.practice.java_practice.array;

import java.util.List;

/**
 * Given an array A of integers, find the maximum of j - i subjected to the constraint of A[i] <= A[j].

If there is no solution possible, return -1.

Example :

A : [3 5 4 2]

Output : 2 
for the pair (3, 4)
 *
 *
 */


public class MaxDistance {
    // DO NOT MODIFY THE LIST. IT IS READ ONLY
    
    public int maximumGap(final List<Integer> A) {
        int len = A.size();
        if (len==0){
            return -1;
        } else if (len==1){
            return 0;
        }
        int maxDist = 0;
        int []leftMin = new int[len];
        int[] rightMax = new int[len];
        
        leftMin[0] = A.get(0);
        for (int i=1;i<len;i++){
            leftMin[i] = Math.min(leftMin[i-1], A.get(i));
        }
        rightMax[len-1] = A.get(len-1);
        for (int i=len-2; i>=0; i--){
            rightMax[i] = Math.max(rightMax[i+1], A.get(i));
        }
        
        int i=0; int j=0;
        while (i<len && j<len){
            if (leftMin[i]<=rightMax[j]){
                maxDist = Math.max(maxDist, j-i);
                j++;
            } else {
                i++;
            }
        }
        return maxDist;
    }
    
   
}