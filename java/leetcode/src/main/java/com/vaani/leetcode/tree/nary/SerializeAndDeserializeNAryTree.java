package com.vaani.leetcode.tree.nary;

import com.vaani.dsa.ds.core.tree.nary.NAryTreeNode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Serialization is the process of converting a data
 * structure or object into a sequence of bits so that it can be stored in a file or memory buffer,
 * or transmitted across a network connection link to be reconstructed later in the same or another
 * computer environment.
 *
 * <p>Design an algorithm to serialize and deserialize an N-ary tree. An N-ary tree is a rooted tree
 * in which each node has no more than N children. There is no restriction on how your
 * serialization/deserialization algorithm should work. You just need to ensure that an N-ary tree
 * can be serialized to a string and this string can be deserialized to the original tree structure.
 *
 * <p>For example, you may serialize the following 3-ary tree
 *
 * <p>1 /|\ 3 2 4 /\ 5 6
 *
 * <p>as [1 [3[5 6] 2 4]]. You do not necessarily need to follow this format, so please be creative
 * and come up with different approaches yourself.
 *
 * <p>Note:
 *
 * <p>N is in the range of [1, 1000] Do not use class member/global/static variables to store
 * states. Your serialize and deserialize algorithms should be stateless.
 *
 * <p>Solution: To encode recursively iterate through each node and build a root and its children as
 * 3[5,6] where 3 is the root and 5, 6 are its children. To decode, build the root node first and
 * then recursively build its children.
 */

public class SerializeAndDeserializeNAryTree {
    public static void main(String[] args) {
        NAryTreeNode n1 = new NAryTreeNode(5, new ArrayList<>());
        NAryTreeNode n2 = new NAryTreeNode(6, Arrays.asList(n1));
        NAryTreeNode n3 = new NAryTreeNode(2, Arrays.asList(n2));
        NAryTreeNode n4 = new NAryTreeNode(4, Arrays.asList(n3));
        NAryTreeNode n5 = new NAryTreeNode(3, Arrays.asList(n4));
        NAryTreeNode root = new NAryTreeNode(1, Arrays.asList(n5));
        SerializeAndDeserializeNAryTree.UsingIterative serializer = new SerializeAndDeserializeNAryTree.UsingIterative();
        String result = serializer.serialize(root);
        NAryTreeNode rootNode = serializer.deserialize(result);
        System.out.println(result);
        System.out.println(rootNode);
    }

    static class UsingIterative {
        // Encodes a tree to a single string.
        public String serialize(NAryTreeNode root) {
            if (root != null) {
                String curr = String.valueOf(root.val);
                List<NAryTreeNode> children = root.children;
                return children != null
                        ? curr
                        + "["
                        + children.stream().map(this::serialize).collect(Collectors.joining(","))
                        + "]"
                        : curr + "[]";
            } else {
                return "";
            }
        }

        // Decodes your encoded data to tree.
        public NAryTreeNode deserialize(String data) {
            char[] arr = data.toCharArray();
            StringBuilder num = new StringBuilder();
            Queue<String> queue = new ArrayDeque<>();
            for (char c : arr) {
                if (c >= '0' && c <= '9') {
                    num.append(c);
                } else if (c == '[') {
                    if (num.length() != 0) {
                        queue.offer(num.toString());
                        num = new StringBuilder();
                    }
                    queue.offer("[");
                } else {
                    queue.offer(String.valueOf(c));
                }
            }
            if (queue.isEmpty()) return null;
            return decode(queue).get(0);
        }

        private List<NAryTreeNode> decode(Queue<String> elements) {
            List<NAryTreeNode> children = new ArrayList<>();
            while (!elements.isEmpty()) {
                String curr = elements.poll();
                if (curr.equals("[") || curr.equals(",")) {
                } else if (curr.equals("]")) {
                    return children;
                } else {
                    int num = Integer.parseInt(curr);
                    NAryTreeNode currNode = new NAryTreeNode(num, decode(elements));
                    children.add(currNode);
                }
            }
            return children;
        }
    }

    static class UsingRecursion {
        static class WrappableInt {
            private int value;

            public WrappableInt(int x) {
                this.value = x;
            }

            public int getValue() {
                return this.value;
            }

            public void increment() {
                this.value++;
            }
        }

        class Codec {
            // Encodes a tree to a single string.
            public String serialize(NAryTreeNode root) {

                StringBuilder sb = new StringBuilder();
                serializeHelper(root, sb);
                return sb.toString();
            }

            private void serializeHelper(NAryTreeNode root, StringBuilder sb) {

                if (root == null) {
                    return;
                }

                // Add the value of the node
                sb.append((char) (root.val + '0'));

                // Add the number of children
                sb.append((char) (root.children.size() + '0'));

                // Recurse on the subtrees and build the
                // string accordingly
                for (NAryTreeNode child : root.children) {
                    serializeHelper(child, sb);
                }
            }

            // Decodes your encoded data to tree.
            public NAryTreeNode deserialize(String data) {
                if (data.isEmpty())
                    return null;

                return deserializeHelper(data, new WrappableInt(0));
            }

            private NAryTreeNode deserializeHelper(String data, WrappableInt index) {

                if (index.getValue() == data.length()) {
                    return null;
                }

                // The invariant here is that the "index" always
                // points to a node and the value next to it
                // represents the number of children it has.
                NAryTreeNode node = new NAryTreeNode(data.charAt(index.getValue()) - '0', new ArrayList<NAryTreeNode>());
                index.increment();
                int numChildren = data.charAt(index.getValue()) - '0';
                for (int i = 0; i < numChildren; i++) {
                    index.increment();
                    node.children.add(deserializeHelper(data, index));
                }

                return node;
            }
        }
    }


}
