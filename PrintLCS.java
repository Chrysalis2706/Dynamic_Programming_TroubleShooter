package Dynamic_Programming;

import java.util.Arrays;

public class PrintLCS {

    public static void printLCS(String s1, String s2){
        int n = s1.length();
        int m = s2.length();

        StringBuilder sb = new StringBuilder();
        int[][] dp = new int[n+1][m+1];

        for (int i=1; i<n+1; i++){
            for (int j=1; j<m+1; j++){
                if (s1.charAt(i-1) == s2.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }else {
                    int len1 = dp[i-1][j];
                    int len2 = dp[i][j-1];
                    dp[i][j] = Math.max(len1, len2);
                }
            }
        }

        // traverse through dp array
        int i = n;
        int j = m;
        while (i>0 && j>0){
            //match
            if (s1.charAt(i-1) == s2.charAt(j-1)){
                sb.append(s1.charAt(i-1));
                i = i-1;
                j = j-1; // go diagonally up
            }else if (dp[i-1][j] >= dp[i][j-1]){
                // go up
                i = i-1;
            }else{
                j = j-1;
            }
        }

        System.out.println(sb.reverse().toString());
    }

    public static void main(String[] args) {
        String s1 = "abcde";
        String s2 = "bdgek";
        printLCS(s1, s2);
    }
}
