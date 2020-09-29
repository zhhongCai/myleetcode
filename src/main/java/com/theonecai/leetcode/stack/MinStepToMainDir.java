package com.theonecai.leetcode.stack;

/**
 * @Author: theonecai
 * @Date: Create in 2020/9/28 20:12
 * @Description:
 */
public class MinStepToMainDir {

    public int stepToMainDir (String[]path){
        int index = -1;
        for (String s : path) {
            if (s.equals("./")) {
                continue;
            }
            if (s.equals("../")) {
                if (index > -1) {
                    index--;
                }
            } else {
                ++index;
            }
        }
        return index + 1;
    }
}
