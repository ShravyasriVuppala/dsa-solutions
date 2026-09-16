class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        //as k increases, hours needed to eat decreases
        //as k decreases, hours needed to eat increases
        //monotonic relation between k and h. Find min value of k such that total hours needed to eat with k <= h
        //hints binary search; search space for k is 1 to max(piles)
        int max = 0;
        for(int pile : piles) max = Math.max(max, pile);
        int left = 1, right = max, ans = 0;
        while(left <= right){
            int mid = left + (right - left)/2;
            //calculate total hours to eat all bananas
            int totalHours = 0;
            for(int pile : piles){
                totalHours += Math.ceil((double)pile / mid);
            }
            //decide search space half
            if(totalHours <= h){
                //k is fast, look for smaller k
                right = mid - 1;
                ans = mid;
            }
            else
                left = mid + 1;
        }
        return ans;

    }
}