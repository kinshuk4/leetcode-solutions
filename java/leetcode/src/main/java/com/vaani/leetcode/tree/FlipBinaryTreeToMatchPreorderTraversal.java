package com.vaani.leetcode.tree;

import com.vaani.dsa.ds.core.tree.binarytree.simple.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 971. Flip Binary Tree To Match Preorder Traversal
 * Medium
 * <p>
 * You are given the root of a binary tree with n nodes, where each node is uniquely assigned a value from 1 to n. You are also given a sequence of n values voyage, which is the desired pre-order traversal of the binary tree.
 * <p>
 * Any node in the binary tree can be flipped by swapping its left and right subtrees. For example, flipping node 1 will have the following effect:
 * <p>
 * <p>
 * Flip the smallest number of nodes so that the pre-order traversal of the tree matches voyage.
 * <p>
 * Return a list of the values of all flipped nodes. You may return the answer in any order. If it is impossible to flip the nodes in the tree to make the pre-order traversal match voyage, return the list [-1].
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [1,2], voyage = [2,1]
 * Output: [-1]
 * Explanation: It is impossible to flip the nodes such that the pre-order traversal matches voyage.
 * Example 2:
 * <p>
 * <p>
 * Input: root = [1,2,3], voyage = [1,3,2]
 * Output: [1]
 * Explanation: Flipping node 1 swaps nodes 2 and 3, so the pre-order traversal matches voyage.
 * Example 3:
 * <p>
 * <p>
 * Input: root = [1,2,3], voyage = [1,2,3]
 * Output: []
 * Explanation: The tree's pre-order traversal already matches voyage, so no nodes need to be flipped.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is n.
 * n == voyage.length
 * 1 <= n <= 100
 * 1 <= Node.val, voyage[i] <= n
 * All the values in the tree are unique.
 * All the values in voyage are unique.
 */
public class FlipBinaryTreeToMatchPreorderTraversal {

    public static void main(String[] args) {
        //
    }

    private int i, count;
    private List<Integer> ans;

    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        i = 0;
        count = 0;
        ans = new ArrayList<>();
        inorderCount(root);
        if (count != voyage.length) {
            return List.of(-1);
        }
        preorder(root, voyage);
        if (i == voyage.length) {
            return ans;
        }
        return List.of(-1);
    }

    private void inorderCount(TreeNode node) {
        if (node == null) {
            return;
        }

        count++;
        inorderCount(node.left);
        inorderCount(node.right);

    }

    private void preorder(TreeNode node, int[] voyage) {
        if (node == null) {
            return;
        }
        if (voyage[i] == node.val) {
            i++;
        }
        if (node.left != null && node.right != null) {
            if (voyage[i] == node.right.val) {
                TreeNode temp = node.left;
                node.left = node.right;
                node.right = temp;
                ans.add(node.val);
            }
        }
        preorder(node.left, voyage);
        preorder(node.right, voyage);

    }
}
