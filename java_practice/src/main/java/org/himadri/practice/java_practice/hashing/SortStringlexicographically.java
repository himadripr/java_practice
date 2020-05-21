package org.himadri.practice.java_practice.hashing;

/**
 * Sort strings lexicographically according to given sequence of charactersSubmissions: 7   
 * 
Given a string of lower case letters alphabets representing alphabetical order in an alien language. Sort the given list of words lexicographically according to that order.
Words will contain only those letters which are the in the alphabets string.

Input:
First line of input consists of number of test cases t. Then t test cases follow. In each test case first line consists of the string alphabets. Second line of test case consists of an integer n, representing the number of words to be sorted. Third line holds the n space separated strings which are to be sorted.

Output:
Output contains the n sorted strings in a single line separated by spaces.

Constraints:
Your task is to complete the function sort_by_order(), which sorts the words as explained. Do not print anything.

Example:
Input:
1
qwerty
5
we qwer erer qw errr
Output:
qw qwer we erer errr

 
 */

import java.util.*;

class SortStringlexicographically
{
    public String[] sortByOrder(String[] words, String arr )
    {
        // Write your code here
        HashMap<Character, Character> nom = new HashMap<>(); //new to org map
        HashMap<Character, Character> onm = new HashMap<>();
        
        for (int i=0;i<arr.length();i++){
            nom.put(arr.charAt(i), (char)('a'+i));
            onm.put((char)('a'+i), arr.charAt(i));
        }
        
        String[] temp = new String[words.length];
        for (int i=0; i<words.length; i++){
            char[] car = words[i].toCharArray();
            for (int j=0; j<car.length; j++){
                car[j] = nom.get(car[j]);
            }
            temp[i] = new String(car);
            
        }
        Arrays.sort(temp);
        for (int i=0; i<temp.length; i++){
            char[] car = temp[i].toCharArray();
            for (int j=0; j<car.length; j++){
                car[j] = onm.get(car[j]);
            }
            temp[i] = new String(car);
            
        }
        return temp;
        
    }
}