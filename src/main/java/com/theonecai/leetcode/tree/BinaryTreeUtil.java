package com.theonecai.leetcode.tree;

/**
 * 序列化/反序列化树
 */
public class BinaryTreeUtil {

    private final static BinaryTreeCodec codec = new BinaryTreeCodec();

    public static String serialize(TreeNode root) {
        return codec.serialize(root);
    }

    public static TreeNode deserialize(String data) {
        return codec.deserialize(data);
    }
}
