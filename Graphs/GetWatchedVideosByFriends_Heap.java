class Solution {
    public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        //BFS with distance "level" to get friends
        //Then compute frequencies of watched videos and put in minHeap
        // Queue item : <friend, level>
        int n = friends.length;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{id, 0}); //push my id to queue to start BFS to find friends at level
        List<Integer> friendsAtLevel = new ArrayList<>();
        boolean[] visited = new boolean[n];
        visited[id] = true;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int currid = curr[0];
            int currlevel = curr[1];
            // curr is at required level, no need to go to next level
            if (currlevel == level) {
                friendsAtLevel.add(currid);
                continue;
            }
            //get friends at next level
            for (int friend : friends[currid]) {
                if (visited[friend]) {
                    continue;
                }
                visited[friend] = true;
                queue.offer(new int[]{friend, currlevel + 1});
            }

        }
        //get freq of watchedvideos of friendsAtLevel
        Map<String, Integer> map = new HashMap<>();
        for (int friend : friendsAtLevel) {
            for (String video : watchedVideos.get(friend)) {
                map.put(video, map.getOrDefault(video, 0) + 1);
            }
        }
        //push <video, freq> pair to minHeap, ordered by freq then lexicographically
        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>((a, b) -> {
            if (!a.getValue().equals(b.getValue()))
                return a.getValue() - b.getValue();
            return a.getKey().compareTo(b.getKey());
        });
        minHeap.addAll(map.entrySet());

        List<String> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            result.add(minHeap.poll().getKey());
        }
        return result;
    }
}