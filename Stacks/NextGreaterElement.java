class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        //Monotonic Decreasing Stack - pop smaller elements from stack while puching current element
        Deque<Integer> st = new ArrayDeque<>();
        Map<Integer, Integer> map = new HashMap<>(); // store ans for key element
        for(int num : nums2){
            //pop smaller elements and set answer
            while(!st.isEmpty() && st.peek() < num){
                int key = st.pop();
                map.put(key, num);
            }
            st.push(num);
        }
        int[] result = new int[nums1.length];
        for(int i = 0; i < nums1.length; i++){
            result[i] = map.getOrDefault(nums1[i], -1);
        }
        return result;
    }
}