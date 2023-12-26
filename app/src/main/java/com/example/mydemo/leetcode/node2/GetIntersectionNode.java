package com.example.mydemo.leetcode.node2;

import com.example.mydemo.leetcode.node.ListNode;

/**
 * 相交链表
 */
public class GetIntersectionNode {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode tempA = headA;
        ListNode tempB = headB;
        while (tempA != tempB) {
            if (tempA != null) {
                tempA = tempA.next;
            } else {
                tempA = headB;
            }

            if (tempB != null) {
                tempB = tempB.next;
            } else {
                tempB = headA;
            }
        }
        return tempA;

    }
}
