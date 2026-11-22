class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = 0, p2 = 0, i = 0;
        int[] res = new int[m+n];
        while(p1 < m && p2 < n){
            if(nums1[p1] < nums2[p2]){
                res[i] = nums1[p1];
                p1++;
            }
            else{
                res[i] = nums2[p2];
                p2++;
            }
            i++;
        }
        while(p1 < m){
            res[i++] = nums1[p1++];
        }
        while(p2 < n){
            res[i++] = nums2[p2++];
        }
        System.arraycopy(res, 0, nums1, 0, m+n);
    }
}