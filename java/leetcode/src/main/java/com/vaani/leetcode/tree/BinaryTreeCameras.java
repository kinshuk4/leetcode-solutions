package com.vaani.leetcode.tree;

import com.vaani.dsa.ds.core.tree.binarytree.simple.TreeNode;

/**
 * https://leetcode.com/problems/binary-tree-cameras/
 * 968. Binary Tree Cameras
 * Hard
 * <p>
 * 1377
 * <p>
 * 20
 * <p>
 * Add to List
 * <p>
 * Share
 * Given a binary tree, we install cameras on the nodes of the tree.
 * <p>
 * Each camera at a node can monitor its parent, itself, and its immediate children.
 * <p>
 * Calculate the minimum number of cameras needed to monitor all nodes of the tree.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: [0,0,null,0,0]
 * Output: 1
 * Explanation: One camera is enough to monitor all nodes if placed as shown.
 * Example 2:
 * <p>
 * <p>
 * Input: [0,0,null,0,null,0,null,null,0]
 * Output: 2
 * Explanation: At least two cameras are needed to monitor all nodes of the tree. The above image shows one of the valid configurations of camera placement.
 * <p>
 * Note:
 * <p>
 * The number of nodes in the given tree will be in the range [1, 1000].
 * Every node has value 0.
 */

public class BinaryTreeCameras {

    int ans = 0;

    public enum CameraStatus {CAMERA_INSTALLED, COVERED, NOT_COVERED}

    public int minCameraCover(TreeNode root) {
        return dfs(root) == CameraStatus.NOT_COVERED ? ans + 1 : ans;
    }

    public CameraStatus dfs(TreeNode node) {
        if (node == null) {
            return CameraStatus.COVERED;
        }

        CameraStatus left = dfs(node.left);
        CameraStatus right = dfs(node.right);

        if (left == CameraStatus.NOT_COVERED || right == CameraStatus.NOT_COVERED) {
            ans++;
            return CameraStatus.CAMERA_INSTALLED;
        }
        if (left == CameraStatus.CAMERA_INSTALLED || right == CameraStatus.CAMERA_INSTALLED) {
            return CameraStatus.COVERED;
        }

        return CameraStatus.NOT_COVERED;
    }

}
