package Dynamic_Programming;
import java.util.*;

public class ShortestCommonSuperSequence {

    // MEMOIZATION

    public static int SCS_memo(String s1, String s2){
        // base case
        int n = s1.length();
        int m = s2.length();
        int[][] dp = new int[n+1][m+1];
        for (int i=0; i<dp.length; i++){
            Arrays.fill(dp[i], -1);
        }

        int lcs = lcs(n, m, s1, s2, dp);
        // scs = (n-lcs) + (m-lcs) + lcs
        return  (n+m) - lcs;
    }

    public static int lcs(int ind1, int ind2, String s1, String s2, int[][] dp){
        // base case
        if (ind1 == 0 || ind2 == 0) return 0;

        if (dp[ind1][ind2] != -1) return dp[ind1][ind2];

        if (s1.charAt(ind1-1) == s2.charAt(ind2-1)) {
            return dp[ind1][ind2] = 1 + lcs(ind1-1, ind2-1, s1, s2, dp);
        }else {
            int len1 = lcs(ind1-1, ind2, s1, s2, dp);
            int len2 = lcs(ind1, ind2-1, s1, s2, dp);
            return dp[ind1][ind2] = Math.max(len1, len2);
        }
    }

    // TABULATION
    public static int SCS_Tab(String s1, String s2){
        int n = s1.length();
        int m = s2.length();

        int[][] dp = new int[n+1][m+1];

        for (int i=1; i<dp.length; i++){
            for (int j=1; j<dp[0].length; j++){
                //  matches
                if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else{
                    int len1 = dp[i-1][j];
                    int len2 = dp[i][j-1];
                    dp[i][j] = Math.max(len1, len2);
                }
            }
        }

        int lcs = dp[n][m];
        int scs = (n-lcs) + (m-lcs) + lcs;
        return scs;
    }

    public static void printSCS(String s1, String s2){
        int n = s1.length();
        int m = s2.length();

        int[][] dp = new int[n+1][m+1];

        for (int i=1; i<dp.length; i++){
            for (int j=1; j<dp[0].length; j++){
                //  matches
                if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else{
                    int len1 = dp[i-1][j];
                    int len2 = dp[i][j-1];
                    dp[i][j] = Math.max(len1, len2);
                }
            }
        }
        StringBuilder scs = new StringBuilder();
        int i = n;
        int j = m;
        while (i > 0 && j > 0){
            if (s1.charAt(i-1) == s2.charAt(j-1)){
                scs.append(s1.charAt(i-1));
                // don't include both
                i--;
                j--;
            }else if (dp[i-1][j] > dp[i][j-1]) {
                // up
                scs.append(s1.charAt(i-1));
                i--;
            }else {
                // left
                scs.append(s2.charAt(j-1));
                j--;
            }
        }

        // remaining
        while (i > 0){
            scs.append(s1.charAt(i-1));
            i--;
        }

        while (j > 0) {
            scs.append(s2.charAt(j-1));
            j--;
        }

        String ans = scs.reverse().toString();
        System.out.println(ans);
    }

    public static void main(String[] args) {
        String s1 = "brute";
        String s2 = "groot";
        // ans = 8

        // Memoize
        System.out.println(SCS_memo(s1, s2));


        // Tabulation
        System.out.println(SCS_Tab(s1, s2));

        // Print SCS
        printSCS(s1, s2);
    }
}
