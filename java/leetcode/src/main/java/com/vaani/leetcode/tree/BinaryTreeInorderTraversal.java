package com.vaani.leetcode.tree;

import com.vaani.dsa.ds.core.tree.binarytree.simple.BinaryTreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 06/08/2017. Given a binary tree, return the inorder traversal
 * of its nodes' values.
 *
 * <p>For example: Given binary tree [1,null,2,3], 1 \ 2 / 3 return [1,3,2].
 *
 * <p>Note: Recursive solution is trivial, could you do it iteratively?
 */
public class BinaryTreeInorderTraversal {
    public static void main(String[] args) throws Exception {
        BinaryTreeNode root = new BinaryTreeNode(3);
        root.left = new BinaryTreeNode(4);
        root.left.left = new BinaryTreeNode(5);
        root.left.right = new BinaryTreeNode(6);
        root.left.left.left = new BinaryTreeNode(9);
        root.left.left.right = new BinaryTreeNode(10);
        root.right = new BinaryTreeNode(2);
        root.right.left = new BinaryTreeNode(7);
        root.right.right = new BinaryTreeNode(8);
        List<Integer> result = new BinaryTreeInorderTraversal().inorderTraversal(root);
        System.out.println(result);
    }

    public List<Integer> inorderTraversal(BinaryTreeNode root) {
        Stack<BinaryTreeNode> stack = new Stack<>();
        BinaryTreeNode curr = root;
        List<Integer> result = new ArrayList<>();
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            result.add(curr.val);
            curr = curr.right;
        }
        return result;
    }
}
