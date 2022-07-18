package com.vaani.leetcode.tree;

import com.vaani.dsa.ds.core.tree.binarytree.simple.TreeNode;

/* https://leetcode.com/problems/sum-of-left-leaves/
Find the sum of all left leaves in a given binary tree.

Example:

    3
   / \
  9  20
    /  \
   15   7

There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
 */
public class SumofLeftLeaves {
    public static void main(String[] args) throws Exception {
    }



    public int sumOfLeftLeaves1(TreeNode root) {
        return inorder(root, false);
    }

    private int inorder(TreeNode node, boolean isLeft) {
        if (node != null) {
            if (node.left == null && node.right == null) {
                if (isLeft) {
                    return node.val;
                } else return 0;
            }
            return inorder(node.left, true) + inorder(node.right, false);
        }
        return 0;
    }

    public int sumOfLeftLeaves2(TreeNode root) {
        if(root == null){
            return 0;
        }
        // if left node is present and is a leaf
        if(root.left != null && root.left.left == null && root.left.right == null){
            return root.left.val + sumOfLeftLeaves2(root.right);
        }

        return sumOfLeftLeaves2(root.left) + sumOfLeftLeaves2(root.right);
    }
}
