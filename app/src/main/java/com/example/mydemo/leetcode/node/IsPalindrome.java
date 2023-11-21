package com.example.mydemo.leetcode.node;

/**
 * 回文链表
 */
public class IsPalindrome {

    public static boolean isPalindrome(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode pre = fast;
        ListNode cur = fast.next;
        pre.next = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;

            pre = cur;
            cur = next;
        }


        ListNode temp = head;
        ListNode temp2 = cur;
        while (temp != null && temp2 != null) {
            if (temp.val != temp2.val) return false;
            temp = temp.next;
            temp2 = temp2.next;
        }

        return true;

    }


    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(0);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(1);
        head.next.next.next.next = new ListNode(5);
        ListNode newHead = reverseList(head);
        LogUtil.printListNode(newHead);
//        isPalindrome(head);
        System.out.print(isPalindrome(head));
    }
    public static ListNode reverseList(ListNode head) {
        ListNode pre = head;
        ListNode temp = head.next;
        pre.next = null;
        while (temp != null) {
            ListNode next = temp.next;
            temp.next = pre;

            pre = temp;
            temp = next;
        }

        return temp;
    }
}
