class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int total = 0, left = 0;
        for(int weight : weights){
            left = Math.max(left, weight);
            total += weight;
        }

        int right = total, ans = 0;
        while(left <= right){
            int mid = left + (right - left)/2;
            //calculate days required with mid as ship capacity
            int reqDays = 0, weightSoFar = 0;
            for(int weight : weights){
                if((weightSoFar + weight) > mid){
                    reqDays++;
                    weightSoFar = weight;
                }
                else
                    weightSoFar += weight;
            }
            if(weightSoFar > 0) reqDays++;
            //if reqDays <= days, capacity is large, can go down. Search left space
            if(reqDays <= days){
                ans = mid;
                right = mid - 1;
            }
            else left = mid + 1;

        }
        return ans;
    }
}