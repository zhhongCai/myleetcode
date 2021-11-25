package com.theonecai.leetcode.string;

import org.junit.Assert;

/**
 * leetcode 423
 */
public class OriginalDigits {

    private static String[] digits = new String[]{"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

    public String originalDigits(String s) {
        int[] count = new int[26];
        int n = s.length();
        for (int i = 0; i < n; i++) {
            count[s.charAt(i) - 'a']++;
        }
        int[] dcount = new int[10];
        int[] order = new int[]{0, 2, 6, 7, 8, 3, 4, 5, 9, 1};
        for (int i : order) {
            while (contains(count, i)) {
                dcount[i]++;
                update(count, i);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dcount.length; i++) {
            int dc = dcount[i];
            while (dc-- > 0) {
                sb.append(i);
            }
        }

        return sb.toString();
    }

    private void update(int[] count, int i) {
        String d = digits[i];
        for (int j = 0; j < d.length(); j++) {
            count[d.charAt(j) - 'a']--;
        }
    }

    private boolean contains(int[] count, int i) {
        String d = digits[i];
        for (int j = 0; j < d.length(); j++) {
            if (count[d.charAt(j) - 'a'] == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        OriginalDigits originalDigits = new OriginalDigits();

        Assert.assertEquals("0123456789", originalDigits.originalDigits("zoetnwhrfofisixseveinineghtenveureeotero"));
        Assert.assertEquals("012", originalDigits.originalDigits("owoztneoer"));
        Assert.assertEquals("45", originalDigits.originalDigits("fviefuro"));
    }
}
