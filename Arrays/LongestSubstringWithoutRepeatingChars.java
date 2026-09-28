class Solution {
    public int lengthOfLongestSubstring(String s) {
        //store indexes of last occurances of chars in hashmap
        //sliding window with only unique chars
        Map<Character, Integer> map = new HashMap<>();
        int l = 0, ans = 0;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(map.containsKey(c)){
                l = Math.max(l, map.get(c) + 1);
            }
            map.put(c, i);
            ans = Math.max(ans, i - l + 1);
            System.out.println(l +" "+ i + " "+ ans);
        }
        return ans;
    }
}