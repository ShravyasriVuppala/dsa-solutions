class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[] dist = new int[n]; //dist[i] = min price to reach city i
        int INF = Integer.MAX_VALUE;
        Arrays.fill(dist, INF);
        dist[src] = 0;
        //atmost k stops meaning k+1 flights allowed
        for(int i = 0; i <= k; i++){
            int[] next = dist.clone();
            for(int[] flight : flights){
                int from = flight[0];
                int to = flight[1];
                int price = flight[2];
                if(dist[from] != INF){ //update min price to reach the city in round i
                    next[to] = Math.min(next[to], dist[from] + price);
                }
            }
            dist = next.clone();
        }
        return dist[dst] == INF ? -1 : dist[dst];
    }
}