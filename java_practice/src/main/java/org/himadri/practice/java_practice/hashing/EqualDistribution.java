package org.himadri.practice.java_practice.hashing;

/**
 * Equal DistributionSubmissions: 18   Accuracy: 38.88%   Max. Score: 100
Problems
Nancy has N boxes which contains some chocolates. She wants to distribute maximum number of chocolates equally among her K friends by selecting a consecutive sequence of boxes . Help Nancy in finding the maximum number of chocolates she can give to each of her friend.
Note: She cannot cut a chocolate into pieces, and every friend should receive equal number of chocolates
Input:
First line of input contains T number of test cases. For each test case, there will be 2 lines. First line contains two space seperated integers N and K . N denotes the number of boxes and K denotes number of Nancy's friends. Second line contains N space separated integers denoting number of chocolates in each box.
Output:
For each test case , print the maximum number of chocolates she can give to each of her friend.
Your Task:
Since it is a functional problem , you don't need to take input just complete the  function maxNumOfChocolates() which accepts an array arr of integer , size of the array n, integer value k  and returns maximum number of chocolates she can give to each of her friend.
Constraints:
1 <= T <= 30
2 <= N <= 104
1 <= K <= 103
1 <= arr[i] <= 100
Example:
Input:
2
6 3
2 7 6 1 4 5
4 2
1 2 3 4
Output:
6
5
Explanation:
Testcase1: The subarray is 7, 6, 1, 4 with sum 18 and Equal distribution of 18 chocolates among 3 friends is 6.


 * 
 *
 */

class EqualDistribution
{
// Function to find the maximum number of chocolates 
// to be distributed equally among k students 
	public static void main(String[] args) {
		int a[] = {2, 7, 6, 1, 4, 5};
		maxNumOfChocolates(a, a.length, 3);
	}
	
    static int maxNumOfChocolates(int arr[], int n, int k) 
    { 
        // code here
        int[] presum = new int[n+1];
        presum[0]=0;
        for (int i=1; i<=n; i++){
            presum[i] = presum[i-1]+arr[i-1];
        }
        if (presum[n]%k==0){
            return (int)(presum[n]/k);
        }
        int max = -1;
        for (int i=n-1; i>=1; i--){
            for (int j=i; j<=n; j++){
                int sum = presum[j]-presum[j-i];
                
                if (sum%k==0){
                    int t = sum/k;
                    if (t>max){
                        max = t;
                    }
                }
            }
        }
        return max;
    } 

}