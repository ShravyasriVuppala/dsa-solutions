class WordDictionary {
    WordDictionary[] children;
    boolean isWord;
    public WordDictionary() {
        this.children = new WordDictionary[26];
        this.isWord = false;
    }

    public void addWord(String word) {
        WordDictionary curr = this;
        for(char c : word.toCharArray()){
            if(curr.children[c - 'a'] == null)
                curr.children[c - 'a'] = new WordDictionary();
            curr = curr.children[c - 'a'];
        }
        curr.isWord = true;
    }

    public boolean dfs(String word, int index, WordDictionary root){
        if(index == word.length()) //reached end of word
            return  root.isWord;
        char c = word.charAt(index);
        if (c == '.') {
            for(WordDictionary child : root.children){
                if(child != null && dfs(word, index + 1, child))
                    return true;
            }
            return false;
        }
        WordDictionary node = root.children[c - 'a'];
        return (node != null && dfs(word, index + 1, node));
    }
    public boolean search(String word) {
        return dfs(word, 0, this);
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */