class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        //Store freq of characters in string as key and group similar ones in hashmap
        Map<String, List<String>> map = new HashMap<>();
        for(String str : strs){
            //compute freq array
            int[] freq = new int[26];
            for(char c : str.toCharArray()){
                freq[c - 'a']++;
            }
            String key = Arrays.toString(freq);
            map.computeIfAbsent(key, v -> new ArrayList<>()).add(str);
        }
        List<List<String>> result = new ArrayList<>(map.values());
        return result;
    }
}