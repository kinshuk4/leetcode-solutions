package com.vaani.leetcode.tree;

import com.vaani.dsa.ds.core.tree.binarytree.simple.BinaryTreeNode;

import java.util.*;

/**
 * https://leetcode.com/problems/pseudo-palindromic-paths-in-a-binary-tree/
 * 1457. Pseudo-Palindromic Paths in a Binary Tree
 * Medium
 * <p>
 * Given a binary tree where node values are digits from 1 to 9. A path in the binary tree is said to be pseudo-palindromic if at least one permutation of the node values in the path is a palindrome.
 * <p>
 * Return the number of pseudo-palindromic paths going from the root node to leaf nodes.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * <p>
 * Input: root = [2,3,1,3,1,null,1]
 * Output: 2
 * Explanation: The figure above represents the given binary tree. There are three paths going from the root node to leaf nodes: the red path [2,3,3], the green path [2,1,1], and the path [2,3,1]. Among these paths only red path and green path are pseudo-palindromic paths since the red path [2,3,3] can be rearranged in [3,2,3] (palindrome) and the green path [2,1,1] can be rearranged in [1,2,1] (palindrome).
 * Example 2:
 * <p>
 * <p>
 * <p>
 * Input: root = [2,1,1,1,3,null,null,null,null,null,1]
 * Output: 1
 * Explanation: The figure above represents the given binary tree. There are three paths going from the root node to leaf nodes: the green path [2,1,1], the path [2,1,3,1], and the path [2,1]. Among these paths only the green path is pseudo-palindromic since [2,1,1] can be rearranged in [1,2,1] (palindrome).
 * Example 3:
 * <p>
 * Input: root = [9]
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The given binary tree will have between 1 and 10^5 nodes.
 * Node values are digits from 1 to 9.
 */
public class PseudoPalindromicPathsInABinaryTree {
    public int pseudoPalindromicPaths(BinaryTreeNode root) {
        return dfs(root, new HashSet<>());
    }

    public int dfs(BinaryTreeNode root, HashSet<Integer> set) {
        if (root == null) {
            return 0;
        }

        if (set.contains(root.val)) {
            set.remove(root.val);
        } else {
            set.add(root.val);
        }

        int ans = 0;
        if (root.left == null && root.right == null && set.size() <= 1) {
            ans++;
        }

        ans += dfs(root.left, set) + dfs(root.right, set);
        if (set.contains(root.val)) {
            set.remove(root.val);
        } else {
            set.add(root.val);
        }
        return ans;
    }
}
