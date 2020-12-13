package com.theonecai.leetcode.list;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode 118
 * @Author: theonecai
 * @Date: Create in 2020/12/6 17:17
 * @Description:
 */
public class Generate {

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>(numRows);
        if (numRows < 1) {
            return result;
        }
        List<Integer> line = new ArrayList<>(1);
        line.add(1);
        result.add(line);
        if (numRows > 1) {
            line = new ArrayList<>(2);
            line.add(1);
            line.add(1);
            result.add(line);
        }

        for (int i = 3; i <= numRows; i++) {
            List<Integer> list = new ArrayList<>(i);
            list.add(1);
            for (int j = 0; j < line.size() - 1; j++) {
                list.add(line.get(j) + line.get(j + 1));
            }
            list.add(1);
            result.add(list);
            line = list;
        }
        return result;
    }

    public static void main(String[] args) {
        Generate g = new Generate();
        List<List<Integer>> list = g.generate(0);
        for (List<Integer> line : list) {
            System.out.println(line);
        }
    }
}
