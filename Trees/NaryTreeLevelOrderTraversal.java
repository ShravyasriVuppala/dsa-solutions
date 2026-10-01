/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        if(root == null)
            return new ArrayList<>();
        //Level order traversal
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        List<List<Integer>> ans = new ArrayList<>();
        while(!queue.isEmpty()){
            int size = queue.size();
            ArrayList<Integer> currLevel = new ArrayList<>();
            for(int i = 0; i < size; i++){
                Node curr = queue.poll();
                //add to current level
                currLevel.add(curr.val);
                //Get children and add to queue
                if(curr.children != null){
                    for(Node child : curr.children){
                        if(child != null)
                            queue.offer(child);
                    }
                }
            }
            //add current level nodes to answer list
            ans.add(currLevel);
        }
        return ans;
    }
}