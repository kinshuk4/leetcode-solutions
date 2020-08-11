package com.vaani.leetcode.tree;

import com.vaani.dsa.ds.core.tree.binarytree.simple.BinaryTreeNode;

/**
 * https://leetcode.com/problems/binary-tree-tilt/
 * 563. Binary Tree Tilt
 * Easy
 * <p>
 * Given a binary tree, return the tilt of the whole tree.
 * <p>
 * The tilt of a tree node is defined as the absolute difference between the sum of all left subtree node values and the sum of all right subtree node values. Null node has tilt 0.
 * <p>
 * The tilt of the whole tree is defined as the sum of all nodes' tilt.
 * <p>
 * Example:
 */
/*
Input:
         1
       /   \
      2     3
Output: 1
Explanation:
Tilt of node 2 : 0
Tilt of node 3 : 0
Tilt of node 1 : |2-3| = 1
Tilt of binary tree : 0 + 0 + 1 = 1

Note:

    The sum of node values in any subtree won't exceed the range of 32-bit integer.
    All the tilt values won't exceed the range of 32-bit integer.

 */

public class BinaryTreeTilt {

    public static void main(String[] args) {
        BinaryTreeNode node = new BinaryTreeNode(1);
        node.left = new BinaryTreeNode(2);
        node.right = new BinaryTreeNode(3);
        System.out.println(new BinaryTreeTilt().findTilt(node));
    }

    /*
    Solution: Find tilt of left node and find tilt of right node and return left + right + curr to
   its parent.
     */

    int result = 0;

    public int findTilt(BinaryTreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root);
        return result;
    }

    private int dfs(BinaryTreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = dfs(node.left);
        int right = dfs(node.right);
        result += Math.abs(left - right);
        return left + right + node.val;
    }
}
