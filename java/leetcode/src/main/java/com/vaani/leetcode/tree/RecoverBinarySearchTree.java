package com.vaani.leetcode.tree;

/**
 * 99. Recover Binary Search Tree
 * Hard
 *
 * 1878
 *
 * 79
 *
 * Add to List
 *
 * Share
 * You are given the root of a binary search tree (BST), where exactly two nodes of the tree were swapped by mistake. Recover the tree without changing its structure.
 *
 * Follow up: A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,3,null,null,2]
 * Output: [3,1,null,null,2]
 * Explanation: 3 cannot be a left child of 1 because 3 > 1. Swapping 1 and 3 makes the BST valid.
 * Example 2:
 *
 *
 * Input: root = [3,1,4,null,null,2]
 * Output: [2,1,4,null,null,3]
 * Explanation: 2 cannot be in the right subtree of 3 because 2 < 3. Swapping 2 and 3 makes the BST valid.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [2, 1000].
 * -2^31 <= Node.val <= 2^31 - 1
 *

 */

import com.vaani.dsa.ds.core.tree.binarytree.simple.TreeNode;

/**
 * * <p>Solution: O(N) time and O(1) space. Step 1, perform a inorder traversal and mark left and
 *  * right pointer at the node where violation of BST occurs. Step2, find the next node which is
 *  * smaller or equal to right pointer node. Finally swap left and right node values.
 */
public class RecoverBinarySearchTree {

    public static void main(String[] args) throws Exception {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(1);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(5);
        new RecoverBinarySearchTree().recoverTree(root);
    }

    private boolean violation;
    private TreeNode left, right, prev;
    public void recoverTree(TreeNode root) {
        inorder(root);
        if (left != null && right != null) {
            int temp = left.val;
            left.val = right.val;
            right.val = temp;
        }
    }

    private void inorder(TreeNode root) {
        if (root != null) {
            inorder(root.left);
            if (prev != null) {
                if (!violation) {
                    if (prev.val > root.val) {
                        violation = true;
                        left = prev;
                        right = root;
                    } else {
                        prev = root;
                    }
                } else {
                    if (root.val <= right.val) {
                        right = root;
                    }
                }
            } else {
                prev = root;
            }
            inorder(root.right);
        }
    }
}
