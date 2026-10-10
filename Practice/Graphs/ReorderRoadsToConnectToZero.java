class Solution {
    public int minReorder(int n, int[][] connections) {
        //Build adj list with original edge (flag 1) and reverse edge (flag 0)
        //instead of checking path from every city to city 0,
        //start BFS from 0 (outward) and count edges in the BFS direction which will need reversal
        //if edge is in the reverse direction of BFS, then it is in right direction

        List<List<int[]>> adj = new ArrayList<>();
        boolean[] visited = new boolean[n];

        //initialise adj list with n cities
        for(int i = 0; i < n; i++){
            adj.add(new ArrayList<>());
        }
        //Add original and reverse/help edges with flags
        for(int[] edge : connections){
            int a = edge[0];
            int b = edge[1];
            // a -> b original edge with flag 1
            adj.get(a).add(new int[]{b, 1});
            // b -> a reverse/help edges with flag 0
            adj.get(b).add(new int[]{a, 0});
        }
        //Start BFS from city 0
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        int reversals = 0;

        while(!queue.isEmpty()){

            int city = queue.poll();
            visited[city] = true;

            //get connected cities and calculate reversals
            for(int[] neighbor : adj.get(city)){
                int nextCity = neighbor[0];
                int flag = neighbor[1];
                if(visited[nextCity])
                    continue;

                queue.offer(nextCity);
                reversals += flag;
            }
        }

        return reversals;
    }
}