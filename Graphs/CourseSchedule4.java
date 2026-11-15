class Solution {
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        // floyd-warshall to determine if direct/indirect prerequisites between all courses
        boolean[][] reachable = new boolean[numCourses][numCourses];
        // mark direct prerequisites
        for(int[] prereq : prerequisites){
            int u = prereq[0];
            int v = prereq[1];
            reachable[u][v] = true; // u is prereq of v
        }
        // mark indirect prerequisites using floyd-warshall
        // for every intermediate k, if i is prereq of k and if k is prereq of j, then i is prereq of j
        for(int k = 0; k < numCourses; k++){
            for(int i = 0; i < numCourses; i++){
                for(int j = 0; j < numCourses; j++){
                    if(reachable[i][k] && reachable[k][j]){
                        reachable[i][j] = true;
                    }
                }
            }
        }
        // set answer for all queries in O(1)
        List<Boolean> answer = new ArrayList<>();
        for(int[] query : queries){
            int u = query[0];
            int v = query[1];
            answer.add(reachable[u][v]);
        }
        return answer;
    }
}