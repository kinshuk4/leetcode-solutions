package com.vaani.leetcode.depth_first_search;

import com.vaani.dsa.ds.core.tree.binarytree.simple.TreeNode;

/**
 * https://leetcode.com/problems/binary-tree-pruning/
 * 814. Binary Tree Pruning
 * Medium
 * <p>
 * <p>
 * Given the root of a binary tree, return the same tree where every subtree (of the given tree) not containing a 1 has been removed.
 * <p>
 * A subtree of a node node is node plus every node that is a descendant of node.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [1,null,0,0,1]
 * Output: [1,null,0,null,1]
 * Explanation:
 * Only the red nodes satisfy the property "every subtree not containing a 1".
 * The diagram on the right represents the answer.
 * Example 2:
 * <p>
 * <p>
 * Input: root = [1,0,1,0,0,0,1]
 * Output: [1,null,1,null,1]
 * Example 3:
 * <p>
 * <p>
 * Input: root = [1,1,0,1,1,0,1,0]
 * Output: [1,1,0,1,1,null,1]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [1, 200].
 * Node.val is either 0 or 1.
 */
public class BinaryTreePruning {
    public TreeNode pruneTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);

        if (root.left == null && root.right == null && root.val == 0) {
            return null;
        }

        return root;
    }
}
