package Dynamic_Programming;

import java.util.Arrays;

public class DP_Part_5 {


    // MATRIX CHAIN MULTIPLICATION

    public static int MCM_Recursion(int[] arr, int i, int j){
        // single matrix in a set : base case
        if (i==j) return 0;

        int ans = Integer.MAX_VALUE;

        for (int k=i; k<j; k++){
            int cost1 = MCM_Recursion(arr, i, k);  // arr[i-1] * arr[k]
            int cost2 = MCM_Recursion(arr, k+1, j); // arr[k] * arr[j]
            int cost3 = arr[i-1] * arr[k] * arr[j];
            ans = Math.min(ans, cost1+cost2+cost3);
        }
        return ans;
    }

    // Using Memoization -> O(n^3)
    public static int MCM_memo(int[] arr, int i, int j, int[][] dp){
        int ans = Integer.MAX_VALUE;

        if (i==j) return 0; // single matrix

        for (int k=i; k<j; k++){
            int cost1 = MCM_memo(arr, i, k, dp); // arr[i-1] * arr[i] x arr[k-1] * arr[k] = arr[i-1] * arr[k]
            int cost2 = MCM_memo(arr, k+1, j, dp); // arr[k+1-1] * arr[k+1] x arr[j-1] * arr[j] = arr[k+1-1] * arr[j]
            int cost3 = arr[i-1] * arr[k] * arr[j];

            ans = Math.min(ans, cost1+cost2+cost3);
        }

        return dp[i][j] = ans;
    }



    // Minimum array partitioning
    public static int minSumPart(int[] arr){
        int n = arr.length;

        // calculate sum of arr
        int sum = 0;
        for (int i=0; i<arr.length; i++){
            sum += arr[i];
        }

        int W = sum/2;
        int[][] dp = new int[n+1][W+1];
        for (int i=0; i<dp.length; i++){
            Arrays.fill(dp[i], -1);
        }

        int sum1 = knapSackTab(arr, W);
        int sum2 = sum - sum1;

        return Math.abs(sum1 - sum2);
    }

    public static int knapSackTab(int[] arr, int W){
        int n = arr.length;
        int[][] dp = new int[n+1][W+1];

        for (int i=1; i< dp.length; i++){
            for (int j=1; j<dp[0].length; j++){
                // valid
                if (arr[i-1] <= j){
                    int take = arr[i-1] + dp[i-1][j-arr[i-1]];
                    int notTake = dp[i-1][j];
                    dp[i][j] = Math.max(take, notTake);
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[n][W];
    }

    public static int knapSack(int[] arr, int W, int[][] dp, int n){
        if (n==0 || W==0) return 0;

        // valid
        if (arr[n-1] <= W){
            int take = arr[n-1] + knapSack(arr, W-arr[n-1], dp, n-1);
            int notTake = knapSack(arr, W, dp, n-1);
            return dp[n][W] = Math.max(take, notTake);
        }else{
            return dp[n][W] = knapSack(arr, W, dp ,n-1);
        }
    }


    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 3};
        int n = arr.length;
        System.out.println(MCM_Recursion(arr, 1, n-1));


        int[][] dp = new int[n][n];
        for (int ind=0; ind<dp.length; ind++){
            Arrays.fill(dp[ind], -1);
        }
        System.out.println(MCM_memo(arr, 1, n-1, dp));

        int[] arr2 = {1, 6, 11, 5};
        System.out.println(minSumPart(arr2));
    }

    public static int frogJump(int n , int[] arr, int[] dp){
        if (n==0) return 0;
        if (dp[n] != -1) return dp[n];

        int oneJump = frogJump(n-1, arr, dp) + Math.abs(arr[n]-arr[n-1]);
        int twoJump = Integer.MAX_VALUE;
        if (n>1) {
            twoJump = frogJump(n-2, arr, dp) + Math.abs(arr[n]-arr[n-2]);
        }

        dp[n] = Math.min(oneJump, twoJump);
        return dp[n];
    }

    public static int frogJump(int[] arr, int n, int k){
        int[] dp = new int[n+1];
        dp[0] = 0;
        int minStep = 0;
        int jumps = 0;

        for (int i=1; i<n; i++){
            minStep = Integer.MAX_VALUE;
            for (int j=1; j<=k; j++){
                if (i-j >= 0) {
                    jumps = dp[i - j] + Math.abs(arr[i - j] - arr[i]);
                    minStep = Math.min(jumps, minStep);
                }
            }
            dp[i] = minStep;
        }
        return dp[n-1];
    }
}
