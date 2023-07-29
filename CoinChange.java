package Dynamic_Programming;

import java.util.Arrays;

public class CoinChange {

    // Memoization
    public static int change(int amount, int[] coins){
        // base case
        int n = coins.length;
        int ind = n-1;

        int[][] dp = new int[n][amount+1];
        for (int i=0; i< dp.length; i++){
            Arrays.fill(dp[i], -1);
        }
        return helper(ind, amount, coins, dp);
    }

    public static int helper(int ind, int amount, int[] coins, int[][] dp){
        if (ind == 0){
            if (amount % coins[ind] == 0) return 1;
            else return 0;
        }

        if (dp[ind][amount] != -1) return dp[ind][amount];

        int notTake = helper(ind-1, amount, coins, dp);
        int take = 0;
        if (coins[ind] <= amount){
            take = helper(ind, amount-coins[ind], coins, dp);
        }

        return dp[ind][amount] =  take + notTake;
    }

    public static int changeTab(int T, int[] coins){
        int n = coins.length;
        int[][] dp = new int[n][T+1];

        // base case
        for (int t=0; t<T+1; t++){
            if (t % coins[0] == 0) dp[0][t] = 1;
            else dp[0][t] = 0;
        }

        for (int i=1; i<dp.length; i++){
            for (int j=0; j<dp[0].length; j++){ // j is T at each Column
                int take = 0;
                if (coins[i] <= j) take = dp[i][j-coins[i]];
                int notTake = dp[i-1][j];

                dp[i][j] = take + notTake;
            }
        }
        return dp[n-1][T];
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 3};
        int amount = 4;
        System.out.println(change(amount, coins));
        System.out.println(changeTab(amount, coins));

    }
}
