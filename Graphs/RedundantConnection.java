class Solution {
    public int[] parent; //Stores parent of the connected component it belongs to
    public int[] rank; //rank of parent of connected component
    public int find(int x){
        //path compression, maps and returns parent of the connected component this x belongs to
        if(parent[x] != x){
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    public boolean union(int x, int y){
        int rootX = find(x);
        int rootY = find(y);
        //if already connected, adding this edge creates cycle, so return false
        if(rootX == rootY){
            return false;
        }
        //connect the components
        if(rank[rootX] < rank[rootY]){
            parent[rootX] = rootY;
        }
        else if(rank[rootY] < rank[rootX]){
            parent[rootY] = rootX;
        }
        else{
            parent[rootY] = rootX;
            rank[rootX]++;
        }
        return true; //connected the 2 components
    }
    public int[] findRedundantConnection(int[][] edges) {
        //initialize union-find
        int n = edges.length;
        parent = new int[n+1];
        rank = new int[n+1];
        int[] result = new int[2];
        for(int i = 1; i <= n; i++){
            parent[i] = i;
        }
        for(int[] edge : edges){
            int x = edge[0];
            int y = edge[1];
            if(!union(x, y)){
                //if union return false, this is a redundant edge and adding this would form a cycle
                result[0] = x;
                result[1] = y;
            }
        }
        return result;
    }
}