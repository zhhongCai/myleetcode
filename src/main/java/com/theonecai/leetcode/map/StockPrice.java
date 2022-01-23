package com.theonecai.leetcode.map;

/**
 * 2034
 */

import java.util.HashMap;
import java.util.TreeMap;

public class StockPrice {
    private int maxTimestamp;
    private HashMap<Integer, Integer> timestampPriceMap;
    private TreeMap<Integer, Integer> priceCountMap;
    public StockPrice() {
        maxTimestamp = 0;
        timestampPriceMap = new HashMap<>();
        priceCountMap = new TreeMap<>();
    }

    public void update(int timestamp, int price) {
        this.maxTimestamp = Math.max(maxTimestamp, timestamp);
        Integer p = timestampPriceMap.get(timestamp);
        if (p != null) {
            int c = priceCountMap.get(p);
            if (c == 1) {
                priceCountMap.remove(p);
            } else {
                priceCountMap.put(p, c - 1);
            }
        }

        priceCountMap.put(price, priceCountMap.getOrDefault(price, 0) + 1);
        timestampPriceMap.put(timestamp, price);

    }

    public int current() {
        return timestampPriceMap.get(maxTimestamp);
    }

    public int maximum() {
        return priceCountMap.lastKey();
    }

    public int minimum() {
        return priceCountMap.firstKey();
    }
}
