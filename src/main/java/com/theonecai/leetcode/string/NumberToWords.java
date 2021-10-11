package com.theonecai.leetcode.string;

import org.junit.Assert;

/**
 * leetcode 273
 */
public class NumberToWords {
    private String[] singles = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
            "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private String[] tens = {"", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private String[] thousands = {"", "Thousand", "Million", "Billion"};

    public String numberToWords(int num) {
        StringBuilder res = new StringBuilder();
        if (num == 0) {
            return "Zero";
        }
        int i = 0;
        while (num > 0) {
            res.insert(0, processPart(res.length(), i++, num % 1000));
            num /= 1000;
        }

        return res.toString();
    }

    private String processPart(int resLen, int i, int n) {
        String str = "";
        if (n / 100 != 0) {
            str += singles[n / 100] + " Hundred";
        }
        n %= 100;
        if (n != 0 && str.length() > 0) {
            str += " ";
        }

        if (n < 20) {
            str += singles[n];
        } else {
           str += tens[n / 10 - 1];
           if (n % 10 != 0) {
               str += " " + singles[n % 10];
           }
        }

        if (i > 0 && str.length() > 0) {
            str += " " + thousands[i];
            if (resLen > 0) {
                str += " ";
            }
        }

        return str;
    }

    public static void main(String[] args) {
        NumberToWords numberToWords = new NumberToWords();

        Assert.assertEquals("One Billion One Hundred Thousand One", numberToWords.numberToWords(1000100001));
        Assert.assertEquals("One Thousand", numberToWords.numberToWords(1000));
        Assert.assertEquals("Twenty", numberToWords.numberToWords(20));
        Assert.assertEquals("One Hundred One", numberToWords.numberToWords(101));
        Assert.assertEquals("One Hundred Twenty Three", numberToWords.numberToWords(123));
        Assert.assertEquals("Twelve Thousand Three Hundred Forty Five", numberToWords.numberToWords(12345));
        Assert.assertEquals("One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven", numberToWords.numberToWords(1234567));
        Assert.assertEquals("One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One", numberToWords.numberToWords(1234567891));
    }
}
