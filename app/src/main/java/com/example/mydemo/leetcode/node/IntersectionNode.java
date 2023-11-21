package com.example.mydemo.leetcode.node;

/**
 * leetCode 160
 * 两个链表的交点
 */
public class IntersectionNode {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode tempA = headA;
        ListNode tempB = headB;
        while (tempA != tempB) {
            if (tempA != null) {
                tempA = tempA.next;
            } else  {
                tempA = headB;
            }

            if (tempB != null) {
                tempB = tempB.next;
            } else  {
                tempB = headA;
            }
        }
        return tempA;
    }

    public static void main(String[] args) {

    }
}
