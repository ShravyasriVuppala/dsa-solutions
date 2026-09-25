class Trie {
    Trie[] children;
    boolean isWord;
    public Trie() {
        this.children = new Trie[26];
        this.isWord = false;
    }

    public void insert(String word) {

        Trie curr = this;
        for(char c : word.toCharArray()){
            if(curr.children[c - 'a'] == null){
                curr.children[c - 'a'] = new Trie();
            }
            curr = curr.children[c - 'a'];
        }
        curr.isWord = true;
    }

    public boolean search(String word) {
        Trie curr = this;
        for(char c : word.toCharArray()){
            if(curr.children[c - 'a'] == null){
                return false;
            }
            curr = curr.children[c - 'a'];
        }
        return curr.isWord;
    }

    public boolean startsWith(String prefix) {
        Trie curr = this;
        for(char c : prefix.toCharArray()){
            if(curr.children[c - 'a'] == null){
                return false;
            }
            curr = curr.children[c - 'a'];
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */