class Solution {
    public List<String> getNeighbors(String word, Set<String> wordSet) {
        List<String> neighbors = new ArrayList<>();
        char[] chars = word.toCharArray();
        for (int i = 0; i < word.length(); i++) {
            char oldChar = chars[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == oldChar)
                    continue;
                chars[i] = c;
                String newWord = new String(chars);
                if (wordSet.contains(newWord))
                    neighbors.add(newWord);
            }
            chars[i] = oldChar;
        }
        return neighbors;
    }

    public void backtrack(String word, String beginWord, List<List<String>> result, List<String> path, Map<String, List<String>> parents) {
        path.add(word);
        if (word.equals(beginWord)) {
            Collections.reverse(path);
            result.add(new ArrayList<>(path));
            Collections.reverse(path);
            path.remove(path.size() - 1);
            return;
        }
        for (String parent : parents.get(word)) {
            backtrack(parent, beginWord, result, path, parents);
        }
        path.remove(path.size() - 1);
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Queue<String> queue = new LinkedList<>();
        Set<String> wordSet = new HashSet<>(wordList);
        List<List<String>> result = new ArrayList<>();
        Map<String, List<String>> parents = new HashMap<>();
        Map<String, Integer> distance = new HashMap<>();

        if (!wordSet.contains(endWord))
            return result;

        distance.put(beginWord, 0);
        queue.offer(beginWord);

        while (!queue.isEmpty()) {
            String word = queue.poll();
            int currDist = distance.get(word);
            if (currDist > distance.getOrDefault(endWord, Integer.MAX_VALUE))
                break;
            for (String neighbor : getNeighbors(word, wordSet)) {
                int newDist = currDist + 1;
                if (!distance.containsKey(neighbor)) {
                    distance.put(neighbor, newDist);
                    parents.computeIfAbsent(neighbor, k -> new ArrayList<>()).add(word);
                    queue.offer(neighbor);
                } else if (newDist == distance.get(neighbor)) {
                    parents.get(neighbor).add(word);
                }
            }
        }

        if (distance.containsKey(endWord))
            backtrack(endWord, beginWord, result, new ArrayList<>(), parents);

        return result;
    }
}