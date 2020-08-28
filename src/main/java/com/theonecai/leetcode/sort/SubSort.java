package com.theonecai.leetcode.sort;

import com.theonecai.algorithms.ArrayUtil;

import java.util.Arrays;

/**
 * leetcode 16.16
 * @Author: theonecai
 * @Date: Create in 2020/6/28 21:37
 * @Description:
 */
public class SubSort {

    public int[] subSort(int[] array) {
        int[] result = new int[2];
        result[0] = -1;
        result[1] = -1;

        if (array == null || array.length < 2) {
            return result;
        }

        int[] temp = Arrays.copyOf(array, array.length);
        Arrays.sort(temp);
        ArrayUtil.print(temp);


        for (int i = 0; i < temp.length; i++) {
            if (array[i] != temp[i]) {
                if (result[0] == -1) {
                    result[0] = i;
                    continue;
                }
                result[1] = i;
            }
        }

        return result;
    }

    public int[] subSort2(int[] array) {
        int[] result = new int[2];

        if (array == null || array.length < 2) {
            result[0] = -1;
            result[1] = -1;
            return result;
        }

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        result[0] = 0;
        result[1] = array.length - 1;
        while (result[0] < array.length - 1 && array[result[0]] <= array[result[0] + 1]) {
            result[0]++;
        }
        while (result[1] > 0 && array[result[1]] >= array[result[1] - 1]) {
            result[1]--;
        }
        if (result[0] >= result[1]) {
            result[0] = -1;
            result[1] = -1;
            return result;
        }

        for (int i = result[0]; i <= result[1]; i++) {
            if (max < array[i]) {
                max = array[i];
            }
            if (min > array[i]) {
                min = array[i];
            }
        }
        while (result[0] > 0 && array[result[0]] > min) {
            result[0]--;
        }
        if (array[result[0]] <= min) {
            result[0]++;
        }
        while (result[1] < array.length - 1 && array[result[1]] < max) {
            result[1]++;
        }
        if (array[result[1]] >= max) {
            result[1]--;
        }

        return result;
    }

    public static void main(String[] args) {
        SubSort subSort = new SubSort();
        int[] a = ArrayUtil.randIntArray(10);
        ArrayUtil.print(a);
        ArrayUtil.print(subSort.subSort(a));
        ArrayUtil.print(subSort.subSort2(a));

        int[] b = {1,2,4,7,10,11,7,12,6,7,16,18,19};
        ArrayUtil.print(b);
        ArrayUtil.print(subSort.subSort(b));
        ArrayUtil.print(subSort.subSort2(b));

        int[] c = {1,3,9,7,5};
        ArrayUtil.print(c);
        ArrayUtil.print(subSort.subSort(c));
        ArrayUtil.print(subSort.subSort2(c));

        int[] d = {1,1,7,1,7,7,8,6,0,5};
        ArrayUtil.print(d);
        ArrayUtil.print(subSort.subSort(d));
        ArrayUtil.print(subSort.subSort2(d));

        int[] e = {-580,-578,-577,-575,-575,-574,-571,-570,-565,-561,-560,-558,-556,-556,-555,-553,-552,-550,-547,-534,-532,-530,-529,-529,-522,-522,-521,-520,-519,-518,-517,-515,-515,-513,-512,-511,-510,-509,-501,-498,-497,-496,-495,-494,-491,-488,-488,-488,-487,-486,-484,-483,-483,-482,-479,-477,-476,-476,-473,-472,-472,-470,-464,-464,-463,-462,-457,-453,-452,-451,-447,-447,-446,-443,-442,-441,-439,-438,-435,-434,-434,-432,-430,-426,-425,-422,-422,-421,-420,-419,-414,-405,-404,-403,-403,-400,-399,-396,-396,-392,-390,-389,-388,-387,-387,-383,-383,-381,-381,-375,-371,-369,-367,-363,-354,-349,-349,-348,-347,-345,-343,-341,-339,-338,-333,-330,-329,-326,-325,-324,-319,-319,-313,-311,-307,-301,-301,-300,-297,-297,-296,-292,-287,-287,-285,-280,-279,-279,-276,-274,-273,-272,-271,-271,-270,-269,-268,-268,-266,-265,-262,-262,-261,-257,-255,-252,-252,-249,-248,-247,-246,-245,-245,-245,-241,-240,-240,-238,-236,-236,-235,-234,-231,-229,-228,-227,-226,-225,-225,-225,-224,-222,-221,-221,-220,-219,-219,-212,-211,-205,-205,-204,-202,-199,-196,-195,-195,-194,-193,-192,-191,-189,-187,-187,-186,-186,-185,-176,-173,-173,-164,-163,-160,-160,-158,-157,-156,-153,-152,-147,-139,-139,-135,-132,-128,-127,-126,-117,-104,-103,-99,-97,-95,-94,-92,-91,-91,-89,-85,-83,-81,-81,-80,-78,-74,-67,-64,-55,-55,-54,-53,-49,-48,-46,-42,-41,-39,-29,-24,-23,-18,-17,-16,-7,-7,-5,-4,-1,3,4,7,7,8,10,15,17,21,27,28,31,34,34,35,35,41,42,45,46,46,51,51,52,58,63,64,69,74,76,79,79,79,81,84,86,86,86,87,87,94,98,100,100,102,108,108,109,114,114,117,118,122,122,122,126,127,127,129,129,130,130,131,131,138,139,142,142,143,149,152,152,153,154,154,157,161,161,164,164,164,165,166,166,180,181,181,183,185,185,190,192,193,196,197,197,202,202,204,205,206,206,209,210,212,214,217,218,225,226,227,227,228,228,231,232,233,234,235,236,239,243,244,244,245,247,251,260,263,265,266,268,271,272,272,275,276,276,277,278,278,280,281,282,282,282,283,283,288,290,291,293,293,293,294,296,301,302,303,305,306,306,307,308,313,314,314,315,318,319,319,320,325,327,329,330,331,332,337,339,339,344,345,347,348,348,348,350,350,350,352,353,353,354,355,355,356,358,360,361,362,364,364,365,370,373,376,376,377,379,386,388,390,391,392,393,430,416,441,428,403,454,403,407,421,572,574,518,507,559,490,465,483,461,483,408,452,479,542,418,393,527,442,511,531,544,403,480,474,570,466,486,437,539,483,571,418,484,567,452,554,398,523,479,472,395,496,470,582,530,442,581,421,417,553,560,576,485,481,489,521,570,552,558,503,514,559,514,460,438,511,432,542,480,582,520,543,513,511,397,502,458,517,505,418,507,417};
        ArrayUtil.print(e, 493, 585);
        ArrayUtil.print(subSort.subSort(e));
        ArrayUtil.print(subSort.subSort2(e));
    }
}
