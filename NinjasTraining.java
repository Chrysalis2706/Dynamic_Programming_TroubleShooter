package Dynamic_Programming;

import java.util.Arrays;

public class NinjasTraining {

    public static int ninjaMemo(int[][] arr, int day, int last,  int[][] dp){
        if (day == 0){
            int max = 0;
            for (int task=0; task<3; task++){
                if (task != last) max = Math.max(max, arr[0][task]);
            }
            return dp[day][last] = max;
        }

        if (dp[day][last] != -1) return dp[day][last];

        int max = 0;
        for (int task=0; task<3; task++){
            if (task != last){
                int points = arr[day][task] + ninjaMemo(arr, day-1, task, dp);
                max = Math.max(max, points);
            }
        }
        return dp[day][last] =  max;
    }


    public static int ninjaTab(int[][] arr){
        int n = arr.length;
        int lasts = arr[0].length+1;
        int[][] dp = new int[n][lasts];
        for (int i=0; i<n; i++){
            Arrays.fill(dp[0], -1);
        }

        // base cases
        dp[0][0] = Math.max(arr[0][1], arr[0][2]);
        dp[0][1] = Math.max(arr[0][0], arr[0][2]);
        dp[0][2] = Math.max(arr[0][0], arr[0][1]);
        dp[0][3] = Math.max(arr[0][0], Math.max(arr[0][1], arr[0][2]));


        for (int days = 1; days < dp.length; days++){
            for (int last = 0; last < 4; last++){
                dp[days][last] = 0;
                for (int task = 0; task < 3; task++){
                    if (task != last) {
                        int point = arr[days][task] + dp[days - 1][task];
                        dp[days][last] = Math.max(point, dp[days][last]);
                    }
                }
            }
        }
        return dp[n-1][lasts-1];
    }

    public static void main(String[] args) {
        int[][] arr = { {10, 40, 70}, {20, 50, 80}, {30, 60, 90} };

        int[][] dp = new int[arr.length][4];
        for (int i=0; i<arr.length; i++){
            Arrays.fill(dp[i], -1);
        }
        System.out.println(ninjaMemo(arr, arr.length-1, 3, dp));
        System.out.println(ninjaTab(arr));
    }
}
