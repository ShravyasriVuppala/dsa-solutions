class Solution {
    public List<List<Integer>> result;
    public void dfs(int node, int dst, List<Integer> path, int[][] graph){
        // add node to path
        //path.add(node);

        //base case : if node is destination, add path to result
        if(node == dst){
            result.add(new ArrayList<>(path));
            return;
        }

        // get connected nodes
        for(int neighbor : graph[node]){
            path.add(neighbor);
            dfs(neighbor, dst, path, graph);
            path.remove(path.size() - 1);
        }
        return;

    }
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        //get all paths from 0 to n-1 using dfs + backtracking
        result = new ArrayList<>();
        int n = graph.length;
        List<Integer> path = new ArrayList<>();
        path.add(0);
        dfs(0, n-1, path, graph);
        return result;
    }
}