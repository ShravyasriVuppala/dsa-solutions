class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        //Adj list
        List<List<int[]>> adj = new ArrayList<>();
        //initialize adj list
        for(int i = 0; i <= n; i++){
            adj.add(new ArrayList<>());
        }
        //populate adj list
        for(int[] edge : times){
            int source = edge[0];
            int destination = edge[1];
            int time = edge[2];
            adj.get(source).add(new int[]{destination, time}); // s -> {<dst, time>}
        }
        //distance array dist[i] = min time to reach node i from source node k
        int[] dist = new int[n+1];
        int INF = Integer.MAX_VALUE;
        Arrays.fill(dist, INF);
        dist[k] = 0;
        //min heap with <node, current time>; poll closest node
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        minHeap.offer(new int[]{k, 0}); //starting from source node k with time 0
        while(!minHeap.isEmpty()){
            int[] curr = minHeap.poll();
            int currNode = curr[0];
            int currTime = curr[1];
            //check if this path takes min time to reach the node
            if(currTime > dist[currNode])
                continue; //no need to go this path, takes more time
            dist[currNode] = currTime;
            //check neighbors
            for(int[] edge : adj.get(currNode)){
                int neighbor = edge[0];
                int time = edge[1];
                int newTime = currTime + time;
                if(newTime < dist[neighbor]){
                    //this path leads to min time to reach neighbor
                    dist[neighbor] = newTime;
                    minHeap.offer(new int[]{neighbor, newTime});
                }

            }
        }
        int ans = 0;
        for(int i = 1; i <= n; i++){
            if(dist[i] == INF)
                return -1;
            else{
                ans = Math.max(ans, dist[i]);
            }
        }
        return ans;
    }
}