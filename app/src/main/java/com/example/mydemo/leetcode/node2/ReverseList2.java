package com.example.mydemo.leetcode.node2;


import com.example.mydemo.leetcode.node.ListNode;

import java.util.List;

public class ReverseList2 {

    private ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null) return head;
        ListNode dumpy = new ListNode(0);
        dumpy.next = head;
        ListNode pre = dumpy;
        ListNode end = dumpy;
        while (end.next != null) {
            // 查找k 个链表
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

    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode cur = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return cur;
    }
}
