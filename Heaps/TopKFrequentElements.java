class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        //freq map
        Map<Integer,Integer> map = new HashMap<>();
        for(int num : nums){
            map.put(num, map.getOrDefault(num, 0)+1);
        }
        //minheap of size k
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a,b) -> a[1] - b[1]);
        for(Map.Entry<Integer,Integer> entry : map.entrySet()){
            int[] pair = new int[2];
            pair[0] = entry.getKey();
            pair[1] = entry.getValue();
            minHeap.offer(pair);
            if(minHeap.size() > k)
                minHeap.poll();
        }
        int[] ans = new int[minHeap.size()];
        int i = 0;
        while(!minHeap.isEmpty()){
            int[] pair = minHeap.poll();
            ans[i++] = pair[0];
        }
        return ans;
    }
}