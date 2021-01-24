package com.theonecai.leetcode.weekend;

import org.junit.Assert;

import java.util.Arrays;

/**
 * @Author: theonecai
 * @Date: Create in 2021/01/24 10:24
 * @Description:
 */
public class Weekend225 {

    public String maximumTime(String time) {
        StringBuilder t = new StringBuilder();
        for (int i = 0; i < time.length(); i++) {
            if (time.charAt(i) == '?') {
                if (i == 0) {
                    if (time.charAt(1) == '?') {
                        t.append("23");
                        i++;
                    } else {
                        if (time.charAt(1) <= '3') {
                            t.append('2');
                        } else {
                            t.append('1');
                        }
                    }
                } else if (i == 1) {
                    if (t.charAt(0) <= '1') {
                        t.append('9');
                    } else {
                        t.append('3');
                    }
                } else if (i == 3) {
                    t.append('5');
                } else {
                    t.append('9');
                }
            } else {
                t.append(time.charAt(i));
            }
        }
        return t.toString();
    }

    public int minCharacters(String a, String b) {
        if (a.length() > b.length()) {
            return minCharacters(b, a);
        }
        int[] aCount = new int[26];
        int[] bCount = new int[26];
        for (int i = 0; i < b.length(); i++) {
            if (i < a.length()) {
                aCount[a.charAt(i) - 'a']++;
            }
            bCount[b.charAt(i) - 'a']++;
        }
        boolean allTheSame = true;
        for (int i = 0; i < 26; i++) {
            if (aCount[i] != bCount[i]) {
                allTheSame = false;
                break;
            }
        }
        if (allTheSame) {
            return 0;
        }
        int count = Integer.MAX_VALUE;
        count = Math.min(count, lessThan(aCount, bCount));
        count = Math.min(count, lessThan(bCount, aCount));
        count = Math.min(count, countDiff(aCount, bCount, a.length(), b.length()));

        return count;
    }

    private int countDiff(int[] aCount, int[] bCount, int aLen, int bLen) {
        int most = 0;
        for (int i = 0; i < 26; i++) {
            int m = aCount[i] + bCount[i];
            if (most < m) {
                most = m;
            }
        }

        return aLen + bLen - most;
    }

    private int lessThan(int[] aCount, int[] bCount) {
        // a 调小
        int count = Integer.MAX_VALUE;

        int s = 0;
        for (int k = 0; k < 26; k++) {
            int c = 0;
            s += bCount[k];
            for (int i = k == 25 ? 25 : k + 1; i < 26; i++) {
                c += aCount[i];
            }

            count = Math.min(c + s, count);
        }

        return count;
    }


    public static void main(String[] args) {
        Weekend225 weekend225 = new Weekend225();
        weekend225.test();
        weekend225.test2();
        weekend225.test3();
        weekend225.test4();
    }

    private void test4() {
    }

    private void test3() {
//        Assert.assertEquals(4, 1);
    }

    private void test2() {
        Assert.assertEquals(2, this.minCharacters("a",
                "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz"));
        Assert.assertEquals(69, this.minCharacters("jukdyrwxmayusovrggihfiluaewjbixpxybjfsjuyjcdnsxacodbwfdbfyklwfkblnijmhwivo",
                "sdtinjseqrjmmumheuimgmnwfjgwftdldjwpugupnwnltslplgufmynmsovqnculunfycwlxrcregkwkvlwwkhitqyiavabxhu"));
        Assert.assertEquals(1, this.minCharacters("acac", "bd"));
        Assert.assertEquals(3, this.minCharacters("dabadd", "cda"));
        Assert.assertEquals(2, this.minCharacters("aba", "caa"));
    }

    private void test() {
        Assert.assertEquals("23:00", this.maximumTime("2?:00"));
        Assert.assertEquals("09:39", this.maximumTime("0?:3?"));
        Assert.assertEquals("19:22", this.maximumTime("1?:22"));
        Assert.assertEquals("23:59", this.maximumTime("??:??"));
    }
}
