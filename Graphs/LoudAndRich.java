class Solution {
    public int dfs(int person, List<List<Integer>> adj, int[] quiet, int[] answer){
        //dfs with memoization

        // if answer already populated, return answer
        if(answer[person] != -1){
            return answer[person];
        }
        // answer of person is itself initially
        answer[person] = person;

        //get richer persons
        for(int richer : adj.get(person)){
            int candidate = dfs(richer, adj, quiet, answer);
            if(quiet[candidate] < quiet[answer[person]]){
                answer[person] = candidate;
            }
        }
        return answer[person];
    }
    public int[] loudAndRich(int[][] richer, int[] quiet) {
        // build adj list
        List<List<Integer>> adj = new ArrayList<>();
        int n = quiet.length;
        // initialize adj list
        for(int i = 0; i < n; i++){
            adj.add(new ArrayList<>());
        }
        for(int[] pair : richer){
            int a = pair[0];
            int b = pair[1];
            // a is richer than b
            adj.get(b).add(a);
        }
        int[] answer = new int[n];
        Arrays.fill(answer, -1);
        // dfs from all persons
        for(int i = 0; i < n; i++){
            dfs(i, adj, quiet, answer);
        }

        return answer;
    }
}