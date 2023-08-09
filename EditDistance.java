package Dynamic_Programming;

import java.util.Arrays;

public class EditDistance {

    public static int funMemo(String s1, String s2){
        int n = s1.length();
        int m = s2.length();
        int[][] dp = new int[n+1][m+1];
        for (int i=0; i<dp.length; i++) Arrays.fill(dp[i], -1);

        return helper(n, m, s1, s2, dp);
    }

    public static int helper(int i, int j, String s1, String s2, int[][] dp){
        // s1 is empty -> then delete all in s2
        if (i == 0) return j;
        // s2 is empty -> insert all in s1
        if (j == 0) return i;

        if (dp[i][j] != -1) return dp[i][j];

        if (s1.charAt(i-1) == s2.charAt(j-1)){
            // if matches then skip all chars
            return dp[i][j] =  helper(i-1, j-1, s1, s2, dp);
        }

        int delete = 1 +  helper(i-1, j, s1, s2, dp);
        int insert = 1 + helper(i, j-1, s1, s2, dp);
        int replace = 1 +  helper(i-1, j-1, s1, s2, dp);

        return dp[i][j] = Math.min(delete, Math.min(insert, replace));
    }

    public static void main(String[] args) {
        String s1 = "whgtdwhgtdg";
        String s2 = "aswcfg";

        // min operations to convert s1 to s2
        System.out.println(funMemo(s1, s2));
    }
}
