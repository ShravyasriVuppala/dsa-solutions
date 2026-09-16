class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //BFS Topological Sort
        //Build adj matrix with dependents[a] = all courses that need a as prerequisite
        List<Integer>[] dependents = new List[numCourses];
        int[] inDegree = new int[numCourses]; // inDegree[a] = no. of courses a is dependent on
        for(int i = 0; i < numCourses; i++){
            dependents[i] = new ArrayList<>();
        }
        for(int[] prereq : prerequisites){
            int a = prereq[0], b = prereq[1];
            dependents[b].add(a);
            inDegree[a]++;
        }
        //Courses that are not dependent on any other course can be taken immediately
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < numCourses; i++){
            if(inDegree[i] == 0)
                queue.offer(i);
        }
        int taken = 0;
        while(!queue.isEmpty()){
            int node = queue.poll();
            taken++;
            for(int dependent : dependents[node]){
                inDegree[dependent]--; //remove node from courses dependent needs to complete
                if(inDegree[dependent] == 0)
                    queue.offer(dependent); // if this can be taken without any dependency, push to queue
            }
        }
        return taken == numCourses; //if all courses are taken, return true, else cant be achieved
    }
}