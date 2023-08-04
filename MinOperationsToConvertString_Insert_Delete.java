package Dynamic_Programming;

public class MinOperationsToConvertString_Insert_Delete {

    public static int fun(String s1, String s2){
        int n = s1.length();
        int m = s2.length(); // s1 -> s2

        int[][] dp = new int[n+1][m+1];

        for (int i=1; i<dp.length; i++){
            for (int j=1; j<dp[0].length; j++){
                if (s1.charAt(i-1) == s2.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }else{
                    int len1 = dp[i-1][j];
                    int len2 = dp[i][j-1];
                    dp[i][j] = Math.max(len1, len2);
                }
            }
        }
        int lcs = dp[n][m];
        int delete = n - lcs;
        int insertion = m - lcs;
        return delete + insertion;
    }

    public static void main(String[] args) {
        String s1 = "abcd";
        String s2 = "and";
        System.out.println(fun(s1, s2));;
    }
}

