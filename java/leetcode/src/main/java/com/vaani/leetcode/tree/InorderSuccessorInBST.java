package com.vaani.leetcode.tree;

import com.vaani.dsa.ds.core.tree.binarytree.simple.BinaryTreeNode;

/**
 * Given a binary search tree and a node in it, find
 * the in-order successor of that node in the BST.
 *
 * <p>Note: If the given node has no in-order successor in the tree, return null.
 *
 * <p>Solution: The below solution works with worst case time complexity of O(h) where h is the
 * height of the tree. If the current node is <= target_node, recursively iterate the right of the
 * current node. else if the current node is > target_node then mark the current node as the
 * successor and recursively iterate the left of the current node.
 */
public class InorderSuccessorInBST {

    public static void main(String[] args) throws Exception {
        BinaryTreeNode root = new BinaryTreeNode(10);
        root.left = new BinaryTreeNode(5);
        root.left.left = new BinaryTreeNode(3);
        root.left.right = new BinaryTreeNode(6);
        root.right = new BinaryTreeNode(15);
        root.right.left = new BinaryTreeNode(13);
        root.right.left.left = new BinaryTreeNode(12);
        root.right.left.right = new BinaryTreeNode(14);
        root.right.right = new BinaryTreeNode(17);
        BinaryTreeNode ans = new InorderSuccessorInBST().inorderSuccessor(root, root.right.left.right);
        if (ans != null) System.out.println(ans.val);
        else System.out.println(ans);
    }

    public BinaryTreeNode inorderSuccessor(BinaryTreeNode root, BinaryTreeNode p) {
        return inOrder(root, p, null);
    }


    private BinaryTreeNode inOrder(BinaryTreeNode curr, BinaryTreeNode target, BinaryTreeNode successor) {
        if (curr == null) return successor;
        if (curr.val <= target.val) return inOrder(curr.right, target, successor);
        return inOrder(curr.left, target, curr); // make the current node as successor
    }
}
