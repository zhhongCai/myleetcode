package com.theonecai.leetcode.sort;

import com.theonecai.leetcode.util.RunUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * leetcode 1452
 * @Author: theonecai
 * @Date: Create in 2020/9/11 20:04
 * @Description:
 */
public class PeopleIndex {

    public List<Integer> peopleIndexes(List<List<String>> favoriteCompanies) {

        Map<Integer, TreeSet<String>> map = new HashMap<>(favoriteCompanies.size());
        for (int i = 0; i < favoriteCompanies.size(); i++) {
            map.put(i, new TreeSet<>(favoriteCompanies.get(i)));
        }

        List<Map.Entry<Integer, TreeSet<String>>> list = map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.comparingInt(TreeSet::size))).collect(Collectors.toList());
        List<Integer> result = new LinkedList<>();

        Set<Integer> excludedIndex = new HashSet<>(100);
        for (int i = 0; i < list.size(); i++) {
            if (excludedIndex.contains(i)) {
                continue;
            }
            Map.Entry<Integer, TreeSet<String>> current = list.get(i);
            boolean subSet = false;
            for (int j = i + 1; j < list.size(); j++) {
                Map.Entry<Integer, TreeSet<String>> next = list.get(j);
                subSet = true;
                for (String company : current.getValue()) {
                    if (!next.getValue().contains(company)) {
                        subSet = false;
                        break;
                    }
                }
                if (subSet) {
                    excludedIndex.add(i);
                    if (next.getValue().size() == current.getValue().size()) {
                        excludedIndex.add(next.getKey());
                    }
                    break;
                }
            }
            if (!subSet) {
                result.add(current.getKey());
            }
        }

        if (result.size() > 1) {
            Collections.sort(result);
        }

        return result;
    }

    public static void main(String[] args) {
        PeopleIndex peopleIndex = new PeopleIndex();
        List<List<String>> companies = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            List<String> list = new ArrayList<>();
            for (int j = 0; j < 500; j++) {
                list.add("iiiiiiiii" + j);
            }
            companies.add(list);
        }
        RunUtil.runAndPrintCostTime(() -> System.out.println(peopleIndex.peopleIndexes(companies)));
        companies.clear();
        List<String> list = new ArrayList<>();
        list.add("leetcode");
        list.add("google");
        list.add("facebook");
        companies.add(list);

        list = new ArrayList<>();
        list.add("google");
        list.add("microsoft");
        companies.add(list);

        list = new ArrayList<>();
        list.add("google");
        list.add("facebook");
        companies.add(list);

        list = new ArrayList<>();
        list.add("google");
        companies.add(list);

        list = new ArrayList<>();
        list.add("amazon");
        companies.add(list);

        RunUtil.runAndPrintCostTime(() -> System.out.println(peopleIndex.peopleIndexes(companies)));
    }
}
