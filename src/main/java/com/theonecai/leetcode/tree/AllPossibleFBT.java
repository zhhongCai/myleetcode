package com.theonecai.leetcode.tree;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * leetcode 894
 * @Author: theonecai
 * @Date: Create in 2021/2/6 20:41
 * @Description:
 */
public class AllPossibleFBT {
    private Map<Integer, List<TreeNode>> memo = new HashMap<>(20);

    public List<TreeNode> allPossibleFBT(int N) {
        if (memo.containsKey(N)) {
            return memo.get(N);
        }
        List<TreeNode> result = new ArrayList<>();
        if (N <= 0 || N == 2) {
            memo.put(N, result);
            return result;
        }
        if (N == 1) {
            result.add(new TreeNode(0));
            memo.put(N, result);
            return result;
        }

        for (int i = 1; i < N - 1; i++) {
            List<TreeNode> left = allPossibleFBT(i);
            List<TreeNode> right = allPossibleFBT(N - 1 - i);
            for (TreeNode leftNode : left) {
                for (TreeNode rightNode : right) {
                    TreeNode node = new TreeNode(0);
                    result.add(node);
                    node.left = leftNode;
                    node.right = rightNode;
                }
            }
        }
        memo.put(N, result);

        return result;
    }

    public static void main(String[] args) {
        AllPossibleFBT allPossibleFBT = new AllPossibleFBT();
        List<TreeNode> list = allPossibleFBT.allPossibleFBT(7);
        for (TreeNode treeNode : list) {
            System.out.println(BinaryTreeUtil.serialize(treeNode));
        }
        Assert.assertEquals(5, list.size());

        list = allPossibleFBT.allPossibleFBT(2);
        Assert.assertEquals(0, list.size());

        list = allPossibleFBT.allPossibleFBT(1);
        Assert.assertEquals(1, list.size());
    }

}
