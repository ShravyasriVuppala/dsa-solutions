class Solution {
    public boolean isSafe(int node, int[] state, int[][] graph){
        if(state[node] == 1){
            return false; //revisiting node in same dfs, detected cycle
        }
        if(state[node] == 2){
            return true; //already visited node
        }
        state[node] = 1; //mark as visiting
        //get neighbors
        for(int neighbor : graph[node]){
            if(!isSafe(neighbor, state, graph)){
                return false;
            }
        }
        state[node] = 2; //mark as visited, all neighbors resolved
        return true; // no cycle detected for all nodes in current dfs
    }
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        //state - not visited : 0; visiting : 1; visited : 2
        int[] state = new int[n];
        List<Integer> result = new ArrayList<>();
        for(int i = 0; i < n; i++){
            if(isSafe(i, state, graph)){
                result.add(i);
            }
        }
        return result;
    }
}