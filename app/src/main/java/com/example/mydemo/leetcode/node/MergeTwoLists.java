package com.example.mydemo.leetcode.node;

import android.os.Looper;

/**
 * 合并两个有序链表
 */
public class MergeTwoLists {

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
       ListNode dumpy = new ListNode(0);
       ListNode cur = dumpy;
       while (list1 != null && list2 != null) {
           if (list1.val < list2.val) {
               cur.next = list1;
               list1 = list1.next;
           } else  {
               cur.next = list2;
               list2 = list2.next;
           }
           cur = cur.next;
       }
       if (list1 == null) {
           cur.next = list2;
       } else {
           cur.next = list1;
       }
       return dumpy.next;
    }

    public static void main(String[] args) {
        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(2);
        list1.next.next = new  ListNode(4);

        ListNode list2 = new ListNode(1);
        list2.next = new ListNode(3);
        list2.next.next = new  ListNode(4);

        ListNode result = mergeTwoLists(list1, list2);
        LogUtil.printListNode(result);


    }


}
