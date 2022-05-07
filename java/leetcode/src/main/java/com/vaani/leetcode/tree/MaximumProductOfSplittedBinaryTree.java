package com.vaani.leetcode.tree;

import com.vaani.dsa.ds.core.tree.binarytree.simple.TreeNode;

import java.util.*;

/**
 * 1339. Maximum Product of Splitted Binary Tree
 * Medium
 * <p>
 * Given the root of a binary tree, split the binary tree into two subtrees by removing one edge such that the product of the sums of the subtrees is maximized.
 * <p>
 * Return the maximum product of the sums of the two subtrees. Since the answer may be too large, return it modulo 10^9 + 7.
 * <p>
 * Note that you need to maximize the answer before taking the mod and not after taking it.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [1,2,3,4,5,6]
 * Output: 110
 * Explanation: Remove the red edge and get 2 binary trees with sum 11 and 10. Their product is 110 (11*10)
 * Example 2:
 * <p>
 * <p>
 * Input: root = [1,null,2,3,4,null,null,5,6]
 * Output: 90
 * Explanation: Remove the red edge and get 2 binary trees with sum 15 and 6.Their product is 90 (15*6)
 * Example 3:
 * <p>
 * Input: root = [2,3,9,10,7,8,6,5,4,11,1]
 * Output: 1025
 * Example 4:
 * <p>
 * Input: root = [1,1]
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [2, 5 * 10^4].
 * 1 <= Node.val <= 10^4
 */
public class MaximumProductOfSplittedBinaryTree {
    private static final long MOD = (long) 1e9 + 7;

    public int maxProduct(TreeNode root) {
        long ans = 0;
        List<Integer> sumList = new ArrayList<>();
        long total = dfs(root, sumList);

        for (long sum : sumList) {
            ans = Math.max(ans, sum * (total - sum));
        }

        return (int) (ans % MOD);

    }

    public int dfs(TreeNode root, List<Integer> sumList) {
        if (root == null) {
            return 0;
        }

        int leftSum = dfs(root.left, sumList);
        int rightSum = dfs(root.right, sumList);

        int sum = root.val + leftSum + rightSum;

        sumList.add(sum);
        return sum;
    }

}
