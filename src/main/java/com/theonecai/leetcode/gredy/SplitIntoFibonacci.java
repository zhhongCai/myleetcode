package com.theonecai.leetcode.gredy;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode 842
 * @Author: theonecai
 * @Date: Create in 2020/9/17 19:47
 * @Description:
 */
public class SplitIntoFibonacci {

    private boolean found;
    private List<Integer> list;

    public List<Integer> splitIntoFibonacci(String S) {
        List<Integer> result = new ArrayList<>();
        if (S == null || S.length() < 3) {
            return result;
        }
        found = false;
        list = new ArrayList<>();
        splitIntoFibonacci(S, 0, result);
        return list;
    }

    private void splitIntoFibonacci(String str, int start, List<Integer> result) {
        if (found) {
            return;
        }
        if (start >= str.length()) {
            found = true;
            list.addAll(result);
            return;
        }

        if (result.size() >= 2) {
            int expectInt = result.get(result.size() - 1) + result.get(result.size() - 2);
            String expectStr = String.valueOf(expectInt);

            if (start + expectStr.length() <= str.length() && stringEq(str, start, expectStr)) {
                result.add(expectInt);
                splitIntoFibonacci(str, start + expectStr.length(), result);
                result.remove(result.size() - 1);
            }
            return;
        }

        String subStr;
        long num;
        for (int i = 1; i <= 10 && start + i < str.length(); i++) {
            if (str.charAt(start) == '0' && i > 1) {
                break;
            }
            if (i > (str.length() - (start + i))) {
                break;
            }
            subStr = str.substring(start, start + i);
            num = Long.parseLong(subStr);
            if (num > Integer.MAX_VALUE) {
                break;
            }
            result.add((int)num);
            splitIntoFibonacci(str, start + subStr.length(), result);
            result.remove(result.size() - 1);
        }
    }

    private boolean stringEq(String str, int start, String expectStr) {
        for (int i = 0; i < expectStr.length(); i++) {
            if (str.charAt(start + i) != expectStr.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        SplitIntoFibonacci splitIntoFibonacci = new SplitIntoFibonacci();
        List<Integer> list = splitIntoFibonacci.splitIntoFibonacci("123456579");
        System.out.println(list);
        Assert.assertFalse(list.isEmpty());

        list = splitIntoFibonacci.splitIntoFibonacci("11235813");
        System.out.println(list);
        Assert.assertFalse(list.isEmpty());

        list = splitIntoFibonacci.splitIntoFibonacci("112358130");
        System.out.println(list);
        Assert.assertTrue(list.isEmpty());

        list = splitIntoFibonacci.splitIntoFibonacci("0123");
        System.out.println(list);
        Assert.assertTrue(list.isEmpty());

        list = splitIntoFibonacci.splitIntoFibonacci("1101111");
        System.out.println(list);
        Assert.assertFalse(list.isEmpty());
    }
}
