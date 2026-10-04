class Solution {
    public int[] parent;
    public int[] rank;
    public void unionFind(int n){
        parent = new int[n];
        rank = new int[n];
        for(int i = 0; i < n; i++){
            parent[i] = i;
        }
    }
    public int find(int x){
        if(parent[x] != x){
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    public void union(int x, int y){
        int rootX = find(x);
        int rootY = find(y);
        if(rootX == rootY)
            return; //already merged
        if(rank[rootX] < rank[rootY]){
            parent[rootX] = rootY;
        }else if (rank[rootY] < rank[rootX]){
            parent[rootY] = rootX;
        } else{
            parent[rootY] = rootX;
            rank[rootX]++;
        }
    }
    public Map<Integer, List<String>> mergeEmails(Map<String, Integer> emailMap){
        //for each email, add to its parent's email list
        Map<Integer, List<String>> accountMap = new HashMap<>();
        for(Map.Entry<String, Integer> entry : emailMap.entrySet()){
            String email = entry.getKey();
            int acc = entry.getValue();
            acc = find(acc);
            accountMap.computeIfAbsent(acc, v -> new ArrayList<>()).add(email);
        }
        return accountMap;
    }
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        //Model each account as a union-find node
        int n = accounts.size();
        unionFind(n);
        //map to store email -> account relation
        Map<String, Integer> emailMap = new HashMap<>();
        for(int i = 0; i < n; i++){
            int m = accounts.get(i).size();
            int currAcc = i;
            for(int j = 1; j < m; j++){
                String email = accounts.get(i).get(j);
                //if email is already mapped to a different account
                if(emailMap.containsKey(email)){
                    int acc = emailMap.get(email);
                    union(acc, currAcc);
                }
                //put email in emailMap with parent account
                emailMap.put(email, find(currAcc));
            }
        }
        //All emails mapped to parent accounts, now merge emails : acc -> all emails
        Map<Integer, List<String>> accountMap = mergeEmails(emailMap);
        //sort emails for each account and add to result
        List<List<String>> result = new ArrayList<>();
        for(Map.Entry<Integer, List<String>> entry : accountMap.entrySet()){
            int acc = entry.getKey();
            List<String> emails = entry.getValue();
            Collections.sort(emails);
            List<String> res = new ArrayList<>();
            res.add(accounts.get(acc).get(0)); // get account name from account index
            res.addAll(emails); //then add all emails in sorted order
            //add entry to final result
            result.add(res);
        }
        return result;
    }
}