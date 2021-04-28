package com.theonecai.leetcode.array;

import org.junit.Assert;

/**
 * leetcode 27
 */
public class RemoveElement {

    public int removeElement(int[] nums, int val) {
        int index = -1;
        for (int i = 0; i < nums.length; i++) {
            if (val == nums[i]) {
                continue;
            }
            if (i != index + 1) {
                nums[++index] = nums[i];
            }  else {
                ++index;
            }
        }

        return index + 1;
    }

    public static void main(String[] args) {
        RemoveElement removeElement = new RemoveElement();
        Assert.assertEquals(0, removeElement.removeElement(new int[]{}, 2));
        Assert.assertEquals(3, removeElement.removeElement(new int[]{1,2,3,4,2}, 2));
        Assert.assertEquals(2, removeElement.removeElement(new int[]{2,2,3,4,2}, 2));
        Assert.assertEquals(0, removeElement.removeElement(new int[]{2,2,2,2,2}, 2));
    }
}
