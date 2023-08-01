package Dynamic_Programming;
import java.util.*;

public class LongestCommonSubstring {
    public static int longSubs(String s1, String s2){
        int n = s1.length();
        int m = s2.length();
        int[][] dp = new int[n+1][m+1];
        int ans = 0;

        for (int i=1; i<dp.length; i++){
            for (int j=1; j<dp[0].length; j++){
                // same char
                if (s1.charAt(i-1) == s2.charAt(j-1)){
                    dp[i][j]  = 1 + dp[i-1][j-1];
                    ans = Math.max(ans, dp[i][j]);
                }else{
                    dp[i][j] = 0;
                }
            }
        }
        display(dp);

        // now print the max value in dp array
        return ans;
    }

    public static void display(int[][] arr){
        for (int i=0; i<arr.length; i++){
            for (int j=0; j<arr[0].length; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }


    public static void main(String[] args) {
        String s1 = "abcjklp";
        String s2 = "acjkp";
        System.out.println("Longest common substring : " + longSubs(s1, s2));
    }
}
