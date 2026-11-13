

class Solution {
    public int[] shortestAlternatingPaths(int n,
                                          int[][] redEdges,
                                          int[][] blueEdges) {

        List<Integer>[][] graph = new ArrayList[n][2];

        for (int i = 0; i < n; i++) {
            graph[i][0] = new ArrayList<>(); // Red
            graph[i][1] = new ArrayList<>(); // Blue
        }

        for (int[] edge : redEdges) {
            graph[edge[0]][0].add(edge[1]);
        }

        for (int[] edge : blueEdges) {
            graph[edge[0]][1].add(edge[1]);
        }

        int[] answer = new int[n];
        Arrays.fill(answer, -1);

        boolean[][] visited = new boolean[n][2];

        // State: [node, color of last edge]
        Queue<int[]> queue = new ArrayDeque<>();

        // Start with both possible last-edge colors
        queue.offer(new int[]{0, 0});
        queue.offer(new int[]{0, 1});

        visited[0][0] = true;
        visited[0][1] = true;

        int distance = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();

                int node = current[0];
                int lastColor = current[1];

                if (answer[node] == -1) {
                    answer[node] = distance;
                }

                // Next edge must have the opposite color
                int nextColor = 1 - lastColor;

                for (int neighbor : graph[node][nextColor]) {
                    if (!visited[neighbor][nextColor]) {
                        visited[neighbor][nextColor] = true;
                        queue.offer(new int[]{neighbor, nextColor});
                    }
                }
            }

            distance++;
        }

        return answer;
    }
}
