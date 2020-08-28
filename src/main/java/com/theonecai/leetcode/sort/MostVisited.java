package com.theonecai.leetcode.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: theonecai
 * @Date: Create in 2020/8/24 18:58
 * @Description:
 */
public class MostVisited {


    public List<Integer> mostVisited(int n, int[] rounds) {
        List<Integer> result = new ArrayList<>();
        Map<Integer, Node> countMap = new HashMap<>();
        int i = 0;
        for (int j = 1; j < rounds.length; j++) {
            if (rounds[j - 1] < rounds[j]) {
                int k = (j == 1 ? rounds[j - 1] : rounds[j - 1] + 1);
                while (k <= rounds[j]) {
                    final int kk = k;
                    countMap.computeIfAbsent(kk, e -> new Node(kk, 0)).count++;
                    k++;
                }
            } else {
                int k = (j == 1 ? 0 : 1);
                int count = n - rounds[j - 1] + rounds[j];
                while (k <= count) {
                    final int kk = (rounds[j - 1] + k) % n;
                    countMap.computeIfAbsent(kk == 0 ? n : kk, e -> new Node(kk == 0 ? n : kk, 0)).count++;
                    k++;
                }
            }
        }

        List<Node> lists = new ArrayList<>(countMap.values());
        Collections.sort(lists);

        int c = -1;
        for (int j = 0; j < lists.size(); j++) {
            if (c == -1) {
                c = lists.get(j).count;
            }
            if (lists.get(j).count < c) {
                break;
            }
            result.add(lists.get(j).n);
        }

        return result;
    }

    static class Node implements Comparable<Node> {
        int n;
        int count;

        public Node(int n, int count) {
            this.n = n;
            this.count = count;
        }

        @Override
        public int compareTo(Node o) {
            int c = o.count - this.count;
            if (c == 0) {
                return this.n - o.n;
            }
            return c;
        }
    }

    public static void main(String[] args) {
        MostVisited mostVisited = new MostVisited();
        int[] rounds = {2,1,2,1,2,1,2,1,2};
        List<Integer> integers = mostVisited.mostVisited(2, rounds);
        System.out.println(integers);
    }
}
