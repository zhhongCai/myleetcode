package com.theonecai.leetcode.bfs;

import org.junit.Assert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * leetcode 488
 */
public class FindMinStep {
    public int findMinStep(String board, String hand) {
//        return bfs(board, hand);
        memo = new HashMap<>();
        long res = dfs(board, hand);
        return res >= Integer.MAX_VALUE ? -1 : (int)res;
    }

    private Map<String, Long> memo;
    private long dfs(String board, String hand) {
        if (board == null || board.length() == 0) {
            return 0;
        }
        if (hand == null || hand.length() == 0) {
            return Integer.MAX_VALUE;
        }
        String key = board + "-" + hand;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        char[] handChars = hand.toCharArray();
        Arrays.sort(handChars);
        long res = Integer.MAX_VALUE;
        for (int i = 0; i < hand.length(); i++) {
            if (i > 0 && hand.charAt(i) == hand.charAt(i - 1)) {
                continue;
            }
            String s = String.valueOf(hand.charAt(i));
            String nextHand = hand.substring(0, i) + hand.substring(i + 1);
            for (int j = 0; j <= board.length(); j++) {
                boolean ok = false;
                if (j > 0 && j < board.length() && board.charAt(j) == board.charAt(j - 1)
                        && board.charAt(j - 1) != hand.charAt(i)) {
                    ok = true;
                }
                if (j < board.length() && board.charAt(j) == hand.charAt(i)) {
                    ok = true;
                }
                if (!ok) {
                    continue;
                }
                String nextBoard = board.substring(0, j) + s + board.substring(j);
                nextBoard = clean(nextBoard);
                res = Math.min(res, dfs(nextBoard, nextHand) + 1);
            }
        }
        memo.put(key, res);
        return res;
    }

    public int bfs(String board, String hand) {
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
