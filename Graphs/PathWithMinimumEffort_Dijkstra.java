class Solution {
    public int minimumEffortPath(int[][] heights) {
        //Dijkstra on grid - minheap with max absolute diff in the path
        int n = heights.length;
        int m = heights[0].length;
        int[][] dist = new int[n][m];
        for(int[] row : dist){
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        // queue -> <distance, r, c>
        Queue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        minHeap.offer(new int[]{0, 0, 0});
        int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while(!minHeap.isEmpty()){
            int[] cell = minHeap.poll();
            int effort = cell[0];
            int r = cell[1];
            int c = cell[2];
            if(effort > dist[r][c])
                continue;
            //check if destination reached
            if(r == n - 1 && c == m - 1)
                return effort;
            //adjacent cells
            for(int[] d : dir){
                int nr = r + d[0];
                int nc = c + d[1];
                if(nr < 0 || nr >= n || nc < 0 || nc >= m)
                    continue;
                int edgeEffort = Math.abs(heights[r][c] - heights[nr][nc]);
                int newEffort = Math.max(effort, edgeEffort);
                if(newEffort < dist[nr][nc]){
                    dist[nr][nc] = newEffort;
                    minHeap.offer(new int[]{newEffort, nr, nc});
                }
            }
        }
        return 0;
    }
}