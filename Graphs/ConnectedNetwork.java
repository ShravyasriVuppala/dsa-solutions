import java.util.List;
import java.util.ArrayList;

class Solution {
    public void dfs(List<List<Integer>> adj, int r, boolean[] visited){
        visited[r] = true;
        for(int c : adj.get(r)){
            if(!visited[c])
                dfs(adj, c, visited);
        }
    }
    public int makeConnected(int n, int[][] connections) {
        if(connections.length < n - 1)
            return -1; //not enough cables to connect all n computers

        //form the adjacency list where adj.get(i) holds the neighbors of i
        List<List<Integer>> adj = new ArrayList<>();
        for(int i = 0; i < n; i++)
            adj.add(new ArrayList<>());
        for(int[] conn : connections){
            int a = conn[0], b = conn[1];
            adj.get(a).add(b);
            adj.get(b).add(a);
        }
        boolean[] visited = new boolean[n];
        int components = 0;
        for(int i = 0; i < n; i++){
            if(!visited[i]){
                dfs(adj, i, visited);
                components++;
            }
        }

        return components - 1;
    }
}