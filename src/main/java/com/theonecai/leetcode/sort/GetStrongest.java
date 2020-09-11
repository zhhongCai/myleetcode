package com.theonecai.leetcode.sort;

import java.util.Arrays;

/**
 * leetcode 1471
 * @Author: theonecai
 * @Date: Create in 2020/9/11 19:32
 * @Description:
 */
public class GetStrongest {

    public int[] getStrongest(int[] arr, int k) {
        int[] result = new int[k];

        Arrays.sort(arr);
        int m = arr[(arr.length - 1) / 2];
        int i = 0;
        int j = arr.length - 1;
        int index = 0;
        while (index < k) {
            int a = Math.abs(arr[i] - m);
            int b = Math.abs(arr[j] - m);
            if (a > b) {
                result[index] = arr[i];
                i++;
            } else {
                result[index] = arr[j];
                j--;
            }
            index++;
        }

        return result;
    }

    public static void main(String[] args) {
        GetStrongest getStrongest = new GetStrongest();
        int[] arr = {1,2,3,4,5};
        System.out.println(Arrays.toString(getStrongest.getStrongest(arr, 2)));
        System.out.println(Arrays.toString(getStrongest.getStrongest(new int[]{1,1,3,5,5}, 5)));
        System.out.println(Arrays.toString(getStrongest.getStrongest(new int[]{6,7,11,7,6,8}, 5)));
        System.out.println(Arrays.toString(getStrongest.getStrongest(new int[]{6,-3,7,2,11}, 3)));
        System.out.println(Arrays.toString(getStrongest.getStrongest(new int[]{-7,22,17,3}, 2)));
    }
}
