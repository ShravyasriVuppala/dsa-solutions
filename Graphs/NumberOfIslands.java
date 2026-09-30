class Solution {
    public void dfs(char[][] grid, int i, int j, int n, int m){
        //base case
        if(i < 0 || i >= n || j < 0 || j >= m || grid[i][j] != '1')
            return;

        //mark cell as visited
        grid[i][j] = '0';

        //dfs in all 4 directions
        dfs(grid, i+1, j, n, m);
        dfs(grid, i-1, j, n, m);
        dfs(grid, i, j+1, n, m);
        dfs(grid, i, j-1, n, m);
    }
    public int numIslands(char[][] grid) {
        //dfs from each cell marking visited cells
        int n = grid.length, m = grid[0].length, ans = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == '1'){
                    dfs(grid, i, j, n, m);
                    ans++;
                }
            }
        }
        return ans;
    }
}