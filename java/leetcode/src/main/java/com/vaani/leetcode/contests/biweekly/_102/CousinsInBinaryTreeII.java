package com.vaani.leetcode.contests.biweekly._102;


import com.vaani.dsa.ds.core.tree.binarytree.simple.TreeNode;

import java.util.*;

public class CousinsInBinaryTreeII {
    static class Pair {
        public TreeNode node;
        public TreeNode parent;

        public Pair(TreeNode node, TreeNode parent) {
            this.node = node;
            this.parent = parent;
        }
    }

    public TreeNode replaceValueInTree(TreeNode root) {
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, null));
        List<List<Pair>> nodesByLevel = new ArrayList<>();
        while (!queue.isEmpty()) {
            List<Pair> currentLevel = new ArrayList<>();
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {

                Pair curr = queue.poll();
                currentLevel.add(curr);

                if (curr != null && curr.node.left != null) {
                    queue.offer(new Pair(curr.node.left, curr.node));
                }
                if (curr != null && curr.node.right != null) {
                    queue.offer(new Pair(curr.node.right, curr.node));
                }


            }

            nodesByLevel.add(currentLevel);
        }

        List<Map<TreeNode, Integer>> nodesByLevelParent = new ArrayList<>();
        for (int i = 0; i < nodesByLevel.size(); i++) {
            List<Pair> currLevel = nodesByLevel.get(i);
            nodesByLevelParent.add(new HashMap<>());
            for (Pair curr : currLevel) {
                TreeNode p = curr.parent;
                nodesByLevelParent.get(i).putIfAbsent(p, 0);
                int sum = nodesByLevelParent.get(i).get(p) + curr.node.val;
                nodesByLevelParent.get(i).put(p, sum);
            }
        }

        makeTree(root, null , 0, nodesByLevelParent);
        return root;

    }

    public void makeTree(TreeNode root, TreeNode parent, int level, List<Map<TreeNode, Integer>> nodesByLevelParent) {
        Map<TreeNode, Integer> currLevelNodes = nodesByLevelParent.get(level);
        if (currLevelNodes.size() == 1) {
            root.val = 0;
        }else {
            int currSum = 0;
            for (TreeNode p: currLevelNodes.keySet()) {
                if (p != parent) {
                    currSum += currLevelNodes.get(p);
                }

            }
            root.val = currSum;
        }
        if (root.left != null) {
            makeTree(root.left, root, level + 1, nodesByLevelParent);
        }
        if (root.right != null) {
            makeTree(root.right, root, level + 1, nodesByLevelParent);
        }
    }
}
