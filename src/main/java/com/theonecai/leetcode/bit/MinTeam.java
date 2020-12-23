package com.theonecai.leetcode.bit;

import java.util.*;

/**
 * 1125
 */
public class MinTeam {

    private List<Integer> minTeam;

    public int[] minTeam(String[] reqSkills, String[][] people) {
        Map<String, Integer> skillsToBinary = new HashMap<>(reqSkills.length);
        int skillMask = 1;
        int reqSkillAllMask = 0;
        for (String reqSkill : reqSkills) {
            skillsToBinary.put(reqSkill, skillMask);
            reqSkillAllMask |= skillMask;
            skillMask <<= 1;
        }

        int[] peopleSillMask = new int[people.length];
        for (int i = 0; i < people.length; i++) {
            String[] skills = people[i];
            for (String skill : skills) {
                peopleSillMask[i] |= skillsToBinary.getOrDefault(skill, 0);
            }
        }

        // 问题转换为：从peopleSillMask中最少取哪几个数或的结果 >= reqSkillAllMask
        backtrace(peopleSillMask, new ArrayList<>(), 0, reqSkillAllMask);
        return minTeam.stream().mapToInt(Integer::intValue).toArray();
    }

    private void backtrace(int[] peopleSillMask, List<Integer> result, int i, int reqSkillAllMask) {
        System.out.println("i=" + i + ",reqSkillAllMask=" + reqSkillAllMask);
        if (reqSkillAllMask == 0) {
            if (minTeam == null|| minTeam.size() > result.size()) {
                minTeam = new ArrayList<>(result);
            }
            return;
        }
        if (i >= peopleSillMask.length) {
            return;
        }
        // 包含people[i]
        result.add(i);
        backtrace(peopleSillMask, result, i + 1, reqSkillAllMask ^ peopleSillMask[i]);
        // 不包含people[i]
        result.remove(result.size() - 1);
        backtrace(peopleSillMask, result, i + 1, reqSkillAllMask);
    }

    public static void main(String[] args) {
        MinTeam minTeam = new MinTeam();
        System.out.println(Arrays.toString(minTeam.minTeam(new String[]{
                "Java","C","C++","Linux","JS"
        }, new String[][]{
                {"Java", "C", "JS"},
                {"C++", "JS", "Java","C","Linux"},
                {"C++", "Linux"},
        })));
    }
}
