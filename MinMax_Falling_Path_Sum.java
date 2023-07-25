package Dynamic_Programming;

import com.sun.jdi.ArrayReference;

import java.util.Arrays;

public class MinMax_Falling_Path_Sum {

    public static int minMaxHelper(int i, int j, int[][] grid, int[][] dp, int m){
        // base case
        if (j < 0 || j >= m) return (int)Math.pow(-10,9);
        if (i == 0) return grid[i][j];

        if (dp[i][j] != -1) return dp[i][j];

        int up = grid[i][j] + minMaxHelper(i-1, j, grid, dp, m);
        int left_up = grid[i][j] + minMaxHelper(i-1, j-1, grid, dp, m);
        int right_up = grid[i][j] + minMaxHelper(i-1, j+1, grid, dp, m);

        return dp[i][j] = Math.max(up, Math.max(left_up, right_up));
    }


    public static int minMax(int[][] grid){
        int n = grid.length;
        int m = grid[0].length;

        int max = Integer.MIN_VALUE;

        int[][] dp = new int[n][m];
        for (int i=0; i<n; i++){
            Arrays.fill(dp[i], -1);
        }

        // run loop for all the values of j
        for (int j=0; j<m; j++){
            int ans = minMaxHelper(n-1, j, grid, dp, m);
            max = Math.max(ans, max);
        }

        display(dp);
        return max;
    }

    public static void display(int[][] dp){
        for (int i=0; i< dp.length; i++){
            for (int j=0; j<dp[0].length; j++){
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int minMaxTab(int[][] grid){
        int n = grid.length;
        int m = grid[0].length;

        int[][] dp = new int[n][m];

        // create first row same
        for (int j=0; j<m; j++){
            dp[0][j] = grid[0][j];
        }

        // run loop for all the rows
        for (int i=1; i<n; i++){
            for (int j=0; j<m; j++){
                int up = grid[i][j] + dp[i-1][j];

                int left_up = grid[i][j];
                if (j-1>=0){
                    left_up += dp[i-1][j-1];
                }else{
                    left_up += Integer.MIN_VALUE;
                }

                int right_up = grid[i][j];
                if (j+1<m){
                    right_up += dp[i-1][j+1];
                }else{
                    right_up += Integer.MIN_VALUE;
                }

                dp[i][j] = Math.max(up, Math.max(left_up, right_up));
            }
        }

        // check the last row to be max
        int max = Integer.MIN_VALUE;
        for (int j=0; j<dp[0].length; j++){
            max = Math.max(max, dp[n-1][j]);
        }

        return max;
    }

    public static void main(String[] args) {
        int[][] grid = {
                {1,2,10,4},
                {100,3,2,1},
                {1,1,20,2},
                {1,2,2,1}
        };
        int n = grid.length;
        int m = grid[0].length;

        System.out.println(minMax(grid));
        System.out.println(minMaxTab(grid));
    }
}
