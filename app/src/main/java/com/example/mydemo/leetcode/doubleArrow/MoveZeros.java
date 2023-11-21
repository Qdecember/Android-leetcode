package com.example.mydemo.leetcode.doubleArrow;

import com.example.mydemo.leetcode.node.LogUtil;

/**
 * 移动零
 * leetCode.283
 * todo again
 */
public class MoveZeros {

    public static void moveZeroes(int[] nums) {
        if (nums.length <= 1) return;
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[j++] = nums[i];
            }
        }


        for (int a =j; a < nums.length; a++) {
            nums[a] =0;
        }
    }

    public static void moveZeroesV2(int[] nums) {
        if (nums == null) return;
        int j = 0;
        for (int i =0; i < nums.length; i++) {
            if (nums[i] != 0) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                j ++;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0,1,0,3,12};
        moveZeroesV2(nums);
        LogUtil.printArray(nums);
    }
}
