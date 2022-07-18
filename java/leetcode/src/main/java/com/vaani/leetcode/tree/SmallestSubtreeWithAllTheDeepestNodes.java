package com.vaani.leetcode.tree;

import com.vaani.dsa.ds.core.tree.binarytree.simple.TreeNode;

import java.util.*;

/**
 * 865. Smallest Subtree with all the Deepest Nodes
 * Medium
 * Given the root of a binary tree, the depth of each node is the shortest distance to the root.
 * <p>
 * Return the smallest subtree such that it contains all the deepest nodes in the original tree.
 * <p>
 * A node is called the deepest if it has the largest depth possible among any node in the entire tree.
 * <p>
 * The subtree of a node is tree consisting of that node, plus the set of all descendants of that node.
 * <p>
 * Note: This question is the same as 1123: https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4]
 * Output: [2,7,4]
 * Explanation: We return the node with value 2, colored in yellow in the diagram.
 * The nodes coloured in blue are the deepest nodes of the tree.
 * Notice that nodes 5, 3 and 2 contain the deepest nodes in the tree but node 2 is the smallest subtree among them, so we return it.
 * Example 2:
 * <p>
 * Input: root = [1]
 * Output: [1]
 * Explanation: The root is the deepest node in the tree.
 * Example 3:
 * <p>
 * Input: root = [0,1,3,null,2]
 * Output: [2]
 * Explanation: The deepest node in the tree is 2, the valid subtrees are the subtrees of nodes 2, 1 and 0 but the subtree of node 2 is the smallest.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree will be in the range [1, 500].
 * 0 <= Node.val <= 500
 * The values of the nodes in the tree are unique.
 */
public class SmallestSubtreeWithAllTheDeepestNodes {
    Map<TreeNode, Integer> map = new HashMap<>();

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        if (root == null) {
            return null;
        }

        int leftD = depth(root.left);
        int rightD = depth(root.right);

        if (leftD == rightD) {
            return root;
        } else if (leftD > rightD) {
            return subtreeWithAllDeepest(root.left);
        } else {
            return subtreeWithAllDeepest(root.right);
        }
    }

    private int depth(TreeNode root) {
        if (root == null) return 0;

        if (map.containsKey(root)) {
            return map.get(root);
        }

        int leftD = depth(root.left);
        int rightD = depth(root.right);

        int depth = Math.max(leftD, rightD) + 1;

        map.put(root, depth);

        return depth;
    }

}
