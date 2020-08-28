package com.theonecai.leetcode.backtrace;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode 89
 * @Author: theonecai
 * @Date: Create in 2020/7/20 21:11
 * @Description:
 */
public class GrayCode {

    public List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<>();
        result.add(0);

        int shift = 1;
        for (int i = 1; i <=n; i++) {
            for (int j = result.size() - 1; j >= 0; j--) {
                result.add(shift + result.get(j));
            }
            shift <<= 1;
        }

        return result;
    }

    public static void main(String[] args) {
        GrayCode grayCode = new GrayCode();
        List<Integer> result;
        for (int i = 0; i < 10; i++) {
            result = grayCode.grayCode(i);
            System.out.println(result);
        }
    }
}
