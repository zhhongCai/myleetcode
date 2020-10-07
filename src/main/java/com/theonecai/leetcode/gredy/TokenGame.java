package com.theonecai.leetcode.gredy;

import java.util.Arrays;

import org.junit.Assert;

/**
 *
 * leetcode 948
 * @Author: theonecai
 * @Date: Create in 2020/9/29 20:22
 * @Description:
 */
public class TokenGame {

    public int bagOfTokensScore(int[] tokens, int P) {
        int score = 0;
        Arrays.sort(tokens);

        int j = tokens.length - 1;
        for (int i = 0; i <= j; i++) {
            if (tokens[i] <= P) {
                P -= tokens[i];
                score += 1;
            } else {
                if (score > 0 && i < j) {
                    score--;
                    P += tokens[j--];
                    i--;
                }
            }
        }

        return score;
    }

    public static void main(String[] args) {
        TokenGame tokenGame = new TokenGame();
        Assert.assertEquals(0, tokenGame.bagOfTokensScore(new int[]{100, 200}, 50));
        Assert.assertEquals(1, tokenGame.bagOfTokensScore(new int[]{100, 200}, 200));
        Assert.assertEquals(2, tokenGame.bagOfTokensScore(new int[]{100, 200, 300, 400}, 200));
        Assert.assertEquals(1, tokenGame.bagOfTokensScore(new int[]{100, 200, 300, 300}, 200));
    }
}
