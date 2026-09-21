class Solution {
    public int[] parent;
    public int[] rank;
    public int find(int x){
        if(parent[x] != x){
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    public void union(int x, int y){
        //find parents of x and y
        int parent_x = find(x);
        int parent_y = find(y);
        if(parent_y == parent_x) return;

        if(rank[parent_x] < rank[parent_y]){
            parent[parent_x] = parent_y;
        }
        else if(rank[parent_y] < rank[parent_x]){
            parent[parent_y] = parent_x;
        }
        else{
            parent[parent_y] = parent_x;
            rank[parent_x]++;
        }
    }
    public int makeConnected(int n, int[][] connections) {
        //if edges < nodes, cant connect; return -1
        if(connections.length < n - 1)
            return -1;
        //find components using union find
        // Initialize Union-Find
        parent = new int[n];
        rank = new int[n];
        //initialize parents to self
        for(int i = 0; i < n; i++)
            parent[i] = i;

        // union of nodes for each connection
        for(int[] conn : connections){
            union(conn[0], conn[1]);
        }
        //Get components
        int components = 0;
        for(int i = 0; i < n; i++){
            if(parent[i] == i)
                components++;
        }
        return components - 1;
    }
}