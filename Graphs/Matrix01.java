class Solution {
    public int[][] updateMatrix(int[][] mat) {
        //Multi-source BFS from all 0s
        int n = mat.length, m = mat[0].length;
        int[][] dist = new int[n][m]; //dist from nearest 0
        //queue -> <r, c>
        Queue<int[]> queue = new LinkedList<>();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(mat[i][j] == 0)
                    queue.offer(new int[]{i, j}); //start BFS from 0
                else dist[i][j] = -1;
            }
        }
        int[][] dir = {{-1,0}, {1,0}, {0, -1}, {0, 1}};
        while(!queue.isEmpty()){
            int[] cell = queue.poll();
            int r = cell[0];
            int c = cell[1];
            for(int[] d : dir){
                int nr = r + d[0];
                int nc = c + d[1];
                if(nr < 0 || nr >= n || nc < 0 || nc >= m)
                    continue;
                if(dist[nr][nc] == -1){ //distance not already computed
                    dist[nr][nc] = dist[r][c] + 1;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }
        return dist;
    }
}