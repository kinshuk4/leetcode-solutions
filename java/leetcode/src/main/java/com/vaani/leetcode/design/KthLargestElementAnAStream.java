package com.vaani.leetcode.design;

import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/kth-largest-element-in-a-stream/
 * 703. Kth Largest Element in a Stream
 * Easy
 * Design a class to find the kth largest element in a stream. Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * <p>
 * Your KthLargest class will have a constructor which accepts an integer k and an integer array nums, which contains initial elements from the stream. For each call to the method KthLargest.add, return the element representing the kth largest element in the stream.
 * <p>
 * Example:
 * <p>
 * int k = 3;
 * int[] arr = [4,5,8,2];
 * KthLargest kthLargest = new KthLargest(3, arr);
 * kthLargest.add(3);   // returns 4
 * kthLargest.add(5);   // returns 5
 * kthLargest.add(10);  // returns 5
 * kthLargest.add(9);   // returns 8
 * kthLargest.add(4);   // returns 8
 * Note:
 * You may assume that nums' length ≥ k-1 and k ≥ 1.
 */
public class KthLargestElementAnAStream {
    static class UsingMinHeap {
        static class KthLargest {
            final PriorityQueue<Integer> minHeap;
            final int k;

            public KthLargest(int k, int[] nums) {
                this.k = k;
                minHeap = new PriorityQueue<>(k);

                for (int num : nums) {
                    add(num);
                }
            }

            public int add(int val) {
                if (minHeap.size() < k) {
                    minHeap.offer(val);
                } else if (minHeap.peek() < val) {
                    minHeap.poll();
                    minHeap.offer(val);
                }
                return minHeap.peek();
            }
        }
    }

    static class UsingBST {
        static class BinaryTreeNodeWithCount {
            int val, count = 1;
            BinaryTreeNodeWithCount left, right;

            BinaryTreeNodeWithCount(int v) {
                val = v;
            }
        }

        static class KthLargest {

            BinaryTreeNodeWithCount root;
            int k;

            public KthLargest(int k, int[] nums) {
                this.k = k;
                for (int num : nums) root = add(root, num);
            }

            public int add(int val) {
                root = add(root, val);
                return findKthLargest();
            }

            private BinaryTreeNodeWithCount add(BinaryTreeNodeWithCount root, int val) {
                if (root == null) return new BinaryTreeNodeWithCount(val);
                root.count++;
                if (val < root.val) root.left = add(root.left, val);
                else root.right = add(root.right, val);

                return root;
            }

            public int findKthLargest() {
                int count = k;
                BinaryTreeNodeWithCount walker = root;

                while (count > 0) {
                    int pos = 1 + (walker.right != null ? walker.right.count : 0);
                    if (count == pos) break;
                    if (count > pos) {
                        count -= pos;
                        walker = walker.left;
                    } else if (count < pos)
                        walker = walker.right;
                }
                return walker.val;
            }

        }
    }

}
