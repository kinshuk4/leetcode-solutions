package com.vaani.leetcode.tree;


import com.vaani.dsa.ds.core.tree.binarytree.simple.BinaryTreeNode;

/**
 * https://leetcode.com/problems/count-complete-tree-nodes/
 * Given a complete binary tree, count the number of nodes.
 * <p>
 * Definition of a complete binary tree from Wikipedia:
 * In a complete binary tree every level, except possibly the last, is completely filled,
 * and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
 */
/*
Example:

Input:
    1
   / \
  2   3
 / \  /
4  5 6

Output: 6
 */

public class CountCompleteTreeNodes {
    // TLE = Time limit exceed
    static class UsingDFS1 {
        int count = 0;

        public int countNodes(BinaryTreeNode root) {
            if (root == null) {
                return 0;
            }
            int height = 0;
            BinaryTreeNode curr = root;
            while (curr != null) {
                curr = curr.left;
                height++;
            }
            // note in above example - height is 2 but depth is 3
            helper(root, height - 1, 0);
            return (1 << (height - 1)) - 1 + count; // 2 ^ (h-1) - 1 + count
            // Math.pow causes TLE - return (int) (Math.pow(2, height) - 1 + count);
        }


        private void helper(BinaryTreeNode root, int height, int curHeight) {
            if (root == null) {
                return;
            }
            // if is leaf node
            if (root.left == null && root.right == null) {
                if (height == curHeight) {
                    count++;
                } else {
                    return;
                }
            }
            helper(root.left, height, curHeight + 1);
            helper(root.right, height, curHeight + 1);
        }
    }

    static class UsingDiffHeight {
        // very inefficient as calculating height again and again
        public int countNodes(BinaryTreeNode root) {
            int left = getLeftHeight(root);
            int right = getRightHeight(root);
            if (left == right) {
                return (1 << left) - 1;  // Don't forget () && Use Math.pow() will cause TLE
            } else
                return countNodes(root.left) + countNodes(root.right) + 1;

        }

        private int getLeftHeight(BinaryTreeNode root) {
            int height = 0;
            while (root != null) {
                root = root.left;
                height++;
            }
            return height;
        }

        private int getRightHeight(BinaryTreeNode root) {
            int height = 0;
            while (root != null) {
                root = root.right;
                height++;
            }
            return height;
        }
    }

}
