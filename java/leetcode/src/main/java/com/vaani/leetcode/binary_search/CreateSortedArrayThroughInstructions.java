package com.vaani.leetcode.binary_search;

import org.checkerframework.checker.units.qual.A;

import java.util.Optional;
import java.util.TreeSet;

/**
 * 1649. Create Sorted Array through Instructions
 * Hard
 * Given an integer array instructions, you are asked to create a sorted array from the elements in instructions. You start with an empty container nums. For each element from left to right in instructions, insert it into nums. The cost of each insertion is the minimum of the following:
 * <p>
 * The number of elements currently in nums that are strictly less than instructions[i].
 * The number of elements currently in nums that are strictly greater than instructions[i].
 * For example, if inserting element 3 into nums = [1,2,3,5], the cost of insertion is min(2, 1) (elements 1 and 2 are less than 3, element 5 is greater than 3) and nums will become [1,2,3,3,5].
 * <p>
 * Return the total cost to insert all elements from instructions into nums. Since the answer may be large, return it modulo 109 + 7
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: instructions = [1,5,6,2]
 * Output: 1
 * Explanation: Begin with nums = [].
 * Insert 1 with cost min(0, 0) = 0, now nums = [1].
 * Insert 5 with cost min(1, 0) = 0, now nums = [1,5].
 * Insert 6 with cost min(2, 0) = 0, now nums = [1,5,6].
 * Insert 2 with cost min(1, 2) = 1, now nums = [1,2,5,6].
 * The total cost is 0 + 0 + 0 + 1 = 1.
 * Example 2:
 * <p>
 * Input: instructions = [1,2,3,6,5,4]
 * Output: 3
 * Explanation: Begin with nums = [].
 * Insert 1 with cost min(0, 0) = 0, now nums = [1].
 * Insert 2 with cost min(1, 0) = 0, now nums = [1,2].
 * Insert 3 with cost min(2, 0) = 0, now nums = [1,2,3].
 * Insert 6 with cost min(3, 0) = 0, now nums = [1,2,3,6].
 * Insert 5 with cost min(3, 1) = 1, now nums = [1,2,3,5,6].
 * Insert 4 with cost min(3, 2) = 2, now nums = [1,2,3,4,5,6].
 * The total cost is 0 + 0 + 0 + 0 + 1 + 2 = 3.
 * Example 3:
 * <p>
 * Input: instructions = [1,3,3,3,2,4,2,1,2]
 * Output: 4
 * Explanation: Begin with nums = [].
 * Insert 1 with cost min(0, 0) = 0, now nums = [1].
 * Insert 3 with cost min(1, 0) = 0, now nums = [1,3].
 * Insert 3 with cost min(1, 0) = 0, now nums = [1,3,3].
 * Insert 3 with cost min(1, 0) = 0, now nums = [1,3,3,3].
 * Insert 2 with cost min(1, 3) = 1, now nums = [1,2,3,3,3].
 * Insert 4 with cost min(5, 0) = 0, now nums = [1,2,3,3,3,4].
 * Insert 2 with cost min(1, 4) = 1, now nums = [1,2,2,3,3,3,4].
 * Insert 1 with cost min(0, 6) = 0, now nums = [1,1,2,2,3,3,3,4].
 * Insert 2 with cost min(2, 4) = 2, now nums = [1,1,2,2,2,3,3,3,4].
 * The total cost is 0 + 0 + 0 + 0 + 1 + 0 + 1 + 0 + 2 = 4.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= instructions.length <= 10^5
 * 1 <= instructions[i] <= 10^5
 */
public class CreateSortedArrayThroughInstructions {
    public static void main(String[] args) {
        int [] arr = {1,5,6,2};
        CreateSortedArrayThroughInstructions underTest = new CreateSortedArrayThroughInstructions();
        int ans = underTest.createSortedArray(arr);
        System.out.println(ans);

    }
    public int createSortedArray(int[] instructions) {
        long ans = 0;
        long mod = 1000000007;
        AVLTree root = new AVLTree();
        for (int i : instructions) {
            AVLTree.Pair pair = root.getBranchCount(i);
            ans = (ans + Math.min(pair.x, pair.y)) % mod;
            root.insert(i);
        }
        return (int) ans;
    }

    static class AVLTree {
        private static class Node {
            long val;
            Node left;
            Node right;

            int height;
            long count;
            long totalCount;

            Node(int val) {
                this.val = val;
                height = 1;
                left = null;
                right = null;
                count = 1;
                totalCount = 1;
            }
        }

        static class Pair {
            long x, y;

            public Pair(long x, long y) {
                this.x = x;
                this.y = y;
            }
        }

        private Node root = null;

        public void insert(int data) {
            root = insert(root, data);
        }

        public Pair getBranchCount(int data) {
            Pair pair = new Pair(0, 0);
            getBranchCount(root, data, pair);
            return pair;
        }


        private int height(Node node) {
            return node == null ? -1 : node.height;
        }

        private Node insert(Node node, int val) {
            if (node == null) {
                node = new Node(val);
                return node;
            }
            if (val == node.val) {
                node.count += 1;
            } else if (val < node.val) {
                node.left = insert(node.left, val);
            } else if (val > node.val) {
                node.right = insert(node.right, val);
            }
            node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
            node.totalCount = node.count + getCount(node.left) + getCount(node.right);
            int bFactor = getBFactor(node);
            if (bFactor > 1 && val < node.left.val) {
                return rotateRight(node);
            }
            if (bFactor > 1 && val > node.left.val) {
                node.left = rotateLeft(node.left);
                return rotateRight(node);
            }
            if (bFactor < -1 && val > node.right.val) {
                return rotateLeft(node);
            }
            if (bFactor < -1 && val < node.right.val) {
                node.right = rotateRight(node.right);
                return rotateLeft(node);
            }
            return node;
        }

        private int getHeight(Node root) {
            if (root == null) {
                return 0;
            }
            return root.height;
        }

        private long getCount(Node root) {
            if (root == null) {
                return 0;
            }
            return root.totalCount;
        }

        private int getBFactor(Node root) {
            if (root == null) {
                return 0;
            }
            return getHeight(root.left) - getHeight(root.right);
        }

        private Node rotateRight(Node root) {
            Node x = root.left;
            Node y = x.right;
            x.right = root;
            root.left = y;
            root.height = 1 + Math.max(getHeight(root.left), getHeight(root.right));
            root.totalCount = root.count + getCount(root.left) + getCount(root.right);
            x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));
            x.totalCount = x.count + getCount(x.left) + getCount(x.right);
            return x;
        }

        private Node rotateLeft(Node root) {
            Node x = root.right;
            Node y = x.left;
            x.left = root;
            root.right = y;
            root.height = 1 + Math.max(getHeight(root.left), getHeight(root.right));
            root.totalCount = root.count + getCount(root.left) + getCount(root.right);
            x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));
            x.totalCount = x.count + getCount(x.left) + getCount(x.right);
            return x;
        }

        private void getBranchCount(Node root, int val, Pair pair) {
            if (root == null) {
                return;
            }
            if (val == root.val) {
                pair.x += getCount(root.left);
                pair.y += getCount(root.right);

            } else if (val < root.val) {
                pair.y += (root.count + getCount(root.right));
                getBranchCount(root.left, val, pair);

            } else {
                pair.x += (root.count + getCount(root.left));
                getBranchCount(root.right, val, pair);
            }
        }
    }


}
