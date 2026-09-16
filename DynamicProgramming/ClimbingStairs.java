class Solution {
    public int climbStairs(int n) {
        //fibonacci approach. Can be solved via DP as well - dp[n] = dp[n-1] + dp[n-2]
        int a = 1, b = 1; //a: ways to reach step 0, b: ways to reach step 1
        if(n <= 1) return 1;
        for(int i = 2; i <= n; i++){
            int c = a + b;
            a = b;
            b = c;
        }
        return b;
    }
}