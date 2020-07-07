package com.vaani.leetcode.tree;

import com.vaani.dsa.ds.core.tree.binarytree.simple.BinaryTreeNode;

/**
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.
 * <p>
 * For example:
 * Given the below binary tree and sum = 22,
 * <p>
 * 5
 * / \
 * 4   8
 * /   / \
 * 11  13  4
 * /  \      \
 * 7    2      1
 * <p>
 * return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
 * <p>
 */
public class PathSum {
    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(5);
        root.left = new BinaryTreeNode(4);
        root.right = new BinaryTreeNode(8);
        root.left.left = new BinaryTreeNode(11);
        root.right.left = new BinaryTreeNode(13);
        root.right.right = new BinaryTreeNode(4);
        root.left.left.left = new BinaryTreeNode(7);
        root.left.left.right = new BinaryTreeNode(2);
        root.right.right.right = new BinaryTreeNode(1);


        PathSum test = new PathSum();
        System.out.println(test.hasPathSum(root, 22));
    }

    public boolean hasPathSum(BinaryTreeNode root, int sum) {
        return hasPathSum(root, sum, 0);
    }

    public boolean hasPathSum(BinaryTreeNode root, int sum, int currSum) {
        if (root == null) {
            return false;
        }
        currSum += root.val;

        if (root.left == null && root.right == null) {
            return currSum == sum;
        } else {
            return hasPathSum(root.left, sum, currSum) || hasPathSum(root.right, sum, currSum);
        }
    }
}
