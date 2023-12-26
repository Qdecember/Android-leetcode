package com.example.mydemo.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉搜索树中第 K 小的元素
 */
public class KthSmallest {

    List<Integer> list = new ArrayList<>();

    public int kthSmallest(TreeNode root, int k) {
        order(root);
        return list.get(k - 1);
    }

    public void order(TreeNode root) {
        if (root == null) {
            return;
        }

        order(root.left);
        list.add(root.val);
        order(root.right);

    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);

        System.out.print(new KthSmallest().kthSmallest(root, 1));
    }

}
