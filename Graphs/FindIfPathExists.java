class Solution {
    public void dfs(int node, boolean[] visited, List<List<Integer>> adj){
        visited[node] = true;
        //get neighbors
        for(int neighbor : adj.get(node)){
            if(!visited[neighbor]){
                dfs(neighbor, visited, adj);
            }
        }
    }
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        // build adj list
        List<List<Integer>> adj = new ArrayList<>();
        for(int i = 0; i < n; i++){
            adj.add(new ArrayList<>());
        }
        for(int[] edge : edges){
            int a = edge[0];
            int b = edge[1];
            adj.get(a).add(b);
            adj.get(b).add(a);
        }
        boolean[] visited = new boolean[n];
        //dfs from source and see if destination is visited
        dfs(source, visited, adj);
        return visited[destination];
    }
}