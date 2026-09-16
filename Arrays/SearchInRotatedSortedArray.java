class Solution {
    public int search(int[] nums, int target) {
        //if we pick a mid, either left half of it is sorted or the right half, can apply binary search to find target
        int left = 0, right = nums.length - 1;
        while(left <= right){
            int mid = left + (right - left)/2;
            if(nums[mid] == target) return mid;

            //if left half is sordted
            if(nums[left] <= nums[mid]){
                //check if target is in this half
                if(nums[left] <= target && target < nums[mid])
                    right = mid - 1; // target is in left half
                else
                    left = mid + 1;
            }
            else{
                if(nums[mid] < target && target <= nums[right])
                    left = mid + 1; //target is in right half
                else
                    right = mid - 1;
            }
        }
        return -1;
    }
}