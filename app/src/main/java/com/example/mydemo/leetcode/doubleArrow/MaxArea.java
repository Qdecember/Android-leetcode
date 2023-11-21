package com.example.mydemo.leetcode.doubleArrow;

import java.util.Map;

/**
 * 盛最多水的容器
 * todo again
 */
public class MaxArea {

    public int maxArea(int[] height) {
        int i = 0, j = height.length - 1, max = 0;
        while (i < j) {
            int temp = Math.min(height[i], height[j]) * (j - i);
            if (temp > max) {
                max = temp;
            }

            if (height[i] > height[j]) {
                j --;
            } else  {
                i ++;
            }
        }
        return max;
    }
}
