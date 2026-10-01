class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        //compute and store frequencies of unique numbers in a hash map
        //store freq buckets of numbers in an array
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for(int num : nums){
            map.put(num, map.getOrDefault(num, 0) + 1);
            max = Math.max(max, map.get(num));
        }
        //bucket array with size as max frequency
        List<Integer>[] bucket = new List[max+1];
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            int num = entry.getKey();
            int freq = entry.getValue();
            if(bucket[freq] == null)
                bucket[freq] = new ArrayList<>();
            bucket[freq].add(num);
        }
        int[] ans = new int[k];
        int count = 0;
        for(int i = max; i >= 0 && count < k; i--){
            if(bucket[i] != null){
                for(int num : bucket[i]){
                    if(count < k)
                        ans[count++] = num;
                }
            }
        }
        return ans;
    }
}