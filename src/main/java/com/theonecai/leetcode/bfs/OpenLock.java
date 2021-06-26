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
 * leetcode 752
 * @Author: theonecai
 * @Date: Create in 6/25/21 22:12
 * @Description:
 */
public class OpenLock {
    private String start = "0000";
    private Set<String> deadendSet;

    public int openLock(String[] deadends, String target) {
        deadendSet = new HashSet<>(deadends.length);
        deadendSet.addAll(Arrays.asList(deadends));
        if (deadendSet.contains(target) || deadendSet.contains("0000")) {
            return -1;
        }
        if (start.equals(target)) {
            return 0;
        }
        Queue<String> queue = new LinkedList<>();
        Queue<String> backQueue = new LinkedList<>();
        Map<String, Integer> timesMap = new HashMap<>();
        Map<String, Integer> backTimesMap = new HashMap<>();
        queue.add(start);
        timesMap.put(start, 0);
        backQueue.add(target);
        backTimesMap.put(target, 0);

        while (!queue.isEmpty() && !backQueue.isEmpty()) {
            int t = -1;
            if (queue.size() > backQueue.size()) {
                t = updateQueue(backQueue, backTimesMap, timesMap);
            } else {
                t = updateQueue(queue, timesMap, backTimesMap);
            }
            if (t != -1) {
                return t;
            }
        }

        return -1;
    }

    private int updateQueue(Queue<String> queue, Map<String, Integer> timesMap, Map<String, Integer> backTmesMap) {
        String cur = queue.poll();
        char[] chars = cur.toCharArray().clone();
        for (int i = 0; i < 4; i++) {
            char ch = cur.charAt(i);
            chars[i] = forward(ch);
            Integer step = nextStep(queue, timesMap, backTmesMap, cur, new String(chars));
            if (step != null) {
                return step;
            }
            chars[i] = back(ch);
            step = nextStep(queue, timesMap, backTmesMap, cur, new String(chars));
            if (step != null) {
                return step;
            }
            chars[i] = ch;
        }
        return -1;
    }

    private Integer nextStep(Queue<String> queue, Map<String, Integer> timesMap,
                             Map<String, Integer> backTmesMap, String cur, String next) {
        if (!timesMap.containsKey(next) && !deadendSet.contains(next)) {
            queue.add(next);
            timesMap.put(next, timesMap.get(cur) + 1);
        }
        if (backTmesMap.containsKey(next)) {
            return timesMap.get(cur) + backTmesMap.get(next) + 1;
        }
        return null;
    }

    private char forward(char ch) {
        int c = ch - '0';
        c += 1;
        c %= 10;
        return (char)(c + '0');
    }

    private char back(char ch) {
        int c = ch - '0';
        c -= 1;
        if (c == -1) {
            c = 9;
        }
        return (char)(c + '0');
    }

    public static void main(String[] args) {
        OpenLock openLock = new OpenLock();
        Assert.assertEquals(0, openLock.openLock(new String[]{"0201","0101","0102","1212","2002"}, "0000"));
        Assert.assertEquals(-1, openLock.openLock(new String[]{"1000","0100","0010","0001","9000","0900","0090","0009"}, "0202"));
        Assert.assertEquals(6, openLock.openLock(new String[]{"0201","0101","0102","1212","2002"}, "0202"));
        Assert.assertEquals(1, openLock.openLock(new String[]{"8888"}, "0009"));
        Assert.assertEquals(-1, openLock.openLock(new String[]{"8887","8889","8878","8898","8788","8988","7888","9888"}, "8888"));
        Assert.assertEquals(-1, openLock.openLock(new String[]{"0000"}, "8888"));
    }
}
