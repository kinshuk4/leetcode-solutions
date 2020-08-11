package com.vaani.leetcode.tree;

import com.vaani.dsa.ds.core.tree.binarytree.simple.BinaryTreeNode;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/second-minimum-node-in-a-binary-tree/
 * 671. Second Minimum Node In a Binary Tree
 * Easy
 * <p>
 * Given a non-empty special binary tree consisting of nodes with the non-negative value, where each node in this tree has exactly two or zero sub-node. If the node has two sub-nodes, then this node's value is the smaller value among its two sub-nodes. More formally, the property root.val = min(root.left.val, root.right.val) always holds.
 * <p>
 * Given such a binary tree, you need to output the second minimum value in the set made of all the nodes' value in the whole tree.
 * <p>
 * If no such second minimum value exists, output -1 instead.
 */
/*
Example 1:

Input:
    2
   / \
  2   5
     / \
    5   7

Output: 5
Explanation: The smallest value is 2, the second smallest value is 5.



Example 2:

Input:
    2
   / \
  2   2

Output: -1
Explanation: The smallest value is 2, but there isn't any second smallest value.

 */
public class SecondMinimumNodeInABinaryTree {
    static class GettingAllValuesOnSpace {
        public int findSecondMinimumValue(BinaryTreeNode root) {
            Set<Integer> uniques = new HashSet<>();
            dfs(root, uniques);

            int min1 = root.val;
            int result = Integer.MAX_VALUE;
            for (int v : uniques) {
                if (min1 < v && v < result)
                    result = v;
            }
            return result < Integer.MAX_VALUE ? result : -1;
        }

        public void dfs(BinaryTreeNode root, Set<Integer> uniques) {
            if (root != null) {
                uniques.add(root.val);
                dfs(root.left, uniques);
                dfs(root.right, uniques);
            }
        }
    }

    static class UsingO1Space {
        int min1;
        // to avoid test case when node value is Integer.MAX_VALUE, hence long
        long min2 = Long.MAX_VALUE;

        public int findSecondMinimumValue(BinaryTreeNode root) {
            if(root == null){
                return -1;
            }
            min1 = root.val;
            dfs(root);

            return min2 < Long.MAX_VALUE ? (int)min2 : -1;
        }

        public void dfs(BinaryTreeNode root) {
            if (root == null) {
                return;
            }
            // Note we are not updating min1 as node value is always min of node.left and node.right value.
            if (min1 < root.val && root.val < min2) {
                min2 = root.val;
            } else if (min1 == root.val) {
                dfs(root.left);
                dfs(root.right);
            }
        }
    }

}
