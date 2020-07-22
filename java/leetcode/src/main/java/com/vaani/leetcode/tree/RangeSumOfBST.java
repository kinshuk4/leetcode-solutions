package com.vaani.leetcode.tree;


import com.vaani.dsa.ds.core.tree.binarytree.simple.BinaryTreeNode;

import java.util.Stack;

/**
 * Given the root node of a binary search tree, return the sum of values of all nodes with value between L and R (inclusive).
 * <p>
 * The binary search tree is guaranteed to have unique values.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: root = [10,5,15,3,7,null,18], L = 7, R = 15
 * Output: 32
 * Example 2:
 * <p>
 * Input: root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10
 * Output: 23
 * <p>
 * <p>
 * Note:
 * <p>
 * The number of nodes in the tree is at most 10000.
 * The final answer is guaranteed to be less than 2^31.
 */
public class RangeSumOfBST {
    static class BetterDFS {
        int ans;

        public int rangeSumBST(BinaryTreeNode root, int L, int R) {
            ans = 0;
            dfs(root, L, R);

            return ans;
        }

        public void dfs(BinaryTreeNode node, int L, int R) {
            if (node == null) {
                return;
            }
            if (node.val >= L && node.val <= R) {
                ans += node.val;
                dfs(node.left, L, R);
                dfs(node.right, L, R);
            } else if (node.val < L) {
                dfs(node.right, L, R);
            } else {
                dfs(node.left, L, R);
            }

        }
    }

    // not using BST property but just doing dfs
    static class DFS {
        int ans;

        public int rangeSumBST(BinaryTreeNode root, int L, int R) {
            ans = 0;
            dfs(root, L, R);
            return ans;
        }

        public void dfs(BinaryTreeNode node, int L, int R) {
            if (node == null)
                return;
            if (node.val >= L && node.val <= R) {
                ans += node.val;
            }
            dfs(node.left, L, R);
            dfs(node.right, L, R);
        }
    }

    static class Iterative {
        public int rangeSumBST(BinaryTreeNode root, int L, int R) {
            int ans = 0;
            Stack<BinaryTreeNode> stack = new Stack();
            stack.push(root);
            while (!stack.isEmpty()) {
                BinaryTreeNode node = stack.pop();
                if (node == null) {
                    continue;
                }
                if (node.val >= L && node.val <= R) {
                    ans += node.val;
                }
                stack.push(node.left);
                stack.push(node.right);

            }
            return ans;
        }
    }
}
