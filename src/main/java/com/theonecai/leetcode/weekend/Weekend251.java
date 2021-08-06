package com.theonecai.leetcode.weekend;


import org.junit.Assert;

import java.util.Arrays;

/**
 * @Author: theonecai
 * @Date: Create in 2021/07/24 10:24
 * @Description:
 */
public class Weekend251 {

    public int getLucky(String s, int k) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            sb.append(c - 'a' + 1);
        }
        long val = 0;
        while(k-- > 0) {
            val = 0;
            for (int i = 0; i < sb.length(); i++) {
                val += Integer.parseInt(String.valueOf(sb.charAt(i)));
            }
            sb.delete(0, sb.length());
            sb.append(val);
        }
        return (int)val;
    }

    public String maximumNumber(String num, int[] change) {
        char[] chars = num.toCharArray();
        boolean changed = false;
        for (int i = 0; i < chars.length; i++) {
            if (changed){
                break;
            }
            int n = chars[i] - '0';
            if (n == change[n]) {
                continue;
            }
            while (n <= change[n]) {
                chars[i] =(char) (change[n] + '0');
                i++;
                changed = true;
                if (i >= chars.length) {
                    break;
                }
                n = chars[i] - '0';
            }
        }

        return String.valueOf(chars);
    }

    private int[] match;
    private int max;
    private int len;
    public int maxCompatibilitySum(int[][] students, int[][] mentors) {
        match = new int[students.length];
        max = 0;
        len = students[0].length;
        int[] std = new int[students.length];
        int[] men = new int[students.length];
        for (int i = 0; i < students.length; i++) {
            int s = 0;
            int m = 0;
            for (int j = len - 1; j >= 0; j--) {
                s += (students[i][j] << j);
                m += (mentors[i][j] << j);
            }
            std[i] = s;
            men[i] = m;
        }

        backtrace(std, men, 0, new boolean[std.length]);
        return max;
    }

    private void backtrace(int[] students, int[] mentors, int idx, boolean[] visited) {
        if (idx > mentors.length) {
            return;
        }
        if (idx == mentors.length) {
            max = Math.max(max, calc(students, mentors, match));
            return;
        }
        for (int i = 0; i < mentors.length; i++) {
            if (!visited[i]) {
                match[idx] = i;
                visited[i] = true;
                backtrace(students, mentors, idx + 1, visited);
                match[idx] = -1;
                visited[i] = false;
            }
        }
    }

    private int calc(int[] std, int[] men, int[] match) {
//        System.out.println(Arrays.toString(match));
        int res = 0;
        for (int i = 0; i < match.length; i++) {
            res += len - Integer.bitCount(std[i] ^ men[match[i]]);
        }
        return res;
    }


    public static void main(String[] args) {
        Weekend251 weekend = new Weekend251();
        weekend.test();
        weekend.test2();
        weekend.test3();
        weekend.test4();
    }

    private void test4() {
    }

    private void test3() {
        Assert.assertEquals(0, this.maxCompatibilitySum(new int[][]{
                {0,0},{0,0},{0,0}
        }, new int[][]{
                {1,1},{1,1},{1,1}
        }));
        Assert.assertEquals(8, this.maxCompatibilitySum(new int[][]{
                {1,1,0},{1,0,1},{0,0,1}
        }, new int[][]{
                {1,0,0},{0,0,1},{1,1,0}
        }));

    }

    private void test2() {
        Assert.assertEquals("334999", this.maximumNumber("334111", new int[]{0,9,2,3,3,2,5,5,5,5}));
        Assert.assertEquals("974676", this.maximumNumber("214010", new int[]{6,7,9,7,4,0,3,4,4,7}));
        Assert.assertEquals("832", this.maximumNumber("132", new int[]{9,8,5,0,3,6,4,2,6,8}));
        Assert.assertEquals("934", this.maximumNumber("021", new int[]{9,4,3,5,7,2,1,9,0,6}));
        Assert.assertEquals("5", this.maximumNumber("5", new int[]{1,4,7,5,3,2,5,6,9,4}));
    }

    private void test() {
        Assert.assertEquals(104, this.getLucky("zzzzzzzzzzzzz", 1));
        Assert.assertEquals(36, this.getLucky("iiii", 1));
        Assert.assertEquals(6, this.getLucky("leetcode", 2));
    }
}
