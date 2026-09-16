public class Solution {
    class TrieNode{
        TrieNode[] children = new TrieNode[26];
        int count = 0; //no. of words passing through this
    }
    TrieNode root = new TrieNode();
    public void insert(String s){
        TrieNode curr = root;
        for(char c : s.toCharArray()){
            int idx = c - 'a';
            if(curr.children[idx] == null)
                curr.children[idx] = new TrieNode();
            curr = curr.children[idx];
            curr.count++;
        }
    }
    String findPrefix(String s){
        StringBuilder sb = new StringBuilder();
        TrieNode curr = root;
        for(char c : s.toCharArray()){
            int idx = c - 'a';
            sb.append(c);
            curr = curr.children[idx];
            if(curr.count == 1)
                break;
        }
        return sb.toString();
    }
    public ArrayList<String> prefix(ArrayList<String> A) {
        //insert words into trie
        for(String s : A){
            insert(s);
        }
        //find prefix for each word
        ArrayList<String> ans = new ArrayList<>();
        for(String s : A){
            String prefix = findPrefix(s);
            ans.add(prefix);
        }
        return ans;
    }
}
