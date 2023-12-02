package com.example.mydemo.leetcode.node;

import java.util.List;

/**
 * K 个一组翻转链表
 */
public class ReverseKGroup {

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dumpy = new ListNode(-1);
        dumpy.next = head;
        ListNode pre = dumpy;
        ListNode end = dumpy;
        while (end.next != null) {
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            if (end == null) {
                break;
            }

            ListNode next = end.next;
            end.next = null;

            ListNode start = pre.next;
            pre.next = null;
            pre.next = reverse(start);
            start.next = next;
            pre = start;
            end = start;
        }
        return dumpy.next;
    }

    /**
     * 递归翻转链表
     * @param head
     * @return
     */
    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode cur = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return cur;
    }
}
