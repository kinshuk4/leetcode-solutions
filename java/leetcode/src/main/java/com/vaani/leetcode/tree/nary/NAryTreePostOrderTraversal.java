package com.vaani.leetcode.tree.nary;

import com.vaani.dsa.ds.core.tree.nary.NAryTreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.com/problems/n-ary-tree-postorder-traversal/
 * 590. N-ary Tree Postorder Traversal
 * Easy
 * <p>
 * Given an n-ary tree, return the postorder traversal of its nodes' values.
 * <p>
 * Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value (See examples).
 * <p>
 * <p>
 * <p>
 * Follow up:
 * <p>
 * Recursive solution is trivial, could you do it iteratively?
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: root = [1,null,3,2,4,null,5,6]
 * Output: [5,6,3,2,4,1]
 * <p>
 * Example 2:
 * <p>
 * Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * Output: [2,6,14,11,7,3,12,8,4,13,9,10,5,1]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The height of the n-ary tree is less than or equal to 1000
 * The total number of nodes is between [0, 10^4]
 */
public class NAryTreePostOrderTraversal {
    static class UsingIterative {
        public List<Integer> postorder(NAryTreeNode root) {
            List<Integer> list = new ArrayList<>();
            if (root == null) return list;

            Stack<NAryTreeNode> stack = new Stack<>();
            stack.add(root);

            while (!stack.isEmpty()) {
                root = stack.pop();
                list.add(root.val);
                stack.addAll(root.children);
            }
            Collections.reverse(list);
            return list;
        }
    }

    static class UsingRecursion {
        List<Integer> list = new ArrayList<>();

        public List<Integer> postorder(NAryTreeNode root) {
            if (root == null) {
                return list;
            }

            for (NAryTreeNode node : root.children) {
                postorder(node);
            }

            list.add(root.val);

            return list;
        }
    }

    // submitted
    static class UsingRecursion2 {

        public List<Integer> postorder(NAryTreeNode root) {
            List<Integer> result = new ArrayList<>();
            if (root == null) {
                return result;
            }

            helper(root, result);

            return result;
        }

        private void helper(NAryTreeNode root, List<Integer> result) {

            for (NAryTreeNode node : root.children) {
                helper(node, result);
            }

            result.add(root.val);
        }
    }
}
