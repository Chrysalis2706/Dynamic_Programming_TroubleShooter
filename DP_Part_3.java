package Dynamic_Programming;

import java.util.Arrays;

public class DP_Part_3 {

    //Unbounded Knapsack : coinChange O(n*sum)
    public static int coinChange(int[] coins, int sum){
        int n = coins.length;
        int[][] dp = new int[n+1][sum+1];

        //initialize 1st col = 0
        for (int i=0; i<dp.length; i++){
            dp[i][0] = 1;
        }

        for (int i=1; i< dp.length; i++){
            for (int j=1; j<dp[0].length; j++){
                int val = coins[i-1];
                //valid
                if (val <= j){
                    //valid + include
                    int taken = dp[i][j-val];
                    //valid + exclude
                    int notTaken = dp[i-1][j];

                    dp[i][j] = taken + notTaken;
                }else{
                    // not valid
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[n][sum];
    }


    // Rod Cutting - Unbounded Knapsack variation
    public static int rodCutting(int[] prices, int[] length, int rodLength){
        int n = prices.length;
        int[][] dp = new int[n+1][rodLength+1];

        // n+1 loop
        for (int i=1; i<dp.length; i++){
            // rodLength+1 loop
            for (int j=1; j<dp[0].length; j++){
                int price = prices[i-1];
                int len = length[i-1];
                //valid
                if (len <= j){
                    //valid + include
                    int taken = price + dp[i][j-len]; //unbounded problem
                    //valid + exclude
                    int notTaken = dp[i-1][j];

                    dp[i][j] = Math.max(taken, notTaken);
                }else{
                    //not valid + exclude
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[n][rodLength];
    }

    // Longest Common Subsequence : Recursion -> O(2^n+m)
    public static int lcs_recursion(String str1, String str2, int n, int m){
        // base case
        if (n==0 || m==0) return 0;

        // same case - no choice only skewed
        if (str1.charAt(n-1) == str2.charAt(m-1)){
            return lcs_recursion(str1, str2, n-1, m-1) + 1;
        }else {
            // two choices : Max out of two sequences
            int len1 = lcs_recursion(str1, str2, n - 1, m);
            int len2 = lcs_recursion(str1, str2, n, m - 1);

            return Math.max(len1, len2);
        }
    }

    // Longest Common Subsequence : Recursion+Memoization -> O(n*m)
    public static int lcs_memo(String str1, String str2, int n, int m, int[][] dp){
        // base case
        if (n==0 || m==0) return 0;

        //initialize with -1
        for (int i=0; i<dp.length; i++){
            Arrays.fill(dp[i], -1);
        }

        //reuse if stored
        if (dp[n][m] != -1) return dp[n][m];

        //checking same condition + storing
        if (str1.charAt(n-1) == str2.charAt(m-1)) {
            dp[n][m] = lcs_memo(str1, str2, n-1, m-1, dp) + 1;
            return dp[n][m];
        }else{
            //checking for different condition + storing
            int len1 = lcs_memo(str1, str2, n-1, m, dp);
            int len2 = lcs_memo(str1, str2, n, m-1, dp);
            dp[n][m] = Math.max(len1, len2);
            return dp[n][m];
        }
    }

    // Longest Common Subsequence : Tabulation -> O(n*m)
    public static int lcs_tab(String str1, String str2){
        int n = str1.length();
        int m = str2.length();

        int[][] dp = new int[n+1][m+1];

        //base case conversion to initialize
        for (int i=0; i< dp.length; i++){
            for (int j=0; j<dp[0].length; j++){
                if (i==0 || j==0) dp[i][j] = 0;
            }
        }

        //n+1 loop
        for (int i=1; i< dp.length; i++){
            // m+1 loop
            for (int j=1; j<dp[0].length; j++){
                // same char
                if (str1.charAt(i-1) == str2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1]+1; // updating
                }else{
                    int len_up = dp[i-1][j];
                    int len_left = dp[i][j-1];
                    dp[i][j] = Math.max(len_left, len_up);
                }
            }
        }
        return dp[n][m];
    }


    public static void main(String[] args) {
        int[] coins = {1, 2, 3};
        int sum = 4;

        int[] coins2 = {2, 5, 3, 6};
        int sum2 = 10;

        System.out.println("Unbounded Knap Sack Variation :\n1. Total ways of coin change = " + coinChange(coins, sum) + "\n2. Total ways of coin change = " + coinChange(coins2, sum2));

        int[] prices = {1, 5, 8, 9, 10, 17, 17, 20};
        int[] length = {1, 2, 3, 4,  5,  6,  7,  8};
        int maxLength = 8;
        System.out.println("\nUnbounded Knapsack variation -\nRod Cutting  :- " + rodCutting(prices, length, maxLength));

        String str1 = "abcde";
        String str2 = "ace";
        int n = str1.length();
        int m = str2.length();
        System.out.println("\nLongest Common subsequence - Recursion : " + lcs_recursion(str1, str2, n, m));
        System.out.println("Longest Common subsequence - (DP) Recursion + Memoization : " + lcs_memo(str1, str2, n, m, new int[n+1][m+1]));
        System.out.println("Longest Common subsequence - (DP) Tabulation : " + lcs_tab(str1, str2));

    }
}
