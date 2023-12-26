package com.example.mydemo.leetcode.node2;

import com.example.mydemo.leetcode.node.ListNode;

/**
 * 反转链表
 */
public class ReverseList {

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode pre = head;
        ListNode cur = head.next;
        ListNode next = null;
        pre.next = null;

        while (cur != null) {
            next  = cur.next;
            cur.next = pre;

            pre = cur;
            cur = next;
        }

        return pre;

    }
}
