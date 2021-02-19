package com.theonecai.leetcode.tree;

import com.theonecai.leetcode.util.RunUtil;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 *  leetcode 919
 */
public class CBTInserter {

    private List<List<TreeNode>> levels;

    private TreeNode root;

    private int[] levelSize;

    public CBTInserter(TreeNode root) {
        this.levels = new ArrayList<>();
        this.levelSize = new int[16];
        this.levelSize[0] = 1;
        for (int i = 1; i < levelSize.length; i++) {
            this.levelSize[i] = this.levelSize[i - 1] << 1;
        }

        dfs(root, 0);

        this.root = root;
    }

    private void dfs(TreeNode node, int level) {
        if (node == null) {
            return;
        }
        if (this.levels.size() == level) {
            this.levels.add(new ArrayList<>());
        }
        this.levels.get(level).add(node);

        dfs(node.left, level + 1);
        dfs(node.right, level + 1);
    }

    public int insert(int v) {
        int level = this.levels.size() - 1;
        List<TreeNode> levelList = this.levels.get(level);
        if (levelList.size() == this.levelSize[level]) {
            levelList = new ArrayList<>();
            this.levels.add(levelList);
            level++;
        }
        TreeNode node = new TreeNode(v);
        levelList.add(node);

        TreeNode parent = this.levels.get(level - 1).get((levelList.size() - 1) >> 1);
        if (parent.left == null) {
            parent.left = node;
        } else {
            parent.right = node;
        }

        return parent.val;
    }

    public TreeNode get_root() {
        return this.root;
    }

    public static void main(String[] args) {
        CBTInserter cbtInserter = new CBTInserter(BinaryTreeUtil.deserialize("[1]"));
        Assert.assertEquals(1, cbtInserter.insert(2));
        Assert.assertEquals("[1,2]", BinaryTreeUtil.serialize(cbtInserter.get_root()));

        cbtInserter = new CBTInserter(BinaryTreeUtil.deserialize("[1,2,3,4,5,6]"));
        Assert.assertEquals(3, cbtInserter.insert(7));
        Assert.assertEquals(4, cbtInserter.insert(8));
        Assert.assertEquals("[1,2,3,4,5,6,7,8]", BinaryTreeUtil.serialize(cbtInserter.get_root()));

        RunUtil.runAndPrintCostTime(() -> {
            CBTInserter inserter = new CBTInserter(BinaryTreeUtil.deserialize("[1]"));
            StringBuilder sb = new StringBuilder("[1,");
            for (int i = 2; i < 10000; i++) {
                Assert.assertEquals(i / 2, inserter.insert(i));
                sb.append(i).append(",");
            }
            sb.deleteCharAt(sb.length() - 1).append("]");
            Assert.assertEquals(sb.toString(), BinaryTreeUtil.serialize(inserter.get_root()));
        });
    }
}
