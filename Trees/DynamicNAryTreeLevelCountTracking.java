/*
Design a data structure for a dynamic N-ary tree that supports:

  1. addNode(parentId, newNodeId)  - add a new leaf node as a child of parentId
  2. removeNode(nodeId)            - remove the node with the given id
  3. countAtLevel(level)           - return the number of nodes currently at
                                     the given level (root is level 0)

countAtLevel must run in O(1) time. addNode and removeNode should also run
in O(1) time (state and justify the semantics you choose for removing an
internal/non-leaf node, and the trade-off it implies).

Example:
DynamicTree tree = new DynamicTree(1); // root with id 1
tree.addNode(1, 2);
tree.addNode(1, 3);
tree.addNode(1, 4);
tree.addNode(2, 5);
tree.countAtLevel(0); // returns 1
tree.countAtLevel(1); // returns 3
tree.countAtLevel(2); // returns 1
tree.addNode(3, 6);
tree.countAtLevel(2); // returns 2
tree.removeNode(5);
tree.countAtLevel(2); // returns 1
*/

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

class DynamicTree {

    public class Node{
        int id;
        int level; //level it belongs to in the tree
        Integer parentId; // FIX: was `int` - a primitive can't hold null, and the root has no parent
        List<Node> children;
        public Node(int nodeId){
            this.id = nodeId;
            this.children = new ArrayList<>();
            this.parentId = null;
        }
    }
    List<Integer> level; // Stores node count at each level to get count in  O(1)
    Map<Integer, Node> map = new HashMap<>(); //nodeID -> node mapping to get node in O(1)
    public DynamicTree(int rootId) {
        //initialize level array
        this.level = new ArrayList<>();
        addNode(null, rootId);
    }

    public void addNode(Integer parentId, int newNodeId) { // FIX: was `int parentId` - couldn't accept null for the root call
        Node newNode = new Node(newNodeId);
        this.map.put(newNodeId, newNode);
        int currlevel;
        if(parentId == null){
            currlevel = 0;
        }
        else{
            //get level of parent
            Node parent = this.map.get(parentId);
            currlevel = parent.level + 1;
            parent.children.add(newNode);
            newNode.parentId = parentId;
        }
        newNode.level = currlevel; // FIX: level was never actually stored on the node itself, so removeNode's `node.level` lookup would always read 0
        if(this.level.size() <= currlevel){ //no Nodes in the level
            this.level.add(1);
        }
        else{ // increment count in the level
            this.level.set(currlevel, this.level.get(currlevel) + 1); // FIX: List.get(i)++ doesn't compile - get() returns a value, not an assignable slot; must use set()
        }
    }

    public void removeNode(int nodeId) {
        //Removes leaf nodes in O(1)
        Node node = this.map.get(nodeId);
        if(node == null)
            return; // FIX: guard against removing a non-existent id (would otherwise NPE below)
        if(!node.children.isEmpty())
            throw new IllegalStateException("removeNode only supports leaf nodes; node " + nodeId + " still has children"); // FIX: the comment said "leaf nodes only" but nothing enforced it - removing an internal node silently orphaned its subtree (those descendants stayed in `map` and in the level counts forever, with a parentId pointing at a now-deleted node)
        if(node.parentId != null){
            Node parent = this.map.get(node.parentId);
            parent.children.remove(node);
        }
        this.level.set(node.level, this.level.get(node.level) - 1); // FIX: same List.get(i)-- issue as above
        this.map.remove(nodeId);
    }

    public int countAtLevel(int level) {
        return this.level.get(level);
    }
}

/**
 * Your DynamicTree object will be instantiated and called as such:
 * DynamicTree obj = new DynamicTree(rootId);
 * obj.addNode(parentId, newNodeId);
 * obj.removeNode(nodeId);
 * int param_3 = obj.countAtLevel(level);
 */
