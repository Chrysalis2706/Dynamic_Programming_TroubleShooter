package Dynamic_Programming;

public class LongestPalindromicSubsequence {

    public static void fun(String s){
        int n = s.length();

        // reverse the string
        StringBuilder sb = new StringBuilder(s);
        sb.reverse();
        String rs = sb.toString();


        // find lcs of s and rs
        int[][] dp = new int[n+1][n+1];

        for (int i=1; i<dp.length; i++){
            for (int j=1; j<dp[0].length; j++){
                if (s.charAt(i-1) == rs.charAt(j-1)) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                }else{
                    int l1 = dp[i-1][j];
                    int l2 = dp[i][j-1];
                    dp[i][j] = Math.max(l1, l2);
                }
            }
        }

        int lcs = dp[n][n];
        StringBuilder ans = new StringBuilder();

        // printing the lcs
        int i = n, j = n;
        while (i>0 && j>0){
            if (s.charAt(i-1) == rs.charAt(j-1)){
                ans.append(s.charAt(i-1));
                i = i-1;
                j = j-1; // diagonal up
            }else if (dp[i-1][j] >= dp[i][j-1]){
                i = i-1;
            }else{
                j = j-1;
            }
        }
        System.out.println("Length : " + lcs + "\nPalindrome : " + ans.reverse().toString());
    }

    public static void main(String[] args) {
        String str = "bbabcbcab";
        fun(str);
    }
}
