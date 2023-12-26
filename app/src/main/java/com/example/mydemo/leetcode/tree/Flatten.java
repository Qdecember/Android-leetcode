package com.example.mydemo.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

public class Flatten {

    private List<Integer> list = new ArrayList<>();

    public void flatten(TreeNode root) {
        if (root == null) return;

        preOrder(root);
        TreeNode temp = root;
        for (int i = 1 ;i < list.size(); i++) {
            temp.right = new TreeNode(list.get(i));
            temp.left = null;
            temp = temp.right;
        }

    }

    private void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }

        list.add(root.val);
        preOrder(root.left);
        preOrder(root.right);
    }
}
