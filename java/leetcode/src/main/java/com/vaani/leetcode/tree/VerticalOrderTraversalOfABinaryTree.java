package com.vaani.leetcode.tree;

import com.vaani.dsa.ds.core.tree.binarytree.simple.TreeNode;
import com.vaani.leetcode.design.SerializeDeserializeBinaryTree;

import java.util.*;

/**
 * https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/
 * 987. Vertical Order Traversal of a Binary Tree
 * Medium
 * <p>
 * Given a binary tree, return the vertical order traversal of its nodes values.
 * <p>
 * For each node at position (X, Y), its left and right children respectively will be at positions (X-1, Y-1) and (X+1, Y-1).
 * <p>
 * Running a vertical line from X = -infinity to X = +infinity, whenever the vertical line touches some nodes, we report the values of the nodes in order from top to bottom (decreasing Y coordinates).
 * <p>
 * If two nodes have the same position, then the value of the node that is reported first is the value that is smaller.
 * <p>
 * Return an list of non-empty reports in order of X coordinate.  Every report will have a list of values of nodes.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: [3,9,20,null,null,15,7]
 * Output: [[9],[3,15],[20],[7]]
 * Explanation:
 * Without loss of generality, we can assume the root node is at position (0, 0):
 * Then, the node with value 9 occurs at position (-1, -1);
 * The nodes with values 3 and 15 occur at positions (0, 0) and (0, -2);
 * The node with value 20 occurs at position (1, -1);
 * The node with value 7 occurs at position (2, -2).
 * <p>
 * Example 2:
 * <p>
 * Input: [1,2,3,4,5,6,7]
 * Output: [[4],[2],[1,5,6],[3],[7]]
 * Explanation:
 * The node with value 5 and the node with value 6 have the same position according to the given scheme.
 * However, in the report "[1,5,6]", the node value of 5 comes first since 5 is smaller than 6.
 * <p>
 * <p>
 * <p>
 * Note:
 * <p>
 * The tree will have between 1 and 1000 nodes.
 * Each node's value will be between 0 and 1000.
 */
public class VerticalOrderTraversalOfABinaryTree {
    public static void main(String[] args) {
        UsingRecursionAndArray sol = new UsingRecursionAndArray();
        SerializeDeserializeBinaryTree creator = new SerializeDeserializeBinaryTree();
//        TreeNode root = creator.deserialize("3,9,#,#,20,15,#,#,7,#,#");
        TreeNode root = creator.deserialize("1,9,#,#,20,15,#,#,7,#,#");
        sol.verticalTraversal(root);
    }
    static class UsingRecursionAndArray {

        public List<List<Integer>> verticalTraversal(TreeNode root) {
            List<List<Integer>> ans = new ArrayList<List<Integer>>();
            Map<Integer, List<Integer>> verticals = new HashMap<>();
            int[] minmax = new int[]{0, 0};
            traverse(root, verticals, 0, minmax);
            for (int i = minmax[0]; i <= minmax[1]; i++) {
                if (verticals.containsKey(i)) {
                    ans.add(verticals.get(i));
                }
            }
            return ans;
        }

        private void traverse(TreeNode node, Map<Integer, List<Integer>> verticals, int score, int[] minmax) {
            verticals.putIfAbsent(score, new ArrayList<Integer>());
            verticals.get(score).add(node.val);

            minmax[0] = Math.min(minmax[0], score);
            minmax[1] = Math.max(minmax[1], score);

            if (node.left != null) {
                traverse(node.left, verticals, score - 1, minmax);
            }
            if (node.right != null) {
                traverse(node.right, verticals, score + 1, minmax);
            }
        }
    }

    static class UsingTreeMap {
        public static List<List<Integer>> verticalTraversal(TreeNode root) {
            if (root == null) {
                return new LinkedList<>();
            }

            // Using treemap as keys are automatically sorted
            Map<Integer, List<Integer>> map = new TreeMap<>();
            List<List<Integer>> result = new LinkedList<>();

            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            Queue<Integer> distanceQueue = new LinkedList<>();
            distanceQueue.add(0);

            while (!queue.isEmpty()) {
                /* This is confusing:
                "If two nodes have the same position, then the value of the node that is reported first is the value that is smaller."
                    This statement is ambiguous which make people think just order from small to large for each position.
                    From test case, the real requirement is:
                    If two nodes have the same position,
                       * check the layer, the node on higher level(close to root) goes first
                       * if they also in the same level, order from small to large
                 */
                int size = queue.size();
                Map<Integer, List<Integer>> tmp = new HashMap<>();

                while (size > 0) {
                    TreeNode current = queue.poll();

                    int currDistance = distanceQueue.poll();

                    List<Integer> currentList = tmp.getOrDefault(currDistance, new LinkedList<>());
                    currentList.add(current.val);
                    tmp.put(currDistance, currentList);

                    if (current.left != null) {
                        queue.add(current.left);
                        distanceQueue.add(currDistance - 1);
                    }

                    if (current.right != null) {
                        queue.add(current.right);
                        distanceQueue.add(currDistance + 1);
                    }
                    size--;
                }
                // Now sort the current level and add to map
                for (int key : tmp.keySet()) {
                    List<Integer> list = tmp.get(key);
                    Collections.sort(list);

                    map.putIfAbsent(key, new LinkedList<>());
                    map.get(key).addAll(list);
                }
            }

            // left to right, from maximum -ve distance from root to maximum +ve distance from root
            for (int i : map.keySet()) {
                List<Integer> list = map.get(i);
                result.add(list);
            }
            return result;
        }
    }

    static class UsingHashMap {
        int min = 0, max = 0;
        Map<Integer, List<Integer>> map = new HashMap();

        public List<List<Integer>> verticalTraversal(TreeNode root) {
            List<List<Integer>> res = new ArrayList();
            if (root == null) return res;
            Queue<TreeNode> qt = new LinkedList();
            Queue<Integer> qi = new LinkedList();
            qt.add(root);
            qi.add(0);//not root.val
            while (!qt.isEmpty()) {
                int size = qt.size();
                Map<Integer, List<Integer>> tmp = new HashMap();
                for (int i = 0; i < size; i++) {
                    TreeNode cur = qt.poll();
                    int idx = qi.poll();
                    if (!tmp.containsKey(idx)) tmp.put(idx, new ArrayList<Integer>());
                    tmp.get(idx).add(cur.val);
                    if (idx < min) min = idx;
                    if (idx > max) max = idx;
                    if (cur.left != null) {
                        qt.add(cur.left);
                        qi.add(idx - 1);
                    }
                    if (cur.right != null) {
                        qt.add(cur.right);
                        qi.add(idx + 1);
                    }
                }
                for (int key : tmp.keySet()) {
                    if (!map.containsKey(key)) map.put(key, new ArrayList<Integer>());
                    List<Integer> list = tmp.get(key);
                    Collections.sort(list);
                    map.get(key).addAll(list);
                }

            }
            for (int i = min; i <= max; i++) {
                List<Integer> list = map.get(i);
                res.add(list);
            }
            return res;
        }
    }

}
