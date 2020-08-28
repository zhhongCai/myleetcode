package com.theonecai.leetcode.backtrace;

/**
 * leetcode 37
 * @Author: theonecai
 * @Date: Create in 2020/7/21 20:14
 * @Description:
 */
public class Sudoku {

    public void solveSudoku(char[][] board) {
        int[][] result = resolve(board);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = (char)(result[i][j] + '0');
            }
        }
    }

    public int[][] resolve(char[][] board) {
        int[][] result = new int[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0';
                    result[i][j] = num;
                }
            }
        }

        resolve(result);

        return result;
    }

    private void resolve(int[][] result) {
        resolve(result, 0, 0);
    }

    private boolean resolve(int[][] result, int row, int col) {
        if (col >= 9) {
            return resolve(result, row + 1, 0);
        }
        if (row == 9) {
            return true;
        }
        if (result[row][col] != 0) {
            return resolve(result, row, col + 1);
        }

        for (int i = 1; i <= 9; i++) {
            if (!checkOk(result, row, col, i)) {
                continue;
            }

            result[row][col] = i;

            if (resolve(result, row, col + 1)) {
                return true;
            }

            result[row][col] = 0;
        }
        return false;
    }

    private boolean checkOk(int[][] result, int row, int col, int num) {
        for (int i = 0; i < 9; i++) {
            if (result[i][col] == num) {
                return false;
            }
            if (result[row][i] == num) {
                return false;
            }
        }

        // 所在九宫格
        int startRow = (row / 3) * 3;
        int startCol  = (col / 3) * 3;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (result[startRow + r][startCol + c] == num) {
                    return false;
                }
            }
        }

        return true;
    }


    public static void main(String[] args) {
        Sudoku sudoku = new Sudoku();
        char[][] board = {
            {'5','3','.', '.','7','.', '.','.','.'},
            {'6','.','.', '1','9','5', '.','.','.'},
            {'.','9','8', '.','.','.', '.','6','.'},

            {'8','.','.', '.','6','.', '.','.','3'},
            {'4','.','.', '8','.','3', '.','.','1'},
            {'7','.','.', '.','2','.', '.','.','6'},

            {'.','6','.', '.','.','.', '2','8','.'},
            {'.','.','.', '4','1','9', '.','.','5'},
            {'.','.','.', '.','8','.', '.','7','9'}
        };
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        sudoku.solveSudoku(board);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
