import java.util.*;

class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        //Dijkstra: process nodes in increasing order of current shortest time
        int[] minTimes = new int[n+1];
        Arrays.fill(minTimes, Integer.MAX_VALUE);
        minTimes[k] = 0;
        ArrayList<ArrayList<int[]>> adj = new ArrayList<>();
        for(int i = 0; i <= n; i++){
            adj.add(new ArrayList<>());
        }
        for(int[] time : times){
            int[] conn = {time[1], time[2]};
            adj.get(time[0]).add(conn);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[]{k, 0});
        boolean[] visited = new boolean[n+1];
        while(!pq.isEmpty()){
            int[] node = pq.poll();
            int a = node[0];
            int currtime = node[1];
            if(visited[a])
                continue;
            visited[a] = true;
            for(int[] neighbor : adj.get(a)){
                int newTime = currtime + neighbor[1];
                if(newTime < minTimes[neighbor[0]]){
                    minTimes[neighbor[0]] = newTime;
                    pq.offer(new int[]{neighbor[0], newTime});
                }
            }
        }
        //if minTimes has Integer.MaxValue for any node, return -1; else compute max of all times
        int ans = 0;
        for(int i = 1; i <= n; i++){
            if(minTimes[i] == Integer.MAX_VALUE)
                return -1;
            ans = Math.max(ans, minTimes[i]);
        }
        return ans;
    }
}
