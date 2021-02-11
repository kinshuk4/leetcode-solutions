package com.vaani.leetcode.tree;

import com.vaani.dsa.ds.core.tree.binarytree.simple.BinaryTreeNode;

import java.util.*;

public class FindACorrespondingNodeOfABinaryTreeInACloneOfThatTree {
    public final BinaryTreeNode getTargetCopy(final BinaryTreeNode original, final BinaryTreeNode cloned, final BinaryTreeNode target) {
        BinaryTreeNode curr = cloned;
        Queue<BinaryTreeNode> queue = new LinkedList<>();
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
