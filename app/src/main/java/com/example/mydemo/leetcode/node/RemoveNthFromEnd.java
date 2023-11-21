package com.example.mydemo.leetcode.node;

/**
 * 删除链表的倒数第 N 个结点
 */
public class RemoveNthFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode dumpy = new ListNode(0);
            dumpy.next = head;

            ListNode first = head;
            for (int i = 0; i < n; i++) {
                first = first.next;
            }
            ListNode second = dumpy;
            while (first != null) {
                first = first.next;
                second = second.next;
            }
            second.next = second.next.next;
            return dumpy.next;
    }

    public static void main(String[] args) {
        for (int i = 0 ; i < 5 ;i++) {
            System.out.print(i + " ");
        }

        System.out.println();

        for (int i = 0 ; i < 5 ;++i) {
            System.out.print(i + " ");
        }
    }
}
