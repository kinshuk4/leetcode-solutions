package com.vaani.leetcode.tree;

import com.vaani.dsa.ds.core.tree.binarytree.simple.TreeNode;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a binary tree, write a function to get the
 * maximum width of the given tree. The width of a tree is the maximum width among all levels. The
 * binary tree has the same structure as a full binary tree, but some nodes are null.
 *
 * <p>The width of one level is defined as the length between the end-nodes (the leftmost and right
 * most non-null nodes in the level, where the null nodes between the end-nodes are also counted
 * into the length calculation.
 */

/*
Example 1:

Input:

           1
         /   \
        3     2
       / \     \
      5   3     9

Output: 4
Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).
Example 2:

Input:

          1
         /
        3
       / \
      5   3

Output: 2
Explanation: The maximum width existing in the third level with the length 2 (5,3).
Example 3:

Input:

          1
         / \
        3   2
       /
      5

Output: 2
Explanation: The maximum width existing in the second level with the length 2 (3,2).
Example 4:

Input:

          1
         / \
        3   2
       /     \
      5       9
     /         \
    6           7
Output: 8
Explanation:The maximum width existing in the fourth level with the length 8 (6,null,null,null,null,null,null,7).
*/


/* Solution: O(N): General idea is to give a position value to each node. On every left traversal
 * give the value curr_pos * 2 and on every right traversal give the value curr_pos * 2 + 1
 * Calculate maximum width for each level using right - left + 1
 */
public class MaximumWidthOfBinaryTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(3);

        MaximumWidthOfBinaryTree underTest = new MaximumWidthOfBinaryTree();
        Assert.assertEquals(2, underTest.widthOfBinaryTree(root));
    }

    public int widthOfBinaryTree(TreeNode root) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        preOrder(root, 1, 1, map);
        int max = 0;
        for (int k : map.keySet()) {
            List<Integer> list = map.get(k);
            if (list.size() == 1) {
                max = Math.max(max, 1);
            } else {
                int currWidth = list.get(list.size() - 1) - list.get(0) + 1;
                max = Math.max(max, currWidth);
            }
        }
        return max;
    }

    private void preOrder(TreeNode node, int level, int pos, Map<Integer, List<Integer>> map) {
        if (node != null) {
            map.putIfAbsent(level, new ArrayList<>());
            map.get(level).add(pos);
            preOrder(node.left, level + 1, pos * 2, map);
            preOrder(node.right, level + 1, pos * 2 + 1, map);
        }
    }

    private void inorder(TreeNode node, int level, int pos, Map<Integer, List<Integer>> map) {
        if (node != null) {

            inorder(node.left, level + 1, pos * 2, map);
            map.putIfAbsent(level, new ArrayList<>());
            map.get(level).add(pos);
            inorder(node.right, level + 1, pos * 2 + 1, map);
        }
    }
}
