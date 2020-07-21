package com.vaani.leetcode.tree;

import com.vaani.dsa.ds.core.tree.binarytree.simple.BinaryTreeNode;

import java.util.ArrayList;
import java.util.List;

import static com.vaani.dsa.ds.utils.simple.BinaryTreeUtil.getABinaryTree1;

/**
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
 * For example:
 */
/*
Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \    / \
7    2  5   1
Return:

[
   [5,4,11,2],
   [5,8,4,5]
]

 */
public class PathSum2 {

    public static void main(String[] args) {
        BinaryTreeNode root = getABinaryTree1();

        PathSum2 underTest = new PathSum2();
        for (List<Integer> path : underTest.pathSum(root, 22)) {
            System.out.println(path);
        }
    }

    public List<List<Integer>> pathSum(BinaryTreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> single = new ArrayList<>();
        if (root == null) {
            return result;
        }
        pathSum(root, sum, single, result);
        return result;
    }

    public void pathSum(BinaryTreeNode root, int sum, List<Integer> single, List<List<Integer>> result) {
        single.add(root.val);

        // is Leaf Node
        if (root.left == null && root.right == null) {
            if (root.val == sum) {
                result.add(new ArrayList<>(single));
            }
        } else {
            if (root.left != null) {
                pathSum(root.left, sum - root.val, single, result);
            }

            if (root.right != null) {
                pathSum(root.right, sum - root.val, single, result);
            }


        }
        single.remove(single.size() - 1);
    }


    public List<List<Integer>> pathSum2(BinaryTreeNode root, int sum) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        helper2(root, 0, sum, new ArrayList<>(), result);
        return result;
    }

    public void helper2(BinaryTreeNode root, int curSum, int target, List<Integer> list, List<List<Integer>> result) {
        ArrayList<Integer> curList = new ArrayList<>(list);
        if (root == null) {
            return;
        }
        curList.add(root.val);
        if (root.left == null && root.right == null) {
            if (curSum + root.val == target) {
                result.add(curList);
            }
        }

        helper2(root.left, curSum + root.val, target, curList, result);
        helper2(root.right, curSum + root.val, target, curList, result);

    }
}
