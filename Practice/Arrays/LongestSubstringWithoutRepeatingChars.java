class Solution {
    public int lengthOfLongestSubstring(String s) {
        //Store index of last occurance of character in a map
        Map<Character, Integer> map = new HashMap<>();
        int l = 0, ans = 0;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(map.containsKey(c)){
                int idx = map.get(c);
                l = Math.max(l, idx + 1);
            }
            map.put(c, i);
            ans = Math.max(ans, i - l + 1);
        }
        return ans;
    }
}