class Solution {
    public int[] parent; //parent of the province the city belongs to
    public int[] rank; //rank of the parent of the province
    public int find(int x){
        //path compression and find the parent of province the x belongs to
        if(parent[x] != x){
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    public void union(int x, int y){
        //make the cities connected under one province
        int parentX = find(x);
        int parentY = find(y);

        if(parentX == parentY){
            //cities are already connected
            return;
        }

        if(rank[parentX] < rank[parentY]){
            parent[parentX] = parentY;
        } else if (rank[parentY] < rank[parentX]) {
            parent[parentY] = parentX;
        } else {
            parent[parentY] = parentX;
            rank[parentX]++;
        }
    }
    public int findCircleNum(int[][] isConnected) {
        //union-find
        int n = isConnected.length;
       parent = new int[n];
       rank = new int[n];
       //initialize parent
        for(int i = 0; i < n; i++){
            parent[i] = i;
        }
        int provinces = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(isConnected[i][j] == 1){
                    union(i, j);
                }
            }
        }
        //get the connected components
        for(int i = 0; i < n; i++){
            if(parent[i] == i)
                provinces++;
        }
        return provinces;
    }
}