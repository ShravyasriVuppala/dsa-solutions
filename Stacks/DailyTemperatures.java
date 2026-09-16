class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        //monotone decreasing stack - pop smaller ones before push
        Stack<int[]> stack = new Stack<>(); //store temp,idx
        stack.push(new int[]{temperatures[0], 0});
        int[] ans = new int[temperatures.length];
        for(int i = 1; i < temperatures.length; i++){
            while(!stack.isEmpty()){
                int[] top = stack.peek();
                if(top[0] < temperatures[i]){
                    ans[top[1]] = i - top[1];
                    stack.pop();
                }
                else break;
            }
            stack.push(new int[]{temperatures[i], i});
        }
        return ans;
    }
}