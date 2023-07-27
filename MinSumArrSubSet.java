package Dynamic_Programming;
import java.util.*;

public class MinSumArrSubSet {

    public static int fun(int[] arr){
        int n = arr.length;
        int sum = 0;
        for (int i=0; i<n; i++){
            sum += arr[i];
        }
        int W = sum/2;
        int[][] dp = new int[n+1][W+1];
        for (int i=0; i<n; i++){
            Arrays.fill(dp[i], -1);
        }

        int sum1 = helper(arr, n-1, W, dp);
        int sum2 = sum - sum1;

        return Math.abs(sum1 - sum2);
    }

    public static int helper(int[] arr, int n, int W, int[][] dp){
        // base case
        if (n==0 || W==0) return 0;

        if (dp[n][W] != -1) return dp[n][W];

        if (arr[n-1] <= W) { // always n-1 not n
            int take = arr[n-1] + helper(arr, n-1, W-arr[n-1], dp);
            int notTake = helper(arr, n-1, W, dp);

            return dp[n][W] = Math.max(take, notTake);
        }else {
            return dp[n][W] = helper(arr, n-1, W, dp);
        }

    }

    public static int funTab(int[] arr){
        int n = arr.length;
        int sum = 0;
        for (int i=0; i<n; i++){
            sum += arr[i];
        }

        int W = sum/2;

        int[][] dp = new int[n][W+1];

        for (int i=1; i<n; i++){
            for (int j=1; j<W+1; j++){
                if (arr[i-1] <= j){
                    int take = arr[i-1] + dp[i-1][j-arr[i-1]];
                    int notTake = dp[i-1][j];

                    dp[i][j] = Math.max(take, notTake);
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        int sum1 = dp[n-1][W];
        int sum2 = sum - sum1;

        return Math.abs(sum1 - sum2);
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        System.out.println(fun(arr));
        System.out.println(funTab(arr));
    }
}
