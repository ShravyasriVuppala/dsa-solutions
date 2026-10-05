import java.util.Arrays;

class Solution {
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;

        boolean[] visited = new boolean[n];

        // minDist[i] = minimum cost to connect point i
        // to any point already in the MST.
        int[] minDist = new int[n];
        Arrays.fill(minDist, Integer.MAX_VALUE);

        // Start MST from point 0.
        minDist[0] = 0;

        int totalCost = 0;

        for (int count = 0; count < n; count++) {

            // Find the unvisited point with the smallest connection cost.
            int curr = -1;

            for (int i = 0; i < n; i++) {
                if (!visited[i] &&
                        (curr == -1 || minDist[i] < minDist[curr])) {
                    curr = i;
                }
            }

            // Add this point to the MST.
            visited[curr] = true;
            totalCost += minDist[curr];

            // Update cost of connecting every remaining point
            // through the newly added point.
            for (int next = 0; next < n; next++) {
                if (!visited[next]) {
                    int distance =
                            Math.abs(points[curr][0] - points[next][0]) +
                                    Math.abs(points[curr][1] - points[next][1]);

                    minDist[next] =
                            Math.min(minDist[next], distance);
                }
            }
        }

        return totalCost;
    }
}