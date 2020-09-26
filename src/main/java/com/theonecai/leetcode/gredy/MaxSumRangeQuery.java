package com.theonecai.leetcode.gredy;

import org.junit.Assert;

import java.util.Arrays;

/**
 * leetcode 1589
 * @Author: theonecai
 * @Date: Create in 2020/9/25 20:28
 * @Description:
 */
public class MaxSumRangeQuery {

    public int maxSumRangeQuery(int[] nums, int[][] requests) {

        // 差分数组
        int[] count = new int[nums.length + 1];
        for (int[] request : requests) {
            count[request[0]] += 1;
            count[request[1] + 1] -= 1;
        }
        for (int i = 1; i < count.length; i++) {
            count[i] = count[i] + count[i - 1];
        }

        Arrays.sort(nums);
        Arrays.sort(count);

        long sum = 0;
        int j = nums.length - 1;
        for (int i = count.length - 1; i >= 0; i--) {
            if (count[i] == 0) {
                break;
            }
            sum += count[i] * nums[j--];
        }

        return (int) (sum % (1000000007L));
    }

    public static void main(String[] args) {
        MaxSumRangeQuery maxSumRangeQuery = new MaxSumRangeQuery();
        Assert.assertEquals(19, maxSumRangeQuery.maxSumRangeQuery(new int[]{1,2,3,4,5}, new int[][]{{1,3}, {0,1}}));
        Assert.assertEquals(49513445, maxSumRangeQuery.maxSumRangeQuery(new int[]{
                13520,52415,58126,75538,3650,15225,54077,28916,50722,36534,91738,24798,12991,33954,2508,78754,9125,42624,87893,28069,31208,14026,19087,72347,39969,3765,36576,89405,24697,60827,41593,76438,14560,57967,10969,73923,90286,61342,7834,35885,30065,81837,69696,9883,58549,47537,57083,18006,80347,20416,48896,52949,60369,1602,92770,31258

        }, new int[][]{
                {44,44},{7,47},{26,36},{12,26},{49,49},{38,49},{1,6},{33,54},{25,38},{11,32},{13,39},{49,54},{49,50},{33,36},{38,41},{17,45},{16,29},{41,54},{9,40},{39,42},{55,55},{14,51},{29,29},{33,34},{44,51},{53,54},{24,48},{9,19},{26,29},{9,39},{36,52},{16,20},{6,27},{20,42},{13,37},{46,48},{12,40},{33,49},{9,45},{12,26},{19,25},{18,33},{6,37},{50,51},{43,47},{32,42},{40,54},{11,50},{17,48},{1,37},{53,55},{10,36},{45,52},{48,55},{25,39},{25,43}
        }));

    }
}
