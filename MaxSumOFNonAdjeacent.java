package Dynamic_Programming;
import java.util.*;

// House Robber on Leetcode

public class MaxSumOFNonAdjeacent {

    // MEMOIZATION
    public int rob(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n+1];
        Arrays.fill(dp, -1);
        return helper(n-1, nums, dp);
    }

    public int helper(int ind, int[] arr,  int[] dp){
        if (ind == 0) return arr[ind];
        if (ind < 0) return 0;

        if (dp[ind] != -1) return dp[ind];

        int pick = arr[ind] + helper(ind-2, arr, dp);
        int notPick = 0 + helper(ind-1, arr, dp);

        dp[ind] = Math.max(pick, notPick);
        return dp[ind];
    }

    // TABULATION
    public int robTab(int[] nums){
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        dp[0] = nums[0];
        int take = 0;
        int notTake = 0;

        for (int i=1; i<dp.length; i++){
            take = nums[i];
            if (i>1) {
                take =  take + dp[i-2];
            }
            notTake = 0 + dp[i-1];

            dp[i] = Math.max(take, notTake);
        }
        return dp[n-1];
    }

}
