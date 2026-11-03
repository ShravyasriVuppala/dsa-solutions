class Solution {
    public void dfs(int room, boolean[] visited, List<List<Integer>> rooms){
        //mark room as visited
        visited[room] = true;
        //get keeys to unlock other rooms
        for(int nextRoom : rooms.get(room)){
            if(!visited[nextRoom]){
                dfs(nextRoom, visited, rooms);
            }
        }

    }
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        //DFS and check if all rooms are visited
        int n = rooms.size();
        boolean[] visited = new boolean[n];
        dfs(0, visited, rooms);
        for(int i = 0; i < n; i++){
            if(!visited[i]){
                return false;
            }
        }
        return true;
    }
}