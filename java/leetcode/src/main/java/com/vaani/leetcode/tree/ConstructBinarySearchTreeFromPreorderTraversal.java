package com.vaani.leetcode.tree;


import com.vaani.dsa.ds.core.tree.binarytree.simple.TreeNode;

import java.util.Stack;

public class ConstructBinarySearchTreeFromPreorderTraversal {


    // O (nlg) solution)
    public TreeNode bstFromPreorder(int[] preorder) {
        TreeNode root = new TreeNode(preorder[0]);

        for (int i = 1; i < preorder.length; i++) {
            TreeNode curr = root;
            while (curr != null) {
                if (curr.val > preorder[i]) {
                    if (curr.left != null) {
                        curr = curr.left;
                    } else {
                        curr.left = new TreeNode(preorder[i]);
                        break;
                    }
                } else if (curr.val < preorder[i]) {
                    if (curr.right != null) {
                        curr = curr.right;
                    } else {
                        curr.right = new TreeNode(preorder[i]);
                        break;
                    }
                }
            }
        }

        return root;
    }

    // T - O(n), S - O(n)
    public TreeNode bstFromPreorder2(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode root = new TreeNode(preorder[0]);
        stack.push(root);
        for (int i = 1; i < preorder.length; i++) {
            TreeNode node = new TreeNode(preorder[i]);
            if (preorder[i] < stack.peek().val) {
                stack.peek().left = node;
            } else {
                TreeNode parent = stack.peek();
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
        public TreeNode bstFromPreorder(int[] arr) {
            return helper(arr, Integer.MAX_VALUE);
        }

        public TreeNode helper(int[] arr, int bound) {
            if (i == arr.length || arr[i] > bound) {
                return null;
            }
            TreeNode root = new TreeNode(arr[i++]);
            root.left = helper(arr, root.val);
            root.right = helper(arr, bound);
            return root;
        }
    }

}
