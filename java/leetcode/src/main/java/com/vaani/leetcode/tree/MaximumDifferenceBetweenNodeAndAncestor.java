package com.vaani.leetcode.tree;

import com.vaani.dsa.ds.core.tree.binarytree.simple.BinaryTreeNode;

/**
 * 1026. Maximum Difference Between Node and Ancestor
 * Medium
 * <p>
 * Given the root of a binary tree, find the maximum value V for which there exist different nodes A and B where V = |A.val - B.val| and A is an ancestor of B.
 * <p>
 * A node A is an ancestor of B if either: any child of A is equal to B, or any child of A is an ancestor of B.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [8,3,10,1,6,null,14,null,null,4,7,13]
 * Output: 7
 * Explanation: We have various ancestor-node differences, some of which are given below :
 * |8 - 3| = 5
 * |3 - 7| = 4
 * |8 - 1| = 7
 * |10 - 13| = 3
 * Among all possible differences, the maximum value of 7 is obtained by |8 - 1| = 7.
 * Example 2:
 * <p>
 * <p>
 * Input: root = [1,null,2,null,0,3]
 * Output: 3
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [2, 5000].
 * 0 <= Node.val <= 10^5
 */
public class MaximumDifferenceBetweenNodeAndAncestor {
    public int maxAncestorDiff(BinaryTreeNode root) {
        return root == null ? 0 : helper (root, Integer.MAX_VALUE, Integer.MIN_VALUE);
    }
    private int helper (BinaryTreeNode root, int min, int max) {
        // diff when we reach leaf
        if (root == null) {
            return max - min;
        }

        min = Math.min (min, root.val);
        max = Math.max (max, root.val);

        int left = helper (root.left, min, max);
        int right = helper (root.right, min, max);

        return Math.max (left, right);
    }
}
