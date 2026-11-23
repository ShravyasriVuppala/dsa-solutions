class Solution {
    public String rearrangeString(String s, char x, char y) {
        // move all y characters to left
        char[] res = s.toCharArray();
        int l = 0;
        for(int r = 0; r < s.length(); r++){
            if(res[r] == y){
                res[r] = res[l];
                res[l] = y;
                l++;
            }
        }
        return new String(res);
    }
}