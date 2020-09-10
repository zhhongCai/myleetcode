package com.theonecai.leetcode.sort;

import org.junit.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * leetcode 1333
 * @Author: theonecai
 * @Date: Create in 2020/9/10 20:52
 * @Description:
 */
public class FilterRestaurants {


    public List<Integer> filterRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
        // restaurants[i] = [idi, ratingi, veganFriendlyi, pricei, distancei]
        // 过滤后返回餐馆的 id，按照 rating 从高到低排序。如果 rating 相同，那么按 id 从高到低排序
        return Arrays.stream(restaurants)
                .filter(restaurant -> ((veganFriendly == 1 && restaurant[2] == veganFriendly) ||
                            veganFriendly == 0)
                        && restaurant[3] <= maxPrice && restaurant[4] <= maxDistance)
                .sorted(((o1, o2) -> {
                    int res = o2[1] - o1[1];
                    if (res == 0) {
                        return o2[0] - o1[0];
                    }
                    return res;
                })).mapToInt(ints -> ints[0]).boxed().collect(Collectors.toList());
    }

    public static void main(String[] args) {
        FilterRestaurants filterRestaurants = new FilterRestaurants();
        int[][] restaurants =  {
                {1,4,1,40,10},
                {2,8,0,50,5},
                {3,8,1,30,4},
                {4,10,0,10,3},
                {5,1,1,15,1}};
        List<Integer> list = filterRestaurants.filterRestaurants(restaurants, 1, 50, 10);
        System.out.println(list);
        Assert.assertEquals(3, list.size());
    }
}
