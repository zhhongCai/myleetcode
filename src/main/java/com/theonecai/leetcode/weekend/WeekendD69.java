package com.theonecai.leetcode.weekend;

import com.theonecai.leetcode.list.ListNode;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class WeekendD69 {

    public String capitalizeTitle(String title) {
        String[] ts = title.split(" ");
        StringBuilder sb = new StringBuilder();

        for (String t : ts) {
            sb.append(t.length() > 2 ? Character.toUpperCase(t.charAt(0)) : Character.toLowerCase(t.charAt(0)));

            sb.append(t.substring(1).toLowerCase());

            sb.append(" ");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public int pairSum(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode node = head;
        while (node != null) {
            list.add(node.val);
            node = node.next;
        }

        int ans = 0;
        int n = list.size();
        for (int i = 0; i < n / 2; i++) {
            ans = Math.max(ans, list.get(i) + list.get(n - 1 - i));
        }

        return ans;
    }

    public int longestPalindrome(String[] words) {
        int ans = 0;
        Map<String, Integer> count = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            String rv = new StringBuilder(word).reverse().toString();
            if (count.containsKey(rv)) {
                ans += word.length() * 2;
                int c = count.get(rv);
                if (c == 1) {
                    count.remove(rv);
                } else {
                    count.put(rv, c - 1);
                }
                continue;
            }
            count.put(word, count.getOrDefault(word, 0) + 1);
        }
        for (String s : count.keySet()) {
            if (s.charAt(0) == s.charAt(1)) {
                ans += 2;
                break;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        WeekendD69 weekend = new WeekendD69();
        weekend.test();
        weekend.test2();
        weekend.test3();
        weekend.test4();
    }

    private void test4() {

    }

    private void test3() {
        //fqfofoooofofqf
        Assert.assertEquals(14, longestPalindrome(new String[]{"qo","fo","fq","qf","fo","ff","qq","qf","of","of","oo","of","of","qf","qf","of"}));
        Assert.assertEquals(0, longestPalindrome(new String[]{"lc"}));
        Assert.assertEquals(6, longestPalindrome(new String[]{"cc","ll","xx","xx"}));
        Assert.assertEquals(6, longestPalindrome(new String[]{"cc","ll","xx","xx","xx"}));
        Assert.assertEquals(10, longestPalindrome(new String[]{"cc","ll","xx","xx","ll", "ll"}));
        Assert.assertEquals(8, longestPalindrome(new String[]{"ab","ty","yt","lc","cl","ab"}));
    }

    private void test2() {
    }

    private void test() {
        Assert.assertEquals("First Letter of Each Word", capitalizeTitle("First leTTeR of EACH Word"));
    }
}
