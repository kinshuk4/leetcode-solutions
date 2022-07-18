package com.vaani.leetcode.tree;


import com.vaani.dsa.ds.core.tree.binarytree.simple.TreeNode;

import java.util.*;

/**
 * https://leetcode.com/problems/delete-nodes-and-return-forest/
 * Given the root of a binary tree, each node in the tree has a distinct value.
 * <p>
 * After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).
 * <p>
 * Return the roots of the trees in the remaining forest.  You may return the result in any order.
 */
/*
 * Example 1:
 *           1
 *      2        3
 *    4   5     6  7
 *    to_delete = 3, 5;
 *
 *    Output: [[1,2,null,4],[6],[7]]
 */
public class DeleteNodesAndReturnForest {

    static class UsingDFSSimpler {
        // Time complexity - O(n), Space complexity - O (n + k), k num values in to_delete
        public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
            Set<Integer> toDeleteSet = new HashSet<>();
            Arrays.stream(to_delete).forEach(toDeleteSet::add);

            List<TreeNode> remainingList = new LinkedList<>();

            removeNodes(root, toDeleteSet, remainingList);

            // we have now modified the tree, so we can add the top level root
            if (!toDeleteSet.contains(root.val)) {
                remainingList.add(root);
            }

            return remainingList;
        }

        // process tree bottom up
        private static TreeNode removeNodes(TreeNode root, Set<Integer> toDeleteSet, List<TreeNode> remainingList) {
            if (root == null) {
                return null;
            }

            root.left = removeNodes(root.left, toDeleteSet, remainingList);
            root.right = removeNodes(root.right, toDeleteSet, remainingList);

            if(toDeleteSet.contains(root.val)){
                if(root.left != null){
                    remainingList.add(root.left);
                }
                if(root.right != null){
                    remainingList.add(root.right);
                }

                return null;
            }

            return root;
        }
    }

    static class UsingDFS1 {

        public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
            Set<Integer> toDeleteSet = new HashSet<>();
            Arrays.stream(to_delete).forEach(toDeleteSet::add);

            Set<TreeNode> remainingSet = Set.of(root);

            removeNodes(root, toDeleteSet, remainingSet); //- slightly complicated, so simplified

            return new LinkedList<>(remainingSet);
        }


        private static void removeNodes(TreeNode root, Set<Integer> toDeleteSet, Set<TreeNode> remainingSet) {
            if (root == null) {
                return;
            }
            if (toDeleteSet.contains(root.val)) {
                if (root.left != null) {
                    remainingSet.add(root.left);
                }
                if (root.right != null) {
                    remainingSet.add(root.right);
                }
                remainingSet.remove(root);
            }

            removeNodes(root.left, toDeleteSet, remainingSet);
            removeNodes(root.right, toDeleteSet, remainingSet);

            if (root.left != null && toDeleteSet.contains(root.left.val)) {
                if (root.left.left != null) {
                    remainingSet.add(root.left.left);
                }
                if (root.left.right != null) {
                    remainingSet.add(root.left.right);
                }
                root.left = null;
            }

            if (root.right != null && toDeleteSet.contains(root.right.val)) {
                if (root.right.left != null) {
                    remainingSet.add(root.right.left);
                }
                if (root.right.right != null) {
                    remainingSet.add(root.right.right);
                }
                root.right = null;
            }
        }
    }


}
