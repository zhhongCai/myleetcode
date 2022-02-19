package com.theonecai.leetcode.string;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode 1078
 */
public class FindOcurrences {
    public String[] findOcurrences(String text, String first, String second) {
        String[] words = text.split(" ");
        int n = words.length;
        if (n <= 2) {
            return new String[]{};
        }
        List<String> list = new ArrayList<>();
        for (int i = 2; i < n; i++) {
            if (words[i - 2].equals(first) && words[i - 1].equals(second)) {
                list.add(words[i]);
            }
        }

        String[] ans = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }
}
