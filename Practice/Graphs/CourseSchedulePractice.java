class Solution {
    public boolean hasCycle(int course, int[] state, ArrayList<ArrayList<Integer>> dependents){
        //if state of course is currently visiting, cycle found. Return true
        if(state[course] == 1)
            return true;
        // if state is already visited, no need to traverse again, return false
        if(state[course] == 2)
            return false;
        //mark course as visiting
        state[course] = 1;
        // check for dependents to detect any cycle
        for(int dependent : dependents.get(course)){
            if(hasCycle(dependent, state, dependents))
                return true;
        }
        //mark as visited; no cycle
        state[course] = 2;
        return  false;
    }
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //if there is a cycle in courses, cant finish all
        //build adjacency matrix with dependents
        ArrayList<ArrayList<Integer>> dependents = new ArrayList<>();
        //initialize list
        for(int i = 0; i < numCourses; i++){
            dependents.add(new ArrayList<>());
        }
        //populate the dependents
        for(int[] prereq : prerequisites){
            int a = prereq[0];
            int b = prereq[1];
            //a is a dependent of b
            dependents.get(b).add(a);
        }
        //store state of nodes - 0 : not visited, 1: visiting, 2: visited
        int[] state = new int[numCourses];
        //check if there is any cycle
        for(int i = 0; i < numCourses; i++){
            if(state[i] == 0 && hasCycle(i, state, dependents))
                return false;
        }
        return true;
    }
}