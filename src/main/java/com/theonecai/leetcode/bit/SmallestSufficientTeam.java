package com.theonecai.leetcode.bit;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 1125
 */
public class SmallestSufficientTeam {

    private List<Integer> minTeam;

    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        Map<String, Integer> skillsToBinary = new HashMap<>(req_skills.length);
        int skillMask = 1;
        int reqSkillAllMask = 0;
        for (String reqSkill : req_skills) {
            skillsToBinary.put(reqSkill, skillMask);
            reqSkillAllMask |= skillMask;
            skillMask <<= 1;
        }
//        System.out.println(Integer.toBinaryString(reqSkillAllMask));

        int[] peopleSillMask = new int[people.size()];
        Set<Integer> exists = new HashSet<>();
        for (int i = 0; i < people.size(); i++) {
            List<String> skills = people.get(i);
            for (String skill : skills) {
                peopleSillMask[i] |= skillsToBinary.getOrDefault(skill, 0);
            }
            if (exists.contains(peopleSillMask[i]) || subSet(exists, peopleSillMask[i])) {
                peopleSillMask[i] = 0;
            }
//            System.out.println("i=" + i + ",peopleSillMask[i]=" + Integer.toBinaryString(peopleSillMask[i]));
            exists.add(peopleSillMask[i]);
        }

//        for (Integer exist : exists) {
//            System.out.println(Integer.toBinaryString(exist));
//        }
        // 问题转换为：从peopleSillMask中最少取哪几个数或的结果 >= reqSkillAllMask
        backtrace(peopleSillMask, new ArrayList<>(), 0, 0, reqSkillAllMask);
        return minTeam.stream().mapToInt(Integer::intValue).toArray();
    }

    private boolean subSet(Set<Integer> exists, int skillMask) {
        for (Integer skill : exists) {
            if ((skill & skillMask) == skillMask) {
                return true;
            }
        }
        return false;
    }

    private void backtrace(int[] peopleSillMask, List<Integer> result, int i, int currentMask, int reqSkillAllMask) {

//        System.out.println("i=" + i + ",reqSkillAllMask=" + reqSkillAllMask);
        if (reqSkillAllMask == currentMask) {
            if (minTeam == null || minTeam.size() > result.size()) {
                minTeam = new ArrayList<>(result);
            }
            return;
        }
        if (i >= peopleSillMask.length) {
            return;
        }
        if (peopleSillMask[i] != 0) {
            // 包含people[i]
            result.add(i);
            backtrace(peopleSillMask, result, i + 1, currentMask | peopleSillMask[i], reqSkillAllMask);
            // 不包含people[i]
            result.remove(result.size() - 1);
        }
        backtrace(peopleSillMask, result, i + 1, currentMask, reqSkillAllMask);
    }

    public static void main(String[] args) {
        SmallestSufficientTeam smallestSufficientTeam = new SmallestSufficientTeam();
        String[][] people = new String[][] {
                {"hdbxcuzyzhliwv","dzsgleocfpl"},
                        {"hdbxcuzyzhliwv","sdi","ylopoifzkacuwp","dzsgleocfpl"},
                        {"bztg","ylopoifzkacuwp"},{"bztg","dzsgleocfpl"},
                        {"hdbxcuzyzhliwv","bztg"},{"dzsgleocfpl"},{"uvwlzkmzgis"},
                        {"dzsgleocfpl"},{"hdbxcuzyzhliwv"},{""},{"dzsgleocfpl"},
                        {"hdbxcuzyzhliwv"},{""},{"hdbxcuzyzhliwv","ylopoifzkacuwp"},
                        {"sdi"},{"bztg","dzsgleocfpl"},
                        {"hdbxcuzyzhliwv","uvwlzkmzgis","sdi","bztg","ylopoifzkacuwp"},
                        {"hdbxcuzyzhliwv","sdi"},{"hdbxcuzyzhliwv","ylopoifzkacuwp"},
                        {"sdi","bztg","ylopoifzkacuwp","dzsgleocfpl"},
                        {"dzsgleocfpl"},{"sdi","ylopoifzkacuwp"},{"hdbxcuzyzhliwv","uvwlzkmzgis","sdi"},
                        {""},{""},{"ylopoifzkacuwp"},{""},{"sdi","bztg"},
                        {"bztg","dzsgleocfpl"},{"sdi","bztg"}};
        List<List<String>> list =  new ArrayList<>();
        for (String[] person : people) {
            list.add(Arrays.stream(person).collect(Collectors.toList()));
        }
        System.out.println(Arrays.toString(smallestSufficientTeam.smallestSufficientTeam(new String[]{
                "hdbxcuzyzhliwv","uvwlzkmzgis","sdi","bztg","ylopoifzkacuwp","dzsgleocfpl"
        }, list)));
    }
}
