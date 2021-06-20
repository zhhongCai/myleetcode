package com.theonecai.leetcode.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * leetcode 1600
 * @Author: zhenghong.cai
 * @Date: Create in 6/20/21 10:09
 * @Description:
 */
public class ThroneInheritance {

    private static class TreeNode {
        private String name;
        private boolean alive;
        private List<TreeNode> children;

        public TreeNode(String name) {
            this.name = name;
            this.alive = true;
            this.children = new ArrayList<>();
        }

        public void dead() {
            this.alive = false;
        }

        public boolean isAlive() {
            return this.alive;
        }
    }

    private TreeNode root;

    private Map<String, TreeNode> nameMap = new HashMap<>();

    private List<String> orderList;

    public ThroneInheritance(String kingName) {
        root = new TreeNode(kingName);
        nameMap.put(kingName, root);
    }

    public void birth(String parentName, String childName) {
        TreeNode parent = nameMap.get(parentName);
        TreeNode child = new TreeNode(childName);
        nameMap.put(childName, child);
        parent.children.add(child);
    }

    public void death(String name) {
        nameMap.get(name).dead();
    }

    public List<String> getInheritanceOrder() {
        this.orderList = new ArrayList<>(nameMap.size());

        preOrder(root);

        return this.orderList;
    }


    private void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }

        if (root.isAlive()) {
            orderList.add(root.name);
        }
        for (TreeNode child : root.children) {
            preOrder(child);
        }
    }

    public static void main(String[] args) {
        ThroneInheritance throneInheritance = new ThroneInheritance("king");
        throneInheritance.birth("king", "andy");
        throneInheritance.birth("king", "bob");
        throneInheritance.birth("king", "catherine");
        throneInheritance.birth("andy", "matthew");
        throneInheritance.birth("bob", "alex");
        throneInheritance.birth("bob", "asha");
        System.out.println(throneInheritance.getInheritanceOrder());
        throneInheritance.death("bob");
        System.out.println(throneInheritance.getInheritanceOrder());
    }
}
