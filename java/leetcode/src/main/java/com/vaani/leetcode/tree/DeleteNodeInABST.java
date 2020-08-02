package com.vaani.leetcode.tree;

import com.vaani.dsa.ds.core.tree.binarytree.simple.BinaryTreeNode;

/**
 * https://leetcode.com/problems/delete-node-in-a-bst/
 * 450. Delete Node in a BST
 * Medium
 * <p>
 * Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.
 * <p>
 * Basically, the deletion can be divided into two stages:
 * <p>
 * Search for a node to remove.
 * If the node is found, delete the node.
 * <p>
 * Note: Time complexity should be O(height of tree).
 */
/*
Example:

root = [5,3,6,2,4,null,7]
key = 3

    5
   / \
  3   6
 / \   \
2   4   7

Given key to delete is 3. So we find the node with value 3 and delete it.

One valid answer is [5,4,6,2,null,null,7], shown in the following BST.

    5
   / \
  4   6
 /     \
2       7

Another valid answer is [5,2,6,null,4,null,7].

    5
   / \
  2   6
   \   \
    4   7

 */
public class DeleteNodeInABST {
    static class Recursive {
        // delete node always return the root of the tree
        public BinaryTreeNode deleteNode(BinaryTreeNode root, int key) {
            if (root == null) {
                return null;
            }
            if (root.val < key) {
                root.right = deleteNode(root.right, key);
            } else if (root.val > key) {
                root.left = deleteNode(root.left, key);
            } else {
                // no children
                if (root.left == null && root.right == null) {
                    return null;
                } else if (root.left != null && root.right == null) { // left children only
                    return root.left;
                } else if (root.left == null && root.right != null) { // right children only
                    return root.right;
                } else {
                    // Not delete the node with key directly, but update the value of node with key and delete the node with updated value.
                    int val = findMinimumNode(root.right);
                    root.val = val;
                    root.right = deleteNode(root.right, val);
                }
            }
            return root;
        }

        private int findMinimumNode(BinaryTreeNode root) {
            while (root.left != null) {
                root = root.left;
            }
            return root.val;
        }
    }

    static class Iterative {
        public BinaryTreeNode deleteNode(BinaryTreeNode root, int key) {
            if (root == null) return null;
            BinaryTreeNode dNode = root;
            BinaryTreeNode parentNode = null;
            while (dNode != null && key != dNode.val) {
                parentNode = dNode;
                if (key > dNode.val) {
                    dNode = dNode.right;
                } else {
                    dNode = dNode.left;
                }
            }
            if (dNode == null)
                return root;
            if (dNode.left == null && dNode.right == null) {
                if (dNode == root)
                    return null;
                if (parentNode.left == dNode)
                    parentNode.left = null;
                else
                    parentNode.right = null;
            } else if (dNode.left != null && dNode.right != null) {
                BinaryTreeNode tmp = dNode.right; //find the leftmost node of the right subtree
                parentNode = null;
                while (tmp.left != null) {
                    parentNode = tmp;
                    tmp = tmp.left;
                }
                if (dNode.right == tmp) {
                    dNode.val = tmp.val;
                    dNode.right = tmp.right;
                    tmp.right = null;
                } else {
                    parentNode.left = tmp.right;
                    dNode.val = tmp.val;
                    tmp.right = null;
                }
            } else { // one child is null
                if (dNode.left != null) {
                    BinaryTreeNode tmp = dNode.left;
                    dNode.val = tmp.val;
                    dNode.left = tmp.left;
                    dNode.right = tmp.right;
                } else {
                    BinaryTreeNode tmp = dNode.right;
                    dNode.val = tmp.val;
                    dNode.left = tmp.left;
                    dNode.right = tmp.right;
                }
            }
            return root;
        }
    }

}
