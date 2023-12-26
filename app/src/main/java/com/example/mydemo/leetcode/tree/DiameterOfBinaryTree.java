package com.example.mydemo.leetcode.tree;

/**
 * 二叉树的直径
 */
public class DiameterOfBinaryTree {
    public int diameterOfBinaryTree(TreeNode root) {
        ans = 1;
        depth(root);
        return ans - 1;
    }

    int ans = 0;
    private int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }

       int l = depth(root.left);
        int r = depth(root.right);
        ans = Math.max(ans, l + r + 1);
        return Math.max(l, r) + 1;
    }


}
