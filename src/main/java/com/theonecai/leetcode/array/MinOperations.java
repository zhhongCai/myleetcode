package com.theonecai.leetcode.array;

import org.junit.Assert;

import java.util.Arrays;

/**
 * leetcode 5686
 * @Author: theonecai
 * @Date: Create in 2021/2/21 14:15
 * @Description:
 */
public class MinOperations {

    public int[] minOperations(String boxes) {
        int[] answers = new int[boxes.length()];
        int left = boxes.charAt(0) - '0';
        int right = 0;
        int answer = 0;
        for (int i = 1; i < boxes.length(); i++) {
            if (boxes.charAt(i) == '1') {
                right++;
                answer += i;
            }
        }
        answers[0] = answer;

        for (int i = 1; i < boxes.length(); i++) {
            answers[i] = answers[i - 1] + left - right;
            if (boxes.charAt(i) == '1') {
                left++;
                right--;
            }
        }
        return answers;
    }

    public int[] minOperations2(String boxes) {
        int[] answers = new int[boxes.length()];
        int[] preSum = new int[boxes.length()];
        answers[0] = countStep(boxes, 0);
        preSum[0] = getValue(boxes, 0);

        for (int i = 1; i < boxes.length(); i++) {
            preSum[i] = preSum[i - 1] + getValue(boxes, i);
        }

        for (int i = 1; i < boxes.length(); i++) {
            answers[i] = answers[i - 1] + preSum[i - 1] - (preSum[boxes.length() - 1] - preSum[i - 1]);
        }
        return answers;
    }
    private int getValue(String boxes, int index) {
        return boxes.charAt(index) - '0';
    }

    public int[] minOperations3(String boxes) {
        int[] answers = new int[boxes.length()];
        for (int i = 0; i < boxes.length(); i++) {
            answers[i] = countStep(boxes, i);
        }
        return answers;
    }

    private int countStep(String boxes, int cur) {
        int count = 0;
        for (int i = 0; i < boxes.length(); i++) {
            if (boxes.charAt(i) == '1') {
                count += Math.abs(cur - i);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        MinOperations minOperations = new MinOperations();
        Assert.assertEquals("[1, 1, 3]", Arrays.toString(minOperations.minOperations("110")));
        Assert.assertEquals("[11,8,5,4,3,4]", Arrays.toString(minOperations.minOperations("001011")).replaceAll(" ", ""));
    }
}
