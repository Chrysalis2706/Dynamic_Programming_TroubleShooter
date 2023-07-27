package Dynamic_Programming;

import java.util.Arrays;

public class CountSubsetWithSumK {

    // Recursion
    public static int funRec(int[] arr, int k, int n){
        if (k==0) return 1;
        if (n==0) {
            if (arr[0] == k) return 1;
            else return 0;
        }

        int take = 0;
        if (arr[n] <= k) take = funRec(arr, k-arr[n], n-1);
        int notTake = funRec(arr, k, n-1);

        return take + notTake;
    }

    // Memoization
    public static int funMemo(int[] arr, int n, int k, int[][] dp){
        if (n==0){
            if (arr[0] == k) return 1;
            else return 0;
        }

        if (k==0) return 1;

        if (dp[n][k] != -1) return dp[n][k];

        int take = 0;
        if (arr[n] <= k) take = funMemo(arr, n-1, k-arr[n], dp);

        int notTake = funMemo(arr, n-1, k, dp);

        return  dp[n][k] = take + notTake;
    }


    public static int funTab(int[] arr, int target){
        int n = arr.length;
        int[][] dp = new int[n][target+1];

        // initialize array

        // when k == 0 1st row all ONE
        for (int i=0; i<n; i++){
            dp[i][0] = 1;
        }

        if (arr[0] <= target) {
            dp[0][arr[0]] = 1;
        }

        for (int i=1; i<dp.length; i++){
            for (int j=1; j<dp[0].length; j++){
                int take = 0;
                if (arr[i] <= j) take = dp[i-1][j-arr[i]];
                int notTake = dp[i-1][j];

                dp[i][j] = take + notTake;
            }
        }
        return dp[n-1][target];
    }


    public static void main(String[] args) {
        int[] arr = {1 , 2, 2, 3};
        int K = 3;
        int n = arr.length;
        System.out.println(funRec(arr, K, n-1));

        int[][] dp = new int[n][K+1];
        for (int i=0; i<dp.length; i++){
            Arrays.fill(dp[i], -1);
        }

        System.out.println(funMemo(arr, n-1, K, dp));

        System.out.println(funTab(arr, K));
    }
}
