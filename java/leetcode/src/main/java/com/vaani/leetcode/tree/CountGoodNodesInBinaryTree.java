package com.vaani.leetcode.tree;

import com.vaani.dsa.ds.core.tree.binarytree.simple.TreeNode;

public class CountGoodNodesInBinaryTree {
    int count = 0;

    public int goodNodes(TreeNode root) {
        dfs(root, Integer.MIN_VALUE);
        return count;
    }

    void dfs(TreeNode root, int max) {
        if (root == null) {
            return;
        }

        if (root.val >= max) {
            count++;
        }

        dfs(root.left, Math.max(max, root.val));
        dfs(root.right, Math.max(max, root.val));
    }

}
