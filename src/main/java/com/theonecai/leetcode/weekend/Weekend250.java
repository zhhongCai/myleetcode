package com.theonecai.leetcode.weekend;


import com.theonecai.algorithms.BinaryDictTree;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: theonecai
 * @Date: Create in 2021/07/18 10:24
 * @Description:
 */
public class Weekend250 {
    public int canBeTypedWords(String text, String brokenLetters) {
        int res = 0;
        String[] words = text.split(" ");
        if (brokenLetters == null || brokenLetters.length() == 0) {
            return words.length;
        }
        for (String word : words) {
            boolean found = false;
            for (int i = 0; i < brokenLetters.length(); i++) {
                if (word.contains(String.valueOf(brokenLetters.charAt(i)))) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                res++;
            }
        }

        return res;
    }

    public int addRungs(int[] rungs, int dist) {
        int res = 0;
        int cur = 0;
        for (int i = 0; i < rungs.length; i++) {
            if (rungs[i] - cur > dist) {
                res += (rungs[i] - cur - 1) / dist;
            }
            cur = rungs[i];
        }

        return res;
    }

    public int[] maxGeneticDifference(int[] parents, int[][] queries) {
        if (queries == null || queries.length == 0) {
            return null;
        }
        Map<Integer, List<Integer>> queryMap = new HashMap<>();
        for (int i = 0; i < queries.length; i++) {
            List<Integer> list = queryMap.getOrDefault(queries[i][0], new ArrayList<>());
            list.add(i);
            queryMap.put(queries[i][0], list);
        }
        List<List<Integer>> tree = new ArrayList<>(parents.length);
        int root = -1;
        for (int i = 0; i < parents.length; i++) {
            if (parents[i] == -1) {
                root = i;
            }
            tree.add(new ArrayList<>());
        }
        for (int i = 0; i < parents.length; i++) {
            if (parents[i] == -1) {
                continue;
            }
            tree.get(parents[i]).add(i);
        }

        int[] res = new int[queries.length];
        BinaryDictTree dictTree = new BinaryDictTree(21);
        dfs(dictTree, root, tree, queries, queryMap, res);

        return res;
    }

    private void dfs(BinaryDictTree dictTree, int index, List<List<Integer>> tree, int[][] queries,
                     Map<Integer, List<Integer>> queryMap, int[] res) {
        dictTree.insert(index);
        if (queryMap.containsKey(index)) {
            for (Integer idx : queryMap.get(index)) {
                res[idx] = dictTree.calcMaxXorValueFor(queries[idx][1]);
            }
        }
        if (tree.get(index).size() > 0) {
            for (Integer idx : tree.get(index)) {
                dfs(dictTree, idx, tree, queries, queryMap, res);
            }
        }

        dictTree.delete(index);
    }


    public static void main(String[] args) {
        Weekend250 weekend = new Weekend250();
        weekend.test();
        weekend.test2();
        weekend.test3();
        weekend.test4();
    }

    private void test4() {
    }

    private void test3() {
        int[] rs = this.maxGeneticDifference(new int[]{-1,0,1,1}, new int[][]{
                {0,2},{3,2},{2,5}
        });
        System.out.println(Arrays.toString(rs));
        Assert.assertArrayEquals(new int[]{2,3,7}, rs);
    }

    private void test2() {
        Assert.assertEquals(4999996, this.addRungs(new int[]{1,3,5,10,10000000}, 2));
        Assert.assertEquals(2, this.addRungs(new int[]{1,3,5,10}, 2));
        Assert.assertEquals(0, this.addRungs(new int[]{3,6,8,10}, 3));
        Assert.assertEquals(1, this.addRungs(new int[]{3,4,6,7}, 2));
        Assert.assertEquals(0, this.addRungs(new int[]{7}, 10));
    }

    private void test() {
        Assert.assertEquals(1, this.canBeTypedWords("hello world", "ad"));
    }
}
