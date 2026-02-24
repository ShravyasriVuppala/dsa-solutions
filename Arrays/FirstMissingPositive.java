class Solution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        //Marks elements that are not in range 1-n with n+1
        for(int i = 0; i < n; i++){
            if(nums[i] < 1)
                nums[i] = n + 1;
        }
        //Mark nums[i] is within 1-n mark nums[nums[i]-1] as -ve indicating it exists
        for(int i = 0; i < n; i++){
            if(Math.abs(nums[i]) <= n){
                nums[Math.abs(nums[i])-1] = -1 * Math.abs(nums[Math.abs(nums[i])-1]);
            }
        }
        // if an index has +ve number, return index+1 which is the first missing +ve number
        for(int i = 0; i < n; i++){
            if(nums[i] > 0)
                return i+1;
        }
        return n+1;
    }
}