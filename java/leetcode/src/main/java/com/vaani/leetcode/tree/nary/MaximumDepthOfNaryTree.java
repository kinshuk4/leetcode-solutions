package com.vaani.leetcode.tree.nary;

import com.vaani.dsa.ds.core.tree.nary.NAryTreeNode;

/**
 * https://leetcode.com/problems/maximum-depth-of-n-ary-tree/
 * 559. Maximum Depth of N-ary Tree
 * Easy
 * <p>
 * Given a n-ary tree, find its maximum depth.
 * <p>
 * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 * <p>
 * Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value (See examples).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: root = [1,null,3,2,4,null,5,6]
 * Output: 3
 * <p>
 * Example 2:
 * <p>
 * Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * Output: 5
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The depth of the n-ary tree is less than or equal to 1000.
 * The total number of nodes is between [0, 10^4].
 */
public class MaximumDepthOfNaryTree {

    public int maxDepth(NAryTreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.children == null || root.children.size() == 0) {
            return 1;
        }
        int max = 0;
        for (NAryTreeNode child : root.children) {
            max = Math.max(max, maxDepth(child) + 1);
        }

        return max;
    }
}
