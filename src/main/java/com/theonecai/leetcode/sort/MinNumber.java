package com.theonecai.leetcode.sort;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode offer 45
 * @Author: theonecai
 * @Date: Create in 2021/3/20 20:51
 * @Description:
 */
public class MinNumber {

    public String minNumber(int[] nums) {
        List<String> list = new ArrayList<>(nums.length);
        for (int num : nums) {
            list.add(String.valueOf(num));
        }

        list.sort((str, str2) -> {
            int len = str.length() + str2.length();
            for (int i = 0, j = 0; i < len; i++,j++) {
                int ii = i % str.length();
                int jj = j % str2.length();
                if (str.charAt(ii) != str2.charAt(jj)) {
                    return str.charAt(ii) - str2.charAt(jj);
                }
            }
            return 0;
        });
        StringBuilder sb = new StringBuilder();
        list.forEach(sb::append);

        return sb.toString();
    }

    public static void main(String[] args) {
        MinNumber minNumber = new MinNumber();
        //121212 121121
        Assert.assertEquals("111", minNumber.minNumber(new int[]{1,1,1}));
        Assert.assertEquals("12112", minNumber.minNumber(new int[]{12,121}));
        Assert.assertEquals("013045", minNumber.minNumber(new int[]{0,30,4,5,1}));
        Assert.assertEquals("30345", minNumber.minNumber(new int[]{3,30,4,5}));
        Assert.assertEquals("32132134545", minNumber.minNumber(new int[]{321,321345,4,5}));
        Assert.assertEquals("32112332145", minNumber.minNumber(new int[]{321,321123,4,5}));
        Assert.assertEquals("32132133345", minNumber.minNumber(new int[]{321,321333,4,5}));
    }

}
