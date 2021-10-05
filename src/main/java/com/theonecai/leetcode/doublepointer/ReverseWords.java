package com.theonecai.leetcode.doublepointer;

import org.junit.Assert;

/**
 * 151
 */
public class ReverseWords {
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        int right = s.length() - 1;

        int left = right;
        while (left >= 0){
            while (right >= 0 && s.charAt(right) == ' ') {
                right--;
            }
            if (right <= 0) {
                sb.append(s, 0, right + 1);
                if (right + 1 > 0) {
                    sb.append(" ");
                }
                break;
            }
            left = right;
            while (left >= 0 && s.charAt(left) != ' ') {
                left--;
            }
            sb.append(s, left + 1, right + 1).append(" ");

            right = left;
        }
        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }

    public static void main(String[] args) {
        ReverseWords reverseWords = new ReverseWords();
        Assert.assertEquals("c b a", reverseWords.reverseWords("      a     b   c  "));
        Assert.assertEquals("b world hello a", reverseWords.reverseWords("   a  hello world  b  "));
        Assert.assertEquals("world hello", reverseWords.reverseWords("  hello world  "));
        Assert.assertEquals("blue is sky the", reverseWords.reverseWords("the sky is blue"));
        Assert.assertEquals("example good a", reverseWords.reverseWords("a good   example"));
        Assert.assertEquals("Alice Loves Bob", reverseWords.reverseWords("  Bob    Loves  Alice   "));
        Assert.assertEquals("bob like even not does Alice", reverseWords.reverseWords("Alice does not even like bob"));
    }
}
