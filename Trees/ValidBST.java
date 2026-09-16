/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public long[] isValid(TreeNode node){
        //base case
        if(node == null) return new long[]{Long.MAX_VALUE, Long.MIN_VALUE, 1}; //min, max, isvalid true = 1/false = 0
        long[] leftVals = isValid(node.left);
        long[] rightVals = isValid(node.right);
        long leftMax = leftVals[1], rightMin = rightVals[0];
        boolean isValid = leftVals[2] == 1 && rightVals[2] == 1
                && node.val > leftMax && node.val < rightMin;
        long min = Math.min(node.val, Math.min(leftVals[0], rightVals[0]));
        long max = Math.max(node.val, Math.max(leftVals[1], rightVals[1]));
        return new long[]{min, max, isValid ? 1 : 0};
    }
    public boolean isValidBST(TreeNode root) {
        return isValid(root)[2] == 1;
    }
}