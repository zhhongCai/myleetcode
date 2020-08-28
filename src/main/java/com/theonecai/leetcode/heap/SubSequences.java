package com.theonecai.leetcode.heap;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode 659
 * @Author: theonecai
 * @Date: Create in 2020/8/17 19:51
 * @Description:
 */
public class SubSequences {

    public boolean isPossible(int[] nums) {
        List<ListInfo> list = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            addToList(list, nums[i]);
        }

        for (ListInfo subList : list) {
            if (subList.getSize() < 3) {
                return false;
            }
        }

        return true;
    }

    private void addToList(List<ListInfo> list, int num) {
        ListInfo subList = null;
        if (list.isEmpty()) {
            subList = new ListInfo(num);
            list.add(subList);
            return;
        }

        for (int i = list.size() - 1; i >= 0; i--) {
            subList = list.get(i);
            if (subList.getEnd() + 1 == num){
                subList.setEnd(num);
                return;
            }
        }

        subList = new ListInfo(num);
        list.add(subList);
    }

    static class ListInfo {
        int start;
        int end;
        int size;

        public void setEnd(int end) {
            this.end = end;
            this.size++;
        }

        public int getEnd() {
            return end;
        }

        public int getSize() {
            return size;
        }

        public ListInfo(int start) {
            this.start = start;
            this.end = start;
            this.size = 1;
        }
    }

    public static void main(String[] args) {
        SubSequences subSequences = new SubSequences();
        int[] nums = {1,2,3,3,4,4,4,5,5,6,7};
        Assert.assertTrue(subSequences.isPossible(nums));
        int[] nums2 = {1,2,3,3,4,4,4,4,5,5,6,7};
        Assert.assertFalse(subSequences.isPossible(nums2));
        int[] nums3 = {1,1,1,2,2,2,3,3,3};
        Assert.assertTrue(subSequences.isPossible(nums3));
        int[] nums4 = {1,1,1,1,1,1,1,1,3};
        Assert.assertFalse(subSequences.isPossible(nums4));
    }
}
