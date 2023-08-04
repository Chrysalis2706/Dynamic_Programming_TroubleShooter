package Dynamic_Programming;

public class MinOperationsToMakeStringPalindrome {

    public static int minOperations(String str){
        int n = str.length();
        StringBuilder sb = new StringBuilder(str);
        sb.reverse();
        String rev = sb.toString();
        int m = rev.length();
        int[][] dp = new int[n+1][m+1];
        int common = lcs(n, m, str, rev, dp);
        return n - common;
    }

    public static int lcs(int ind1, int ind2, String str, String rev, int[][] dp){
        if (ind1 == 0 || ind2 == 0) return 0;

        if (str.charAt(ind1-1) == rev.charAt(ind2-1)){
            return dp[ind1][ind2] = lcs(ind1-1, ind2-1, str, rev, dp) + 1;
        }else{
            int len1 = lcs(ind1-1, ind2, str, rev, dp);
            int len2 = lcs(ind1, ind2-1, str, rev, dp);
            return dp[ind1][ind2] = Math.max(len1, len2);
        }
    }

    public static void main(String[] args) {
        String str = "abcaa";
        System.out.println(minOperations(str));
    }
}
