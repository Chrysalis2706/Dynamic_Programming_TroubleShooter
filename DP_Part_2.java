package Dynamic_Programming;

import java.sql.Array;
import java.util.Arrays;

public class DP_Part_2 {
    // 0-1 Knapsack

    // Recursion
    public static int knapSackRec(int[] val, int[] wt, int W, int n){
        //Base case
        if (n==0 || W==0) return 0;

        //Valid choice
        if (wt[n-1] <= W){
            // Include
            int taken = val[n-1] + knapSackRec(val, wt, W-wt[n-1], n-1);
            // Excluded
            int notTaken = knapSackRec(val, wt, W, n-1);

            // Max of both
            return Math.max(taken, notTaken);
        }else /*Not valid*/{
             return knapSackRec(val, wt, W, n-1);
        }
    }

    // DP : Memoization
    public static int knapSackMemo(int[] val, int[] wt, int W, int n, int[][] dp){
        if (W == 0 || n == 0) return 0;

        // reuse
        if (dp[n][W] != -1)  return dp[n][W];

        //Valid
        if (wt[n-1] <= W){
            //include and add value
            int taken = val[n-1] + knapSackMemo(val, wt, W-wt[n-1], n-1, dp);
            //exclude
            int notTaken = knapSackMemo(val, wt, W, n-1, dp);

            //store
            dp[n][W] = Math.max(taken, notTaken);
            return dp[n][W];
        }else{
            // Not valid
            //store
            dp[n][W] = knapSackMemo(val, wt, W, n-1, dp);
            return dp[n][W];
        }
    }

    //DP : Tabulation
    public static int knapSackTab(int[] val, int[] wt, int W){
        int n = val.length;
        int[][] dp = new int[n+1][W+1];

        // make zeroth row and column zero
        for (int i=0; i<dp.length; i++){
            dp[i][0] = 0;
        }
        for (int i=0; i<dp[0].length; i++){
            dp[0][i] = 0;
        }

        //n - loop
        for (int i=1; i<dp.length; i++){
            // W - loop
            for (int j=1; j<dp[0].length; j++){
                int v = val[i-1];
                int w = wt[i-1];
                //valid
                if (w <= j){
                    dp[i][j] = Math.max(v + dp[i-1][j-w], dp[i-1][j]);
                }
                /*invalid*/ else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[n][W];
    }


    // Target Sum Subset
    public static boolean targetSum(int[] arr, int sum){
        int n = arr.length;
        boolean[][] dp = new boolean[n+1][sum+1];

        // when sum zero then always true
        for (int i=0; i<dp.length; i++){
            dp[i][0] = true;
        }

        // same like 01 Knapsack
        for (int i=1; i<dp.length; i++){
            for (int j=1; j<dp[0].length; j++){
                int val = arr[i-1];
                //include
                if (val <= j && dp[i-1][j-val] == true){
                    dp[i][j] = true;
                }else if(dp[i-1][j] == true){
                    dp[i][j] = true;
                }
            }
        }
        return dp[n][sum];
    }


    // Unbounded knapsack : valid + include : i not i-1
    public static int unboundedKnapSacak(int[] val, int[] wt, int W){
        int n = val.length;
        int[][] dp = new int[n+1][W+1];

        // initialize 0th indices
        for (int i=0; i<dp.length; i++){
            Arrays.fill(dp[i], 0);
        }

        //n+1 loop
        for (int i=1; i< dp.length; i++){
            // W+1 loop
            for (int j=1; j<dp[0].length; j++){
                //valid
                int value = val[i-1];
                int weight = wt[i-1];
                if (weight <= j){
                    //valid + include
                    int taken = value + dp[i][j-weight];
                    //valid + exclude
                    int notTaken = dp[i-1][j];
                    dp[i][j] = Math.max(taken, notTaken);
                }else{
                    //not valid + exlude
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[n][W];
    }

    public static void main(String[] args) {
        int[] val = {15, 14, 10, 45, 30};
        int[] wt =  {2,  5,  1,  3,  4};
        int capacity = 7;
        int n = val.length;

        int ans = knapSackRec(val, wt, capacity, n);
        System.out.println("O(2^n) -> Recursion :" + ans);

        int[][] dp = new int[n+1][capacity+1];

        for (int i=0; i<dp.length; i++){
            Arrays.fill(dp[i], -1);
        }
        int dpAns = knapSackMemo(val, wt, capacity, n, dp);
        System.out.println("O(n*W) -> DP(memoization) : " + dpAns);

        System.out.println("O(n*W) -> DP(Tabulation) : " + knapSackTab(val, wt, capacity));


        int[] arr = {4, 2, 7, 1, 3};
        int sum = 10;
        System.out.println("\nTarget Sum problem\nO(n*targetSum) -> DP(Tabulation) : " + targetSum(arr, sum));


        System.out.println("\nUnbounded Knapsack\nO(n*W) -> DP(Tabulation) : " + unboundedKnapSacak(val, wt, capacity));
    }
}
