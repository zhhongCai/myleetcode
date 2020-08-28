package com.theonecai.leetcode.backtrace;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode 60
 * @Author: theonecai
 * @Date: Create in 2020/7/4 16:55
 * @Description:
 */
public class GetPermutation {

    private int[] ncount = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880};

    public String getPermutation(int n, int k) {
        if (n == 0 || k == 0) {
            return "";
        }
        return permute(n, k);
    }

    public String permute(int n, int k) {
        List<Integer> nums = new ArrayList<>(n);
        for (int i = 1; i <= n; i++) {
            nums.add(i);
        }

        int kth = k - 1;

        StringBuilder sb = new StringBuilder();
        for (int i = n - 1; i > -1; i--) {
            int idx = kth / ncount[i];
            kth -= idx * ncount[i];

            sb.append(nums.get(idx));
            nums.remove(idx);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        GetPermutation p = new GetPermutation();
        long a = System.currentTimeMillis();
        System.out.println(p.getPermutation(10,1));
        System.out.println("cost: " + (System.currentTimeMillis() - a));
    }
}
