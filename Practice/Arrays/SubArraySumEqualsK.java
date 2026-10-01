class Solution {
    public int subarraySum(int[] nums, int k) {
        //[0 -> i] = currsum suppose
        //if there exists prevsum = currsum - k from idx [0 -> x] => from [x+1 -> i] = k
        //find no. of times prevsum has formed
        //map to store frequency of prefixsums
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int count = 0, currSum = 0;
        for(int num : nums){
            currSum += num;
            int neededSum = currSum - k;
            if(map.containsKey(neededSum)){
                count += map.get(neededSum);
            }
            map.put(currSum, map.getOrDefault(currSum, 0)+1);
        }
        return count;
    }
}