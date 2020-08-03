package com.vaani.leetcode.tree.nary;

import com.vaani.dsa.ds.core.tree.nary.NAryTreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.com/problems/n-ary-tree-preorder-traversal/
 * 589. N-ary Tree Preorder Traversal
 * Easy
 * <p>
 * Given an n-ary tree, return the preorder traversal of its nodes' values.
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
 * Output: [1,3,5,6,2,4]
 * <p>
 * Example 2:
 * <p>
 * Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * Output: [1,2,3,6,7,11,14,4,8,12,5,9,13,10]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The height of the n-ary tree is less than or equal to 1000
 * The total number of nodes is between [0, 10^4]
 */
public class NAryTreePreOrderTraversal {
    static class UsingIterative {
        public List<Integer> preorder(NAryTreeNode root) {
            List<Integer> list = new ArrayList<>();
            if (root == null) return list;

            Stack<NAryTreeNode> stack = new Stack<>();
            stack.add(root);

            while (!stack.empty()) {
                root = stack.pop();
                list.add(root.val);
                for (int i = root.children.size() - 1; i >= 0; i--)
                    stack.add(root.children.get(i));
            }

            return list;
        }
    }

    static class UsingRecursion {
        public List<Integer> list = new ArrayList<>();

        public List<Integer> preorder(NAryTreeNode root) {
            if (root == null) {
                return list;
            }

            list.add(root.val);
            for (NAryTreeNode node : root.children) {
                preorder(node);
            }

            return list;
        }
    }

    // submitted
    static class UsingRecursion2 {

        public List<Integer> preorder(NAryTreeNode root) {
            List<Integer> result = new ArrayList<>();
            if (root == null) {
                return result;
            }

            helper(root, result);

            return result;
        }

        private void helper(NAryTreeNode root, List<Integer> result) {
            result.add(root.val);
            for (NAryTreeNode node : root.children) {
                helper(node, result);
            }
        }
    }
}
