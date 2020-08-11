package com.vaani.leetcode.tree.bst;

import com.vaani.dsa.ds.core.tree.binarytree.simple.BinaryTreeNode;

/**
 * 530. Minimum Absolute Difference in BST
 * Easy
 * Given a binary search tree with non-negative values, find the minimum absolute difference between values of any two nodes.
 */
/*
Example:

Input:

   1
    \
     3
    /
   2

Output:
1

Explanation:
The minimum absolute difference is 1, which is the difference between 2 and 1 (or between 2 and 3).
 */

/**
 * <p>
 * Note:
 * <p>
 * There are at least two nodes in this BST.
 * This question is the same as 783: https://leetcode.com/problems/minimum-distance-between-bst-nodes/
 */
public class MinimumAbsoluteDifferenceInBST {
    public static void main(String[] args) throws Exception {
        BinaryTreeNode root = new BinaryTreeNode(1);
        root.right = new BinaryTreeNode(2);
        root.right.right = new BinaryTreeNode(3);
        new MinimumAbsoluteDifferenceInBST().getMinimumDifference(root);
    }

    int result = Integer.MAX_VALUE;

    public int getMinimumDifference(BinaryTreeNode root) {
        getMin(root, null);
        return result;
    }

    private Integer getMin(BinaryTreeNode root, Integer prev) {
        if (root == null) {
            return prev;
        }
        Integer min = getMin(root.left, prev);
        if (min != null) {
            this.result = Math.min(this.result, Math.abs(root.val - min));
        }
        return getMin(root.right, root.val);
    }
}
