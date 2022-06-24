package com.vaani.leetcode.tree;

import com.vaani.dsa.ds.core.tree.binarytree.simple.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree, return all root-to-leaf paths.
 * <p>
 * Note: A leaf is a node with no children.
 */

/*
Example:

Input:

   1
 /   \
2     3
 \
  5

Output: ["1->2->5", "1->3"]

Explanation: All root-to-leaf paths are: 1->2->5, 1->3

 */

public class BinaryTreePaths {

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        //inorder1(root, result, "");
        inorder(root, result, "");
        return result;
    }

    private final static String ARROW = "->";

    private void inorder(TreeNode root, List<String> list, String currentPath) {
        currentPath += root.val;
        // if leaf
        if (root.left == null && root.right == null) {
            list.add(currentPath);
            return;
        }
        if (root.left != null) {
            inorder(root.left, list, currentPath + ARROW);
        }

        if (root.right != null) {
            inorder(root.right, list, currentPath + ARROW);
        }

    }

    private void inorder1(TreeNode root, List<String> list, String currentPath) {
        if (root != null) {
            if (root.left == null && root.right == null) {
                list.add(currentPath + root.val);
            } else {
                inorder1(root.left, list, currentPath + root.val + "->");
                inorder1(root.right, list, currentPath + root.val + "->");
            }
        }
    }
}
