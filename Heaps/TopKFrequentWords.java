class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        //map with word frequency
        Map<String, Integer> map = new HashMap<>();
        for(String word : words){
            map.put(word, map.getOrDefault(word, 0)+1);
        }
        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(
                (a,b) -> {
                    if(!a.getValue().equals(b.getValue()))
                        return a.getValue() - b.getValue(); //return smaller freq entry
                    return b.getKey().compareTo(a.getKey()); //lexicographically larger at top
                }
        );
        //insert map entries into min heap of size k
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            minHeap.offer(entry);
            if(minHeap.size() > k)
                minHeap.poll();
        }
        List<String> result = new ArrayList<>();
        while(!minHeap.isEmpty()){
            result.add(minHeap.poll().getKey());
        }

        Collections.reverse(result);
        return result;
    }
}