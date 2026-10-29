class Solution {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        //Find shortest distances between every pair of cities - Floyd Warshall
        //Get city connected with smallest # of cities at threshold distance

        int INF = Integer.MAX_VALUE;

        //dist[i][j] = shortest known distance between i and j
        int[][] dist = new int[n][n];
        //initialize dist
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                dist[i][j] = i == j ? 0 : INF;
            }
        }
        //traverse edges and populate known distances
        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            dist[u][v] = w;
            dist[v][u] = w;
        }

        /* Floyd-Warshall
           For every intermediate city k,
           check whether going through it gives us a shorter route between i and j.
         */
        for(int k = 0; k < n; k++){
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    //bound for integer overflow when distances are added
                    if(dist[i][k] != INF && dist[k][j] != INF){
                        dist[i][j] = Math.min(
                                dist[i][j],
                                dist[i][k] + dist[k][j]
                        );
                    }
                }
            }
        }

        //Get city with smallest # of connected cities at threshold distance
        int minCount = INF, answer = -1;
        for(int i = 0; i < n; i++){
            int count = 0;
            for(int j = 0; j < n; j++){
                if(i != j && dist[i][j] <= distanceThreshold){
                    count++;
                }
            }
            //get city with min count; if multiple, then override city with larger index
            if(count <= minCount){
                minCount = count;
                answer = i;
            }
        }
        return answer;

    }
}