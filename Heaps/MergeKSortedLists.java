/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        // insert one node from each list into min heap
        //poll the smallest node in the heap and insert next node from that list
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a.val, b.val));
        ListNode head = new ListNode(0);
        ListNode curr = head;
        //insert all heads into minHEap
        for(ListNode node : lists){
            if(node != null)
                minHeap.offer(node);
        }
        while(!minHeap.isEmpty()){
            ListNode node = minHeap.poll();
            curr.next = node;
            curr = node;
            //insert next of node if exists
            if(node.next != null){
                minHeap.offer(node.next);
            }
        }
        return head.next;
    }
}