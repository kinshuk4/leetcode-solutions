package com.vaani.leetcode.tree.bst;

import com.vaani.dsa.ds.core.tree.binarytree.simple.BinaryTreeNode;

/**
 * https://leetcode.com/problems/convert-bst-to-greater-tree/
 * 538. Convert BST to Greater Tree
 * Easy
 * Given a Binary Search Tree (BST), convert it to a Greater Tree such that
 * every key of the original BST is changed to the original key plus sum of
 * all keys greater than the original key in BST.
 *
 * <p>Example:
 */
/*
Input: The root of a Binary Search Tree like this:
              5
            /   \
           2     13

Output: The root of a Greater Tree like this:
             18
            /   \
          20     13

Note: This question is the same as 1038: https://leetcode.com/problems/binary-search-tree-to-greater-sum-tree/
 */
public class ConvertBSTToGreaterTree {
    public static void main(String[] args) {
        BinaryTreeNode node = new BinaryTreeNode(5);
        node.right = new BinaryTreeNode(13);
        node.left = new BinaryTreeNode(2);
        node.left.left = new BinaryTreeNode(1);
        node.left.right = new BinaryTreeNode(3);
        BinaryTreeNode result = new ConvertBSTToGreaterTree().convertBST(node);
        System.out.println(result);
    }

    public BinaryTreeNode convertBST(BinaryTreeNode root) {
        postOrder(root, 0);
        return root;
    }

    private int postOrder(BinaryTreeNode root, int value) {
        if (root == null) {
            return value;
        }
        int right = postOrder(root.right, value);
        root.val = root.val + right;
        return postOrder(root.left, root.val);
    }
}
