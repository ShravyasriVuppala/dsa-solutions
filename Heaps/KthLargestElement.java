class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        //min heap of size k to store k largest elements
        for(int x : nums){
            minHeap.offer(x);
            if(minHeap.size() > k)
                minHeap.poll();
        }
        return minHeap.poll();
    }
}