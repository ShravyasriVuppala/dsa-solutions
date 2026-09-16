class Solution {
    public String reorganizeString(String s) {
        //calculate frequency array
        int[] freq = new int[26];
        for(char c : s.toCharArray())
            freq[c - 'a']++;

        int maxFreq = 0;
        for(int f: freq) maxFreq = Math.max(maxFreq, f);
        if(maxFreq > (s.length() + 1)/2) return ""; // if a char freq is more than half then rearragement impossible

        //insert freq pairs into heap and place max count char alternatively greedily
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a,b) -> b[1] - a[1]);
        for(int i = 0; i < 26; i++){
            if(freq[i] > 0) maxHeap.offer(new int[]{i, freq[i]});
        }
        int[] prev = null;
        StringBuilder sb = new StringBuilder();
        while(!maxHeap.isEmpty()){
            int[] current = maxHeap.poll();
            sb.append((char) ('a' + current[0]));
            current[1]--;
            if(prev != null && prev[1] > 0)
                maxHeap.offer(prev);
            prev = current;
        }
        return (sb.length() == s.length())? sb.toString() : "";
    }
}