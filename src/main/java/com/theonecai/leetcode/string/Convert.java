package com.theonecai.leetcode.string;

import org.junit.Assert;

/**
 * leetcode 6
 *
 * @Author: theonecai
 * @Date: Create in 6/6/21 19:45
 * @Description:
 */
public class Convert {
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        StringBuilder[] rowStr = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rowStr[i] = new StringBuilder("");
        }

        int row = 0;
        int i = 0;
        boolean down = true;
        while (i < s.length()) {
            rowStr[row].append(s.charAt(i++));
            if (i > 1 && (row == 0 || row == numRows - 1)) {
                down = !down;
            }
            row += (down ? 1 : -1);
        }
        for (int j = 1; j < numRows; j++) {
            rowStr[0].append(rowStr[j]);
        }
        return rowStr[0].toString();
    }

    public static void main(String[] args) {
        Convert convert = new Convert();
        Assert.assertEquals("AB", convert.convert("AB", 1));
        Assert.assertEquals("PAHNAPLSIIGYIR", convert.convert("PAYPALISHIRING", 3));
        Assert.assertEquals("PINALSIGYAHRPI", convert.convert("PAYPALISHIRING", 4));
        Assert.assertEquals("A", convert.convert("A", 1));
    }
}
