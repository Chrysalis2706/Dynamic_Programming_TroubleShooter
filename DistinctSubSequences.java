package Dynamic_Programming;
import java.util.*;

public class DistinctSubSequences {

    public static int funMemo(String s1, String s2){
        int n = s1.length();
        int m = s2.length();
        // one base indexing
        int[][] dp = new int[n+1][m+1];
        for (int i=0; i<dp.length; i++){
            Arrays.fill(dp[i], -1);
        }
        return helper(n, m, s1, s2, dp);
    }
    public static int helper(int ind1, int ind2, String s1, String s2, int[][] dp){
        // is s1 is empty -> then not matches
        if (ind1 == 0) return 0;
        // is s2 is empty -> then matching only one empty string
        if (ind2 == 0) return 1;

        if (dp[ind1][ind2] != -1) return dp[ind1][ind2];

        // matches
        if (s1.charAt(ind1-1) == s2.charAt(ind2-1)){
            int take = helper(ind1-1, ind2, s1, s2, dp);
            int notTake = helper(ind1, ind2-1, s1, s2, dp);
            return dp[ind1][ind2] = take + notTake;
        }else {
            return dp[ind1][ind2] = helper(ind1-1, ind2, s1, s2, dp);
        }
    }

    public static void main(String[] args) {
        String s1 = "baggbag";
        String s2 = "bag";

        // no of occurrences of s2 in s1
        System.out.println(funMemo(s1, s2));

    }
}
