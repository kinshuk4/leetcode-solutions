package com.vaani.leetcode.tree;

import com.vaani.dsa.ds.core.tree.binarytree.simple.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/average-of-levels-in-binary-tree/
 * 637. Average of Levels in Binary Tree
 * Easy
 * Given a non-empty binary tree, return the average
 * value of the nodes on each level in the form of an array. Example 1: Input: 3 / \ 9 20 / \ 15 7
 * Output: [3, 14.5, 11] Explanation: The average value of nodes on level 0 is 3, on level 1 is
 * 14.5, and on level 2 is 11. Hence return [3, 14.5, 11]. Note: The range of node's value is in the
 * range of 32-bit signed integer.
 *
 * <p>Solution O(n) : Perform a BFS and calculate average for each level.
 */
public class AverageOfLevelsInBinaryTree {
    public static void main(String[] args) throws Exception {
    }

    static class UsingLevelNode{
        static class LevelNode {
            int level;
            TreeNode node;

            LevelNode(int level, TreeNode node) {
                this.level = level;
                this.node = node;
            }
        }

        public List<Double> averageOfLevels(TreeNode root) {
            Queue<LevelNode> queue = new ArrayDeque<>();
            LevelNode node = new LevelNode(0, root);
            queue.offer(node);
            int currLevel = 0, count = 0;
            long sum = 0L;
            List<Double> result = new ArrayList<>();
            while (!queue.isEmpty()) {
                LevelNode curr = queue.poll();
                if (curr.level == currLevel) {
                    sum += curr.node.val;
                    count++;
                } else {
                    result.add((double) sum / count);
                    sum = curr.node.val;
                    count = 1;
                    currLevel++;
                }
                if (curr.node.left != null) {
                    queue.offer(new LevelNode(currLevel + 1, curr.node.left));
                }
                if (curr.node.right != null) {
                    queue.offer(new LevelNode(currLevel + 1, curr.node.right));
                }
            }
            result.add((double) sum / count);
            return result;
        }
    }

    // submitted
    static class UsingSimpleLevelOrderTraversal {
        public List<Double> averageOfLevels(TreeNode root) {
            Queue<TreeNode> queue = new ArrayDeque<>();
            queue.offer(root);

            List<Double> result = new ArrayList<>();
            while (!queue.isEmpty()) {
                int queueSize = queue.size();
                long sum = 0L;
                for (int i = 0; i < queueSize; i++) {
                    TreeNode curr = queue.poll();
                    sum += curr.val;
                    if (curr.left != null) {
                        queue.offer(curr.left);
                    }
                    if (curr.right != null) {
                        queue.offer(curr.right);
                    }
                }
                result.add((double)sum / queueSize);
            }
            return result;
        }
    }

}
