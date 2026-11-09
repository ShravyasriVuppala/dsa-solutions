class Solution {
    public int minOperations(String[] logs) {
        // every move to folder is recorded in stack
        Deque<String> st = new ArrayDeque<>();
        for(String log : logs){
            if(log.equals("../")){
                // move to parent folder
                if(st.isEmpty()){
                    continue;
                }
                st.pop();
            }
            else if(log.equals("./")){
                continue;
            }
            else{
                // move to child folder
                st.push(log.substring(0, log.length()-1));
            }
        }
        return st.size();
    }
}