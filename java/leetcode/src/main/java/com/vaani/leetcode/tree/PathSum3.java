package com.vaani.leetcode.tree;

import com.vaani.dsa.ds.core.tree.binarytree.simple.BinaryTreeNode;

import java.util.HashMap;
import java.util.Map;

import static com.vaani.dsa.ds.utils.simple.BinaryTreeUtil.getABinaryTree1;

/**
 * https://leetcode.com/problems/path-sum-iii/
 * 437. Path Sum III
 * Medium
 * You are given a binary tree in which each node
 * contains an integer value.
 *
 * <p>Find the number of paths that sum to a given value.
 *
 * <p>The path does not need to start or end at the root or a leaf, but it must go downwards
 * (traveling only from parent nodes to child nodes).
 *
 * <p>The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.
 *
 * <p>Example:
 */

/*
Example:

root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1

Return 3. The paths that sum to 8 are:

1.  5 -> 3
2.  5 -> 2 -> 1
3. -3 -> 11


 */
public class PathSum3 {

    private Map<Integer, Integer> pathCount = new HashMap<>();
    private int totalCount;

    public static void main(String[] args) throws Exception {
        BinaryTreeNode node = getABinaryTree1();
        System.out.println(new PathSum3().pathSum1(node, 0));
    }

    public int pathSum1(BinaryTreeNode root, int sum) {
        if (root == null) {
            return 0;
        }

        return findPathFromNode(root, sum) + pathSum1(root.left, sum) + pathSum1(root.right, sum);
    }

    public int findPathFromNode(BinaryTreeNode root, int sum) {
        int cnt = 0;
        if (root == null) {
            return cnt;
        }
        if (root.val == sum) {
            cnt++;
        }
        cnt += findPathFromNode(root.left, sum - root.val);
        cnt += findPathFromNode(root.right, sum - root.val);
        return cnt;
    }

    public int pathSum2(BinaryTreeNode root, int sum) {
        if (root == null) return 0;
        dfs2(root, sum, 0);
        return totalCount;
    }

    private void dfs2(BinaryTreeNode root, int target, int currSum) {
        if (root != null) {
            currSum += root.val;
            if (currSum == target) {
                totalCount++;
            }
            totalCount += pathCount.getOrDefault(currSum - target, 0);
            pathCount.put(currSum, pathCount.getOrDefault(currSum, 0) + 1);
            dfs2(root.left, target, currSum);
            dfs2(root.right, target, currSum);
            pathCount.put(currSum, pathCount.get(currSum) - 1);
        }
    }
}
