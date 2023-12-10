package com.example.mydemo.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树的中序遍历
 */
public class InorderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorder(root, res);
        return res;
    }

    public void inorder(TreeNode node, List res) {
        if (node == null) {
            return;
        }

        inorder(node.left, res);
        res.add(node.val);
        inorder(node.right, res);
    }
}
