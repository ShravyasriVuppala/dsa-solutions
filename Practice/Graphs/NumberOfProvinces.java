class Solution {
    public void dfs(int city, int[][] isConnected, boolean[] visited){
        //mark visited
        visited[city] = true;
        //get neighbors
        for(int c = 0; c < isConnected.length; c++){
            if(!visited[c] && isConnected[city][c] == 1){ //if cities are connected
                dfs(c, isConnected, visited);
            }
        }
    }
    public int findCircleNum(int[][] isConnected) {
        //dfs from each city to find number of connected components
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int provinces = 0;
        for(int i = 0; i < n; i++){
            if(!visited[i]){
                dfs(i, isConnected, visited);
                provinces++;
            }
        }
        return provinces;
    }
}