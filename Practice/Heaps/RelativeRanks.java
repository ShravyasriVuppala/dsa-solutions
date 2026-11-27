class Solution {
    public String[] findRelativeRanks(int[] score) {
        // max heap
        String[] ans = new String[score.length];
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a,b) -> Integer.compare(b[1], a[1]));
        for(int i = 0; i < score.length; i++){
            maxHeap.offer(new int[]{i, score[i]});
        }
        int i = 1;
        while(!maxHeap.isEmpty()){
            int[] entry = maxHeap.poll();

            if(i == 1){
                ans[entry[0]] = "Gold Medal";
            }
            else if(i == 2){
                ans[entry[0]] = "Silver Medal";
            }
            else if(i == 3){
                ans[entry[0]] = "Bronze Medal";
            }
            else{
                ans[entry[0]] = String.valueOf(i);
            }
            i++;
        }
        return ans;
    }
}