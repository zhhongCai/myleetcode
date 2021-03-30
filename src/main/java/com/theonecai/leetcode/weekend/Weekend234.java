package com.theonecai.leetcode.weekend;


import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author: theonecai
 * @Date: Create in 2021/03/20 10:24
 * @Description:
 */
public class Weekend234 {

    public int numDifferentIntegers(String word) {
        char[] chars = word.toCharArray();
        Set<String> set = new HashSet<>();
        StringBuilder wd = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            if (chars[i] >= 'a' && chars[i] <= 'z') {
                chars[i] = ' ';
                if (wd.length() > 0) {
                    set.add(wd.toString());
                    wd.delete(0, wd.length());
                }
                continue;
            }
            if (chars[i] == '0' && i > 0 && chars[i - 1] == ' ' && i + 1 < word.length() &&
                    (chars[i + 1] >= '0' && chars[i + 1] <= '9') ) {
                chars[i] = ' ';
                continue;
            }
            wd.append(chars[i]);
        }
        if (wd.length() > 0) {
            set.add(wd.toString());
        }

        return set.size();
    }

    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> map = new HashMap<>(knowledge.size());
        for (List<String> strings : knowledge) {
            map.put(strings.get(0), strings.get(1));
        }
        StringBuilder result = new StringBuilder();
        StringBuilder wd = new StringBuilder();
        boolean exp = false;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                exp = true;
            } else if (ch == ')') {
                result.append(map.getOrDefault(wd.toString(), "?"));
                wd.delete(0, wd.length());
                exp = false;
            } else {
                if (!exp) {
                    result.append(ch);
                } else {
                    wd.append(ch);
                }
            }
        }

        return result.toString();
    }

    public int reinitializePermutation(int n) {
        int[] perm = new int[n];
        for (int i = 0; i < n; i++) {
            perm[i] = i;
        }
        int[] arr = new int[n];
        doStep(perm, arr);
        if (checkOk(arr)) {
            return 1;
        }

        int count = 1;
        while (true) {
            doStep(arr, perm);
            count++;
            int[] tmp = arr;
            arr = perm;
            perm = tmp;
            if (checkOk(arr)) {
                break;
            }
        }

        return count;
    }

    public int maxNiceDivisors(int primeFactors) {
        if (primeFactors == 1) {
            return 1;
        }
        int mod = 1000000007;
        long result = 1;
        int remain = primeFactors % 3;
        if (remain == 1) {
            result = pow(3L, (primeFactors - 4) / 3);
            result *= 4L;
        } else if (remain == 2) {
            result = pow(3L, primeFactors / 3);
            result *= 2L;
        } else {
            result = pow(3L, primeFactors / 3);
        }
        result %= mod;

        return (int)result;
    }

    private long pow(long x, long n) {
        int mod = 1000000007;
        long ret = 1;
        while (n > 0) {
            if (n % 2 == 1) {
                ret = (ret * x) % mod;
            }
            x = (x * x) % mod;
            n >>= 1;
        }
        return ret;
    }

    private boolean checkOk(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != i) {
                return false;
            }
        }
        return true;
    }

    private void doStep(int[] perm, int[] arr) {
        int n = perm.length;
        for (int i = 0; i < n; i++) {
            arr[i] = i % 2 == 0 ? perm[i / 2] : perm[n / 2 + (i - 1) / 2];
        }
    }

    public static void main(String[] args) {
        Weekend234 weekend = new Weekend234();
        weekend.test();
        weekend.test2();
        weekend.test3();
        weekend.test4();
    }

    private void test4() {
        Assert.assertEquals(947137513, this.maxNiceDivisors(64));
        Assert.assertEquals(572712676, this.maxNiceDivisors(73));
        Assert.assertEquals(6, this.maxNiceDivisors(5));
        Assert.assertEquals(18, this.maxNiceDivisors(8));
    }

    private void test3() {
        List<List<String>> list = new ArrayList<>();
        List<String> a = new ArrayList<>();
        a.add("name");
        a.add("bob");
        list.add(a);
        a = new ArrayList<>();
        a.add("age");
        a.add("two");
        list.add(a);
        Assert.assertEquals("bobistwoyearsold", this.evaluate("(name)is(age)yearsold", list));

        list.clear();;
        a = new ArrayList<>();
        a.add("a");
        a.add("yes");
        list.add(a);
        Assert.assertEquals("yesyesyesaaa", this.evaluate("(a)(a)(a)aaa", list));

        list.clear();;
        a = new ArrayList<>();
        a.add("a");
        a.add("b");
        list.add(a);
        Assert.assertEquals("hi?", this.evaluate("hi(name)", list));
        list.clear();;
        a = new ArrayList<>();
        a.add("a");
        a.add("b");
        list.add(a);
        a = new ArrayList<>();
        a.add("b");
        a.add("a");
        list.add(a);
        Assert.assertEquals("ba", this.evaluate("(a)(b)", list));
    }

    private void test2() {
        Assert.assertEquals(1, this.reinitializePermutation(2));
        Assert.assertEquals(2, this.reinitializePermutation(4));
        Assert.assertEquals(4, this.reinitializePermutation(6));
        Assert.assertEquals(36, this.reinitializePermutation(1000));
    }

    private void test() {
        Assert.assertEquals(8, this.numDifferentIntegers("4w31am0ets6sl5go5ufytjtjpb7b0sxqbee2blg9ss"));
        Assert.assertEquals(3, this.numDifferentIntegers("a123bc34d8ef34"));
        Assert.assertEquals(2, this.numDifferentIntegers("leet1234code234"));
        Assert.assertEquals(1, this.numDifferentIntegers("a1b01c001"));
    }
}
