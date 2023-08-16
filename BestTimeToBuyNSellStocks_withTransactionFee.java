package Dynamic_Programming;

import java.util.Arrays;

public class BestTimeToBuyNSellStocks_withTransactionFee {
    // Tabulation
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n+1][2];
        int profit = 0;

        for (int i=n-1; i>=0; i--){
            for (int buy=0; buy<=1; buy++){
                // i can buy -> buy == 0
                if (buy == 0){
                    int op1 = -prices[i] + dp[i+1][1];
                    int op2 = 0 + dp[i+1][0];
                    profit = Math.max(op1, op2);
                }
                // i can NOT buy -> buy == 1
                if (buy == 1){
                    int op1 = prices[i] + dp[i+1][0];
                    // process completed
                    op1 = op1 - fee;
                    int op2 =  0 + dp[i+1][1];
                    profit = Math.max(op1, op2);
                }
                dp[i][buy] = profit;
            }
        }

        return dp[0][0];
    }

    // Memoization

    public static int solveMemo(int[] prices, int fee){
        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int i=0; i<n; i++){
            Arrays.fill(dp[i], -1);
        }

        return helper(0, 1, prices, dp, n, fee);
    }
    public static int helper(int ind, int buy, int[] prices, int[][] dp, int n, int fee){
        // base case
        if (ind >= n) return 0;

        if (dp[ind][buy] != -1) return dp[ind][buy];

        // i can buy
        if (buy == 1){
            int op1 =  -prices[ind] + helper(ind+1, 0, prices, dp, n, fee);
            int op2 = 0 + helper(ind+1, 1, prices, dp, n, fee);
            return dp[ind][buy] = Math.max(op1, op2);
        }else {
            // i can not buy

            // cool down of just after -> ind + 2
            int op1 = prices[ind] + helper(ind+1, 1, prices, dp, n, fee);
            // process completed
            op1 = op1 - fee;
            int op2 = 0 + helper(ind+1, 0, prices, dp, n, fee);
            return dp[ind][buy] = Math.max(op1, op2);
        }
    }

    public static void main(String[] args) {
        int[] prices = {1,3,2,8,4,9};
        int fee = 2;
        System.out.println(solveMemo(prices, fee));
    }
}
