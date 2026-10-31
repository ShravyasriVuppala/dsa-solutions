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
    public void dfs(List<Integer> ans, TreeNode root){
        //base case
        if(root == null){
            return;
        }
        if(root.left != null){
            dfs(ans, root.left);
        }

        ans.add(root.val);

        if(root.right != null){
            dfs(ans, root.right);
        }


    }
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        dfs(ans, root);

        return ans;
    }
}