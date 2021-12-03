package com.theonecai.leetcode.string;

/**
 * leetcode 383
 */
public class CanConstruct {

    public boolean canConstruct(String ransomNote, String magazine) {
        int n = ransomNote.length();
        int m = magazine.length();
        if (m < n) {
            return false;
        }
        int[] count = new int[26];
        int[] count2 = new int[26];
        for (int i = 0; i < n; i++) {
            count[ransomNote.charAt(i) - 'a']++;
        }
        for (int i = 0; i < n; i++) {
            count2[magazine.charAt(i) - 'a']++;
        }
        for (int i = 0; i < count.length; i++) {
            if (count[i] > count2[i]) {
                return false;
            }
        }

        return true;
    }
}
