class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums1.length > nums2.length)
            return findMedianSortedArrays(nums2, nums1);
        int m = nums1.length, n = nums2.length;
        //binary search on nums1 to find valid partition
        int lo = 0, hi = m;
        while(lo <= hi){
            int partX = (lo + hi)/2;
            int partY = (m + n + 1)/2 - partX;
            int maxLeftX = partX == 0? Integer.MIN_VALUE : nums1[partX - 1];
            int minRightX = partX == m? Integer.MAX_VALUE : nums1[partX];
            int maxLeftY = partY == 0? Integer.MIN_VALUE : nums2[partY - 1];
            int minRightY = partY == n? Integer.MAX_VALUE : nums2[partY];
            //if valid partition, max in the left should be less than min in the right
            if(maxLeftX <= minRightY && maxLeftY <= minRightX){
                if((m + n)%2 == 1)
                    return (double)Math.max(maxLeftX, maxLeftY);
                else
                    return (double)(Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY))/2;
            }
            else if(maxLeftX > minRightY){//partition is far too right, move left
                hi = partX - 1;
            }
            else
                lo = partX + 1; // partition is far too left, move right
        }
        return 0.0;

    }
}