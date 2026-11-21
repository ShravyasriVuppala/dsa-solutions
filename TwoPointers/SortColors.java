class Solution {
    public void sortColors(int[] nums) {
        int l = 0, r = nums.length - 1;
        int i = 0;
        while(i <= r){
            // if blue, swap with right
            if(nums[i] == 2){
                nums[i] = nums[r];
                nums[r] = 2;
                r--;
            }
            // if red, advance left
            else if(nums[i] == 0){
                nums[i] = nums[l];
                nums[l] = 0;
                l++;
                i++;
            }
            else{
                i++;
            }

        }
    }
}