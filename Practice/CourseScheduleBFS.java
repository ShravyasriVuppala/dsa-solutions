class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //build adjacency list a -> dependents of a
        //list : a -> count of prereq courses to start a
        List<Integer>[] dependents = new List[numCourses];
        int[] preReqs = new int[numCourses];
        //initilaize and populate adjacency list
        for(int i = 0; i < numCourses; i++){
            dependents[i] = new ArrayList<>();
        }
        for(int[] prereq : prerequisites){
            int a = prereq[0], b = prereq[1];
            //a is dependent of b
            dependents[b].add(a);
            preReqs[a]++;
        }
        //Get all courses that can be started immediately(no prepreqs) and start BFS from these nodes
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < numCourses; i++){
            if(preReqs[i] == 0)
                queue.offer(i);
        }
        //BFS
        int taken = 0; // no. of courses taken so far
        while(!queue.isEmpty()){
            int course = queue.poll();
            taken++;
            //check if dependents can be started
            for(int dependent : dependents[course]){
                preReqs[dependent]--; //decrement prereq as current course is taken
                if(preReqs[dependent] == 0) //no more prereqs, can be started
                    queue.offer(dependent);
            }
        }
        return numCourses == taken;
    }
}