public class RottenOranges {
    public int solve(int[][] A) {
        if(A == null || A.length == 0 || A[0].length == 0)
            return 0;
        //count fresh oranges and queue the list of rotten oranges for BFS
        int m = A.length, n = A[0].length, freshOranges = 0;
        Queue<int[]> queue = new LinkedList<>();
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(A[i][j] == 1)
                    freshOranges++;
                else if(A[i][j] == 2)
                    queue.offer(new int[]{i,j});
            }
        }
        if(freshOranges == 0) return 0; //no fresh oranges to be rotten
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        int mins = 0;
        while(!queue.isEmpty()){
            int rotted = 0;
            int size = queue.size();
            for(int i = 0; i < size; i++){
                int[] cell = queue.poll();
                int x = cell[0], y = cell[1];
                for(int d = 0; d < 4; d++){
                    int nx = x + dx[d];
                    int ny = y + dy[d];
                    if(nx >= 0 && nx < m && ny >= 0 && ny < n && A[nx][ny] == 1){
                        // mark as rotten, increment rotted, decrement freshoranges and add to queue
                        A[nx][ny] = 2;
                        freshOranges--;
                        rotted++;
                        queue.offer(new int[]{nx, ny});
                    }
                }
            }
            if(rotted > 0)
                mins++;
        }
        return freshOranges > 0 ? -1 : mins;
    }
}
