class Solution {
    public int rob(int[] nums) {
        int a = 0, b = 0;
        for(int i = 0; i < nums.length; i++){
            if(i < 2){
                if(i == 0){
                    a = nums[i];
                }
                else{
                    b = Math.max(a, nums[i]);
                }
            }
            else{
                int c = Math.max(b, a + nums[i]);
                a = b;
                b = c;
            }
        }
        return Math.max(a, b);
    }
}