package com.vaani.leetcode.tree;

import com.vaani.dsa.ds.core.tree.binarytree.simple.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.com/problems/leaf-similar-trees/
 * 872. Leaf-Similar Trees
 * Easy
 * <p>
 * Consider all the leaves of a binary tree. From left
 * to right order, the values of those leaves form a leaf value sequence.
 *
 * <p>For example, in the given tree above, the leaf value sequence is (6, 7, 4, 9, 8).
 *
 * <p>Two binary trees are considered leaf-similar if their leaf value sequence is the same.
 *
 * <p>Return true if and only if the two given trees with head nodes root1 and root2 are
 * leaf-similar.
 *
 * <p>Solution: Do a inorder traversal for each trree and keep track of all the leaf nodes of the
 * tree in a list. Compare the list and return the answer.
 */
public class LeafSimilarTrees {

    public static void main(String[] args) {
    }

    static class UsingRecursion {
        public boolean leafSimilar(TreeNode root1, TreeNode root2) {
            List<Integer> list1 = new ArrayList<>();
            List<Integer> list2 = new ArrayList<>();
            preorder(root1, list1);
            preorder(root2, list2);
            if (list1.size() != list2.size()) {
                return false;
            } else {
                for (int i = 0, l = list1.size(); i < l; i++) {
                    if (list1.get(i).intValue() != list2.get(i).intValue()) {
                        return false;
                    }
                }
            }
            return true;
        }

        private void preorder(TreeNode root, List<Integer> list) {
            if (root == null) {
                return;
            }

            // if is leaf
            if (root.left == null && root.right == null) {
                list.add(root.val);
            }
            preorder(root.left, list);
            preorder(root.right, list);
        }
    }

    static class UsingIteration {
        public boolean leafSimilar(TreeNode root1, TreeNode root2) {
            List<Integer> leaves1 = new ArrayList<>();
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root1);
            while (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                if (node.right != null) {
                    stack.push(node.right);
                }
                if (node.left != null) {
                    stack.push(node.left);
                }
                if (node.left == null && node.right == null) {
                    leaves1.add(node.val);
                }
            }

            return check(leaves1, root2);
        }

        private boolean check(List<Integer> leaves1, TreeNode root2) {
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root2);
            int cur = 0;
            while (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                if (node.right != null)
                    stack.push(node.right);
                if (node.left != null)
                    stack.push(node.left);
                if (node.left == null && node.right == null) {
                    if (node.val != leaves1.get(cur)) {
                        return false;
                    } else {
                        cur++;
                    }
                }
            }
            return true;
        }
    }


}
