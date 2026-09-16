class Solution {
    public int minimumObstacles(int[][] grid) {
        //Use Deque for BFS to find minimum obstacles between source and destination
        //Let cost be 0 to move to empty cell, 1 to move to obstacle
        //queue empty cell to front, obstacle to last of the queue so that they will be processed later
        Deque<int[]> deque = new ArrayDeque<>();
        int m = grid.length, n = grid[0].length;
        int[][] distance = new int[m][n];
        //initialize distances to max value
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                distance[i][j] = Integer.MAX_VALUE;
            }
        }
        distance[0][0] = 0;
        //add first cell to queue
        deque.offer(new int[]{0,0});
        //directions
        int[][] directions = {{0,1},{1,0},{0,-1},{-1,0}};
        while(!deque.isEmpty()){
            int[] cell = deque.poll();
            int x = cell[0], y = cell[1];
            //traverse all directions
            for(int[] dir : directions){
                int nx = x + dir[0];
                int ny = y + dir[1];
                //check bounds
                if(nx < 0 || nx >= m || ny < 0 || ny >= n)
                    continue;
                int cost = grid[nx][ny];
                //queue only if cost to reach this cell is less that computed distance
                if(distance[x][y] + cost < distance[nx][ny]){
                    distance[nx][ny] = distance[x][y] + cost;
                    if(cost == 0)
                        deque.offerFirst(new int[]{nx, ny});
                    else
                        deque.offerLast(new int[]{nx, ny});
                }
            }
        }
        return distance[m-1][n-1];
    }
}