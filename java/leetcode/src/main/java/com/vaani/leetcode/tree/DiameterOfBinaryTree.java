package com.vaani.leetcode.tree;

import com.vaani.dsa.ds.core.tree.binarytree.simple.BinaryTreeNode;

import static com.vaani.dsa.algo.ds.tree.binary.TreeDiameter.getBinaryTreeDiameter;

/**
 * https://leetcode.com/problems/diameter-of-binary-tree/
 *
 * <p>Given a binary tree, you need to compute the length of the diameter of the tree. The diameter
 * of a binary tree is the length of the longest path between any two nodes in a tree. This path may
 * or may not pass through the root.
 *
 * <p>Example: Given a binary tree 1 / \ 2 3 / \ 4 5 Return 3, which is the length of the path
 * [4,2,1,3] or [5,2,1,3].
 *
 * <p>Note: The length of path between two nodes is represented by the number of edges between them.
 */
public class DiameterOfBinaryTree {

    private int max = 0;

    public static void main(String[] args) throws Exception {
        BinaryTreeNode root = new BinaryTreeNode(5);
        root.left = new BinaryTreeNode(4);
        System.out.println(new DiameterOfBinaryTree().diameterOfBinaryTreeDFS(root));
        System.out.println(new DiameterOfBinaryTree().diameterOfBinaryTree(root));
    }

    public int diameterOfBinaryTreeDFS(BinaryTreeNode root) {
        dfs(root);
        return max;
    }

    public int diameterOfBinaryTree(BinaryTreeNode root) {
        if (root == null) {
            return 0;
        }
        return getBinaryTreeDiameter(root) - 1;
    }

    private int dfs(BinaryTreeNode node) {
        if (node != null) {
            int left = dfs(node.left);
            int right = dfs(node.right);
            max = Math.max(max, left + right);
            return left > right ? left + 1 : right + 1;
        }
        return 0;
    }
}
