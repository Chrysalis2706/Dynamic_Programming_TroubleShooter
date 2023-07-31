package Dynamic_Programming;

import java.util.Arrays;

public class RodCutting {

    //Memoization
    public static int rodCutting(int[] prices, int L){
        int n = prices.length;
        int[] len = new int[n];
        for (int i=0; i<n; i++){
            len[i] = i;
        }

        // len == wt
        // prices == value
        // L == W

        int[][] dp = new int[n][L+1];
        for (int i=0; i<dp.length; i++){
            Arrays.fill(dp[i], -1);
        }

        return helper(n-1, L, prices, dp);
    }

    public static int helper(int ind, int L, int[] prices, int[][] dp){

        if (ind == 0){
            // length = 1
            return prices[0] * L;
        }

        if (dp[ind][L] != -1) return dp[ind][L];

        int notTake = 0 + helper(ind-1, L, prices, dp);
        int take = Integer.MIN_VALUE;
        int currlen = ind + 1;
        if (currlen <= L) take = prices[ind] + helper(ind, L-currlen, prices, dp);

        return dp[ind][L] = Math.max(take, notTake);
    }

    // Perform below method or else go with traditional method
    public static int rodCutTab(int[] prices, int L){
        int n = prices.length;
        int[][] dp = new int[n][L+1];

        // initialize dp array
        for (int j=0; j<L+1; j++){
            dp[0][j] = j*prices[0];
        }

        for (int i=1; i<dp.length; i++){
            for (int j=0; j<dp[0].length; j++){
                int notTake = 0 + dp[i-1][j];
                int take = Integer.MIN_VALUE;
                int currlen = i + 1;
                if (currlen <= j) take = prices[i] + dp[i][j-currlen];

                dp[i][j] = Math.max(take, notTake);
            }
        }
        return dp[n-1][L];
    }

    public static int rodCutTrad(int[] prices, int L){
        int n = prices.length;
        int[] len = new int[n];
        int k = 1;
        int ind = 0;
        while (ind<n){
            len[ind] = k;
            ind++;
            k++;
        }

//        for (int j=0; j<n; j++){
//            System.out.print(len[j] + " ");
//        }
        int[][] dp = new int[n+1][L+1];

        for (int i=0; i<n; i++){
            for (int j=0; j<L+1; j++){
                if (i==0 || j==0) dp[i][j] = 0;
            }
        }

        for (int i=1; i<dp.length; i++){
            for (int j=1; j<dp[0].length; j++){
                if (len[i-1] <= j){
                    int take = prices[i-1] + dp[i][j-len[i-1]];
                    int notTake = 0 + dp[i-1][j];

                    dp[i][j] = Math.max(take, notTake);
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[n][L];
    }



    public static void main(String[] args) {
        int[] prices = {2, 5, 7, 8, 10};
        int RodLength = 5;
        System.out.println(rodCutting(prices, RodLength));
        System.out.println(rodCutTab(prices, RodLength));
        System.out.println(rodCutTrad(prices, RodLength));
    }
}
