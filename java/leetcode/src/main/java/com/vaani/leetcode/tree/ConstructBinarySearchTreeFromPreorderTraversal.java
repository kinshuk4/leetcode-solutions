package com.vaani.leetcode.tree;


import com.vaani.dsa.ds.core.tree.binarytree.simple.BinaryTreeNode;

import java.util.Stack;

public class ConstructBinarySearchTreeFromPreorderTraversal {


    // O (nlg) solution)
    public BinaryTreeNode bstFromPreorder(int[] preorder) {
        BinaryTreeNode root = new BinaryTreeNode(preorder[0]);

        for (int i = 1; i < preorder.length; i++) {
            BinaryTreeNode curr = root;
            while (curr != null) {
                if (curr.val > preorder[i]) {
                    if (curr.left != null) {
                        curr = curr.left;
                    } else {
                        curr.left = new BinaryTreeNode(preorder[i]);
                        break;
                    }
                } else if (curr.val < preorder[i]) {
                    if (curr.right != null) {
                        curr = curr.right;
                    } else {
                        curr.right = new BinaryTreeNode(preorder[i]);
                        break;
                    }
                }
            }
        }

        return root;
    }

    // T - O(n), S - O(n)
    public BinaryTreeNode bstFromPreorder2(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        Stack<BinaryTreeNode> stack = new Stack<>();
        BinaryTreeNode root = new BinaryTreeNode(preorder[0]);
        stack.push(root);
        for (int i = 1; i < preorder.length; i++) {
            BinaryTreeNode node = new BinaryTreeNode(preorder[i]);
            if (preorder[i] < stack.peek().val) {
                stack.peek().left = node;
            } else {
                BinaryTreeNode parent = stack.peek();
                while (!stack.isEmpty() && preorder[i] > stack.peek().val) {
                    parent = stack.pop();
                }
                parent.right = node;
            }
            stack.push(node);
        }
        return root;
    }

    // Recursive
    static class UsingRecursion {
        int i = 0;

        // https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/discuss/589801/JAVA-3-WAYS-TO-DO-THE-PROBLEM!-O(N)-APPROACH
        // It is  possible to do this because when we construct the " left child " the upper bound will be the node value itself and no lower bound will be needed!
        //	-no lower bound is required for "right child" because we have arrived at this point of creating the right child only because these elements failed to satisfy the left subtree conditions!"
        public BinaryTreeNode bstFromPreorder(int[] arr) {
            return helper(arr, Integer.MAX_VALUE);
        }

        public BinaryTreeNode helper(int[] arr, int bound) {
            if (i == arr.length || arr[i] > bound) {
                return null;
            }
            BinaryTreeNode root = new BinaryTreeNode(arr[i++]);
            root.left = helper(arr, root.val);
            root.right = helper(arr, bound);
            return root;
        }
    }

}
