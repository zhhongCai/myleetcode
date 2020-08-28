package com.theonecai.leetcode.unionfind;

import java.util.Arrays;

/**
 * leetcode 990
 * @Author: theonecai
 * @Date: Create in 2020/6/9 09:34
 * @Description:
 */
public class Equations {

    public boolean equationsPossible(String[] equations) {
        int size = 26;
        short[] eqSet = new short[size];
        Arrays.fill(eqSet, (short) -1);

        int[] indexs = new int[equations.length];
        String str = null;
        short a = 'a';
        int j, k, jroot, kroot;
        int id = 0;
        for (int i = 0; i < equations.length; i++) {
            str = equations[i];
            if (str.charAt(1) == '!') {
                if (str.charAt(0) == str.charAt(3)) {
                    return false;
                }
                indexs[id++] = i;
            } else {
                j = str.charAt(0) - a;
                k = str.charAt(3) - a;
                jroot = findRoot(j, eqSet);
                kroot = findRoot(k, eqSet);
                if (jroot != -1) {
                   if (kroot != -1) {
                       if (jroot == kroot) {
                           continue;
                       }
                       int kr = eqSet[k];
                       eqSet[kr] = (short)jroot;
                       eqSet[k] = (short)jroot;
                   } else {
                       eqSet[k] = (short)j;
                   }
                } else {
                   if (kroot == -1) {
                       eqSet[j] = (short)j;
                       eqSet[k] = (short)j;
                   } else {
                       eqSet[j] = (short) kroot;
                   }
                }
            }
        }
        if (id == 0) {
            return true;
        }

        String equation;
        for (int i = 0; i < id; i++) {
            equation = equations[indexs[i]];
            int x = equation.charAt(0) - a;
            int y = equation.charAt(3) - a;
            int xroot = findRoot(x, eqSet);
            int yroot = findRoot(y, eqSet);
            if (xroot != -1 && xroot == yroot) {
                return false;
            }
        }

        return true;
    }

    private int findRoot(int x, short[] eqSet) {
        int xroot = -1;
        while (eqSet[x] != xroot) {
            xroot = eqSet[x];
            x = eqSet[xroot];
        }
        return xroot;
    }


    public static void main(String[] args) {
        Equations solution = new Equations();
        String[] equations = {"a==a"};
        System.out.println(equations + ":" + solution.equationsPossible(equations));
        equations = new String[]{"a==b"};
        System.out.println(equations + ":" + solution.equationsPossible(equations));
        equations = new String[]{"a==b", "b==c", "c==a"};
        System.out.println(equations + ":" + solution.equationsPossible(equations));
        equations = new String[]{"a==b", "b==c", "c!=d"};
        System.out.println(equations + ":" + solution.equationsPossible(equations));
        equations = new String[]{"a==b", "b==c", "c!=a"};
        System.out.println(equations + ":" + solution.equationsPossible(equations));
        equations = new String[]{"a!=a"};
        System.out.println(equations + ":" + solution.equationsPossible(equations));
        equations = new String[]{"a!=b"};
        System.out.println(equations + ":" + solution.equationsPossible(equations));
        equations = new String[]{"e==e","d!=e","c==d","d!=e"};
        System.out.println(equations + ":" + solution.equationsPossible(equations));
        equations = new String[]{"e==e","d!=e","c==d","d!=e", "d==e"};
        System.out.println(equations + ":" + solution.equationsPossible(equations));
        equations = new String[]{"a==b","e==c","b==c","a!=e"};
        System.out.println(equations + ":" + solution.equationsPossible(equations));
    }

}
