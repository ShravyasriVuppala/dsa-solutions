class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        //Store freq of nums in map
        //push [num, freq] to minHeap if size k
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : nums){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        //push [num, freq] to minHeap if size k
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            int num = entry.getKey();
            int freq = entry.getValue();
            minHeap.offer(new int[]{num, freq});
            if(minHeap.size() > k)
                minHeap.poll();
        }
        int[] result = new int[k];
        int count = 0;
        while(!minHeap.isEmpty() && count < k){
            int[] cell = minHeap.poll();
            result[count++] = cell[0];
        }
        return result;
    }
}