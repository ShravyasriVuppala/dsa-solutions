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
        //Add all heads to minHeap and keep polling smallest, and add next of the polled node to minheap
        //minheap ordered by node value
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a.val, b.val));

        //insert all heads to minHeap
        for(ListNode head : lists){
            if(head != null)
                minHeap.offer(head);
        }
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while(!minHeap.isEmpty()){
            ListNode smallest = minHeap.poll();
            tail.next = smallest;
            tail = tail.next;

            if(smallest.next != null)
                minHeap.offer(smallest.next);
        }

        return dummy.next;
    }
}