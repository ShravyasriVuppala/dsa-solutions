class FloodFill {
    public void dfs(int[][] image, int i, int j, int color, int newColor, int m, int n){
        //base case
        if(i < 0 || i >= m || j < 0 || j >= n || image[i][j] != color || image[i][j] == newColor)
            return;
        image[i][j] = newColor;
        dfs(image, i+1, j, color, newColor, m, n);
        dfs(image, i-1, j, color, newColor, m, n);
        dfs(image, i, j+1, color, newColor, m, n);
        dfs(image, i, j-1, color, newColor, m, n);
    }
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        if(image == null || image.length == 0)
            return image;
        int m = image.length, n = image[0].length;
        dfs(image, sr, sc, image[sr][sc], color, m, n);
        return image;
    }
}