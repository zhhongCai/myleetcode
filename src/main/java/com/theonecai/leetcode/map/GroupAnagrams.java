package com.theonecai.leetcode.map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 10.02
 * @Author: theonecai
 * @Date: Create in 7/18/21 10:05
 * @Description:
 */
public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null) {
            return null;
        }

        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = String.valueOf(chars);
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }

        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        GroupAnagrams groupAnagrams = new GroupAnagrams();
        List<List<String>> list = groupAnagrams.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});
        for (List<String> strings : list) {
            System.out.println(strings);
        }
    }
}
