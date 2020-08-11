package com.vaani.leetcode.tree;

import com.vaani.dsa.ds.core.tree.binarytree.simple.BinaryTreeNode;

/**
 * https://leetcode.com/problems/construct-string-from-binary-tree/
 * 606. Construct String from Binary Tree
 * Easy
 * You need to construct a string consists of
 * parenthesis and integers from a binary tree with the preorder traversing way.
 *
 * <p>The null node needs to be represented by empty parenthesis pair "()". And you need to omit all
 * the empty parenthesis pairs that don't affect the one-to-one mapping relationship between the
 * string and the original binary tree.
 */
/*
Example 1:

Input: Binary tree: [1,2,3,4]
       1
     /   \
    2     3
   /
  4

Output: "1(2(4))(3)"

Explanation: Originallay it needs to be "1(2(4)())(3()())",
but you need to omit all the unnecessary empty parenthesis pairs.
And it will be "1(2(4))(3)".

Example 2:

Input: Binary tree: [1,2,3,null,4]
       1
     /   \
    2     3
     \
      4

Output: "1(2()(4))(3)"

Explanation: Almost the same as the first example,
except we can't omit the first parenthesis pair to break the one-to-one mapping relationship between the input and the output.
 */
public class ConstructStringFromBinaryTree {
    public static void main(String[] args) throws Exception {
        BinaryTreeNode t = new BinaryTreeNode(1);
        t.left = new BinaryTreeNode(2);
        t.left.left = new BinaryTreeNode(4);
        t.right = new BinaryTreeNode(3);
        System.out.println(new ConstructStringFromBinaryTree().tree2str(t));
    }

    public String tree2str(BinaryTreeNode t) {
        if (t == null) {
            return "";
        }
        String left = tree2str(t.left);
        String right = tree2str(t.right);
        if (left.equals("") && right.equals("")) {
            return String.valueOf(t.val);
        }
        // we have to check empty for left side only, as right side can be skipped
        if (left.equals("")) {
            left = "()";
        } else {
            left = "(" + left + ")";
        }
        if (!right.equals("")) {
            right = "(" + right + ")";
        }
        return t.val + left + right;
    }
}
