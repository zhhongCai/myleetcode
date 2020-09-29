package com.theonecai.leetcode.gredy;

import java.util.Arrays;

import org.junit.Assert;

/**
 *
 * @Author: theonecai
 * @Date: Create in 2020/9/29 20:22
 * @Description:
 */
public class TokenGame {

    public int tokenGame(int[] token, int p) {
        int score = 0;
        Arrays.sort(token);

        int j = token.length - 1;
        for (int i = 0; i <= j; i++) {
            if (token[i] <= p) {
                p -= token[i];
                score += 1;
            } else {
                if (score > 0 && i < j) {
                    score--;
                    p += token[j--];
                    i--;
                }
            }    
        }

        return score;
    }

    public static void main(String[] args) {
        TokenGame tokenGame = new TokenGame();
        Assert.assertEquals(0, tokenGame.tokenGame(new int[]{100, 200}, 50));
        Assert.assertEquals(1, tokenGame.tokenGame(new int[]{100, 200}, 200));
        Assert.assertEquals(2, tokenGame.tokenGame(new int[]{100, 200, 300, 400}, 200));
        Assert.assertEquals(1, tokenGame.tokenGame(new int[]{100, 200, 300, 300}, 200));
    }
}
