class Solution {
    public int[] finalPrices(int[] prices) {
        //find next smaller price - monotonic increasing stack
        int[] answer = new int[prices.length];
        Deque<Integer> st = new ArrayDeque<>();
        for(int i = 0; i < prices.length; i++){
            int price = prices[i];
            answer[i] = price;
            //pop price if current price <= prices in stack
            while(!st.isEmpty() && prices[st.peek()] >= price){
                answer[st.pop()] -= price;
            }
            st.push(i);
        }
        return answer;
    }
}