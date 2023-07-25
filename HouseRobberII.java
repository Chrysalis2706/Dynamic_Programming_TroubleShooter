package Dynamic_Programming;
import java.util.*;

public class HouseRobberII {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n==1) return nums[0];
        int[] temp1 = new int[n];
        int[] temp2 = new int[n];
        for (int i=0; i<n; i++){
            if (i!=0) temp1[i] = nums[i];
            if (i!=n-1) temp2[i] = nums[i];
        }
        int a1 = helper(temp1);
        int a2 = helper(temp2);

        return Math.max(a1, a2);
    }

    // TABULATON
    public int helper(int[] arr){
        int n = arr.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        int take = 0, notTake = 0;

        dp[0] = arr[0];
        for (int i=1; i<dp.length; i++){
            take = arr[i];
            if (i>1) take = take + dp[i-2];
            notTake = 0 + dp[i-1];
            dp[i] = Math.max(take, notTake);
        }
        return dp[n-1];
    }
}
