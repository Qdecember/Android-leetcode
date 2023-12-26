package com.example.mydemo.leetcode.node2;

import com.example.mydemo.leetcode.node.ListNode;

import java.util.ArrayList;
import java.util.List;

public class IsPalindrome {

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return false;

       List<Integer> list = new ArrayList<>();

       ListNode temp = head;
       while (temp != null) {
           list.add(temp.val);
           temp = temp.next;
       }

       int start = 0;
       int end = list.size() - 1;
       while (start < end) {
           if (list.get(start) != list.get(end)) {
               return false;
           }
           start ++;
           end--;
       }

       return true;
    }
}
