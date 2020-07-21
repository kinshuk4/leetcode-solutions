package com.vaani.leetcode.tree;

import com.vaani.dsa.ds.core.tree.binarytree.simple.BinaryTreeNode;

import java.util.*;
/*
Given a binary tree, return all duplicate subtrees. For each kind of duplicate subtrees, you only need to return the root node of any one of them.

Two trees are duplicate if they have the same structure with same node values.

Example 1:

        1
       / \
      2   3
     /   / \
    4   2   4
       /
      4

The following are two duplicate subtrees:

      2
     /
    4

and

    4

Therefore, you need to return above trees' root in the form of a list.
 */

// https://leetcode.com/articles/find-duplicate-subtrees/#
public class FindDuplicateSubtrees {
    public List<BinaryTreeNode> findDuplicateSubtrees(BinaryTreeNode root) {
//        return new DFSSolution().findDuplicateSubtrees(root);
        return new CloneHashCodeSolution().findDuplicateSubtrees(root);
    }

    // submitted
    static class CloneHashCodeSolution{
        static class BinaryTreeNodeHashed{
            public int val;
            public BinaryTreeNodeHashed left;
            public BinaryTreeNodeHashed right;

            public BinaryTreeNodeHashed(int val) {
                this.val = val;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                BinaryTreeNodeHashed that = (BinaryTreeNodeHashed) o;
                return val == that.val &&
                        Objects.equals(left, that.left) &&
                        Objects.equals(right, that.right);
            }

            @Override
            public int hashCode() {
                return Objects.hash(val, left, right);
            }
        }

        public static BinaryTreeNodeHashed cloneTree(BinaryTreeNode root) {
            if (root == null) {return null;}
            BinaryTreeNodeHashed newNode = new BinaryTreeNodeHashed(root.val);
            newNode.left = cloneTree(root.left);
            newNode.right = cloneTree(root.right);
            return newNode;
        }

        public static BinaryTreeNode cloneTreeBack(BinaryTreeNodeHashed root) {
            if (root == null) {return null;}
            BinaryTreeNode newNode = new BinaryTreeNode(root.val);
            newNode.left = cloneTreeBack(root.left);
            newNode.right = cloneTreeBack(root.right);
            return newNode;
        }

        public void inorderTraversalRecursiveHelper(BinaryTreeNodeHashed root, Set<BinaryTreeNodeHashed> visited, Set<BinaryTreeNodeHashed> resultSet) {
            if (root == null) {
                return;
            }
            inorderTraversalRecursiveHelper(root.left, visited, resultSet);
            if(visited.contains(root)){
                resultSet.add(root);
            }else{
                visited.add(root);
            }
            inorderTraversalRecursiveHelper(root.right, visited, resultSet);
        }


        public List<BinaryTreeNode> findDuplicateSubtrees(BinaryTreeNode root) {
            BinaryTreeNodeHashed hashedRoot = cloneTree(root);
            Set<BinaryTreeNodeHashed> visited = new HashSet<>();
            Set<BinaryTreeNodeHashed> resultSet = new HashSet<>();
            inorderTraversalRecursiveHelper(hashedRoot, visited, resultSet);

            List<BinaryTreeNode> result = new ArrayList<>();
            resultSet.forEach(x -> result.add(cloneTreeBack(x)));
            return result;

        }
    }


    // Fails with duplicates
    static class DFSSolution {
        private Map<String, Integer> count;
        private List<BinaryTreeNode> ans;

        // dfs
        public List<BinaryTreeNode> findDuplicateSubtrees(BinaryTreeNode root) {
            count = new HashMap<>();
            ans = new ArrayList<>();
            collect(root);
            return ans;
        }

        public String collect(BinaryTreeNode node) {
            if (node == null) return "#";
            String serial = node.val + "," + collect(node.left) + "," + collect(node.right);
            count.put(serial, count.getOrDefault(serial, 0) + 1);
            if (count.get(serial) > 1)
                ans.add(node);
            return serial;
        }
    }

    // solution 2 - O(n)
    static class UniqueIdentifier {
        int t;
        Map<String, Integer> trees;
        Map<Integer, Integer> count;
        List<BinaryTreeNode> ans;

        public List<BinaryTreeNode> findDuplicateSubtrees(BinaryTreeNode root) {
            t = 1;
            trees = new HashMap<>();
            ans = new ArrayList<>();
            lookup(root);
            return ans;
        }

        public int lookup(BinaryTreeNode node) {
            if (node == null) return 0;
            String serial = node.val + "," + lookup(node.left) + "," + lookup(node.right);
            int uid = trees.computeIfAbsent(serial, x -> t++);
            count.put(uid, count.getOrDefault(uid, 0) + 1);
            if (count.get(uid) == 2)
                ans.add(node);
            return uid;
        }
    }
}
