package com.vaani.leetcode.tree.bst;

import com.vaani.dsa.ds.core.tree.binarytree.simple.BinaryTreeNode;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * 501. Find Mode in Binary Search Tree
 * Easy
 * Given a binary search tree (BST) with duplicates, find all the mode(s) (the most frequently occurred element) in the given BST.
 * <p>
 * Assume a BST is defined as follows:
 * <p>
 * The left subtree of a node contains only nodes with keys less than or equal to the node's key.
 * The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * For example:
 * Given BST [1,null,2,2],
 * 1
 * \
 * 2
 * /
 * 2
 * return [2].
 * <p>
 * Note: If a tree has more than one mode, you can return them in any order.
 * <p>
 * Follow up: Could you do that without using any extra space? (Assume that the implicit stack space incurred due to recursion does not count).
 */
public class FindModeInBinarySearchTree {
    static class UsingFrequencyMap {
        public int[] findMode(BinaryTreeNode root) {
            HashMap<Integer, Integer> map = new HashMap<>();
            preorder(root, map);
            LinkedList<Integer> list = new LinkedList<>();
            int max = 0;
            for (int key : map.keySet()) {
                if (map.get(key) > max) {
                    list.clear();
                    list.add(key);
                    max = map.get(key);
                } else if (map.get(key) == max) {
                    list.add(key);
                }

            }
            int[] result = list.stream().mapToInt(i -> i).toArray();

            return result;
        }

        private void preorder(BinaryTreeNode root, HashMap<Integer, Integer> map) {
            if (root == null) {
                return;
            }
            map.put(root.val, map.getOrDefault(root.val, 0) + 1);
            preorder(root.left, map);
            preorder(root.right, map);
        }
    }

    static class UsingO1SpaceInorderTraversal {
        public int[] findMode(BinaryTreeNode root) {
            inorder(root);
            modes = new int[modeCount];
            modeCount = 0;
            currCount = 0;
            inorder(root);
            return modes;
        }

        private int currVal;
        private int currCount = 0;
        private int maxCount = 0;
        private int modeCount = 0;

        private int[] modes;

        private void handleValue(int val) {
            if (val != currVal) {
                currVal = val;
                currCount = 0;
            }
            currCount++;
            if (currCount > maxCount) {
                maxCount = currCount;
                modeCount = 1;
            } else if (currCount == maxCount) {
                if (modes != null)
                    modes[modeCount] = currVal;
                modeCount++;
            }
        }

        private void inorder(BinaryTreeNode root) {
            if (root == null) return;
            inorder(root.left);
            handleValue(root.val);
            inorder(root.right);
        }
    }

    static class UsingO1SpaceInorderMorrisTraversal {
        public int[] findMode(BinaryTreeNode root) {
            inorder(root);
            modes = new int[modeCount];
            modeCount = 0;
            currCount = 0;
            inorder(root);
            return modes;
        }

        private int currVal;
        private int currCount = 0;
        private int maxCount = 0;
        private int modeCount = 0;

        private int[] modes;

        private void handleValue(int val) {
            if (val != currVal) {
                currVal = val;
                currCount = 0;
            }
            currCount++;
            if (currCount > maxCount) {
                maxCount = currCount;
                modeCount = 1;
            } else if (currCount == maxCount) {
                if (modes != null)
                    modes[modeCount] = currVal;
                modeCount++;
            }
        }

        // Morris Traversal
        private void inorder(BinaryTreeNode root) {
            BinaryTreeNode node = root;
            while (node != null) {
                if (node.left == null) {
                    handleValue(node.val);
                    node = node.right;
                } else {
                    BinaryTreeNode prev = node.left;
                    while (prev.right != null && prev.right != node)
                        prev = prev.right;
                    if (prev.right == null) {
                        prev.right = node;
                        node = node.left;
                    } else {
                        prev.right = null;
                        handleValue(node.val);
                        node = node.right;
                    }
                }
            }
        }
    }

}
