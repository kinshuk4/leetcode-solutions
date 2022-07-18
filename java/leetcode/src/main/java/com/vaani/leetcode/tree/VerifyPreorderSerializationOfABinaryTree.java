package com.vaani.leetcode.tree;

/**
 * 331. Verify Preorder Serialization of a Binary Tree
 * Medium
 * <p>
 * One way to serialize a binary tree is to use preorder traversal. When we encounter a non-null node, we record the node's value.
 * If it is a null node, we record using a sentinel value such as '#'.
 * <p>
 * <p>
 * For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", where '#' represents a null node.
 * <p>
 * Given a string of comma-separated values preorder, return true if it is a correct preorder traversal serialization of a binary tree.
 * <p>
 * It is guaranteed that each comma-separated value in the string must be either an integer or a character '#' representing null pointer.
 * <p>
 * You may assume that the input format is always valid.
 * <p>
 * For example, it could never contain two consecutive commas, such as "1,,3".
 * Note: You are not allowed to reconstruct the tree.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: preorder = "9,3,4,#,#,1,#,#,2,#,6,#,#"
 * Output: true
 * Example 2:
 * <p>
 * Input: preorder = "1,#"
 * Output: false
 * Example 3:
 * <p>
 * Input: preorder = "9,#,#,1"
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= preorder.length <= 10^4
 * preoder consist of integers in the range [0, 100] and '#' separated by commas ','.
 */
public class VerifyPreorderSerializationOfABinaryTree {
    public boolean isValidSerialization(String preorder) {
        int numChildren = 1;
        for (int i = 0; i<preorder.length(); i++) {
            char c = preorder.charAt(i);
            if (c == ','){
                continue;
            }
            if (numChildren <= 0) {
                return false;
            }
            if (c == '#') {
                numChildren--;
            } else {
                numChildren++;
                while(c != '#' && c != ',' && i+1 < preorder.length()){
                    i++;
                    c = preorder.charAt(i);
                }
            }
        }
        return numChildren == 0;
    }
}
