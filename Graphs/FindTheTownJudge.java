class Solution {
    public int findJudge(int n, int[][] trust) {
        int[] score = new int[n+1];

        for(int[] t : trust){
            int a = t[0]; //a trusts b so score of b is incremented and a is decremented
            int b = t[1];
            score[a]--;
            score[b]++;
        }

        for(int i = 1; i <= n; i++){
            if(score[i] == n - 1){ //everybody trusts judge except judge itself (n-1)
                return i;
            }
        }
        return -1;
    }
}