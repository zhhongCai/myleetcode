package com.theonecai.leetcode.weekend;

import org.junit.Assert;

import java.util.Arrays;

/**
 * @Author: theonecai
 * @Date: Create in 2020/10/18 10:24
 * @Description:
 */
public class Weekend211 {

    /**
     * 5543
     * @param s
     * @return
     */
    public int maxLengthBetweenEqualCharacters(String s) {
        int maxLen = 0;
        boolean noDup = true;
        for (int i = 0; i < s.length() - 1; i++) {
            for (int j = s.length() - 1; j > i; j--) {
                if (s.charAt(i) == s.charAt(j)) {
                    noDup = false;
                    maxLen = Math.max(maxLen, j - i - 1);
                    break;
                }
            }
        }
        if (noDup) {
            return -1;
        }

        return maxLen;
    }

    public String findLexSmallestString(String s, int a, int b) {

        return "";
    }

    public int bestTeamScore(int[] scores, int[] ages) {
        int[][] people = new int[ages.length][2];
        for (int i = 0; i < ages.length; i++) {
            people[i] = new int[]{scores[i], ages[i]};
        }
        Arrays.sort(people, ((o1, o2) -> {
            int res = o1[1] - o2[1];
            if (res == 0) {
                return o1[0] - o2[0];
            }
            return res;
        }));
        // dp[i] 表示到0~i的最大分数,dp[i] =Math.max(dp[i], dp[j] + score[i])，当score[i] >= score[j] && i > j;
        int[] dp = new int[people.length];
        dp[0] = people[0][0];
        for (int i = 1; i < people.length; i++) {
            dp[i] = people[i][0];
            for (int j = 0; j < i; j++) {
                if (people[i][0] >= people[j][0]) {
                    dp[i] = Math.max(dp[i], dp[j] + people[i][0]);
                }
            }
        }
        int sc = 0;
        for (int i = 0; i < dp.length; i++) {
            sc = Math.max(sc, dp[i]);
        }
        return sc;
    }

    public static void main(String[] args) {
        Weekend211 weekend211 = new Weekend211();
        weekend211.test();
        weekend211.test2();
        weekend211.test3();
    }

    private void test3() {
    }

    private void test2() {
        Assert.assertEquals(3287, this.bestTeamScore(new int[]{596,277,897,622,500,299,34,536,797,32,264,948,645,537,83,589,770},
                new int[]{18,52,60,79,72,28,81,33,96,15,18,5,17,96,57,72,72}));
        Assert.assertEquals(16, this.bestTeamScore(new int[]{4,5,6,5}, new int[]{2,1,2,1}));
        Assert.assertEquals(34, this.bestTeamScore(new int[]{1,3,5,10,15}, new int[]{1,2,3,4,5}));
        Assert.assertEquals(6, this.bestTeamScore(new int[]{1,2,3,5}, new int[]{8,9,10,1}));
    }

    private void test() {
        Assert.assertEquals(0, this.maxLengthBetweenEqualCharacters("aa"));
        Assert.assertEquals(2, this.maxLengthBetweenEqualCharacters("abca"));
        Assert.assertEquals(-1, this.maxLengthBetweenEqualCharacters("cbzxy"));
        Assert.assertEquals(4, this.maxLengthBetweenEqualCharacters("cabbac"));
    }
}
