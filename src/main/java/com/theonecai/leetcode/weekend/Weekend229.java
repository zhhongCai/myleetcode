package com.theonecai.leetcode.weekend;


import org.junit.Assert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: theonecai
 * @Date: Create in 2021/02/21 10:24
 * @Description:
 */
public class Weekend229 {
    public int[] minOperations(String boxes) {
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

    public String mergeAlternately(String word1, String word2) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int j = 0;
        while (i < word1.length()) {
            sb.append(word1.charAt(i));
            if (j < word2.length()) {
                sb.append(word2.charAt(j));
            }
            i++;
            j++;
        }
        while (j < word2.length()) {
            sb.append(word2.charAt(j++));
        }
        return sb.toString();
    }

    public int maximumScore(int[] nums, int[] multipliers) {
        int m = multipliers.length;
        int n = nums.length;
        //dp[left][right]
        int[][] dp = new int[m + 1][m + 1];
        for (int i = 1; i <= m; i++) {
            dp[0][i] = dp[0][i - 1] + multipliers[i - 1] * nums[n - i];
            dp[i][0] = dp[i - 1][0] + multipliers[i - 1] * nums[i - 1];
        }

        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; i + j <= m; j++) {
                int multiplier =  multipliers[i + j - 1];
                dp[i][j] = Math.max(dp[i - 1][j] + multiplier * nums[i - 1], dp[i][j - 1] + multiplier * nums[n - j]);
                if (i + j == m) {
                    max = Math.max(max, dp[i][m - i]);
                }
            }
        }
        return max;
    }

    private Map<String, Integer> memo;
    public int maximumScore2(int[] nums, int[] multipliers) {
        memo = new HashMap<>();
        return dfs(nums, multipliers, 0, nums.length - 1, 0);
    }


    private int dfs(int[] nums, int[] multipliers, int left, int right, int step) {
        String key = left + "-" + right;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        if (step >= multipliers.length) {
            return 0;
        }

        int score = dfs(nums, multipliers, left + 1, right, step + 1) + nums[left] * multipliers[step];
        int score2 = dfs(nums, multipliers, left, right - 1, step + 1) + nums[right] * multipliers[step];

        memo.put(key, Math.max(score, score2));
        return memo.get(key);
    }

    public static void main(String[] args) {
        Weekend229 weekend = new Weekend229();
        weekend.test();
        weekend.test2();
        weekend.test3();
        weekend.test4();
    }

    private void test4() {
    }

    /**
     *
     *
     [555,526,732,182,43,-537,-434,-233,-947,968,-250,-10,470,-867,-809,-987,120,607,-700,25,-349,-657,349,-75,-936,-473,615,691,-261,-517,-867,527,782,939,-465,12,988,-78,-990,504,-358,491,805,756,-218,513,-928,579,678,10]
     [783,911,820,37,466,-251,286,-74,-899,586,792,-643,-969,-267,121,-656,381,871,762,-355,721,753,-521]

[-947,897,328,-467,14,-918,-858,-701,-518,-997,22,259,-4,968,947,582,-449,895,-121,-403,633,490,64,543,-396,-997,841,-398,247,297,-147,-708,804,-199,-765,-547,-599,406,-223,-11,663,746,-365,-859,256,-25,919,-334,490,-511,865,-139,-968,961,-793,451,317,645,-294,240,-312,-822,961,-572,309,579,161,780,525,-622,-511,423,946,-28,-199,822,-123,-316,-913,113,-458,-428,-414,49,922,722,-854,323,-219,581,302,124,164,31,727,186,308,-936,-937,-862,939,213,966,-74,-76,-1,521,777,-966,454,-199,526,-895,447,-749,-518,-639,849,-771,979,-760,-763,-601,-201,40,-911,207,890,-942,-352,700,267,830,-396,536,877,-896,-687,262,-60,-373,-373,526]
[864,849,586,769,309,-413,318,652,883,-690,796,251,-648,433,1000,-969,422,194,-785,-242,-118,69,187,-925,-418,-556,88,-399,-619,-383,-188,206,-793,-9,738,-587,878,360,640,318,-399,-366,365,-291,-75,-451,-674,-199,177,862,1,11,390,-52,-101,127,-354,-717,-717,180,655,817,-898,28,-641,-35,-563,-737,283,-223,-322,-59,955,172,230,512,-205,-180,899,169,-663,-253,270,651,168,417,613,-443,980,-189,417,372,-891,-272,993,-877,906,680,-630,-328,-873,-811,78,-667,-2,190,-773,878,529,620,-951,-687,314,-989,-48,-601,-950,31,-789,-663,-480,750,-872,-358,529,-426,-111,517,750,-536,-673,370]
     */
    private void test3() {
        Assert.assertEquals(6861161, this.maximumScore2(
                new int[]{555,526,732,182,43,-537,-434,-233,-947,968,-250,-10,470,-867,-809,-987,120,607,-700,25,-349,-657,349,-75,-936,-473,615,691,-261,-517,-867,527,782,939,-465,12,988,-78,-990,504,-358,491,805,756,-218,513,-928,579,678,10},
                new int[]{783,911,820,37,466,-251,286,-74,-899,586,792,-643,-969,-267,121,-656,381,871,762,-355,721,753,-521}));
        Assert.assertEquals(102, this.maximumScore(new int[]{-5,-3,-3,-2,7,1}, new int[]{-10,-5,3,4,6}));
        Assert.assertEquals(14, this.maximumScore(new int[]{1,2,3}, new int[]{3,2,1}));
    }

    private void test2() {
        Assert.assertEquals("acbde", this.mergeAlternately("ab", "cde"));
        Assert.assertEquals("acbd", this.mergeAlternately("ab", "cd"));
        Assert.assertEquals("acbdc", this.mergeAlternately("abc", "cd"));
    }

    private void test() {
        Assert.assertEquals("[1, 1, 3]", Arrays.toString(this.minOperations("110")));
        Assert.assertEquals("[11,8,5,4,3,4]", Arrays.toString(this.minOperations("001011")).replaceAll(" ", ""));
    }
}
