class Solution {
    public int[] productExceptSelf(int[] nums) {
        //prefix and suffix product array
        int n = nums.length;
        int[] ans = new int[n];
        ans[0] = 1;
        //first pass - store product of all elements to left
        for(int i = 1; i < n; i++){
            ans[i] = ans[i-1] * nums[i-1]; //ans[i] = product of elements till i-1
        }
        //second pass - multiply with right product
        int right = 1;
        for(int i = n-1; i >= 0; i--){
            ans[i] *= right; //left*right
            right *= nums[i]; //right product
        }
        return ans;
    }
}