class Solution {
    public int[] gardenNoAdj(int n, int[][] paths) {
        // build adj list
        // greedily pick flower for each garder by inspecting neighbors

        List<List<Integer>> adj = new ArrayList<>();
        for(int i = 0; i <= n; i++){
            adj.add(new ArrayList<>());
        }
        // populate adj list
        for(int[] path : paths){
            int u = path[0] - 1;
            int v = path[1] - 1;
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        // calculate answer for each garden
        int[] answer = new int[n];
        for(int garden = 0; garden < n; garden++){
            // get flowers of neighbors and greedily select next available flower
            boolean[] bool = new boolean[5]; // 4 flowers numbered 1 -> 4
            for(int neighbor : adj.get(garden)){
                bool[answer[neighbor]] = true;
            }
            // pick next available flower
            for(int i = 1; i <= 4; i++){
                if(!bool[i]){
                    answer[garden] = i;
                    break;
                }
            }
        }
        return answer;
    }
}