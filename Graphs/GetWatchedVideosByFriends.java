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
        int max = 0;
        for (int friend : friendsAtLevel) {
            for (String video : watchedVideos.get(friend)) {
                int freq = map.getOrDefault(video, 0) + 1;
                map.put(video, freq);
                max = Math.max(max, freq);
            }
        }
        //bucket sort freq
        List<String>[] bucket = new List[max + 1];
        for(int i = 0; i <= max; i++){
            bucket[i] = new ArrayList<>();
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String video = entry.getKey();
            int freq = entry.getValue();
            bucket[freq].add(video);
        }
        List<String> result = new ArrayList<>();
        for (int i = 0; i <= max; i++) {
            Collections.sort(bucket[i]);
            for (String video : bucket[i]) {
                result.add(video);
            }
        }
        return result;
    }
}