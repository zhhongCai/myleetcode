package com.theonecai.algorithms;

/**
 * 回溯算法
 * @Author: theonecai
 * @Date: Create in 2020/7/3 19:26
 * @Description:
 */
public class Backtracking {

    /**
     * 8皇后问题
     * @param row 当前第row+1行
     * @param result result[i] = k 表示 第i+1行第k+1列放一个皇后
     */
    public void calcEightQueen(int row, int[] result) {
        if (row == 8) {
            printQueens(result);
            return;
        }

        for (int col = 0; col < 8; col++) {
            if (isOk(row, col, result)) {
                result[row] = col;
                calcEightQueen(row + 1, result);
            }
        }

    }

    private void printQueens(int[] result) {
        System.out.println("解法:");
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (result[row] == col) {
                    System.out.print("Q ");
                } else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
    }

    private boolean isOk(int row, int col, int[] result) {
        int leftUp = col - 1;
        int rightUp = col + 1;
        for (int i = row - 1; i >= 0; i--) {
            // 上一行col列已放,返回false
            if (result[i] == col) {
                return false;
            }
            // 左上角已放，返回false
            if (result[i] == leftUp) {
                return false;
            }
            // 右上角已放，返回false
            if (result[i] == rightUp) {
               return false;
            }
            leftUp--;
            rightUp++;
        }
        return true;
    }

    public static void main(String[] args) {
        Backtracking backtracking = new Backtracking();
        backtracking.calcEightQueen(0, new int[8]);

        // 物品重量
        int[] items = {2,2,4,6,3,7,3,2};
        // 背包承受的最大重量
        int w = 9;
        System.out.println("\n可转入物品重量:" + backtracking.maxPackageWeight(items, w));

        Pattern pattern = new Pattern("abcd*d");
        String text = "abcdefghd";
        System.out.println("\"" + pattern.pattern + "\" matched \"" + text +"\": " + pattern.rmatch(text));
    }

    /**
     * 01背包
     * @param itemsWeight: 每个物品的重量
     * @param maxWeight: 背包最大承重
     * @return 物品装入背包的最大重量
     */
    public int maxPackageWeight(int[] itemsWeight, int maxWeight) {
        int currentWeight = 0;

        return maxPackageWeight(itemsWeight, maxWeight, 0, currentWeight);
    }

    private int maxPackageWeight(int[] itemsWeight, int maxWeight, int nextItemIndex, int currentWeight) {
        if (nextItemIndex == itemsWeight.length - 1 || currentWeight == maxWeight) {
            return currentWeight;
        }

        // 不装nextItemIndex
        currentWeight = maxPackageWeight(itemsWeight, maxWeight, nextItemIndex + 1, currentWeight);
        // 未超重，装入nextItemIndex
        if (currentWeight + itemsWeight[nextItemIndex] <= maxWeight) {
            currentWeight = maxPackageWeight(itemsWeight, maxWeight, nextItemIndex + 1, currentWeight + itemsWeight[nextItemIndex]);
        }
        return currentWeight;
    }


    static class Pattern {
        private String pattern;
        private boolean matched = false;

        public Pattern(String pattern) {
            this.pattern = pattern;
        }

        public boolean rmatch(String text) {

            rmatch(0, 0, text);

            return matched;
        }

        private void rmatch(int patternIndex, int textIndex, String text) {
            if (matched) {
                return;
            }
            if (patternIndex == pattern.length() && textIndex == text.length()) {
                matched = true;
                return;
            }

            if (pattern.charAt(patternIndex) == text.charAt(textIndex)) {
                rmatch(patternIndex + 1, textIndex + 1, text);
            } else {
                if (pattern.charAt(patternIndex) == '*') {
                    for (int k = 0; k <= text.length() - textIndex; k++) {
                        rmatch(patternIndex + 1, textIndex + k, text);
                    }
                } else if (pattern.charAt(patternIndex) == '?') {
                    rmatch(patternIndex, textIndex + 1, text);
                    rmatch(patternIndex + 1, textIndex + 1, text);
                }
            }
        }
    }

}
