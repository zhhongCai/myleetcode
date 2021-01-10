package com.theonecai.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * leetcode 429
 * @Author: theonecai
 * @Date: Create in 2021/1/10 14:54
 * @Description:
 */
public class NTreeLevelOrder {

    public List<List<Integer>> levelOrder(Node root) {
         List<List<Integer>> tree = new LinkedList<>();
         if (root == null) {
             return tree;
         }
         Queue<Node> queue = new LinkedList<>();
         queue.offer(root);
         while (!queue.isEmpty()) {
             int s = queue.size();
             int i = 0;
             List<Integer> list = new ArrayList<>(s);
             while (i < s) {
                 Node node = queue.poll();
                 if (node.children != null) {
                     queue.addAll(node.children);
                 }
                 list.add(node.val);
                 i++;
             }
             tree.add(list);
         }

         return tree;
    }

    public static void main(String[] args) {
        NTreeLevelOrder nTreeLevelOrder = new NTreeLevelOrder();
        Node root = new Node(1);
        root.children = new ArrayList<>();
        root.children.add(new Node(2));
        root.children.add(new Node(3));
        root.children.add(new Node(4));
        List<List<Integer>> list = nTreeLevelOrder.levelOrder(root);
        for (List<Integer> children : list) {
            System.out.println(children);
        }
    }

    private static class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };
}
