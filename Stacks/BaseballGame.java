class Solution {
    public int calPoints(String[] operations) {
        Deque<Integer> st = new ArrayDeque<>();
        for(String op : operations){
            //System.out.println(op);
            if(op.equals("+")){
                int op1 = st.pop();
                int op2 = st.peek();
                st.push(op1);
                st.push(op1 + op2);
            }
            else if(op.equals("D")){
                st.push(2 * st.peek());
            }
            else if(op.equals("C")){
                st.pop();
            }
            else{
                st.push(Integer.parseInt(op));
            }
        }
        //calculate sum of all scores
        int ans = 0;
        while(!st.isEmpty()){
            ans += st.pop();
        }
        return ans;
    }
}