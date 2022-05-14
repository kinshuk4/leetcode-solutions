package com.vaani.leetcode.tree;

import com.vaani.dsa.ds.core.tree.binarytree.simple.TreeNode;

/**
 * https://leetcode.com/problems/sum-of-root-to-leaf-binary-numbers/
 * 1022. Sum of Root To Leaf Binary Numbers
 * Easy
 * <p>
 * Given a binary tree, each node has value 0 or 1.  Each root-to-leaf path represents a binary number starting with the most significant bit.  For example, if the path is 0 -> 1 -> 1 -> 0 -> 1, then this could represent 01101 in binary, which is 13.
 * <p>
 * For all leaves in the tree, consider the numbers represented by the path from the root to that leaf.
 * <p>
 * Return the sum of these numbers.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: [1,0,1,0,1,0,1]
 * Output: 22
 * Explanation: (100) + (101) + (110) + (111) = 4 + 5 + 6 + 7 = 22
 * <p>
 * <p>
 * <p>
 * Note:
 * <p>
 * The number of nodes in the tree is between 1 and 1000.
 * node.val is 0 or 1.
 * The answer will not exceed 2^31 - 1.
 */
public class SumOfRootToLeafBinaryNumbers {


    static class UsingRecursion {
        public int sumRootToLeaf(TreeNode root) {
            return dfs(root, 0);
        }

        public int dfs(TreeNode root, int prevSum) {
            if (root == null) {
                return 0;
            }
            prevSum = prevSum * 2 + root.val; // Can be written as: prevSum = prevSum << 1 | root.val;

            return root.left == null && root.right == null ? prevSum : dfs(root.left, prevSum) + dfs(root.right, prevSum);
        }
    }
}
