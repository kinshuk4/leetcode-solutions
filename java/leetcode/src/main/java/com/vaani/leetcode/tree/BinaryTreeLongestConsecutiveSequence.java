package com.vaani.leetcode.tree;

import com.vaani.dsa.ds.core.tree.binarytree.simple.BinaryTreeNode;

import java.util.*;

/*
Example 1: Input:
    1
     \
      3
     / \
    2   4
         \
          5

Output: 3
Sequence is 3,4,5

 Note: All the values of tree nodes are in the range of [-1e7, 1e7].

 Example 2:
   2
    \
     3
    /
   2
  /
 1

 Output: 2
Sequence is 2,3 and 3-2-1 as we cannot go up
 */
public class BinaryTreeLongestConsecutiveSequence {
    public int longestConsecutive(BinaryTreeNode root) {
        int[] max = new int[1];
        finalLongestConsecutiveSequence(root, max, 0, 0);
        return max[0];
    }

    // Time complexity O(n)
    private void finalLongestConsecutiveSequence(BinaryTreeNode root, int[] max, int count, int target) {
        if(root == null){
            return;
        }else if (root.val == target){
            count++;
        }else {
            count = 1;
        }
        max[0] = Math.max(max[0], count);
        finalLongestConsecutiveSequence(root.left, max, count, root.val+1);
        finalLongestConsecutiveSequence(root.right, max, count, root.val+1);
    }
}
