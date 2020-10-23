package com.theonecai.leetcode.gredy;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Author: theonecai
 * @Date: Create in 2020/10/17 15:02
 * @Description:
 */
public class IsPrintable {
    int replacedCount = 0;

    public boolean isPrintable(int[][] targetGrid) {
        Map<Integer, Range> colorRanges = getColorRanges(targetGrid);
        int total = targetGrid.length * targetGrid[0].length;
        while (replacedCount < total) {
            Iterator<Map.Entry<Integer, Range>> it = colorRanges.entrySet().iterator();
            boolean print = false;
            while (it.hasNext()) {
                Map.Entry<Integer, Range> entry = it.next();
                Range range = entry.getValue();
                int color = entry.getKey();
                boolean canPrint = true;
                for (int row = range.topLeft[0]; row <= range.bottomRight[0]; row++) {
                    for (int col = range.topLeft[1]; col <= range.bottomRight[1]; col++) {
                        if (targetGrid[row][col] != 0 && targetGrid[row][col] != color) {
                            canPrint = false;
                            break;
                        }
                    }
                    if (!canPrint) {
                        break;
                    }
                }
                // 替换为0
                if (canPrint) {
                    print = true;
                    for (int row = range.topLeft[0]; row <= range.bottomRight[0]; row++) {
                        for (int col = range.topLeft[1]; col <= range.bottomRight[1]; col++) {
                            if (targetGrid[row][col] != 0) {
                                replacedCount++;
                            }
                            targetGrid[row][col] = 0;
                        }
                    }
                    it.remove();
                }
            }
            if (!print) {
                return false;
            }
        }

        return replacedCount == total;
    }

    /**
     * 获取每种颜色的边界点
     * @param targetGrid
     * @return
     */
    private Map<Integer, Range> getColorRanges(int[][] targetGrid) {
        Map<Integer, Range> colorRanges = new HashMap<>(60);
        for (int row = 0; row < targetGrid.length; row++) {
            for (int col = 0; col < targetGrid[0].length; col++) {
                int color = targetGrid[row][col];
                if (!colorRanges.containsKey(color)) {
                    colorRanges.put(color, new Range(color, row, col));
                }

                Range range = colorRanges.get(color);
                range.update(row, col);
            }
        }

        return colorRanges;
    }

    private static class Range {
        int color;
        /**
         * 左上角
         */
        int[] topLeft;
        /**
         * 又下角
         */
        int[] bottomRight;

        public Range(int color, int row, int col) {
            this.color = color;
            topLeft = new int[]{row, col};
            bottomRight = new int[]{row, col};
        }

        public void update(int row, int col) {
            if (row < topLeft[0]) {
                topLeft[0] = row;
            }
            if (col < topLeft[1]) {
                topLeft[1] = col;
            }

            if (row > bottomRight[0]) {
                bottomRight[0] = row;
            }
            if (col > bottomRight[1]) {
                bottomRight[1] = col;
            }
        }

    }

    public static void main(String[] args) {
        IsPrintable isPrintable = new IsPrintable();
        Assert.assertTrue(isPrintable.isPrintable(new int[][]{
                {1,1,1,1},
                {1,1,3,3},
                {1,1,3,4},
                {5,5,1,4}
        }));
    }
}
