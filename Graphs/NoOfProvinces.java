class NoOfProvinces {
    public void dfs(int[][] isConnected, boolean[] visited, int r, int n){
        visited[r] = true;
        // get connected provinces
        for(int c = 0; c < n; c++){
            if(isConnected[r][c] == 1 && !visited[c]){
                dfs(isConnected, visited, c, n);
            }
        }
    }
    public int findCircleNum(int[][] isConnected) {
        if(isConnected == null || isConnected.length == 0)
            return 0;
        int m = isConnected.length, n = isConnected[0].length, ans = 0;
        boolean[] visited = new boolean[m];
        for(int i = 0; i < m; i++){
            if(!visited[i]){
                dfs(isConnected, visited, i, n);
                ans++;
            }
        }
        return ans;
    }
}
