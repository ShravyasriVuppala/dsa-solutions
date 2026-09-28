class Solution {
    public void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    public int findKthLargest(int[] nums, int k) {
        //quick select - choose random pivot and keep swapping until
        // all nums to left of pivot are smaller then pivot
        // and nums to right are greater than pivot
        int n = nums.length;
        int left = 0, right = n - 1, target = n - k;
        while(left <= right){
            //choose random pivot
            int pivot = nums[(int)(Math.random() * (right - left + 1)) + left];
            int lt = left;
            int gt = right;
            int i = lt;
            while(i <= gt){
                if(nums[i] < pivot){ //move smaller ones to left
                    swap(nums, i, lt);
                    lt++;
                    i++;
                }
                else if(nums[i] > pivot){ //move greater ones to right
                    swap(nums, i, gt);
                    gt--; //i is not incremented because it is swapped with gt value
                }
                else{
                    i++;
                }
            }
            if(target < lt){
                //search left space
                right = lt - 1;
            }
            else if(target > gt){
                left = gt + 1;
            }
            else{
                return pivot;
            }
        }
        return -1;
    }
}