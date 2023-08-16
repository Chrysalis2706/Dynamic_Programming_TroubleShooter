package Dynamic_Programming;

import java.util.Arrays;

public class BestTimeToBuyNSellStocks_withCoolDown {

    public static int solveMemo(int[] prices){
        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int i=0; i<n; i++){
            Arrays.fill(dp[i], -1);
        }

        return helper(0, 1, prices, dp, n);
    }
    public static int helper(int ind, int buy, int[] prices, int[][] dp, int n){
        // base case
        if (ind >= n) return 0;

        if (dp[ind][buy] != -1) return dp[ind][buy];

        // i can buy
        if (buy == 1){
            int op1 =  -prices[ind] + helper(ind+1, 0, prices, dp, n);
            int op2 = 0 + helper(ind+1, 1, prices, dp, n);
            return dp[ind][buy] = Math.max(op1, op2);
        }else {
            // i can not buy

            // cool down of just after -> ind + 2
            int op1 = prices[ind] + helper(ind+2, 1, prices, dp, n);
            int op2 = 0 + helper(ind+1, 0, prices, dp, n);
            return dp[ind][buy] = Math.max(op1, op2);
        }
    }

    // Tabulation
    public int maxProfit(int[] prices) {
        int n = prices.length;
        // size is imp
        int[][] dp = new int[n+2][2];

        // tab -> n-1 to 0
        for (int i=n-1; i>=0; i--){
            for (int buy = 0; buy <= 1; buy++){
                int profit = 0;
                // buy == 0 -> i can buy
                if (buy == 0){
                    profit = Math.max(-prices[i] + dp[i+1][1], 0 + dp[i+1][0]);
                }
                // buy == 1 -> i can not buy
                if (buy == 1){
                    profit = Math.max(prices[i] + dp[i+2][0], 0 + dp[i+1][1]);
                }

                dp[i][buy] = profit;
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        int[] prices = {1,2,3,0,2};
        System.out.println(solveMemo(prices));
    }
}
