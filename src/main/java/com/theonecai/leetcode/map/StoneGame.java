package com.theonecai.leetcode.map;

import org.junit.Assert;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 1046
 */
public class StoneGame {

    public int game(int[] stones) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        for (int stone : stones) {
            maxHeap.offer(stone);
        }

        while (!maxHeap.isEmpty()) {
            int top = maxHeap.poll();
            if (maxHeap.isEmpty()) {
                return top;
            }
            int top2 = maxHeap.poll();
            if (top > top2) {
                maxHeap.offer(top - top2);
            }
        }
        return 0;
    }

    public int game2(int[] stones) {
        int[] bucket = new int[1001];
        for (int stone : stones) {
            bucket[stone]++;
        }
        while (true) {
            int index = bucket.length - 1;
            while (index >= 0 && bucket[index] == 0 ) {
                index--;
            }
            if (index == -1) {
                return 0;
            }
            int top = index;
            bucket[index]--;
            while (index >= 0 && bucket[index] == 0 ) {
                index--;
            }
            if (index == -1) {
                return top;
            }
            int top2 = index;
            bucket[index]--;
            if (top > top2) {
                bucket[top - top2]++;
            }
        }
    }

    public static void main(String[] args) {
        StoneGame stoneGame = new StoneGame();
        Assert.assertEquals(0, stoneGame.game2(new int[]{1,2,3,4,5,6,7}));
        Assert.assertEquals(0, stoneGame.game(new int[]{1,2,3,4,5,6,7,8}));
        Assert.assertEquals(1, stoneGame.game2(new int[]{1,2,3,4,5,11,7,8}));
        Assert.assertEquals(1, stoneGame.game(new int[]{1,2,3,4,5,11,7,8}));
    }
}
