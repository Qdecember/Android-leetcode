package com.example.mydemo.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树的中序遍历
 */
public class InorderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> arr = new ArrayList<>();
        inorder(arr, root);
        return arr;
    }

    private void inorder(List<Integer> arr, TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(arr,root.left);
        arr.add(root.val);
        inorder(arr, root.right);
    }
}
