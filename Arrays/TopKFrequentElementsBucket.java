class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        //freq map
        Map<Integer, Integer> map = new HashMap<>();
        for(int num: nums)
            map.put(num, map.getOrDefault(num, 0) + 1);
        //bucket array of size nums.length+1; frequency can range from 0 tho nums.length
        //bucket[freq] = list of all elements with frequency freq
        List<Integer>[] bucket = new List[nums.length+1];
        for(Map.Entry<Integer,Integer> entry : map.entrySet()){
            int key = entry.getKey();
            int value = entry.getValue();
            if(bucket[value] == null)
                bucket[value] = new ArrayList<>();
            bucket[value].add(key);
        }
        //iterate backwards on bucket array and store most frequent k elements
        int[] ans = new int[k];
        int count = 0;
        for(int i = nums.length; i >= 0 && count < k; i--){
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