package com.vaani.leetcode.design;

import com.vaani.dsa.ds.core.tree.binarytree.simple.BinaryTreeNode;

import java.util.*;

/**
 * https://leetcode.com/problems/serialize-and-deserialize-bst/
 * 449. Serialize and Deserialize BST
 * Medium
 * <p>
 * Serialization is converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 * <p>
 * Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your serialization/deserialization algorithm should work. You need to ensure that a binary search tree can be serialized to a string, and this string can be deserialized to the original tree structure.
 * <p>
 * The encoded string should be as compact as possible.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: root = [2,1,3]
 * Output: [2,1,3]
 * Example 2:
 * <p>
 * Input: root = []
 * Output: []
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [0, 10^4].
 * 0 <= Node.val <= 10^4
 * The input tree is guaranteed to be a binary search tree.
 */
public class SerializeAndDeserializeBST {

    private static final String NULL = "#";

    public static void main(String[] args) throws Exception {
        BinaryTreeNode root = new BinaryTreeNode(1);
        String serializedStr = new SerializeAndDeserializeBST().serialize(root);
        BinaryTreeNode result = new SerializeAndDeserializeBST().deserialize(serializedStr);
    }

    // Encodes a tree to a single string.
    public String serialize(BinaryTreeNode root) {
        if (root == null) {
            return "";
        }

        return root.val + "," + serialize(root.left) + "," + serialize(root.right);

    }

    // Decodes your encoded data to tree.
    public BinaryTreeNode deserialize(String data) {
        String[] nodeValues = data.split(",");
        Queue<String> queue = new LinkedList<>();
        Collections.addAll(queue, nodeValues);


        return decode(queue);

    }

    public BinaryTreeNode decode(Queue<String> queue) {
        while (!queue.isEmpty()) {
            String s = queue.poll();
            if (s.equals("")) {
                return null;
            }
            BinaryTreeNode root = new BinaryTreeNode(Integer.parseInt(s));
            root.left = decode(queue);
            root.right = decode(queue);
            return root;
        }
        return null;
    }


}
