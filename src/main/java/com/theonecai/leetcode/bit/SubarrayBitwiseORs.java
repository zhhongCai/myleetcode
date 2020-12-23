package com.theonecai.leetcode.bit;

import org.junit.Assert;

import java.util.HashSet;
import java.util.Set;

/**
 * leetcode 898
 */
public class SubarrayBitwiseORs {

    public int subarrayBitwiseORs2(int[] nums) {
        Set<Integer> ans = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            int orValue = nums[i];
            ans.add(orValue);
            for (int j = i + 1; j < nums.length; j++) {
                orValue |= nums[j];
                ans.add(orValue);
            }
        }

        return ans.size();
    }

    /**
     * 与的结果只会增加某些位置的1的个数，最多全为1
     * @param nums
     * @return
     */
    public int subarrayBitwiseORs(int[] nums) {
        Set<Integer> ans = new HashSet<>();
        Set<Integer> cur = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            Set<Integer> tmp = new HashSet<>();
            for (Integer orValue : cur) {
                tmp.add(orValue | nums[i]);
            }
            tmp.add(nums[i]);
            cur = tmp;
            ans.addAll(tmp);
        }

        return ans.size();
    }

    public static void main(String[] args) {
        SubarrayBitwiseORs subarrayBitwiseORs = new SubarrayBitwiseORs();
        int[] arr = {1,2,3,4,5,6};
        Assert.assertEquals(subarrayBitwiseORs.subarrayBitwiseORs(arr), subarrayBitwiseORs.subarrayBitwiseORs(arr));
    }
}
