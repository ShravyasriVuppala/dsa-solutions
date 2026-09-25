class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        //cheapest path using atmost k+1 edges
        int[] dist = new int[n]; // min price to reach city i
        int INF = Integer.MAX_VALUE;
        Arrays.fill(dist, INF);
        dist[src] = 0;
        for(int round = 0; round <= k; round++){ // k stops = k + 1 flights
            int[] next = dist.clone();
            for(int[] flight : flights){
                int from = flight[0];
                int to = flight[1];
                int price = flight[2];
                if(dist[from] != INF){ // if from city has been visited already
                    next[to] = Math.min(next[to], dist[from] + price);
                }
            }
            dist = next;
        }
        return (dist[dst] == INF)? -1 : dist[dst];
    }
}