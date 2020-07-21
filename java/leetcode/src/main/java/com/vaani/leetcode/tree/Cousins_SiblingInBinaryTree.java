package com.vaani.leetcode.tree;

import com.vaani.dsa.ds.core.tree.binarytree.simple.BinaryTreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/cousins-in-binary-tree/
 * In a binary tree, the root node is at depth 0, and children of each depth k node are at depth k+1.
 * <p>
 * Two nodes of a binary tree are cousins if they have the same depth, but have different parents.
 * <p>
 * We are given the root of a binary tree with unique values, and the values x and y of two different nodes in the tree.
 * <p>
 * Return true if and only if the nodes corresponding to the values x and y are cousins.
 */
public class Cousins_SiblingInBinaryTree {

    // not passing all cases
    public boolean isCousins1(BinaryTreeNode root, int x, int y) {
        if (root == null) {
            return false;
        }

        // get the heights of both the nodes and return false if heights ate not same

        if (getHeight(root, x, 1) != getHeight(root, y, 1)) {
            return false;
        } else {
            // Now check if or not parents are same for both the node, if not ,
            // return true
            if (!sameParents(root, x, y)) {
                return true;
            } else {
                return false;
            }
        }
    }

    public int getHeight(BinaryTreeNode root, int x, int height) {
        if (root == null)
            return 0;
        if (root.val == x)
            return height;

        int level = getHeight(root.left, x, height + 1);
        if (level != 0)
            return level;
        return getHeight(root.right, x, height + 1);
    }

    public boolean sameParents(BinaryTreeNode root, int x, int y) {
        if (root == null) {
            return false;
        }

        if (root.left != null && root.right != null) {
            return ((root.left.val == x && root.right.val == y)
                    || (root.left.val == y && root.right.val == x)
                    || sameParents(root.left, x, y) || sameParents(root.right, x, y));
        }

        return false;
    }

    // submitted
    boolean isCousins2(BinaryTreeNode root, int x, int y) {
        // Use queue to get nodes at the same level
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);
        int found = 0;
        while (!queue.isEmpty()) {
            BinaryTreeNode current = queue.poll();
            if (current == null) {
                // move to next level
                if (!queue.isEmpty()) queue.add(null);
                found = 0;
                continue;
            }

            if (current.left != null && (current.left.val == x || current.left.val == y)) {
                found++;
            } else if (current.right != null && (current.right.val == x || current.right.val == y)) {
                found++;
            }
            if (found == 2) return true;

            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }

        return false;
    }

    public boolean isCousins3(BinaryTreeNode root, int x, int y) {
        int[] res = new int[5];
        inorder(root.left, x, y, 0, root, res);
        inorder(root.right, x, y, 0, root, res);
        if (res[1] != res[3] && res[2] == res[4]) {
            return true;
        }
        return false;
    }

    public void inorder(BinaryTreeNode node, int x, int y, int depth, BinaryTreeNode parent, int[] res) {
        if (node == null)
            return;
        depth = depth + 1;
        inorder(node.left, x, y, depth, node, res);

        if (node.val == x) {
            res[1] = parent.val;
            res[2] = depth;
            return;
        } else if (node.val == y) {
            res[3] = parent.val;
            res[4] = depth;
            return;
        }

        inorder(node.right, x, y, depth, node, res);
    }
}
