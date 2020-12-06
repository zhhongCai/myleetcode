package com.theonecai.leetcode;

import com.theonecai.leetcode.util.RunUtil;
import org.junit.Assert;

import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: theonecai
 * @Date: Create in 2020/12/06 10:24
 * @Description:
 */
public class Weekend218 {

    public String interpret(String command) {
        StringBuilder sb = new StringBuilder();
        while(true) {
            int idx = 0;
            if (command.startsWith("G")) {
                sb.append("G");
                idx += 1;
            } else if (command.startsWith("()")) {
                sb.append("o");
                idx += 2;
            } else if (command.startsWith("(al)")) {
                sb.append("al");
                idx += 4;
            }
            command = command.substring(idx);
            if (command.equals("")) {
                break;
            }
        }
        return sb.toString();
    }

    public int maxOperations(int[] nums, int k) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            counts.put(nums[i], counts.getOrDefault(nums[i], 0) + 1);
        }

        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (counts.get(nums[i]) <= 0) {
                continue;
            }
            int remain = k - nums[i];
            while (counts.get(nums[i]) > 0 && remain > 0 && counts.containsKey(remain) && counts.get(remain) > 0) {
                if (remain == nums[i] && counts.get(nums[i]) < 2) {
                    break;
                }
                counts.put(nums[i], counts.get(nums[i]) - 1);
                counts.put(remain, counts.get(remain) - 1);
                count++;
            }
        }
        return count;
    }

    public int concatenatedBinary(int n) {
        long result = 1;
        int exp = 1000000007;
        Map<Long, String> map = new HashMap<>();
        for (int i = 2; i <= n; i++) {
            long e = digits(i);
            result = ((result << e) % exp + i) % exp;
        }
        return (int)(result % exp);
    }

    private long digits(int i) {
        int c = 0;
        while (i > 0) {
            i >>= 1;
            c++;
        }
        return c;
    }

    public static void main(String[] args) {
        Weekend218 weekend218 = new Weekend218();
        weekend218.test();
        weekend218.test2();
        weekend218.test3();
        weekend218.test4();
    }

    private void test4() {

    }

    private void test3() {
//        RunUtil.runAndPrintCostTime(() -> {
//            for (int i = 1; i < 400; i++) {
//                this.concatenatedBinary(100000);
//            }
//        });

        Assert.assertEquals(1, this.concatenatedBinary(1));
        Assert.assertEquals(27, this.concatenatedBinary(3));
        Assert.assertEquals(505379714, this.concatenatedBinary(12));
        RunUtil.runAndPrintCostTime(() -> Assert.assertEquals(505379714, this.concatenatedBinary(100000)));
    }

    private void test2() {
        Assert.assertEquals(2, this.maxOperations(new int[]{2,2,2,3,1,1,4,1}, 4));
        Assert.assertEquals(4, this.maxOperations(new int[]{2,5,4,4,1,3,4,4,1,4,4,1,2,1,2,2,3,2,4,2}, 3));
        Assert.assertEquals(2, this.maxOperations(new int[]{4,4,1,3,1,3,2,2,5,5,1,5,2,1,2,3,5,4}, 2));
        Assert.assertEquals(2, this.maxOperations(new int[]{1,2,3,4}, 5));
        Assert.assertEquals(1, this.maxOperations(new int[]{3,1,3,4,3}, 6));
    }

    private void test() {
        System.out.println(this.interpret("G()(al)"));
        System.out.println(this.interpret("G()()()()(al)"));
    }
}
