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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);

        ListNode first = dummy;
        ListNode second = dummy;

        // Move first pointer n + 1 steps ahead
        for (int i = 0; i <= n; i++) {
            first = first.next;
        }

        // Move both pointers until first reaches null
        while (first != null) {
            first = first.next;
            second = second.next;
        }

        // second is now just before the node to remove
        second.next = second.next.next;

        return dummy.next;
    }
}