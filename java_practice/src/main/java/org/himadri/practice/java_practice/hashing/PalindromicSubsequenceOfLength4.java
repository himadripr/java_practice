package org.himadri.practice.java_practice.hashing;

public class PalindromicSubsequenceOfLength4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int ans = shortPalindrome("ababab");

	}
	
	// Complete the shortPalindrome function below.
    static int shortPalindrome(String s) {
        long mod = 1000000007l;
        int len = s.length();
        long [][]prefix = new long[26][len];
        long [][]suffix = new long[26][len];
        preCompute(prefix, suffix, s);
        long ans=0;

        for (int i=1; i<len-1; i++){
            for (int j=i+1; j<len-1; j++){
                if (s.charAt(i)==s.charAt(j)){
                    for (int k=0; k<26; k++){
                        ans += ((prefix[k][i-1]%mod) *(suffix[k][j+1]%mod) ) %mod;
                    }
                }
            }
        }
        return (int)ans;
    }

    static void preCompute(long [][]prefix, long [][]suffix, String s){
        int len = s.length();
        char []arr = s.toCharArray();
        //computing prefix sum matrix
        prefix[arr[0]-'a'][0]++;
        //i is the column
        for (int i=1; i<len; i++){
            for (int j=0; j<26; j++){
                prefix[j][i] = prefix[j][i]+prefix[j][i-1];
            }
            prefix[arr[i]-'a'][i]++;
        }

        //computing suffix sum matrix
        suffix[arr[len-1]-'a'][len-1]++;
        for (int i=len-2; i>=0; i--){
            for (int j=0; j<26; j++){
                suffix[j][i] = suffix[j][i]+suffix[j][i+1];
            }
            suffix[arr[i]-'a'][i]++;
        }

    }

}
