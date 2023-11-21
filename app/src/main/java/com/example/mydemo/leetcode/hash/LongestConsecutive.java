package com.example.mydemo.leetcode.hash;

import com.example.mydemo.leetcode.node.LogUtil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * leetcode.128 最长连续序列
 */
public class LongestConsecutive {

    public static int longestConsecutive(int[] nums) {
       Set<Integer> set = new HashSet<>();
       for (int num: nums) {
           set.add(num);
       }

       int longestConsecutive = 0;
       for (int num: set) {
           // 如果当前存在比num小的数字，放弃本次查找
           if (set.contains(num - 1)) {
               continue;
           }
           int targetNum = num;
           while (set.contains(++targetNum)) {

           }
           longestConsecutive = Math.max(longestConsecutive, targetNum - num);
       }
       return longestConsecutive;
    }

    public static void main(String[] args) {
        int[] nums = {100,4,200,1,3,2};
        System.out.print(longestConsecutive(nums));
    }
}
