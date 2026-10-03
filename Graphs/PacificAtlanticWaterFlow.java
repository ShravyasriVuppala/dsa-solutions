class Solution {
    public void dfs(int r, int c, int[][] heights, boolean[][] visited){
        visited[r][c] = true;
        int[][] dir = {{-1, 0}, {1 , 0}, {0, -1}, {0, 1}};
        for(int[] d : dir){
            int nr = r + d[0];
            int nc = c + d[1];
            if(nr < 0 || nr >= heights.length || nc < 0 || nc >= heights[0].length)
                continue;
            if(!visited[nr][nc] && heights[nr][nc] >= heights[r][c]){
                //height of neighbor should be greater than or equals current cell
                dfs(nr, nc, heights, visited);
            }
        }
    }
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        //reverse DFS from border cells to check if a cell is reachable
        //boolean reachable matrix for both the oceans
        int rows = heights.length, cols = heights[0].length;
        boolean[][] pacific = new boolean[rows][cols];
        boolean[][] atlantic = new boolean[rows][cols];

        //start dfs from top border - pacific
        for(int c = 0; c < cols; c++){
            dfs(0, c, heights, pacific);
        }
        //start dfs from left border - pacific
        for(int r = 0; r < rows; r++){
            dfs(r, 0, heights, pacific);
        }
        //start dfs from bottom border - pacific
        for(int c = 0; c < cols; c++){
            dfs(rows - 1, c, heights, atlantic);
        }
        //start dfs from right border - pacific
        for(int r = 0; r < rows; r++){
            dfs(r, cols - 1, heights, atlantic);
        }
        //collect cells that are reachable from both atlantic and pacific
        List<List<Integer>> result = new ArrayList<>();
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                if(pacific[r][c] && atlantic[r][c])
                    result.add(Arrays.asList(r, c));
            }
        }
        return result;
    }
}