class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // Fixed-round updates instead of Dijkstra, since a "settle once"
        // visited check would wrongly discard a pricier-but-fewer-stops
        // route that's needed to stay within the stop budget.
        int[] dist = new int[n]; //dist[i] = min price to reach city i
        int INF = Integer.MAX_VALUE;
        Arrays.fill(dist, INF);
        dist[src] = 0;
        for(int i = 0; i <= k; i++){ //at most k stops = at most k+1 flights
            int[] next = dist.clone(); //write into a copy so each round only adds one more flight
            for(int[] flight : flights){
                int from = flight[0];
                int to = flight[1];
                int price = flight[2];
                if(dist[from] != INF){
                    next[to] = Math.min(next[to], dist[from] + price);
                }
            }
            dist = next;
        }
        return dist[dst] == INF ? -1 : dist[dst];
    }
}