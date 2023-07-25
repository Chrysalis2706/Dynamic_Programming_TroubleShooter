package Dynamic_Programming;
import java.util.*;

public class UniqueGridPaths {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m+1][n+1];
        for (int i=0; i<dp.length; i++){
            Arrays.fill(dp[i], -1);
        }
        return solve(m-1, n-1, dp);
    }

    public int solve(int row, int col, int[][] dp){
        if (row == 0 && col == 0) return 1;
        if (row < 0 || col < 0) return 0;

        if (dp[row][col] != -1) return dp[row][col];

        int left = solve(row, col-1, dp);
        int up = solve(row-1, col, dp);

        dp[row][col] = left + up;

        return dp[row][col];
    }

}
