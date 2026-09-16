class Solution {
    public boolean isAnagram(String s, String t) {
        //frequency array for each character. Increment for string s and decrement for string t.
        if(s.length() != t.length()) return false; //unequal length, not anagram
        int[] frequency = new int[26];
        for(int i = 0; i < s.length(); i++){
            frequency[s.charAt(i) - 'a']++;
            frequency[t.charAt(i) - 'a']--;
        }
        for(int i = 0; i < 26; i++){
            if(frequency[i] != 0)
                return false;
        }
        return true;
    }
}