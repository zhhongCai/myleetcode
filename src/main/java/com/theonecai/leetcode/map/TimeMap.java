package com.theonecai.leetcode.map;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * leetcode 981
 * @Author: theeeonecai
 * @Date: Create in 7/11/21 10:21
 * @Description:
 */
public class TimeMap {

    private Map<String, TreeMap<Integer, String>> map;

    /** Initialize your data structure here. */
    public TimeMap() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        if (!map.containsKey(key)) {
            map.put(key, new TreeMap<>());
        }
        map.get(key).put(timestamp, value);
    }

    public String get(String key, int timestamp) {
        TreeMap<Integer, String> treeMap = map.get(key);
        if (treeMap == null) {
            return "";
        }
        Map.Entry<Integer, String> entry = treeMap.floorEntry(timestamp);
        if (entry == null) {
            return "";
        }
        return entry.getValue();
    }

    public static void main(String[] args) {
        TimeMap timeMap = new TimeMap();
        timeMap.set("foo", "bar", 1);
        Assert.assertEquals("bar", timeMap.get("foo", 1));
        Assert.assertEquals("bar", timeMap.get("foo", 3));
        timeMap.set("foo", "bar2", 4);
        Assert.assertEquals("bar2", timeMap.get("foo", 4));
        Assert.assertEquals("bar2", timeMap.get("foo", 5));
    }
}
