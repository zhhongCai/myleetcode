package com.theonecai.leetcode.gredy;

import com.theonecai.leetcode.util.RunUtil;
import org.junit.Assert;

import java.util.PriorityQueue;

/**
 * leetcode 1405
 * @Author: theonecai
 * @Date: Create in 2020/9/16 19:40
 * @Description:
 */
public class LongestDiverseString {
    public String longestDiverseString(int a, int b, int c) {
        if (a == 0 && b == 0 && c == 0) {
            return "";
        }
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(3, (o1, o2) -> o2[1] - o1[1]);
        maxHeap.add(new int[]{'a', a});
        maxHeap.add(new int[]{'b', b});
        maxHeap.add(new int[]{'c', c});

        StringBuilder sb = new StringBuilder();
        int len = 0;
        int[] top;
        while (!maxHeap.isEmpty()) {
            len = sb.length();
            top = maxHeap.poll();
            if (top == null) {
                break;
            }
            if (len >= 2 && sb.charAt(len - 1) == top[0] && sb.charAt(len - 2) == top[0]) {
                int[] second = maxHeap.poll();
                if (second != null && second[1] > 0) {
                    sb.append((char)second[0]);
                    second[1] -= 1;

                    maxHeap.add(second);
                    maxHeap.add(top);
                } else {
                    break;
                }
            } else {
                if (top[1] > 0) {
                    sb.append((char)top[0]);
                    top[1] -= 1;
                    maxHeap.add(top);
                } else {
                    break;
                }
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        LongestDiverseString longestDiverseString = new LongestDiverseString();
        Assert.assertEquals("ccaccbccaccbccacbcbabacacbcbabac", longestDiverseString.longestDiverseString(8, 8, 16));
        Assert.assertEquals("ccaccbccaccbcacacbcbabacacbcbabac", longestDiverseString.longestDiverseString(9, 8, 16));
        Assert.assertEquals("ccaccbcc", longestDiverseString.longestDiverseString(1, 1, 7));
        Assert.assertEquals("ccaccbccaccbcc", longestDiverseString.longestDiverseString(2, 2, 11));
        Assert.assertEquals("ccaccbccaccbcc", longestDiverseString.longestDiverseString(2, 2, 12));
        RunUtil.runAndPrintCostTime(() -> {
            String str = longestDiverseString.longestDiverseString(100, 100, 100);
            Assert.assertEquals(300, str.length());
        });
    }
}
