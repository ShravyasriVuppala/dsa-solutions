class Solution {
    public boolean isValidBracket(char open, char close){
        return (open == '(' && close == ')') || (open == '{' && close == '}') || (open == '[' && close == ']');
    }
    public boolean isValid(String s) {
        //if open bracket push into stack and pop if closing bracket
        Stack<Character> st = new Stack<>();
        for(char c : s.toCharArray()){
            //if open bracket, push
            if(c == '(' || c == '{' || c == '['){
                st.push(c);
            }
            //if close, check valid and pop
            else{
                if(st.isEmpty()){
                    return false;
                }
                if(isValidBracket(st.peek(), c)){
                    st.pop();
                }
                else{
                    return false;
                }
            }
        }
        return st.isEmpty()? true : false;
    }
}