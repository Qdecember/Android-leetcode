package com.example.mydemo.leetcode.node;

import java.util.List;

/**
 * 指定区间内反转
 */
public class ReverseBetween {

//    public ListNode reverseBetween (ListNode head, int m, int n) {
//        // 虚拟头节点
//        ListNode dummyNode = new ListNode(-1);
//        dummyNode.next = head;
//
//        ListNode pre = dummyNode;
//        for (int i  =0; i < m-1;i++) {
//            pre = pre.next;
//        }
//
//        ListNode rightNote = pre;
//
//    }

    public static void main(String[] args) {

    }

    /**
     * 反转局部链表
     * @param head
     */
    private void reverseLinkedList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode curNext = cur.next;
            cur.next = pre;

            pre = cur;
            cur = curNext;
        }
    }
}
