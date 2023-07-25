package Dynamic_Programming;
import java.util.*;

public class TriangleProblem {

    // Memoization
    public static int minPathSum(int[][] grid){
        int n = grid.length;
        int m = grid[n-1].length;
        int[][] dp = new int[n][m];

        for (int i=0; i< dp.length; i++){
            Arrays.fill(dp[i], -1);
        }

        return minPathSumHelp(grid, 0, 0, dp, n);
    }
    public static int minPathSumHelp(int[][] grid, int i, int j, int[][] dp, int n){
        // base case
        if (i==n-1) return grid[i][j];

        if (dp[i][j] != -1) return dp[i][j];

        int down = grid[i][j] + minPathSumHelp(grid, i+1, j, dp, n);
        int diagonal_down = grid[i][j] + minPathSumHelp(grid, i+1, j+1, dp, n);

        return dp[i][j] = Math.min(down, diagonal_down);
    }

    // Tabulation
    public static int minTriTab(int[][] grid){
        int n = grid.length;
        int m = grid[n-1].length;
        int[][] dp = new int[n][m];

        // fill bottom same row
        for (int i=0; i<m; i++){
            dp[n-1][i] = grid[n-1][i];
        }

        for (int i=n-2; i>=0; i--){
            for (int j=i; j>=0; j--){
                // base case
                if (i==0) dp[i][j] = grid[i][j];

                int down = grid[i][j] + dp[i+1][j];
                int dg = grid[i][j] + dp[i+1][j+1];

                dp[i][j] = Math.min(down, dg);
            }
        }
        return  dp[0][0];
    }
    

    public static void main(String[] args) {
        int[][] triangle = {
                {1},
                {2, 3},
                {3, 6, 9},
                {8, 9, 6, 10}
        };

        System.out.println(minPathSum(triangle));
        System.out.println(minTriTab(triangle));



    }
}
