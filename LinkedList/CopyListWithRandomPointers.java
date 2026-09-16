/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/
//Hash map approach
//class Solution {
//    public Node copyRandomList(Node head) {
//        //map to store original and copy
//        Map<Node, Node> map = new HashMap<>();
//        Node curr = head;
//        //first pass to initialize copy nodes and store it in map
//        while(curr != null){
//            Node newNode = new Node(curr.val);
//            map.put(curr, newNode);
//            curr = curr.next;
//        }
//        //second pass to set random and next pointers
//        curr = head;
//        while(curr != null){
//            map.get(curr).next = map.get(curr.next);
//            map.get(curr).random = map.get(curr.random);
//            curr = curr.next;
//        }
//        return map.get(head);
//    }
//}

// O(1) interweaving approach


class Solution {
    public Node copyRandomList(Node head) {

        Node curr = head;
        // pass 1 - insert new copy nodes inline
        while(curr != null){
            Node newNode = new Node(curr.val);
            newNode.next = curr.next;
            curr.next = newNode;
            curr = curr.next.next;
        }
        //pass 2 - set random pointers for copy nodes
        curr = head;
        while(curr != null){
            if(curr.random != null)
                curr.next.random = curr.random.next;
            curr = curr.next.next;
        }
        //pass 3 - separate the original and copy list
        Node dummy = new Node(0);
        Node copyCurr = dummy; // copy list head
        curr = head;
        while(curr != null){
            copyCurr.next = curr.next;
            copyCurr = copyCurr.next;
            curr.next = curr.next.next;
            curr = curr.next;
        }
        return dummy.next;
    }
}