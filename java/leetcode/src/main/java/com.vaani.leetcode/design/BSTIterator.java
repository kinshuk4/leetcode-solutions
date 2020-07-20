package com.vaani.leetcode.design;

import com.vaani.dsa.ds.core.tree.binarytree.simple.BinaryTreeNode;

import java.util.Stack;

/**
 * 13/08/2017. Implement an iterator over a binary search com.vaani.leetcode.tree
 * (BST). Your iterator will be initialized with the root node of a BST.
 *
 * <p>Calling next() will return the next smallest number in the BST.
 *
 * <p>Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is
 * the height of the tree.
 *
 * <p>Solution: The below solution works in average O(1) time and worst case O(h) time using O(h)
 * memory. Use a stack to keep track of min value node.
 */
public class BSTIterator {

    private Stack<BinaryTreeNode> stack;

    public static void main(String[] args) throws Exception {
        BinaryTreeNode root = new BinaryTreeNode(10);
        root.left = new BinaryTreeNode(5);
        root.left.left = new BinaryTreeNode(4);
        root.left.right = new BinaryTreeNode(8);
        root.left.left.left = new BinaryTreeNode(1);
        root.left.right.left = new BinaryTreeNode(7);
        root.right = new BinaryTreeNode(12);
        root.right.left = new BinaryTreeNode(11);
        root.right.right = new BinaryTreeNode(15);
        BSTIterator ite = new BSTIterator(root);
        System.out.println("Hasnext: " + ite.hasNext());
        System.out.println("next: " + ite.next());
        System.out.println("next: " + ite.next());
        System.out.println("next: " + ite.next());
        System.out.println("next: " + ite.next());
        System.out.println("next: " + ite.next());
        System.out.println("next: " + ite.next());
        System.out.println("next: " + ite.next());
        System.out.println("next: " + ite.next());
        System.out.println("next: " + ite.next());
        System.out.println("Hasnext: " + ite.hasNext());
    }

    public BSTIterator(BinaryTreeNode root) {
        stack = new Stack<>();
        fillStack(root);
    }

    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /**
     * @return the next smallest number
     */
    public int next() {
        if (!stack.isEmpty()) {
            BinaryTreeNode top = stack.pop();
            fillStack(top.right);
            return top.val;
        }
        return -1;
    }

    /**
     * Fill stack with min values
     *
     * @param node curr node to begin with
     */
    private void fillStack(BinaryTreeNode node) {
        BinaryTreeNode ite = node;
        while (ite != null) {
            stack.push(ite);
            ite = ite.left;
        }
    }
}
