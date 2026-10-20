class Solution {
    private boolean canShip(int[] weights, int days, int capacity){
        int daysNeeded = 1;
        int currentLoad = 0;
        for(int weight : weights){
            if(currentLoad + weight > capacity){
                daysNeeded++;
                currentLoad = 0;
            }
            currentLoad += weight;
            if(days < daysNeeded){
                return false;
            }
        }
        return true;
    }
    public int shipWithinDays(int[] weights, int days) {
        //get min and max(sum of all weights) and binary search on this range
        int left = Integer.MIN_VALUE, right = 0;
        for(int weight : weights){
            left = Math.max(left, weight);
            right += weight;
        }
        while(left < right){
            int mid = left + (right - left) / 2;
            //calculate total days to ship all packages
            if(canShip(weights, days, mid)){
                //try with small capacity
                right = mid;
            }
            else{
                left = mid + 1; // need more capacity
            }

        }
        return left;
    }
}