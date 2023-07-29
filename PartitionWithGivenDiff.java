package Dynamic_Programming;

import java.util.Arrays;

public class PartitionWithGivenDiff {

    public static int funMemo(int[] arr, int d){
        int n = arr.length;
        int sum = 0;
        for (int i=0; i<n; i++){
            sum += arr[i];
        }

        int target = (sum-d)/2;

        if (sum-d < 0) return 0;
        if ((sum-d)%2 != 0) return 0;

        int[][] dp = new int[n][target+1];
        for (int i=0; i<n; i++){
            Arrays.fill(dp[i], -1);
        }

        int count = helper(n-1, target, arr, dp);
        return count;
    }

    // just like count subset with target
    public static int helper(int n, int target, int[] arr, int[][] dp){
        // base case
        if (n==0){
            if (target==0 && arr[0] == 0) return 2;
            if (target == 0 || arr[0] == target) return 1;
            return 0;
        }

        if (dp[n][target] != -1) return dp[n][target];

        int take = 0;
        if (target >= arr[n]) take += helper(n-1, target-arr[n], arr, dp);
        int notTake = helper(n-1, target, arr, dp);

        dp[n][target] = take + notTake;

        return dp[n][target];
    }

    public static void main(String[] args) {
        int[] arr = {2, 2, 2, 2};
        int diff = 0;
        System.out.println(funMemo(arr, diff));
    }
}
