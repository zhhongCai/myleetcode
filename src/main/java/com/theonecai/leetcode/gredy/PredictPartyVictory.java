package com.theonecai.leetcode.gredy;

import com.theonecai.leetcode.util.RunUtil;
import org.junit.Assert;

/**
 * leetcode 649
 * @Author: theonecai
 * @Date: Create in 2020/9/18 21:42
 * @Description:
 */
public class PredictPartyVictory {
    private static final String RADIANT = "Radiant";
    private static final String  DIRE = "Dire";

    public String predictPartyVictory(String senate) {
        int radiantCount = 0;
        int direCount = 0;
        int deleteD = 0;
        int deleteR= 0;
        char[] remainStr = senate.toCharArray();
        int index;
        int len = senate.length();
        while (true) {
            index = 0;
            radiantCount = 0;
            direCount = 0;
            for (int i = 0; i < len; i++) {
                if ('R' == remainStr[i]) {
                    if (deleteR > 0) {
                        deleteR--;
                    } else {
                        radiantCount++;
                        remainStr[index++] = 'R';
                        deleteD++;
                    }
                } else {
                    if (deleteD > 0) {
                        deleteD--;
                    } else {
                        direCount++;
                        remainStr[index++] = 'D';
                        deleteR++;
                    }
                }
            }
            if (radiantCount == 0) {
                return DIRE;
            }
            if (direCount == 0) {
                return RADIANT;
            }
            len = index;
        }

    }

    public static void main(String[] args) {
        PredictPartyVictory predictPartyVictory = new PredictPartyVictory();
        Assert.assertEquals(DIRE, predictPartyVictory.predictPartyVictory("DRDRR"));
        Assert.assertEquals(DIRE, predictPartyVictory.predictPartyVictory("RDD"));
        Assert.assertEquals(RADIANT, predictPartyVictory.predictPartyVictory("RD"));
        Assert.assertEquals(RADIANT, predictPartyVictory.predictPartyVictory("RDRRDDRD"));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            if (System.nanoTime() % 2 == 0) {
                sb.append("R");
            } else {
                sb.append("D");
            }
        }
        RunUtil.runAndPrintCostTime(() -> {
            Assert.assertEquals(RADIANT, predictPartyVictory.predictPartyVictory(sb.toString()));
        });
    }

}
