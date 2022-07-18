package com.vaani.leetcode.tree;

import com.vaani.dsa.ds.core.tree.binarytree.simple.TreeNode;
import org.junit.Assert;

import static com.vaani.dsa.ds.utils.simple.BinaryTreeUtil.getABinaryTree1;

/**
 * https://leetcode.com/problems/path-sum/
 * 112. Path Sum
 * Easy
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.
 * <p>
 * For example:
 * <p>
 */
/*
Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \      \
7    2      1
return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.

https://www.youtube.com/watch?v=Hg82DzMemMI
 */
public class PathSum {
    public static void main(String[] args) {
        TreeNode root = getABinaryTree1();


        PathSum test = new PathSum();
        Assert.assertTrue(test.hasPathSum(root, 22));
        Assert.assertFalse(test.hasPathSum(null, 0)); // as per leetcode
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        return hasPathSumRecursiveWithoutHelper(root, sum);
    }

    public boolean hasPathSumRecursiveWithoutHelper(TreeNode root, int sum) {
        // isLeafNode?
        if (root != null && root.left == null && root.right == null) {
            return root.val == sum;
        } else {
            boolean isLeftSum = false;
            boolean isRightSum = false;

            if (root.left != null) {
                isLeftSum = hasPathSumRecursiveWithoutHelper(root.left, sum - root.val);
            }
            if (root.right != null) {
                isRightSum = hasPathSumRecursiveWithoutHelper(root.right, sum - root.val);
            }
            return isLeftSum || isRightSum;
        }
//  Will not work
//        if (root == null && sum == 0) {
//            return true;
//        } else if (root == null || sum == 0) {
//            return false;
//        }
//
//        return hasPathSumRecursiveWithoutHelper(root.left, sum - root.val) || hasPathSumRecursiveWithoutHelper(root.right, sum - root.val);
    }

    public boolean hasPathSumRecursive2(TreeNode root, int sum) {
        return hasPathSumHelper2(root, sum, 0);
    }

    public boolean hasPathSumHelper2(TreeNode root, int sum, int currSum) {
        if (root == null) {
            return false;
        }
        currSum += root.val;

        if (root.left == null && root.right == null) {
            return currSum == sum;
        } else {
            return hasPathSumHelper2(root.left, sum, currSum) || hasPathSumHelper2(root.right, sum, currSum);
        }
    }
}
