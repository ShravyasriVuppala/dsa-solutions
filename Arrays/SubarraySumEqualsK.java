class Solution {
    public int subarraySum(int[] nums, int k) {
        //store prefix sums in hashmap with freq
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0,1); //prefix sum 0 appears 1 time
        int currentSum = 0, count = 0;
        for(int num : nums){
            currentSum += num;
            int target = currentSum - k;
            //check if target exists in map
            if(map.containsKey(target)){
                count += map.get(target);
            }
            map.put(currentSum, map.getOrDefault(currentSum,0)+1);
        }
        return count;
    }
}