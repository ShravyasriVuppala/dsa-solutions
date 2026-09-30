class Solution {
    public int orangesRotting(int[][] grid) {
        if(grid == null || grid.length == 0)
            return 0;
        //Get fresh oranges count and push rotten oranges to queue
        Queue<int[]> queue = new LinkedList<>();
        int freshOranges = 0;
        int n = grid.length, m = grid[0].length;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                //fresh orange, increment fresh count
                if(grid[i][j] == 1)
                    freshOranges++;
                //rotten orange, push to queue
                else if(grid[i][j] == 2)
                    queue.offer(new int[]{i, j});
            }
        }
        //Start BFS from rotten oranges and keep track of minutes
        int minutes = 0;
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        while(!queue.isEmpty()){
            int size = queue.size();
            int rottenThisRound = 0; //if no rotten oranges, no need to increment minutes
            //get neighbors for each rotten orange in queue
            for(int i = 0; i < size; i++){
                int[] cell = queue.poll();
                int x = cell[0], y = cell[1];
                //neighbors of cell
                for(int j = 0; j < 4; j++){
                    int nx = x + dx[j];
                    int ny = y + dy[j];
                    //check if new cell is valid and has a fresh orange
                    if(nx>= 0 && nx < n && ny >= 0 && ny < m && grid[nx][ny] == 1){
                        rottenThisRound++;
                        freshOranges--;
                        queue.offer(new int[]{nx, ny});
                        grid[nx][ny] = 2; //mark as rotten
                    }
                }

            }
            if(rottenThisRound > 0)
                minutes++;
        }
        //fresh oranges left then return -1 as its impossible to rot all
        return freshOranges > 0 ? -1 : minutes;
    }
}