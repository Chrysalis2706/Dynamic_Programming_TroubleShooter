package Dynamic_Programming;

import java.util.Arrays;

public class BestTimeToBuyNSellStocks_II {

    public static int solve(int[] prices){
        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int i=0; i<dp.length; i++){
            Arrays.fill(dp[i], -1);
        }

        return helper(0, 1, prices, dp, n);
    }

    public static int helper (int ind, int buy, int[] prices, int[][] dp, int n){
        // base case
        if (ind == n) return 0;

        // can buy
        if (buy == 1){
            // included
            int op1 = -prices[ind] + helper(ind+1, 0, prices, dp, n);
            // not included
            int op2 = 0 + helper(ind+1, 1, prices, dp, n);

            return dp[ind][buy] = Math.max(op1, op2);
        } else {
            // can not buy

            // include
            int op1 = prices[ind] + helper(ind+1, 1, prices, dp, n);
            // not include
            int op2 = 0 + helper(ind+1, 0, prices, dp, n);

            return dp[ind][buy] = Math.max(op1, op2);
        }

    }

    public static int solveTab(int[] Arr){
        int n = Arr.length;
        int[][] dp = new int[n+1][2];

        // base case
        dp[n][1] = 0;
        dp[n][0] = 0;
        int profit = 0 ;

        for(int ind= n-1; ind>=0; ind--){
            for(int buy=0; buy<=1; buy++){
                if(buy==0){// We can buy the stock
                    profit = Math.max(0+dp[ind+1][0], -Arr[ind] + dp[ind+1][1]);
                }

                if(buy==1){// We can sell the stock
                    profit = Math.max(0+dp[ind+1][1], Arr[ind] + dp[ind+1][0]);
                }

                dp[ind][buy]  = profit;
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6 ,4};
        System.out.println(solve(prices));
        System.out.println(solveTab(prices));
    }
}
