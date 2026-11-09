class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        //BFS to find order of course completion
        //populate dependency list and inDegree for each course
        //course with 0 inDegree can start immediately
        int[] inDegree = new int[numCourses];
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        //initialize dependency list
        for(int i = 0; i < numCourses; i++){
            adj.add(new ArrayList<>());
        }
        for(int[] prerequisite : prerequisites){
            int a = prerequisite[0];
            int b = prerequisite[1];
            // a is dependent on b => b has a as dependent
            adj.get(b).add(a);
            inDegree[a]++; // course needs to be completed before a
        }
        //start bfs with courses that are ready to be completed(with inDegree 0)
        Deque<Integer> queue = new ArrayDeque<>();
        for(int i = 0; i < numCourses; i++){
            if(inDegree[i] == 0){
                queue.offer(i);
            }
        }
        int[] result = new int[numCourses]; //course completion order
        int i = 0;
        while(!queue.isEmpty()){
            int course = queue.poll();
            result[i++] = course;
            //check if dependent courses can be started
            for(int dependent : adj.get(course)){
                inDegree[dependent]--;
                if(inDegree[dependent] == 0){
                    queue.offer(dependent);
                }
            }
        }
        return (i == numCourses)? result : new int[]{};
    }
}