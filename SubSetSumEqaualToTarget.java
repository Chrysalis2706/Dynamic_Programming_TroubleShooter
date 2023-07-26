package Dynamic_Programming;

import java.util.Arrays;

public class SubSetSumEqaualToTarget {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        int target = 30;

        System.out.println(funMemo(arr, target));
        System.out.println(funTab(arr, target));
    }

    // Memoization
    public static boolean funMemo(int[] arr, int target) {
        int n = arr.length;
        int[][] dp = new int[n][target+1];

        //initialize with -1
        for (int i=0; i< dp.length; i++){
            Arrays.fill(dp[i], -1);
        }

//        // if target == 0 all true
//        for (int j=0; j<dp.length; j++){
//            dp[0][j] =  1;
//        }
//        // target and arr bot zero
//        dp[0][0] = 1;

        return helper(n-1, target, arr, dp);
    }

    public static boolean helper(int i, int target, int[] arr, int[][] dp){
        // base case
        if (i==0) return target==arr[i];
        if (target == 0) return true;

        //reuse
        if (dp[i][target] != -1) {
            if (dp[i][target] == 0) return false;
            else return true;
        }

        // store
        boolean take = false;
        if (arr[i] <= target){
            take = helper(i-1, target-arr[i], arr, dp);
        }
        boolean notTake = helper(i-1, target, arr, dp);

        if (notTake || take == true) dp[i][target] = 1;
        else dp[i][target] = 0;

        return notTake || take;
    }

    // Tabulation
    public static boolean funTab(int[] arr, int target){
        int n = arr.length;
        boolean[][] dp = new boolean[n][target+1];

        // row 1 = true as target == 0
        for (int j=0; j<target+1; j++){
            dp[0][j] = true;
        }

        if(arr[0] <= target) dp[0][arr[0]] = true;

        for (int i=1; i<dp.length; i++){
            for (int j=1; j<dp[0].length; j++){
                boolean take = false;
                if (arr[i] <= j) take = dp[i-1][j-arr[i]];

                boolean notTake = dp[i-1][j];

                dp[i][j] = take || notTake;
            }
        }
        return dp[n-1][target];
    }

}
