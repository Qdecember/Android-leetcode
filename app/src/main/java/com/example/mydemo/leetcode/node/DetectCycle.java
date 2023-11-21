package com.example.mydemo.leetcode.node;

/**
 * 环形链表
 * 返回环的开始节点
 */
public class DetectCycle {

    public static ListNode detectCycle(ListNode head) {
        if (head.next == null) return null;
        ListNode fast = head;
        ListNode slow = head;
        while (true) {
            if (fast.next == null || fast.next.next == null) return null;
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) break;
        }
        System.out.print(slow.val);
        ListNode temp = head;
        while (temp != slow) {
            temp = temp.next;
            slow = slow.next;
        }

        return temp;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        head.next = new ListNode(2);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(-4);
        head.next.next.next = head.next;

        System.out.println(detectCycle(head).val);


    }
}
