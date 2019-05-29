package org.himadri.practice.java_practice.array;

import java.util.*;

/**
 * 
 * Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.

 Note:
Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ≤ b ≤ c ≤ d)
The solution set must not contain duplicate quadruplets.
Example : 
Given array S = {1 0 -1 0 -2 2}, and target = 0
A solution set is:

    (-2, -1, 1, 2)
    (-2,  0, 0, 2)
    (-1,  0, 0, 1)
Also make sure that the solution set is lexicographically sorted.
Solution[i] < Solution[j] iff Solution[i][0] < Solution[j][0] OR (Solution[i][0] == Solution[j][0] AND ... Solution[i][k] < Solution[j][k])


 *
 */

public class Sum4 {
    public ArrayList<ArrayList<Integer>> fourSum(ArrayList<Integer> A, int B) {
        ArrayList<ArrayList<Integer>> ll = new ArrayList<>();
        if (A.size()<4){
            return ll;
        }
        HashMap<Integer, ArrayList<Pair>> map = new HashMap<>();
        
        HashSet<String> hash = new HashSet<>();
        int len = A.size();
        for (int i=0; i<len; i++){
            for (int j=i+1; j<len; j++){
                int sum = A.get(i)+A.get(j);
                Pair pair = new Pair(i, j);
                if (map.containsKey(sum)){
                    ArrayList<Pair> l = map.get(sum);
                    l.add(pair);
                    map.put(sum, l);
                } else {
                    ArrayList<Pair> l = new ArrayList<>();
                    l.add(pair);
                    map.put(sum, l);
                }
            }
        }
        
        for (int sum: map.keySet()){
            ArrayList<Pair> l1, l2;
            l1 = map.get(sum);
            l2 = map.get(B-sum);
            if (l2==null){
                continue;
            }
            for (int i=0;i<l1.size(); i++){
                Pair p1 = l1.get(i);
                for (int j=0; j<l2.size(); j++){
                    Pair p2 = l2.get(j);
                    if (p1.i!=p2.i && p1.i!=p2.j && p1.j!=p2.i && p1.j!=p2.j){
                        ArrayList<Integer> l = new ArrayList<>();
                        l.add(A.get(p1.i));
                        l.add(A.get(p1.j));
                        l.add(A.get(p2.i));
                        l.add(A.get(p2.j));
                        Collections.sort(l);
                        String s = "" + l.get(0)+l.get(1)+l.get(2)+l.get(3);
                        if (!hash.contains(s)){
                            ll.add(l);
                            hash.add(s);
                        }
                    }
                }
            }
        }
        
        Collections.sort(ll, new Comparator<ArrayList<Integer>>(){
            public int compare(ArrayList<Integer> l1, ArrayList<Integer> l2){
                for (int i=0; i<l1.size(); i++){
                    if (l1.get(i)<l2.get(i)){
                        return -1;
                    } else if (l1.get(i)>l2.get(i)){
                        return 1;
                    }
                }
                return 0;
            } 
        });
        
        return ll;
    }
    
    class Pair{
        int i, j;
        
        Pair(int a, int b){
            i=a;
            j=b;
        }
    }
}
