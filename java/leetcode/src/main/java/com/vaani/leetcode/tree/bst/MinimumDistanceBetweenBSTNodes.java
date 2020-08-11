package com.vaani.leetcode.tree.bst;

import com.vaani.dsa.ds.core.tree.binarytree.simple.BinaryTreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/minimum-distance-between-bst-nodes/
 * <p>
 * 783. Minimum Distance Between BST Nodes
 * Easy
 * <p>
 * Given a Binary Search Tree (BST) with the root node root,
 * return the minimum difference between the values of any two different nodes in the tree.
 */
/*
 * Example :
 *
 * Input: root = [4,2,6,1,3,null,null]
 * Output: 1
 * Explanation:
 * Note that root is a TreeNode object, not an array.
 *
 * The given tree [4,2,6,1,3,null,null] is represented by the following diagram:
 *
 *           4
 *         /   \
 *       2      6
 *      / \
 *     1   3
 *
 * while the minimum difference in this tree is 1, it occurs between node 1 and node 2, also between node 3 and node 2.
 *
 */


/**
 * Note:
 * <p>
 * The size of the BST will be between 2 and 100.
 * The BST is always valid, each node's value is an integer, and each node's value is different.
 * This question is the same as 530: https://leetcode.com/problems/minimum-absolute-difference-in-bst/
 */
public class MinimumDistanceBetweenBSTNodes {
    static class UsingOnSpaceInorder {
        public int minDiffInBST(BinaryTreeNode root) {
            List<Integer> res = new ArrayList<Integer>();
            inorder(res, root);
            int min = Integer.MAX_VALUE;
            for (int i = 1; i < res.size(); i++) {
                min = Math.min(min, res.get(i) - res.get(i - 1));
            }
            return min;
        }

        private void inorder(List<Integer> result, BinaryTreeNode root) {
            if (root == null) {
                return;
            }
            inorder(result, root.left);
            result.add(root.val);
            inorder(result, root.right);
        }
    }

    static class UsingO1SpaceInorder {
        Integer result = Integer.MAX_VALUE, prev = null;

        public int minDiffInBST(BinaryTreeNode root) {
            if (root.left != null) {
                minDiffInBST(root.left);
            }
            if (prev != null) {
                result = Math.min(result, root.val - prev);
            }
            prev = root.val;
            if (root.right != null) {
                minDiffInBST(root.right);
            }
            return result;
        }
    }

}
