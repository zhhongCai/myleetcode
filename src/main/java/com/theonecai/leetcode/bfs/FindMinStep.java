package com.theonecai.leetcode.bfs;

import org.junit.Assert;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * leetcode 488
 */
public class FindMinStep {
    public int findMinStep(String board, String hand) {
        char[] handChars = hand.toCharArray();
        Arrays.sort(handChars);
        Queue<String[]> queue = new LinkedList<>();
        queue.add(new String[]{board, String.valueOf(handChars)});
        int res = 0;
        Set<String> visited = new HashSet<>();
        while (!queue.isEmpty()) {
            res++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String[] curStep = queue.poll();
                String curBoard = curStep[0];
                String curHand = curStep[1];
                char pre = ' ';
                for (int j = 0; j < curHand.length(); j++) {
                    char ch = curHand.charAt(j);
                    if (ch == pre) {
                        continue;
                    }
                    String c = String.valueOf(ch);
                    String nextHand = curHand.substring(0, j) + curHand.substring(j + 1);
                    for (int k = 0; k < curBoard.length(); k++) {
                        String nextBoard = curBoard.substring(0, k) + c + curBoard.substring(k);
                        nextBoard = clean(nextBoard);
                        if (nextBoard == null) {
                            return res;
                        }
                        String key = nextBoard + "-" + nextHand;
//                        System.out.println(key);
                        if (visited.contains(key)) {
                            continue;
                        }
                        visited.add(key);
                        queue.add(new String[]{nextBoard, nextHand});
                    }
                    pre = ch;
                }
            }
        }
        return -1;
    }

    private String clean(String board) {
        int n = board.length();
        int left = 0;
        int right = 0;
        StringBuilder sb = new StringBuilder();
        while (right < n) {
            right++;
            if (right >= n) {
                break;
            }
            if (board.charAt(right) == board.charAt(left)) {
                continue;
            }
            if (right - left >= 3) {
                left = right;
            } else {
                sb.append(board, left, right);
                left = right;
            }

        }
        if (right - left < 3) {
            sb.append(board, left, right);
        }

        if (sb.length() == 0 ) {
            return null;
        }
        String b = sb.toString();
        if (b.equals(board)) {
            return b;
        }
        return clean(b);
    }

    public static void main(String[] args) {
        FindMinStep step = new FindMinStep();
        Assert.assertEquals(6, step.findMinStep("RGRGRG", "RRGGRG"));
        Assert.assertEquals(3, step.findMinStep("RBYYBBRRB", "YRBGB"));
        Assert.assertEquals(2, step.findMinStep("G", "GGGGG"));
        Assert.assertEquals(-1, step.findMinStep("WRRBBW", "RB"));
        Assert.assertEquals(2, step.findMinStep("WWRRBBWW", "WRBRW"));
        System.out.println(step.clean("aaAAcAbbbAAAa"));
    }
}
