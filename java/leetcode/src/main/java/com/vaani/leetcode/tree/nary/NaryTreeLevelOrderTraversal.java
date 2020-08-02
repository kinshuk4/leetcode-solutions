package com.vaani.leetcode.tree.nary;

import com.vaani.dsa.ds.core.tree.nary.NAryTreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/n-ary-tree-level-order-traversal/
 * 429. N-ary Tree Level Order Traversal
 * Medium
 * Given an n-ary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
 * <p>
 * Example 1:
 * Given a 3-ary tree:
 * Input: root = [1,null,3,2,4,null,5,6]
 * Output: [[1],[3,2,4],[5,6]]
 * i.e.We should return its level order traversal:
 * <p>
 * [
 * [1],
 * [3,2,4],
 * [5,6]
 * ]
 * <p>
 * Example 2
 * Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * Output: [[1],[2,3,4,5],[6,7,8,9,10],[11,12,13],[14]]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The height of the n-ary tree is less than or equal to 1000
 * The total number of nodes is between [0, 10^4]
 */
public class NaryTreeLevelOrderTraversal {
    public List<List<Integer>> levelOrder(NAryTreeNode root) {

        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;

        Queue<NAryTreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> currentList = new ArrayList<>();
            while (size > 0) {
                NAryTreeNode node = queue.poll();
                currentList.add(node.val);
                if (node.children != null) {
                    queue.addAll(node.children);
                }
                size--;
            }
            res.add(currentList);
        }
        return res;
    }
}
