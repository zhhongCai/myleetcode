package com.theonecai.leetcode.gredy;

import org.junit.Assert;

import java.util.Arrays;

/**
 * 1024
 * @Author: thonecai
 * @Date: Create in 2020/10/24 19:45
 * @Description:
 */
public class VideoStitching {

    public int videoStitching(int[][] clips, int T) {
        Arrays.sort(clips, ((o1, o2) -> {
            int res = o1[0] - o2[0];
            if (res == 0) {
                return o2[1] - o1[1];
            }
            return res;
        }));

        int count = 0;
        int[] pre = null;
        for (int i = 0; i < clips.length; i++) {
            int[] current = clips[i];
            if (pre == null) {
                count++;
                pre = current;
                if (pre[0] != 0) {
                     return -1;
                }
                continue;
            }
            if (pre[1] >= T) {
                return count;
            }
            if (current[0] == pre[0]) {
                continue;
            }
            int j = i;
            int[] maxRight = null;
            for (; j < clips.length; j++) {
                int[] n = clips[j];
                if (n[0] > pre[1]) {
                    break;
                }
                if (maxRight == null) {
                    maxRight = n;
                } else {
                    if (maxRight[1] < n[1]) {
                        maxRight = n;
                    }
                }
            }
            if (maxRight == null) {
                return -1;
            }
            i += j - i;
            pre = new int[]{Math.max(maxRight[0], pre[1]), maxRight[1]};
            count++;
        }
        if (pre == null || pre[1] < T) {
            return -1;
        }
        return count;
    }

    public static void main(String[] args) {
        VideoStitching videoStitching = new VideoStitching();
        Assert.assertEquals(3, videoStitching.videoStitching(new int[][]{
                {0,1},{6,8},{0,2},{5,6},{0,4},{0,3},{6,7},{1,3},{4,7},{1,4},{2,5},{2,6},{3,4},{4,5},{5,7},{6,9}
        }, 9));
        Assert.assertEquals(3, videoStitching.videoStitching(new int[][]{
                {0,2},{4,6},{8,10},{1,9},{1,5},{5,9}
        }, 10));
        Assert.assertEquals(3, videoStitching.videoStitching(new int[][]{
                {0,1},{6,8},{0,2},{5,6},{0,4},{0,3},{6,7},{1,3},{4,7},{1,4},{2,5},{2,6},{3,4},{4,5},{5,7},{6,9}
        }, 9));
        Assert.assertEquals(2, videoStitching.videoStitching(new int[][]{
                {0,4},{2,8}
        }, 5));
    }
}
