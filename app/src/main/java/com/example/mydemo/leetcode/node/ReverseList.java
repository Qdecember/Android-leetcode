package com.example.mydemo.leetcode.node;

import android.annotation.SuppressLint;

import java.util.List;

/**
 * 反转链表
 */
public class ReverseList {


    @SuppressLint("NotConstructor")
    public ListNode ReverseList(ListNode head) {
        if(head == null) return null; // 判断head 情况
        if(head.next == null) return head;

        ListNode pre = head;
        ListNode temp = head.next;
        ListNode next ;
        pre.next = null;
        while (temp.next != null) {
            next = temp.next;
            temp.next = pre;

            pre = temp;
            temp = next;
        }

        temp.next = pre;
        return temp;
    }

//    public ListNode reverseList(ListNode head) {
//        ListNode cur = head, pre = null;
//        while(cur != null) {
//            ListNode tmp = cur.next; // 暂存后继节点 cur.next
//            cur.next = pre;          // 修改 next 引用指向
//            pre = cur;               // pre 暂存 cur
//            cur = tmp;               // cur 访问下一节点
//        }
//        return pre;
//    }

    public static ListNode reverseList(ListNode head) {
        ListNode cur = head;
        ListNode next = null;
        ListNode pre = null;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;

            pre = cur;
            cur = next;
        }
        return pre;
    }


    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        LogUtil.printListNode(reverseList(head));

    }



}
