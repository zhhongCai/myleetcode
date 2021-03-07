package com.theonecai.leetcode.tree;

import org.junit.Assert;

/**
 * leetcode 1028
 * @Author: theonecai
 * @Date: Create in 2021/3/7 14:13
 * @Description:
 */
public class RecoverFromPreorder {
    public TreeNode recoverFromPreorder(String S) {
       return recoverFromPreorder(S, "-");
    }

    private TreeNode recoverFromPreorder(String s, String level) {
        if (s == null || "".equals(s)) {
            return null;
        }
        String[] strs = split(s, level);
        if ("".equals(strs[0])) {
            return null;
        }
        TreeNode node =  new TreeNode(Integer.parseInt(strs[0]));
        if (strs.length > 1) {
            node.left = recoverFromPreorder(strs[1], level + "-");
        }
        if (strs.length > 2) {
            node.right = recoverFromPreorder(strs[2], level + "-");
        }

        return node;
    }

    /**
     * 将字符串s按level分割
     * @param s
     * @param level
     * @return
     */
    private String[] split(String s, String level) {
        int first = s.indexOf(level);
        if (first == -1) {
            return new String[] {s};
        }

        String[] data = new String[3];
        data[0] = s.substring(0, first);

        int second = -1;
        int i = first + level.length();
        while (i != -1) {
            i = s.indexOf(level, i);
            if (i >= 0 ) {
                int idx = i + level.length();
                if (idx >= s.length()) {
                    break;
                }
                if (s.charAt(idx) != '-') {
                    second = i;
                    break;
                } else {
                    while (idx < s.length() && s.charAt(idx) == '-') {
                        idx++;
                    }
                    i = idx;
                }
            }
        }

        if (second == -1) {
            data[1] = s.substring(first + level.length());
            if (data[1].startsWith("-")) {
                int st = -1;
                for (int j = 0; j < data[1].length(); j++) {
                    if (data[1].charAt(j) != '-') {
                        st = j;
                        break;
                    }
                }
                if (st != -1) {
                    data[1] = data[1].substring(st);
                }
            }
        } else {
            data[1] = s.substring(first + level.length(), second);
            data[2] = s.substring(second + level.length());
        }

        return data;
    }

    public static void main(String[] args) {
        RecoverFromPreorder recoverFromPreorder = new RecoverFromPreorder();
        Assert.assertEquals("[1,5,null,6,7]", BinaryTreeUtil.serialize(
                recoverFromPreorder.recoverFromPreorder("1------5--6--7")));
        Assert.assertEquals("[1,2,5,3,4,6,7]", BinaryTreeUtil.serialize(
                recoverFromPreorder.recoverFromPreorder("1-2--3--4-5--6--7")));
        Assert.assertEquals("[1,401,null,349,88,90]", BinaryTreeUtil.serialize(
                recoverFromPreorder.recoverFromPreorder("1-401--349---90--88")));
    }
}
