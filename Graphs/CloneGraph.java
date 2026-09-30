/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    public void dfs(Node node, Node root, Map<Integer, Node> cloned){
        //push cloned node into map so that it is not re-visited again:
        cloned.put(node.val, root);

        for(Node neighbor : node.neighbors){
            if(!cloned.containsKey(neighbor.val)){ //if neighbor is not already cloned
                //create new node for each neighbor and link with root
                Node newNeighbor = new Node(neighbor.val);
                root.neighbors.add(newNeighbor);
                dfs(neighbor, newNeighbor, cloned);
                //visited.remove(neighbor.val);
            }
            else{
                //if the node is already cloned, reuse it to tag the neighbors
                root.neighbors.add(cloned.get(neighbor.val));
            }
        }
    }
    public Node cloneGraph(Node node) {
        if(node == null)
            return null;
        Node root = new Node(node.val);
        Map<Integer, Node> cloned = new HashMap<>();

        dfs(node, root, cloned);
        return root;
    }
}