package org.himadri.practice.java_practice.dynamic_programming;

//https://www.geeksforgeeks.org/count-subsequences-product-less-k/

public class CountAllSubSeqHavingProdLessThanP {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = {1,2,3,4};
		int n=arr.length;
		System.out.println(f(arr, n, 10));

	}
	
	static int f(int arr[], int n , int prod){
		int[][] dp = new int[prod+1][n+1];
		for (int i=1; i<=prod; i++){
			for (int j=1; j<=n; j++){
				dp[i][j] = dp[i][j-1];
				if (arr[j-1]<=i && arr[j-1]>0){
					dp[i][j] += dp[i/arr[j-1]][j-1]+1;
				}
			}
		}
		return dp[prod][n];
		
	}

}
