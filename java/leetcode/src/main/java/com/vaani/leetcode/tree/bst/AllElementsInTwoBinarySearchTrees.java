package com.vaani.leetcode.tree.bst;

import com.vaani.dsa.ds.core.tree.binarytree.simple.BinaryTreeNode;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * https://leetcode.com/problems/all-elements-in-two-binary-search-trees/
 * 1305. All Elements in Two Binary Search Trees
 * Medium
 * <p>
 * Given two binary search trees root1 and root2.
 * <p>
 * Return a list containing all the integers from both trees sorted in ascending order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root1 = [2,1,4], root2 = [1,0,3]
 * Output: [0,1,1,2,3,4]
 * Example 2:
 * <p>
 * Input: root1 = [0,-10,10], root2 = [5,1,7,0,2]
 * Output: [-10,0,0,1,2,5,7,10]
 * Example 3:
 * <p>
 * Input: root1 = [], root2 = [5,1,7,0,2]
 * Output: [0,1,2,5,7]
 * Example 4:
 * <p>
 * Input: root1 = [0,-10,10], root2 = []
 * Output: [-10,0,10]
 * Example 5:
 * <p>
 * <p>
 * Input: root1 = [1,null,8], root2 = [8,1]
 * Output: [1,1,8,8]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * Each tree has at most 5000 nodes.
 * Each node's value is between [-10^5, 10^5].
 */
public class AllElementsInTwoBinarySearchTrees {

    static class UsingInOrderAndMinHeap {

        // without stream = 37 ms
        public List<Integer> getAllElements(BinaryTreeNode root1, BinaryTreeNode root2) {
            if (root1 == null && root2 == null) {
                return new ArrayList<>();
            }
            Queue<Integer> minHeap = new PriorityQueue<>((n1, n2) -> n1 - n2);
            inOrder(root1, minHeap);
            inOrder(root2, minHeap);

            List<Integer> ans = new ArrayList<>();
            while (!minHeap.isEmpty()) {
                ans.add(minHeap.poll());
            }

            return ans;
        }

        // with stream = 41 ms
        public List<Integer> getAllElements2(BinaryTreeNode root1, BinaryTreeNode root2) {
            if (root1 == null && root2 == null) {
                return new ArrayList<>();
            }
            Queue<Integer> minHeap = new PriorityQueue<>((n1, n2) -> n1 - n2);
            inOrder(root1, minHeap);
            inOrder(root2, minHeap);

            return Stream.generate(minHeap::poll).takeWhile(Objects::nonNull).collect(Collectors.toList());
        }

        private void inOrder(BinaryTreeNode root, Queue<Integer> heap) {
            if (root == null) {
                return;
            }
            inOrder(root.left, heap);
            heap.add(root.val);
            inOrder(root.right, heap);
        }
    }

}
