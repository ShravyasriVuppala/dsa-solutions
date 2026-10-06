class Solution {
    public int[] parent;
    public int[] rank;
    public void unionFind(int n){
        //initialize union find
        parent = new int[n];
        rank = new int[n];
        for(int i = 0; i < n; i++){
            parent[i] = i;
        }
    }
    public int find(int x){
        if(parent[x] != x){
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    public void union(int x, int y){
        int rootX = find(x);
        int rootY = find(y);
        //already connected in network; current cable is extra
        if(rootX == rootY){
            return;
        }
        //not yet connected, connect the computers
        if(rank[rootX] < rank[rootY]){
            parent[rootX] = rootY;
        } else if (rank[rootY] < rank[rootX]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
    }
    public int makeConnected(int n, int[][] connections) {
        //Get number of redundant cables and number of components
        //need atleast n - 1 cables to connect n computers; not enough cables
        if(connections.length < n - 1)
            return -1;
        //union find to get no of connected components
        unionFind(n);

        int components = 0;
        for(int[] cable : connections){
            int x = cable[0];
            int y = cable[1];
            union(x, y);
        }
        //Get number of connected components
        for(int i = 0; i < n; i++){
            if(i == find(i)){
                components++;
            }
        }
        //cant connect if components are more than extra cables needed to connect them
        return components - 1;
    }
}