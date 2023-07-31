package Dynamic_Programming;
import java.util.*;

public class LongestCommonSubsequence {

    public static int LCSMemo(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }

//        // s1 is null
//        for (int i=0; i<m; i++){
//            dp[0][i] = 0;
//        }
//
//        // s2 is null
//        for(int i=0; i<n; i++){
//            dp[i][0] = 0;
//        }

        return helper(n, m, s1, s2, dp);
    }

    public static int helper(int n, int m, String s1, String s2, int[][] dp){
        if (n==0 || m==0) return 0;

        if (dp[n][m] != -1) return dp[n][m];

        if (s1.charAt(n-1) == s2.charAt(m-1)){
            return dp[n][m] = 1 + helper(n-1, m-1, s1, s2, dp);
        }else{
            int len1 = helper(n-1, m, s1, s2, dp);
            int len2 = helper(n, m-1, s1, s2, dp);
            return dp[n][m] = Math.max(len1, len2);
        }
    }

    public static int LCSTab(String s1, String s2){
        int n = s1.length();
        int m = s2.length();

        int[][] dp = new int[n+1][m+1];

        // initialize row and col to be 0 where s1 ans s2 are 0
        for (int i=0; i<n+1; i++){
            for (int j=0; j<m+1; j++){
                if (i==0 || j==0) dp[i][j] = 0;
            }
        }

        for (int i=1; i<dp.length; i++){
            for (int j=1; j<dp[0].length; j++){
                // same char
                if (s1.charAt(i-1) == s2.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }else{
                    int len1 = dp[i-1][j];
                    int len2 = dp[i][j-1];
                    dp[i][j] = Math.max(len1, len2);
                }
            }
        }
        return dp[n][m];
    }

    public static void main(String[] args) {
        String s1 = "abcde";
        String s2 = "ace";
        System.out.println(LCSMemo(s1, s2));
        System.out.println(LCSTab(s1, s2));
    }
}
