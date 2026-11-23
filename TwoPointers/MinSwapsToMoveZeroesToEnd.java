class Solution {
    public int minimumSwaps(int[] nums) {
        int l = 0, r = nums.length - 1;
        int swaps = 0;
        while(l < r){
            if(nums[l] == 0){
                // check if this needs to be swapped
                while(l < r && nums[r] == 0){
                    r--;
                }
                if(l < r){
                    swaps++;
                    r--;
                }
            }
            l++;
        }
        return swaps;
    }
}