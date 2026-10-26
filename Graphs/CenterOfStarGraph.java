class Solution {
    public int findCenter(int[][] edges) {
        // set score for each node
        int n = edges.length + 1;
        int[] score = new int[n+1];
        for(int[] edge : edges){
            int a = edge[0];
            int b = edge[1];
            score[a]++;
            score[b]++;
        }
        for(int i = 1; i <= n; i++){
            if(score[i] == n - 1){
                return i;
            }
        }
        return -1;
    }
}