package com.vaani.leetcode.tree;

import com.vaani.dsa.ds.core.tree.binarytree.simple.TreeNode;

import java.util.*;

/**
 * https://leetcode.com/problems/deepest-leaves-sum/
 * 1302. Deepest Leaves Sum
 * Medium
 * <p>
 * Given the root of a binary tree, return the sum of values of its deepest leaves.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
 * Output: 15
 * Example 2:
 * <p>
 * Input: root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
 * Output: 19
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [1, 104].
 * 1 <= Node.val <= 100
 */
public class DeepestLeavesSum {
    public int deepestLeavesSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        int ans = 0;
        while (!q.isEmpty()) {
            int size = q.size();

            ans = 0;

            while (size-- > 0) {
                TreeNode curr = q.poll();
                ans += curr.val;
                if (curr.left != null) {
                    q.offer(curr.left);
                }
                if (curr.right != null) {
                    q.offer(curr.right);
                }
            }
        }
        return ans;
    }
}
