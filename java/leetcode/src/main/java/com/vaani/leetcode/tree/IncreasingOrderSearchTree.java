package com.vaani.leetcode.tree;

import com.vaani.dsa.ds.core.tree.binarytree.simple.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/increasing-order-search-tree/
 * 897. Increasing Order Search Tree
 * Easy
 * Given a binary search tree, rearrange the tree in in-order so that the leftmost node in the tree is now the root of the tree, and every node has no left child and only 1 right child.
 * <p>
 */
/*


Example 1:
Input: [5,3,6,2,4,null,8,1,null,null,null,7,9]

       5
      / \
    3    6
   / \    \
  2   4    8
 /        / \
1        7   9

Output: [1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]

 1
  \
   2
    \
     3
      \
       4
        \
         5
          \
           6
            \
             7
              \
               8
                \
                 9
 */
public class IncreasingOrderSearchTree {

    static class UsingExtraSpace {
        // Time Complexity: O(N) where N is the number of nodes in the given tree.
        // Space Complexity: O(N) the size of the answer.
        public TreeNode increasingBST(TreeNode root) {
            if (root == null) {
                return null;
            }
            List<Integer> res = new LinkedList<>();
            inOrder(root, res);
            TreeNode newRoot = new TreeNode(res.get(0));
            TreeNode curr = newRoot;
            for (int i = 1; i < res.size(); i++) {
                curr.right = new TreeNode(res.get(i));
                curr = curr.right;
            }
            return newRoot;
        }

        private void inOrder(TreeNode root, List<Integer> result) {
            if (root.left != null) {
                inOrder(root.left, result);
            }
            result.add(root.val);
            if (root.right != null) {
                inOrder(root.right, result);
            }
        }
    }

    static class WithoutExtraSpace {
        TreeNode curr;

        public TreeNode increasingBST(TreeNode root) {
            TreeNode result = new TreeNode(0);
            curr = result;
            inorder(root);
            return result.right;
        }

        public void inorder(TreeNode root) {
            if (root == null) {
                return;
            }
            inorder(root.left);
            root.left = null;
            curr.right = curr = root;
            inorder(root.right);
        }
    }

}
