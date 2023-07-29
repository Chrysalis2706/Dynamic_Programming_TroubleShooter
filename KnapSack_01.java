package Dynamic_Programming;
import java.util.Arrays;
public class KnapSack_01 {

    // Memoization
    public static int funMemo(int[] val, int[] wt, int W){
        int n = val.length;
        int[][] dp = new int[n][W+1];

        for (int i=0; i<n; i++){
            Arrays.fill(dp[i], -1);
        }

        return helper(n-1, W, val, wt, dp);
    }

    public static int helper(int n, int W, int[] val, int[] wt, int[][] dp){
        // base case
        if (n == 0){
            if (wt[n] <= W) return val[n];
            return 0;
        }

        if (dp[n][W] != -1) return dp[n][W];

        int take = Integer.MIN_VALUE;
        if (wt[n] <= W) take = val[n] + helper(n-1, W-wt[n], val, wt, dp);

        int notTake  = 0 + helper(n-1, W, val, wt, dp);

        return dp[n][W] = Math.max(take, notTake);
    }

    // Tabulation
    public static int funTab(int[] val, int[] wt, int W){
        int n = val.length;

        int[][] dp = new int[n+1][W+1];

        for (int i=0; i<n; i++){
            for (int j=0; j<dp[0].length; j++){
                if (i==0 || j==0) dp[i][j] = 0;
            }
        }

        for (int i=1; i<dp.length; i++){
            for (int j=1; j<dp[0].length; j++){
                if (wt[i-1] <= j){

                    // use -1 to avoid problem of unsorted array of wt
                    int take = val[i-1] + dp[i-1][j-wt[i-1]];
                    int notTake = 0 + dp[i-1][j];

                    dp[i][j] = Math.max(take, notTake);
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[n][W];
    }

    public static void main(String[] args) {
        int[] wt =  {2, 5, 1, 3, 4};
        int[] val = {15, 14, 10, 45, 30};
        int capacity = 7;

        System.out.println(funMemo(val, wt, capacity)); // 75
        System.out.println(funTab(val, wt, capacity));  // 75
    }
}
