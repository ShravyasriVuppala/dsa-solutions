import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public List<int[]> subarraySum(int[] nums, int k) {
        //[0 -> i] = currsum suppose
        //if there exists prevsum = currsum - k from idx [0 -> x] => from [x+1 -> i] = k
        //find no. of times prevsum has formed
        //map to store prefixsums -> Ending indices list
        Map<Integer, List<Integer>> map = new HashMap<>();
        map.put(0, new ArrayList<>(List.of(-1)));
        int currSum = 0;
        List<int[]> result = new ArrayList<>();
        for(int i = 0; i < nums.length; i++){
            currSum += nums[i];
            int neededSum = currSum - k;
            if(map.containsKey(neededSum)){
                // count += map.get(neededSum);
                for(int idx : map.get(neededSum)){
                    result.add(new int[]{idx+1, i});
                }
            }
            //map.put(currSum, map.getOrDefault(currSum, 0)+1);
            map.computeIfAbsent(currSum, v -> new ArrayList<>()).add(i);
        }
        return result;
    }
}