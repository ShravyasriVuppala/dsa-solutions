class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> map = new HashMap<>();
        for(String s : strs){
            //Populate freq array for the current string and strore it as key in map
            int[] freq = new int[26];
            for(char c : s.toCharArray())
                freq[c - 'a']++;
            String key = Arrays.toString(freq);
            map.computeIfAbsent(key, v -> new ArrayList<String>()).add(s);
        }
        return new ArrayList<>(map.values());
    }
}