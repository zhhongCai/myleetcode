package com.theonecai.leetcode.stack;

/**
 * leetcode 71
 * @Author: theonecai
 * @Date: Create in 2020/7/1 21:02
 * @Description:
 */
public class SimplifyPath {

    public String simplifyPath(String path) {
        if (path == null || path.length() == 0) {
            return "";
        }
        String[] strs = path.split("/");
        String[] stack = new String[strs.length];
        int current = -1;
        for (String str : strs) {
            if (str == null || str.length() == 0) {
                continue;
            }
            switch (str) {
            case ".":
                break;
            case "..":
                if (current >= 0) {
                    current--;
                }
                break;
            default:
                stack[++current] = str;
            }
        }
        StringBuilder sb = new StringBuilder("/");
        for (int i = 0; i <= current; i++) {
            sb.append(stack[i]).append("/");
        }
        if (sb.length() > 1) {
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        SimplifyPath path = new SimplifyPath();
        String a = "/home/";
        System.out.println(path.simplifyPath(a));

        a = "/../";
        System.out.println(path.simplifyPath(a));
        a = "/home//foo/";
        System.out.println(path.simplifyPath(a));
        a = "/a/./b/../../c/";
        System.out.println(path.simplifyPath(a));
        a = "/a/../../b/../c//.//";
        System.out.println(path.simplifyPath(a));
        a = "/a//b////c/d//././/..";
        System.out.println(path.simplifyPath(a));
    }
}
