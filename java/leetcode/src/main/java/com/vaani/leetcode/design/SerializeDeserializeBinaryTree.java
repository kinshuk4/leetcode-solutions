package com.vaani.leetcode.design;

import com.vaani.dsa.ds.core.tree.binarytree.simple.BinaryTreeNode;

import java.util.*;

/**
 * https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 * 297. Serialize and Deserialize Binary Tree
 * Hard
 * <p>
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 * <p>
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
 * <p>
 * Clarification: The input/output format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [1,2,3,null,null,4,5]
 * Output: [1,2,3,null,null,4,5]
 * Example 2:
 * <p>
 * Input: root = []
 * Output: []
 * Example 3:
 * <p>
 * Input: root = [1]
 * Output: [1]
 * Example 4:
 * <p>
 * Input: root = [1,2]
 * Output: [1,2]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [0, 104].
 * -1000 <= Node.val <= 1000
 * <p>
 * <p>
 * <p>
 * just the same as how LeetCode OJ serializes a
 * binary tree. You do not necessarily need to follow this format, so please be creative and come up
 * with different approaches yourself. Note: Do not use class member/global/static variables to
 * store states. Your serialize and deserialize algorithms should be stateless.
 */
public class SerializeDeserializeBinaryTree {

    private static final String NULL = "#";

    public static void main(String[] args) throws Exception {
        BinaryTreeNode root = new BinaryTreeNode(1);
        BinaryTreeNode two = new BinaryTreeNode(2);
        BinaryTreeNode three = new BinaryTreeNode(3);
        BinaryTreeNode four = new BinaryTreeNode(4);
        BinaryTreeNode five = new BinaryTreeNode(5);
        BinaryTreeNode six = new BinaryTreeNode(6);
        BinaryTreeNode seven = new BinaryTreeNode(7);
        BinaryTreeNode eight = new BinaryTreeNode(8);
        BinaryTreeNode nine = new BinaryTreeNode(9);
        BinaryTreeNode ten = new BinaryTreeNode(10);
        root.left = null;
        root.right = two;
        two.left = three;
        three.left = four;
        four.right = five;
        five.right = six;
        six.left = seven;
        seven.left = eight;
        eight.right = nine;
        nine.right = ten;
        String serializedStr = new SerializeDeserializeBinaryTree().serialize(root);

        BinaryTreeNode result = new SerializeDeserializeBinaryTree().deserialize(serializedStr);
    }

    // Encodes a tree to a single string.
    public String serialize(BinaryTreeNode root) {
        if (root == null) {
            return null;
        }
        List<String> list = new ArrayList<>();
        encode(root, list);
        StringBuilder sb = new StringBuilder();
        sb.append(list.get(0));
        for (int i = 1, l = list.size(); i < l; i++) {
            sb.append(",").append(list.get(i));
        }
        return sb.toString();
    }

    private void encode(BinaryTreeNode root, List<String> list) {
        if (root == null) {
            list.add(NULL);
        } else {
            list.add(String.valueOf(root.val));
            encode(root.left, list);
            encode(root.right, list);
        }
    }

    // Decodes your encoded data to tree.
    public BinaryTreeNode deserialize(String data) {
        if (data == null || data.isEmpty()) {
            return null;
        }
        StringTokenizer st = new StringTokenizer(data, ",");
        Queue<String> queue = new ArrayDeque<>();
        while (st.hasMoreTokens()) {
            queue.offer(st.nextToken());
        }
        return decode(queue);
    }

    private BinaryTreeNode decode(Queue<String> queue) {
        String nodeVal = queue.poll();
        if (NULL.equals(nodeVal)) {
            return null;
        }
        BinaryTreeNode root = new BinaryTreeNode(Integer.parseInt(nodeVal));
        root.left = decode(queue);
        root.right = decode(queue);
        return root;
    }
}
