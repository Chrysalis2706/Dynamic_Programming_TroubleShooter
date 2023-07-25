package Dynamic_Programming;

public class DP_Part_4 {
    // Longest Common Substring : based on LCS
    public static int longestCommonSubstring(String s1, String s2){
        int n = s1.length();
        int m = s2.length();

        // initialize just like LCS
        int[][] dp = new int[n+1][m+1];
        for (int i=0; i<dp.length; i++){
            for (int j=0; j<dp[0].length; j++){
                if (i==0 || j==0) dp[i][j] = 0;
            }
        }
        int ans = 1;
        // same loop as LCS
        for (int i=1; i<dp.length; i++){
            for (int j=1; j<dp[0].length; j++){
                // same char = same logic
                if (s1.charAt(i-1) == s2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else{
                    // make dp[i][j] = 0
                    dp[i][j] = 0;
                }
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String s1 = "ABCDE";
        String s2 = "ABGCE";
        System.out.println(longestCommonSubstring(s1, s2));
    }
}
