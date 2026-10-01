import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/*
Reference implementation - see DynamicNAryTreeLevelCountTracking.java for the
problem statement.

Design decision: removeNode only supports leaf nodes in O(1). Removing an
internal node would require recursively decrementing the level count of
every node in its subtree, which is O(subtree size), not O(1) - there is no
way around that without extra machinery (e.g. an Euler-tour + segment tree
with lazy propagation), which is overkill here. That trade-off is enforced
with an exception rather than silently corrupting state.

addNode:      O(1)
removeNode:   O(1) for a leaf; throws for an internal node
countAtLevel: O(1)
*/
class DynamicTree {

    private static class Node {
        final int id;
        final int level;
        Node parent;
        final Set<Node> children = new HashSet<>();

        Node(int id, int level, Node parent) {
            this.id = id;
            this.level = level;
            this.parent = parent;
        }
    }

    private final Map<Integer, Node> nodesById = new HashMap<>();
    private final List<Integer> countsByLevel = new ArrayList<>();

    public DynamicTree(int rootId) {
        Node root = new Node(rootId, 0, null);
        nodesById.put(rootId, root);
        bumpLevelCount(0, 1);
    }

    public void addNode(int parentId, int newNodeId) {
        if (nodesById.containsKey(newNodeId)) {
            throw new IllegalArgumentException("node " + newNodeId + " already exists");
        }
        Node parent = nodesById.get(parentId);
        if (parent == null) {
            throw new NoSuchElementException("no such parent " + parentId);
        }

        Node newNode = new Node(newNodeId, parent.level + 1, parent);
        parent.children.add(newNode);
        nodesById.put(newNodeId, newNode);
        bumpLevelCount(newNode.level, 1);
    }

    public void removeNode(int nodeId) {
        Node node = nodesById.get(nodeId);
        if (node == null) {
            throw new NoSuchElementException("no such node " + nodeId);
        }
        if (!node.children.isEmpty()) {
            throw new UnsupportedOperationException(
                "removeNode only supports leaf nodes; node " + nodeId + " still has children");
        }

        if (node.parent != null) {
            node.parent.children.remove(node);
        }
        nodesById.remove(nodeId);
        bumpLevelCount(node.level, -1);
    }

    public int countAtLevel(int level) {
        if (level < 0 || level >= countsByLevel.size()) {
            return 0;
        }
        return countsByLevel.get(level);
    }

    private void bumpLevelCount(int level, int delta) {
        while (countsByLevel.size() <= level) {
            countsByLevel.add(0);
        }
        countsByLevel.set(level, countsByLevel.get(level) + delta);
    }
}

/**
 * Your DynamicTree object will be instantiated and called as such:
 * DynamicTree obj = new DynamicTree(rootId);
 * obj.addNode(parentId, newNodeId);
 * obj.removeNode(nodeId);
 * int param_3 = obj.countAtLevel(level);
 */
