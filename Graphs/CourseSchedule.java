class Solution {
    public boolean hasCycle(int node, int[] state, List<Integer>[] dependents){

        if(state[node] == 1) return true; //if state = visiting , cycle detected
        if(state[node] == 2) return false; // already visited, no need to traverse from here

        state[node] = 1; // mark as visiting

        //travserse on dependents and check if cycle exists
        for(int dependent : dependents[node]){
            if(hasCycle(dependent, state, dependents))
                return true;
        }

        state[node] = 2; // mark as visited
        return false;
    }
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //cycle detection in DFS
        //build adjacency matrix
        List<Integer>[] dependents = new List[numCourses];
        for(int i = 0; i < numCourses; i++){
            dependents[i] = new ArrayList<>();
        }
        for(int[] prereq : prerequisites){
            int a = prereq[0], b = prereq[1];
            dependents[b].add(a);
        }
        //state array for each course; 0-not visited, 1- visiting(currently in stack), 2- visited already
        int[] state = new int[numCourses];
        for(int i = 0; i < numCourses; i++){
            if(state[i] == 0 && hasCycle(i, state, dependents))
                return false; //cycle found
        }
        return true; //no cycle
    }
}