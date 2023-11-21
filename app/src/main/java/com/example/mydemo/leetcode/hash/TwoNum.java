package com.example.mydemo.leetcode.hash;


import com.example.mydemo.leetcode.node.LogUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和
 */
public class TwoNum {
    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            int a = nums[i];
            for (int j = i; j < nums.length; j++) {
                if (a + nums[j] == target) {
                    return new int[]{i , j};
                }
            }
        }
        return new int[]{0 ,0};
    }

    public static int[] twoSumV2(int[] nums, int target) {
        Map<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int a = target - nums[i];

            if (hashMap.containsKey(a)) {
                return new int[]{hashMap.get(a), i};
            }
            hashMap.put(nums[i], i);
        }
        return new int[0];
    }

    public static void main(String[] args) {
        int[] nums = {2,7,11,15};
        LogUtil.printArray(twoSum(nums, 9));
    }
}
