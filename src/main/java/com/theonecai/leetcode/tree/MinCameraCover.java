package com.theonecai.leetcode.tree;

import org.junit.Assert;

/**
 * leetcode 968
 * @Author: theonecai
 * @Date: Create in 2021/2/17 15:44
 * @Description:
 */
public class MinCameraCover {

    public int minCameraCover2(TreeNode root) {
        return minCamera(root)[1];
    }

    private int[] minCamera(TreeNode node) {
        if (node == null) {
            return new int[] {Integer.MAX_VALUE / 2, 0, 0};
        }
        // count[0]: node 必须放置摄像头的情况下，覆盖整棵树需要的摄像头数目
        // count[1]: 覆盖整棵树需要的摄像头数目，无论 node 是否放置摄像头。
        // count[2]: 覆盖两棵子树需要的摄像头数目，无论节点 node 本身是否被监控到
        int[] count = new int[3];

        int[] leftCount = minCamera(node.left);
        int[] rightCount = minCamera(node.right);
        count[0] = leftCount[2] + rightCount[2] + 1;
        count[1] = Math.min(count[0], Math.min(leftCount[0] + rightCount[1], leftCount[1] + rightCount[0]));
        count[2] = Math.min(count[0], leftCount[1] + rightCount[1]);

        return count;
    }

    private int count;
    /**
     * 无覆盖
     */
    private final static int NO_COVERED = 0;
    /**
     * 有摄像头
     */
    private final static int HAS_CAMERA = 1;
    /**
     * 已覆盖
     */
    private final static int COREREDD = 2;

    public int minCameraCover(TreeNode root) {
        this.count = 0;
        if (postOrder(root) == NO_COVERED) {
            this.count++;
        }
        return count;
    }

    private int postOrder(TreeNode node) {
        if (node == null) {
            return COREREDD;
        }
        int left = postOrder(node.left);
        int right = postOrder(node.right);
        if (left == COREREDD && right == COREREDD) {
            return NO_COVERED;
        }
        if (left == NO_COVERED || right == NO_COVERED) {
            this.count++;
            return HAS_CAMERA;
        }
        if (left == HAS_CAMERA || right == HAS_CAMERA) {
            return COREREDD;
        }
        return -1;
    }


    public static void main(String[] args) {
        MinCameraCover minCameraCover = new MinCameraCover();
        Assert.assertEquals(1, minCameraCover.minCameraCover(
                BinaryTreeUtil.deserialize("[0,0,null,0,0]")));
        Assert.assertEquals(2, minCameraCover.minCameraCover(
                BinaryTreeUtil.deserialize("[0,0,null,0,null,0,null,null,0]")));
    }
}
