package com.theonecai.algorithms;

/**
 * 动态规划
 * @Author: caizhh
 * @Date: Create in 19-1-30 下午1:33
 * @Description:
 */
public class DynamicPlanning {

    public static void main(String[] args) {
        // 物品重量
        int[] weight = {2,2,4,6,3,7,3,2};
        // 背包承受的最大重量
        int w = 9;

        System.out.println("\ntotalWeight=" + dp(weight, w));
    }

    /**
     * 0-1背包:
     * f(i)为装或不装第i个物品时的重量
     * f(i) = f(i-1) + weight[i]  (装了第i个物品) or f(i) = f(i-1) (不装第i个物品);
     * @param weight: 物品重量
     * @param w: 背包可承载重
     */
    public static int dp(int[] weight, int w) {
        int n = weight.length;
        boolean[][] state = new boolean[n][w+1];
        state[0][0] = true;
        state[0][weight[0]] = true;

        for (int i = 1; i < n; i++) {
            //i不放入背包
            for (int j = 0; j < w; j++) {
                if (state[i-1][j]) {
                    state[i][j] = true;
                }
            }
            //i放入背包
            for (int j = 0; j <= w-weight[i]; j++) {
                if (state[i-1][j]) {
                    state[i][j + weight[i]] = true;
                }
            }
            printState(state, weight);
        }

        int maxW = 0;
        for (int i = w; i > 0; i--) {
            if (state[n-1][i]) {
                maxW = i;
                break;
            }
        }

        printPicked(weight, state, maxW);

        return maxW;
    }

    /**
     * 打印取法
     * @param weight
     * @param state
     * @param maxW
     */
    private static void printPicked(int[] weight, boolean[][] state, int maxW) {
        for (int k = weight.length - 1; k >= 0; k--) {
            if (state[k][maxW]) {
                int i = k;
                int j = maxW;
                System.out.print("取法: ");
                while(j > 0 && i > 0) {
                    if (state[i][j] && j >= weight[i] && state[i-1][j-weight[i]]) {
                        j -= weight[i];
                        System.out.print("weight[" + i + "]=" + weight[i] + ", ");
                    }
                    i--;
                }
                System.out.println();
            }
        }
    }

    /**
     * 打印state
     * @param state
     * @param weight
     */
    private static void printState(boolean[][] state, int[] weight) {
        System.out.print("  |");
        for (int i = 0; i < state[0].length; i++) {
            System.out.print(" " + i);
        }
        System.out.println("\n--+--------------------");
        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state[i].length; j++) {
                if (j == 0) {
                    System.out.print(i + " | ");
                }
                System.out.print(state[i][j] ? "1 " : "0 ");
            }
            System.out.println(" --weight[" + i + "]=" + weight[i]);
        }
        System.out.println("\n");
    }


}
