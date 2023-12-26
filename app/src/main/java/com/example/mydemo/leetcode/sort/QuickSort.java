package com.example.mydemo.leetcode.sort;

import java.util.Arrays;

/**
 * 快速排序
 * 排序n个项目要O(nlogn)次比较，在最坏情况下则需要O(n2)次比较
 */
public class QuickSort {

    public int[] sort(int[] sourceArray) throws Exception {

        return quickSort(sourceArray, 0, sourceArray.length - 1);
    }

    public static int[] quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int partition = partition(arr, left , right);
            quickSort(arr, left, partition - 1);
            quickSort(arr, partition + 1, right);
        }
        return arr;
    }

    public static int partition(int[] arr, int left, int right) {
        int pivot = left; // 通常选择最左侧的为基准点
        int index = left + 1; // 从左侧+1 开始
        for (int i = index;  i <= right; i++) {
            if (arr[i] < arr[pivot]) { // 依次和基准点做比较
                swap(arr, i, index); // 如果小于基准点就交换位置
                index ++; // index 指针++
            }
        }
        swap(arr, pivot, index - 1); // 和最后指针处交换
        return index - 1;
    }

    public static void swap(int[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    public static void main(String[] args) {
        int[] array = new int[] {3, 44, 28, 19, 26, 57, 50};
        quickSort(array, 0, array.length - 1);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }
}
