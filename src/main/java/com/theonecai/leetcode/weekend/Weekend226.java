package com.theonecai.leetcode.weekend;


import org.junit.Assert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 * @Author: theonecai
 * @Date: Create in 2021/01/31 10:24
 * @Description:
 */
public class Weekend226 {

    public int countBalls(int lowLimit, int highLimit) {
        int[] count = new int[100];
        for (int i = lowLimit; i <= highLimit; i++) {
            count[getBox(i)]++;
        }
        int max = 0;
        for (int i = 0; i < count.length; i++) {
            max = Math.max(count[i], max);
        }
        return max;
    }

    private int getBox(int i) {
        int sum = 0;
        while (i > 0) {
            sum += i % 10;
            i /= 10;
        }
        return sum;
    }

    public int[] restoreArray(int[][] adjacentPairs) {
        if (adjacentPairs.length == 1) {
            return adjacentPairs[0];
        }
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] adjacentPair : adjacentPairs) {
            Set<Integer> set = map.getOrDefault(adjacentPair[0], new HashSet<>());
            Set<Integer> set2 = map.getOrDefault(adjacentPair[1], new HashSet<>());
            set.add(adjacentPair[1]);
            set2.add(adjacentPair[0]);
            map.put(adjacentPair[0], set);
            map.put(adjacentPair[1], set2);
        }
        LinkedList<Integer> list = new LinkedList<>();
        list.add(adjacentPairs[0][0]);
        list.add(adjacentPairs[0][1]);
        Set<Integer> exists = new HashSet<>();
        exists.add(adjacentPairs[0][0]);
        exists.add(adjacentPairs[0][1]);
        int head;
        int tail;
        while (true) {
            if (list.size() == adjacentPairs.length + 1) {
                break;
            }
            head = list.getFirst();
            tail = list.getLast();
            Set<Integer> headMap = map.get(head);
            if (headMap != null) {
                for (Integer num : headMap) {
                    if (!exists.contains(num)) {
                        list.addFirst(num);
                        exists.add(num);
                    }
                }

            }
            Set<Integer> tailMap = map.get(tail);
            if (tailMap != null) {
                for (Integer num : tailMap) {
                    if (!exists.contains(num)) {
                        list.addLast(num);
                        exists.add(num);
                    }
                }
            }
        }

        return list.stream().mapToInt(Integer::intValue).toArray();
    }


    public boolean[] canEat(int[] candiesCount, int[][] queries) {
        boolean[] result = new boolean[queries.length];
        long[] preSum = new long[candiesCount.length];
        preSum[0] = candiesCount[0];
        for (int i = 1; i < candiesCount.length; i++) {
            preSum[i] = preSum[i - 1] + candiesCount[i];
        }
        int i = 0;
        for (int[] query : queries) {
            long eatMin = query[1] + 1;
            long eatMax = (long)(query[1] + 1) * (long)query[2];
            int type = query[0];
            long existMin = type == 0 ? 1 : preSum[type - 1] + 1;
            long existMax = preSum[type];
            result[i] = !(eatMax < existMin || eatMin > existMax);

            i++;
        }

        return result;
    }

    public static void main(String[] args) {
        Weekend226 weekend226 = new Weekend226();
        weekend226.test();
        weekend226.test2();
        weekend226.test3();
        weekend226.test4();
    }

    private void test4() {
    }

    /**
     * [5215,14414,67303,93431,44959,34974,22935,64205,28863,3436,45640,34940,38519,5705,14594,30510,4418,87954,8423,65872,79062,83736,47851,64523,15639,19173,88996,97578,1106,17767,63298,8620,67281,76666,50386,97303,26476,95239,21967,31606,3943,33752,29634,35981,42216,88584,2774,3839,81067,59193,225,8289,9295,9268,4762,2276,7641,3542,3415,1372,5538,878,5051,7631,1394,5372,2384,2050,6766,3616,7181,7605,3718,8498,7065,1369,1967,2781,7598,6562,7150,8132,1276,6656,1868,8584,9442,8762,6210,6963,4068,1605,2780,556,6825,4961,4041,4923,8660,4114]
     * [[46,4191056,444472063],[75,865431,146060662],[91,244597,840227137],[89,2601754,901415801],[69,1777314,444098682],[78,2957259,231019870],[19,4350225,516815116],[42,4081198,594990005],[59,3176552,508520222],[77,4577766,38900694],[92,320256,1362],[44,3992014,7209],[55,1950613,1370],[97,734069,3066],[39,1188632,661],[58,4526426,6202],[51,3083812,1767],[46,2563654,9680],[21,4012578,7014],[66,2185952,7039],[67,3712445,1239],[0,1840130,185],[35,605159,7105],[94,2269908,416],[68,4117247,2076],[0,4540381,2412],[20,579583,8917],[62,4407388,7127],[17,4468545,6287],[50,3462654,1410],[7,1883037,77],[4,4089924,5849],[5,4340465,3843],[68,596099,5796],[29,542371,5952],[91,441898,2227],[35,912775,6110],[12,267236,3248],[27,990261,771],[76,320119,5220],[23,738123,2504],[66,439801,4436],[18,372357,1654],[51,846227,5325],[80,502088,3751],[49,117408,102],[75,837527,8747],[46,984134,7924],[42,463312,7558],[50,214995,1043],[94,981465,6758],[79,892988,1063],[17,985872,2314],[71,870151,2004],[63,793308,7608],[49,873121,2846],[32,453564,3739],[42,890492,6026],[19,278107,2649],[64,792101,2208],[98,577463,526],[41,572006,748],[99,478120,895],[52,224338,423],[83,532978,600],[67,92281,486],[28,829955,925],[22,171381,749],[82,986821,603],[57,294692,194],[9,730892,973],[69,241093,931],[70,646855,27],[45,233480,669],[60,369922,965],[27,935011,659],[96,667580,837],[7,919344,188],[99,584762,131],[5,93173,898],[16,736395,184],[57,893061,196],[28,352640,924],[87,980414,80],[88,432895,129],[23,461032,85],[73,645991,268],[5,241036,458],[9,422324,785],[28,124913,224],[51,815633,765],[59,894120,559],[70,459876,192],[80,423125,584],[85,824496,142],[18,578975,104],[56,477816,303],[6,702127,400],[43,35371,850],[3,226423,10]]
     *
     * [46,5,47,48,43,34,15,26,11,25,41,47,15,25,16,50,32,42,32,21,36,34,50,45,46,15,46,38,50,12,3,26,26,16,23,1,4,48,47,32,47,16,33,23,38,2,19,50,6,19,29,3,27,12,6,22,33,28,7,10,12,8,13,24,21,38,43,26,35,18,34,3,14,48,50,34,38,4,50,26,5,35,11,2,35,9,11,31,36,20,21,37,18,34,34,10,21,8,5]
     * [[80,2329,69],[14,1485,76],[33,2057,83],[13,1972,27],[11,387,25],[24,1460,47],[22,1783,35],[1,513,33],[66,2124,85],[19,642,26],[15,1963,79],[93,722,96],[15,376,88],[60,1864,89],[86,608,4],[98,257,35],[35,651,47],[96,795,73],[62,2077,18],[27,1724,57],[34,1984,75],[49,2413,95],[76,1664,5],[28,38,13],[85,54,42],[12,301,3],[62,2016,29],[45,2316,37],[43,2360,28],[87,192,98],[27,2082,21],[74,762,37],[51,35,17],[73,2193,4],[60,425,65],[11,1522,58],[21,1699,66],[42,1473,5],[30,2010,48],[91,796,74],[82,2162,31],[23,2569,65],[24,684,23],[70,1219,51],[5,1817,15],[81,2446,34],[96,771,60],[49,1171,60],[41,567,67],[39,799,59],[90,957,81],[84,2122,27],[82,1707,44],[11,1889,20],[80,1697,83],[24,1786,60],[90,1847,99],[51,114,21],[44,466,85],[56,469,20],[44,350,96],[66,1946,10],[14,2470,12],[69,1175,18],[98,1804,25],[77,2187,40],[89,2265,45],[19,2246,45],[40,2373,79],[60,2222,17],[37,385,5],[97,1759,97],[10,903,5],[87,842,45],[74,2398,66],[62,49,94],[48,156,77],[76,2310,80],[64,2360,95],[70,1699,83],[39,1241,66],[92,2312,21],[63,2148,29],[95,594,74],[89,90,51],[82,137,70],[54,301,97],[15,819,43],[47,1402,60],[17,2377,43],[50,1937,95],[62,1174,74],[67,1411,87],[39,1151,48]]
     *
     * [false,false,false,false,true,false,false,false,false,false,false,true,true,false,true,true,true,true,false,false,false,false,true,false,true,true,false,false,false,true,false,true,false,false,true,false,false,false,false,true,true,false,true,true,false,false,true,true,true,true,true,true,true,false,true,false,true,true,true,true,true,false,false,true,true,false,true,false,false,false,true,true,false,true,false,true,true,false,false,true,false,true,false,true,true,true,true,false,true,false,false,true,true,true]
     * [false,false,false,false,true,false,false,false,false,false,false,true,true,false,true,true,true,true,false,false,false,false,true,false,true,true,false,false,false,true,false,true,false,false,true,false,false,false,false,true,true,false,true,true,false,false,true,true,true,true,true,true,true,false,true,false,true,true,true,true,true,false,false,true,true,false,true,false,false,false,true,true,false,true,false,true,true,false,false,true,false,true,false,true,true,true,true,false,true,false,false,true,true,true]
     * [false,false,false,false,true,false,false,false,false,false,false,true,true,false,true,true,true,true,false,false,false,false,true,false,false,true,false,false,false,true,false,true,false,false,true,false,false,false,false,true,true,false,true,true,false,false,true,true,true,true,true,true,true,false,true,false,true,true,true,true,true,false,false,true,true,false,true,false,false,false,true,true,false,true,false,true,true,false,false,true,false,true,false,true,true,true,true,false,true,false,false,true,true,true]
     */
    private void test3() {
        System.out.println(Arrays.toString(this.canEat(new int[]{46,5,47,48,43,34,15,26,11,25,41,47,15,25,16,50,32,42,32,21,36,34,50,45,46,15,46,38,50,12,3,26,26,16,23,1,4,48,47,32,47,16,33,23,38,2,19,50,6,19,29,3,27,12,6,22,33,28,7,10,12,8,13,24,21,38,43,26,35,18,34,3,14,48,50,34,38,4,50,26,5,35,11,2,35,9,11,31,36,20,21,37,18,34,34,10,21,8,5}, new int[][]{
                {85,54,42}
        })));
        System.out.println(Arrays.toString(this.canEat(new int[]{7,4,5,3,8}, new int[][]{
                {0,2,2},{4,2,4},{2,16,1000000000}
        })));
        System.out.println(Arrays.toString(this.canEat(new int[]{5,2,6,4,1}, new int[][]{
                {3,1,2},{4,10,3},{3,10,100},{4,100,30},{1,3,1}
        })));
    }

    private void test2() {
//        Assert.assertEquals("1,2,3,4", Arrays.toString(this.restoreArray(new int[][] {
//                {-3,-9},{-5,3},{2,-9},{6,-3},{6,1},{5,3},{8,5},{-5,1},{7,2}
//        })));
//        Assert.assertEquals("1,2,3,4", Arrays.toString(this.restoreArray(new int[][] {
//                {2,1},{3,4},{3,2}
//        })));
    }

    private void test() {

        Assert.assertEquals(2, this.countBalls(1, 10));
        Assert.assertEquals(2, this.countBalls(5, 15));
        Assert.assertEquals(2, this.countBalls(19, 28));
    }
}
