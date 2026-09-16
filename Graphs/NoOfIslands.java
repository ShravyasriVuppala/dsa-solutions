public class NoOfIslands {
    public void dfs(int[][] A, int i, int j, int m, int n){
        //base case
        if(i < 0 || i >= m || j < 0 || j >= n || A[i][j] == 0)
            return;
        A[i][j] = 0; //mark cell as visited
        dfs(A, i+1, j, m, n);
        dfs(A, i-1, j, m, n);
        dfs(A, i, j+1, m, n);
        dfs(A, i, j-1, m, n);
        dfs(A, i-1, j-1, m, n);
        dfs(A, i-1, j+1, m, n);
        dfs(A, i+1, j+1, m, n);
        dfs(A, i+1, j-1, m, n);
    }
    public int solve(int[][] A) {
        if(A == null || A.length == 0)
            return 0;
        int ans = 0, m = A.length, n = A[0].length;
        //iterate on the matrix and start DFS from cell whose value is 1
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(A[i][j] == 1) {
                    dfs(A, i, j, m, n);
                    ans++;
                }
            }
        }
        return ans;
    }
}
