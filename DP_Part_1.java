package Dynamic_Programming;

public class DP_Part_1 {


    // from Recursion
    public static int fib_rec(int n){
        if (n==0 || n==1) return n;
        return fib_rec(n-2) + fib_rec(n-1);
    }

    // from DP
    public static int fib_memo(int n, int[] dp){
        if (n==0 || n==1) return n;

        //store and reuse
        if (dp[n] != 0){
            return dp[n];
        }

        int store = fib_memo(n-2, dp) + fib_memo(n-1, dp);
        return store;
    }

    public static int fib_tab(int n){
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i=2; i<=n; i++){
            dp[i] = dp[i-2] + dp[i-1];
        }
        return dp[n];
    }



    // Climb Stairs

    //1. recursion
    public static int countWaysRec(int n){
        if (n==0) return 1;
        if (n<0) return 0;

        return countWaysRec(n-1) + countWaysRec(n-2);
    }

    //2. DP : rec + memo
    public static int countWaysMemo(int n, int[] dp){
        if (n==0) return 1;
        if (n<0) return 0;

        // resuse
        if (dp[n] != 0){
            return dp[n];
        }

        // store
        int store = countWaysMemo(n-1, dp) + countWaysMemo(n-2, dp);
        return store;
    }

    //3. DP : tabulation
    public static int countWaysTab(int n){
        // create table + meaning
        int[] dp = new int[n+1];
        dp[0] = 1;

        // filling
        for (int i=1; i<=n; i++){
            if (i==1) {
                dp[i] = dp[i-1];
            }else{
                dp[i] = dp[i-1] + dp[i-2];
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {

        int n = 6;

        // from recursion
        System.out.println(fib_rec(n));

        // from DP : memoization
        System.out.println(fib_memo(n, new int[n+1]));

        // from DP : tabulation
        System.out.println(fib_tab(n));

        System.out.println("\nClimb stairs ->\nRecursion :");

        // Climb Stairs
        System.out.println(countWaysRec(6));
        System.out.println("Memoization :");
        System.out.println(countWaysMemo(6, new int[6+1]));
        System.out.println("Tabulation :");
        System.out.println(countWaysTab(6));
    }
}
