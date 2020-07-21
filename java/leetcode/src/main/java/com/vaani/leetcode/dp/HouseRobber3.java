package com.vaani.leetcode.dp;

import com.vaani.dsa.ds.core.tree.binarytree.simple.BinaryTreeNode;

/**
 * The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.
 * <p>
 * Determine the maximum amount of money the thief can rob tonight without alerting the police.
 */
/*
Input: [3,2,3,null,3,null,1]

     3
    / \
   2   3
    \   \
     3   1

Output: 7
Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.


example 2:
     3
    / \
   12   4
    \   \
     3   17

So, we rob 12 + 17 = 29

 */
public class HouseRobber3 {
    public int rob(BinaryTreeNode root) {
        return robRecursive(root);
    }

    public int robRecursive(BinaryTreeNode root) {
        if (root == null) {
            return 0;
        }

        int[] result = helper(root);
        return Math.max(result[0], result[1]);
    }

    // int[0] - rob, int[1] - dont rob
    private int[] helper(BinaryTreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }

        int[] result = new int[2];
        int[] left = helper(root.left);
        int[] right = helper(root.right);

        // result[0] is when root is selected, result[1] is when not.
        result[0] = root.val + left[1] + right[1];
        result[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        return result;
    }

    private int[] helper2(BinaryTreeNode root) {
        int rob = root.val, noRob = 0;
        if (root.left != null) {
            int[] left = helper2(root.left);
            rob += left[1];
            noRob += Math.max(left[0], left[1]);
        }
        if (root.right != null) {
            int[] right = helper2(root.right);
            rob += right[1];
            noRob += Math.max(right[0], right[1]);
        }
        return new int[]{rob, noRob};
    }
}
