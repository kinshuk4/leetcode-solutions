package com.vaani.leetcode.tree;

import com.vaani.dsa.ds.core.tree.binarytree.simple.TreeNode;

/**
 * https://leetcode.com/problems/longest-univalue-path/
 * 687. Longest Univalue Path
 * Easy
 *
 * Given a binary tree, find the length of the longest path where each node in the path has the same value. This path may or may not pass through the root.
 *
 * The length of path between two nodes is represented by the number of edges between them.
 *
 */
/*
 * Example 1:
 *
 * Input:
 *
 *               5
 *              / \
 *             4   5
 *            / \   \
 *           1   1   5
 *
 * Output: 2
 *
 *
 *
 * Example 2:
 *
 * Input:
 *
 *               1
 *              / \
 *             4   5
 *            / \   \
 *           4   4   5
 *
 * Output: 2
 *
 *
 *
 * Note: The given binary tree has not more than 10000 nodes. The height of the tree is not more than 1000.
 */
public class LongestUnivaluePath {
    int result = 0;

    public int longestUnivaluePath(TreeNode root) {
        if (root == null) {
            return 0;
        }
        helper(root);
        return result;
    }

    private int helper(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = helper(node.left), right = helper(node.right);
        int curr = 0;

        if (node.left != null && node.left.val == node.val) {
            curr = Math.max(left + 1, curr);
        }

        if (node.right != null && node.val == node.right.val) {
            curr = Math.max(right + 1, curr);
        }

        // include root in the path
        if (node.left != null && node.right != null && node.left.val == node.val && node.val == node.right.val) {
            result = Math.max(result, left + right + 2);
        }
        // Update Global value
        result = Math.max(result, curr);
        return curr;
    }
}
