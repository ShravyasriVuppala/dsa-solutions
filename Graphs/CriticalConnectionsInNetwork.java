class Solution {
    int[] disc, low;
    List<List<Integer>> ans = new ArrayList<>();
    int timer = 0;
    public void dfs(int node, int parent, List<List<Integer>> adj){
        disc[node] = low[node] = timer++;

        for(int nei : adj.get(node)){
            if(nei == parent) //parent
                continue;
            if(disc[nei] == -1){
                dfs(nei, node, adj);
                // Child might reach an earlier ancestor
                low[node] = Math.min(low[node], low[nei]);

                // No alternate path to node or its ancestors
                if (low[nei] > disc[node]) {
                    ans.add(Arrays.asList(node, nei));
                }
            }
            else{
                // Back edge to an already visited node
                low[node] = Math.min(low[node], disc[nei]);
            }
        }
    }
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        //dfs and check if child is connected to grandparent if parent link removed
        //set discovered time for each node
        disc = new int[n];
        //low -> connected to node discovered at time low[i]
        low = new int[n];
        Arrays.fill(disc, -1);
        //build adj list
        List<List<Integer>> adj = new ArrayList<>();
        for(int i = 0; i < n; i++){
            adj.add(new ArrayList<>());
        }
        for(List<Integer> edge : connections){
            int a = edge.get(0);
            int b = edge.get(1);
            adj.get(a).add(b);
            adj.get(b).add(a);
        }

        for (int i = 0; i < n; i++) {
            if (disc[i] == -1) {
                dfs(i, -1, adj);
            }
        }
        return ans;
    }
}