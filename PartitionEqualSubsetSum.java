package Dynamic_Programming;
import java.util.*;

public class PartitionEqualSubsetSum {
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int i=0; i<n; i++){
            sum += nums[i];
        }

        // if sum is odd then can not be devided
        if (sum%2 != 0) return false;

        int target = sum/2;

        // initialize dp
        int[][] dp = new int[n][target+1];

        for (int i=0; i<n; i++){
            Arrays.fill(dp[i], -1);
        }

        // for (int j=0; j<target+1; j++){
        //     dp[0][j] = 1;
        // }

        // same as subset sum eqaul to k
        return helper(n-1, target, nums, dp);
    }

    public boolean helper(int i, int target, int[] arr, int[][] dp){
        // n-1 -> 0

        // base case
        if (i==0) return arr[i] == target;
        if (target == 0) return true;

        // reuse
        if (dp[i][target] != -1){
            if (dp[i][target] == 1) return true;
            else return false;
        }

        boolean take = false;
        if (arr[i] <= target) {
            take = helper(i-1, target-arr[i], arr, dp);
        }

        boolean notTake = helper(i-1, target, arr, dp);

        if (notTake || take) dp[i][target] = 1;
        else dp[i][target] = 0;

        return notTake || take;
    }
}
