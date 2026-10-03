class Solution {
    public int minReorder(int n, int[][] connections) {
        //Prepare adj list with 2 entries - flag 1: original direction; flag 0: reverse/helper direction
        List<List<int[]>> adj = new ArrayList<>();
        //initialize adj list
        for(int i = 0; i < n; i++){
            adj.add(new ArrayList<>());
        }
        for(int[] road : connections){
            int a = road[0];
            int b = road[1];
            adj.get(a).add(new int[]{b, 1}); // Flag 1 for road pointing outward
            adj.get(b).add(new int[]{a, 0}); // Flag 0 for reverse/helper edge
        }
        //Start BFS from 0 -> walking outward from 0
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        int reversals = 0;
        boolean[] visited = new boolean[n];
        while(!queue.isEmpty()){
            int city = queue.poll();
            visited[city] = true;
            //get neighbors
            for(int[] neighbor : adj.get(city)){
                int nextCity = neighbor[0];
                int needReversal = neighbor[1]; //1 if traversing in BFS direction, so needs reversal
                if(visited[nextCity])
                    continue; //Already visited this city

                reversals += needReversal;
                queue.offer(nextCity);
            }
        }
        return reversals;
    }
}