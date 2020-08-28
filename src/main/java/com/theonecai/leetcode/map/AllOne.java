package com.theonecai.leetcode.map;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode 432
 * @Author: theonecai
 * @Date: Create in 2020/6/16 20:09
 * @Description:
 */
public class AllOne {
    private String maxKey;
    private String minKey;
    private Map<String, Integer> map;

    /** Initialize your data structure here. */
    public AllOne() {
        this.maxKey = null;
        this.minKey = null;
        this.map = new HashMap<>();
    }

    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        Integer keyCount = map.get(key);
        if (keyCount == null) {
            if (maxKey == null) {
                maxKey = key;
            }
            map.put(key, 1);
            minKey = key;
        } else {
            keyCount++;
            if (!maxKey.equals(key)) {
                Integer maxKeyCount = map.get(maxKey);
                if (maxKeyCount < keyCount) {
                    maxKey = key;
                }
            }
            map.put(key, keyCount);
            if (minKey.equals(key)) {
                updateMinKey();
            }
        }
    }

    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        Integer keyCount = map.get(key);
        if (keyCount == null) {
            return;
        } else if (keyCount == 1) {
            map.remove(key);
            if (map.isEmpty()) {
                maxKey = null;
                minKey = null;
            } else {
                if (key.equals(maxKey)) {
                    updateMaxKey();
                }
                if (key.equals(minKey)) {
                    updateMinKey();
                }
            }
        } else {
            keyCount--;
            map.put(key, keyCount);
            if (!minKey.equals(key)) {
                Integer minKeyCount = map.get(minKey);
                if (minKeyCount > keyCount) {
                    minKey = key;
                }
            }
            if (key.equals(maxKey)) {
                updateMaxKey();
            }
        }
    }

    private void updateMinKey() {
        int min = Integer.MAX_VALUE;
        String mKey = null;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (min > entry.getValue()) {
                min = entry.getValue();
                mKey = entry.getKey();
            }
        }
        minKey = mKey;
    }

    private void updateMaxKey() {
        int max = Integer.MIN_VALUE;
        String mKey = null;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (max < entry.getValue()) {
                max = entry.getValue();
                mKey = entry.getKey();
            }
        }
        maxKey = mKey;
    }

    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        return maxKey == null ? "" : maxKey;
    }

    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        return minKey == null ? "" : minKey;
    }

    public static void main(String[] args) {
        AllOne allOne = new AllOne();
        System.out.println("maxKey=" + allOne.maxKey + ", minKey=" + allOne.getMinKey());
        allOne.inc("a");
        System.out.println("maxKey=" + allOne.maxKey + ", minKey=" + allOne.getMinKey());
        allOne.inc("b");
        System.out.println("maxKey=" + allOne.maxKey + ", minKey=" + allOne.getMinKey());
        allOne.inc("c");
        System.out.println("maxKey=" + allOne.maxKey + ", minKey=" + allOne.getMinKey());
        allOne.inc("d");
        System.out.println("maxKey=" + allOne.maxKey + ", minKey=" + allOne.getMinKey());
        allOne.inc("a");
        System.out.println("maxKey=" + allOne.maxKey + ", minKey=" + allOne.getMinKey());
        allOne.inc("b");
        System.out.println("maxKey=" + allOne.maxKey + ", minKey=" + allOne.getMinKey());
        allOne.inc("c");
        System.out.println("maxKey=" + allOne.maxKey + ", minKey=" + allOne.getMinKey());
        allOne.inc("d");
        System.out.println("maxKey=" + allOne.maxKey + ", minKey=" + allOne.getMinKey());
        allOne.inc("c");
        allOne.inc("d");
        allOne.inc("d");
        allOne.inc("a");
        System.out.println("maxKey=" + allOne.maxKey + ", minKey=" + allOne.getMinKey());

    }
}

/**
 * ["AllOne","inc","inc","inc","inc","inc","inc","inc","inc","inc","inc","inc","inc","getMinKey"]
 * [[],["a"],["b"],["c"],["d"],["a"],["b"],["c"],["d"],["c"],["d"],["d"],["a"],[]]
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */