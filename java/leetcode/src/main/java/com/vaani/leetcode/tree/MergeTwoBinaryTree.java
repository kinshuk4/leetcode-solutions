package com.vaani.leetcode.tree;

import com.vaani.dsa.ds.core.tree.binarytree.simple.TreeNode;

/**
 * https://leetcode.com/problems/merge-two-binary-trees/
 * Given two binary trees and imagine that when you put one of them to cover the other, some nodes of the two trees are overlapped while the others are not.
 * <p>
 * You need to merge them into a new binary tree. The merge rule is that if two nodes overlap, then sum node values up as the new value of the merged node. Otherwise, the NOT null node will be used as the node of new tree.
 */
/*
 * Example 1:
 *
 * Input:
 * 	Tree 1                     Tree 2
 *           1                         2
 *          / \                       / \
 *         3   2                     1   3
 *        /                           \   \
 *       5                             4   7
 * Output:
 * Merged tree:
 * 	     3
 * 	    / \
 * 	   4   5
 * 	  / \   \
 * 	 5   4   7
 */


public class MergeTwoBinaryTree {
    public static void main(String[] args) {

    }

    public TreeNode mergeTreesRecursive(TreeNode t1, TreeNode t2) {
        TreeNode t3 = mergeTreeHelper(t1, t2);
        return t3;
    }


    private TreeNode mergeTreeHelper(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return null;
        } else if (t1 == null) {
            return t2;
        } else if (t2 == null) {
            return t1;
        } else {
            TreeNode t3 = new TreeNode(t1.val + t2.val);
            t3.left = mergeTreeHelper(t1.left, t2.left);
            t3.right = mergeTreeHelper(t1.right, t2.right);
            return t3;
        }

    }
}
