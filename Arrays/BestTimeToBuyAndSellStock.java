class Solution {
    public int maxProfit(int[] prices) {
        int profit = 0, n = prices.length, low = prices[0];
        for(int i = 1; i < n; i++){
            if(low < prices[i]){
                profit = Math.max(profit, prices[i] - low); //calculate probable profit if value more than low
            }
            else{
                low = prices[i]; //update low so far
            }
        }
        return profit;
    }
}