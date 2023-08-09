package Dynamic_Programming;

public class BestTimeToBuyNSellStocks_I {

    public static int solve(int[] arr){
        int minPrice = arr[0];
        int maxProfit = Integer.MIN_VALUE;
        for (int i=1; i<arr.length; i++){
            int currProfit = arr[i] - minPrice;
            minPrice = Math.min(arr[i], minPrice);
            maxProfit = Math.max(maxProfit, currProfit);
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6 ,4};
        System.out.println(solve(prices));
    }
}
