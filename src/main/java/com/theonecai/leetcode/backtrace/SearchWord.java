package com.theonecai.leetcode.backtrace;

import org.junit.Assert;

/**
 * leetcode 79
 * @Author: theonecai
 * @Date: Create in 2020/7/15 21:05
 * @Description:
 */
public class SearchWord {
    private boolean found = false;
    private boolean[][] visited;

    public boolean exist(char[][] board, String word) {
        visited = new boolean[board.length][board[0].length];
        found = false;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    visited[i][j] = true;
                    search(board, i, j, word, 0);
                    visited[i][j] = false;
                }
            }
        }
        return found;
    }

    private void search(char[][] chars, int i, int j, String word, int currentIndex) {
        if (found) {
            return;
        }
        if (word.length() - 1 == currentIndex) {
            found = chars[i][j] == word.charAt(currentIndex);
//            System.out.println("i=" + i + ",j=" + j);
            return;
        }

        if (chars[i][j] == word.charAt(currentIndex)) {
            visited[i][j] = true;
            if (i < chars.length - 1 && !visited[i + 1][j]) {
                search(chars, i + 1, j, word, currentIndex + 1);
                if (!found) {
                    visited[i + 1][j] = false;
                }
            }
            if (j < chars[0].length - 1 && !visited[i][j + 1]) {
                search(chars, i, j + 1, word, currentIndex + 1);
                if (!found) {
                    visited[i][j + 1] = false;
                }
            }
            if (i > 0 && !visited[i - 1][j]) {
                search(chars, i - 1, j, word, currentIndex + 1);
                if (!found) {
                    visited[i - 1][j] = false;
                }
            }
            if (j > 0 && !visited[i][j - 1]) {
                search(chars, i, j - 1, word, currentIndex + 1);
                if (!found) {
                    visited[i][j - 1] = false;
                }
            }
        }
    }

    public static void main(String[] args) {
        SearchWord searchWord = new SearchWord();
        String word = "hellos";
        char[][] chars = {
            {'h', 'o', 'l', 'l', 'h'},
            {'e', 'l', 'l', 'o', 's'},
            {'g', 'h', 'e', 'l', 'h'},
            {'f', 'q', 'l', 'l', 'o'},
        };
        Assert.assertTrue(searchWord.exist(chars, word));

        char[][] arr2 = {
            {'A','B','C','E'},
            {'S','F','C','S'},
            {'A','D','E','E'}
        };
        word = "ABCCE";
        Assert.assertTrue(searchWord.exist(arr2, word));

        char[][] arr3 = {
            {'A','B','C','E'},
            {'S','F','C','S'},
            {'A','D','E','E'}
        };
        word = "SEE";
        Assert.assertTrue(searchWord.exist(arr3, word));

        char[][] arr4 = {
            {'A','B','C','E'},
            {'S','F','C','S'},
            {'A','D','E','E'}
        };
        word = "ABCB";
        Assert.assertFalse(searchWord.exist(arr4, word));

        char[][] arr5 ={
            {'a', 'a'}
        };
        word = "aaa";
        Assert.assertFalse(searchWord.exist(arr5, word));

        char[][] arr6 = {
            {'C','A','A'},
            {'A','A','A'},
            {'B','C','D'}
        };
        word = "AAB";
        Assert.assertTrue(searchWord.exist(arr6, word));
    }

}
