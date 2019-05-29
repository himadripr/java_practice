package org.himadri.practice.java_practice.array;

import java.util.*;


/**
 * 
 * Given an array A of integers, find the index of values that satisfy A + B = C + D, where A,B,C & D are integers values in the array

Note:

1) Return the indices `A1 B1 C1 D1`, so that 
  A[A1] + A[B1] = A[C1] + A[D1]
  A1 < B1, C1 < D1
  A1 < C1, B1 != D1, B1 != C1 

2) If there are more than one solutions, 
   then return the tuple of values which are lexicographical smallest. 

Assume we have two solutions
S1 : A1 B1 C1 D1 ( these are values of indices int the array )  
S2 : A2 B2 C2 D2

S1 is lexicographically smaller than S2 iff
  A1 < A2 OR
  A1 = A2 AND B1 < B2 OR
  A1 = A2 AND B1 = B2 AND C1 < C2 OR 
  A1 = A2 AND B1 = B2 AND C1 = C2 AND D1 < D2
Example:

Input: [3, 4, 7, 1, 2, 9, 8]
Output: [0, 2, 3, 5] (O index)
If no solution is possible, return an empty list.

 
 *
 */

public class EqualSumInArray {
    public ArrayList<Integer> equal(ArrayList<Integer> A) {
        HashMap<Long, Quad> hash = new HashMap<>();
        Quad min = null;
        for (int i=0;i<A.size();i++){
            for (int j=i+1;j<A.size(); j++){
                long x = A.get(i);
                long y = A.get(j);
                long sum = x+y;
                if (hash.containsKey(sum)){
                    Quad q = hash.get(sum);
                    if (q.c == -1){
                        if ((i!=q.a) && (i!=q.b) &&(j!=q.b)){
                            q.c = i;
                            q.d = j;
                            if (min==null){
                                min=q;
                            } else {
                                min = minimum(min, q);
                            }
                        }
                        
                    }
                } else {
                    Quad q = new Quad();
                    q.a=i;
                    q.b=j;
                    hash.put(sum, q);
                }
            }
        }
        ArrayList<Integer> list = new ArrayList<>();
        if (min==null){
            return list;
        }
        list.add(min.a);
        list.add(min.b);
        list.add(min.c);
        list.add(min.d);
        
        return list;
    }
    
    public Quad minimum(Quad q1, Quad q2){
        if (q1.a<q2.a){
            return q1;
        } else if (q1.a>q2.a){
            return q2;
        } else if (q1.b<q2.b){
            return q1;
        } else if (q1.b>q2.b){
            return q2;
        } else if (q1.c<q2.c){
            return q1;
        } else if (q1.c>q2.c){
            return q2;
        } else if (q1.d<q2.d){
            return q1;
        } else {
            return q2;
        }
            
        
    }
    
    class Quad {
        int a=-1, b=-1, c=-1, d=-1;
    }
}
