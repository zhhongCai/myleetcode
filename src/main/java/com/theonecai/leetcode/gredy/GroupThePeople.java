package com.theonecai.leetcode.gredy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * leetcode 1282
 * @Author: theonecai
 * @Date: Create in 2020/10/17 09:52
 * @Description:
 */
public class GroupThePeople {
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        Set<Integer>[] sets = new HashSet[500];
        for (int i = 0; i < groupSizes.length; i++) {
            if (sets[groupSizes[i]] == null) {
                sets[groupSizes[i]] = new HashSet<>();
            }
            sets[groupSizes[i]].add(i);
        }
        List<List<Integer>> result = new ArrayList<>();
        for (int i = sets.length - 1; i > 0; i--) {
            if (sets[i] == null) {
                continue;
            }
            Set<Integer> set = sets[i];
            int count = 0;
            List<Integer> list = null;
            for (Integer g : set) {
                if (count % i == 0) {
                    list = new ArrayList<>();
                    result.add(list);
                }
                list.add(g);
                count++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        GroupThePeople groupThePeople = new GroupThePeople();
        List<List<Integer>> list = groupThePeople.groupThePeople(new int[]{3,3,3,3,3,1,3});
        for (List<Integer> group : list) {
            System.out.println(group);
        }
        list = groupThePeople.groupThePeople(new int[]{2,1,3,3,3,2});
        for (List<Integer> group : list) {
            System.out.println(group);
        }
    }
}
