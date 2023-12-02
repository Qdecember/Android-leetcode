package com.example.mydemo.leetcode.node;

/**
 * 链表 排序
 * 归并排序
 */
public class SortNodeList {

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode fast = head.next;
        ListNode slow = head;

        // 查找中间节点
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode temp = slow.next;
        slow.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(temp);
        ListNode dumpy = new ListNode(0);
        ListNode res = dumpy;
        while (left != null && right != null) {
            if (left.val < right.val) {
                dumpy.next = left;
                left = left.next;
            } else  {
                dumpy.next = right;
                right = right.next;
            }
            dumpy = dumpy.next;
        }
        if (left != null) {
            dumpy.next = left;
        } else  {
            dumpy.next = right;
        }
        return res.next;

    }
}
