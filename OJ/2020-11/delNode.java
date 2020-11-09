/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode
 * next; ListNode(int x) { val = x; } }
 */
class Solution {
    public ListNode deleteNode(ListNode head, int val) {

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        for (ListNode crt = dummy; crt != null; crt = crt.next) {
            if (crt.next != null && crt.next.val == val) {
                if (crt.next.next == null) {
                    crt.next = null;
                } else {
                    ListNode tmp = crt.next;
                    crt.next = crt.next.next;
                    tmp.next = null;
                }
            }
        }
        return head;
    }
}