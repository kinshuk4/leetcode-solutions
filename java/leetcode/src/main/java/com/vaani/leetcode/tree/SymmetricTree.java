package com.vaani.leetcode.tree;

import com.vaani.dsa.ds.core.tree.binarytree.simple.TreeNode;

import static com.vaani.dsa.algo.ds.tree.binary.IsSymmetricTree.isSymmetricRecursive;

/**
 * 14/08/2017. Given a binary tree, check whether it is a mirror
 * of itself (ie, symmetric around its center).
 *
 * <p>For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
 *
 * <p>1 / \ 2 2 / \ / \ 3 4 4 3
 *
 * <p>But the following [1,2,2,null,3,null,3] is not: 1 / \ 2 2 \ \ 3 3
 */
public class SymmetricTree {

    public static void main(String[] args) throws Exception {
        TreeNode node = new TreeNode(3);
        node.left = new TreeNode(4);
        node.right = new TreeNode(5);
        System.out.println(new SymmetricTree().isSymmetric(node));
    }

    public boolean isSymmetric(TreeNode root) {
        return isSymmetricRecursive(root);
    }
}
