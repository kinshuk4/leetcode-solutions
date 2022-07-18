package com.vaani.leetcode.tree;

import com.vaani.dsa.ds.core.tree.binarytree.simple.TreeNode;

import java.util.*;

public class FindACorrespondingNodeOfABinaryTreeInACloneOfThatTree {
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        TreeNode curr = cloned;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(curr);
        while (queue.size() > 0) {
            curr = queue.poll();
            if (curr.val == target.val) {
                return curr;
            }
            if (curr.left != null) {
                queue.add(curr.left);
            }
            if (curr.right != null) {
                queue.add(curr.right);
            }
        }
        return null;
    }
}
