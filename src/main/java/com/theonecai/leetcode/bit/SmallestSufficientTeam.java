package com.theonecai.leetcode.bit;

import java.util.*;
import java.util.stream.Collectors;

/**
 * leetcode 1125
 */
public class SmallestSufficientTeam {

    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        Map<String, Integer> skillsToBinary = new HashMap<>(req_skills.length);
        int skillMask = 1;
        // 团队需要满足的技能
        int reqSkillAllMask = 0;
        for (String reqSkill : req_skills) {
            skillsToBinary.put(reqSkill, skillMask);
            reqSkillAllMask |= skillMask;
            skillMask <<= 1;
        }

        // Map<员工的技能, 员工索引>, 去除技能重复,技能被包含,没有有效技能的员工
        Map<Integer, Integer> skillIndexMap = new HashMap<>();
        for (int i = 0; i < people.size(); i++) {
            List<String> skills = people.get(i);
            int peopleSkill = 0;
            for (String skill : skills) {
                peopleSkill |= skillsToBinary.getOrDefault(skill, 0);
            }
            if (skillIndexMap.containsKey(peopleSkill) || subSet(skillIndexMap, peopleSkill)) {
                peopleSkill = 0;
            }
            if (peopleSkill != 0) {
                skillIndexMap.put(peopleSkill, i);
            }
        }

        // 员工技能
        int[] peopleSkills = skillIndexMap.keySet().stream().mapToInt(Integer::intValue).toArray();
        // dp[skillMaskB] = Math.min(dp[skillMaskB], dp[skillMaskA] + 1),
        // 其中skillMaskB = skillMaskA | peopleSkills[i],且dp[skillMaskB] != -1 && dp[skillMaskA] != -1
        int[] dp = new int[reqSkillAllMask + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        // Map<满足的技能,团队成员索引列表>
        Map<Integer, List<Integer>> team = new HashMap<>(8);
        for (int peopleSkill : peopleSkills) {
            for (int skillMaskA = 0; skillMaskA < dp.length; skillMaskA++) {
                if (dp[skillMaskA] == -1) {
                    continue;
                }
                int skillMaskB = skillMaskA | peopleSkill;
                if (dp[skillMaskB] == -1 || dp[skillMaskB] > dp[skillMaskA] + 1) {
                    dp[skillMaskB] = dp[skillMaskA] + 1;
                    List<Integer> aTeam = new ArrayList<>(team.getOrDefault(skillMaskA, new ArrayList<>()));
                    aTeam.add(skillIndexMap.get(peopleSkill));
                    team.put(skillMaskB, aTeam);
                }
            }
        }

        return team.get(reqSkillAllMask).stream().mapToInt(Integer::intValue).toArray();
    }

    private List<Integer> minTeam;

    public int[] smallestSufficientTeam2(String[] req_skills, List<List<String>> people) {
        Map<String, Integer> skillsToBinary = new HashMap<>(req_skills.length);
        int skillMask = 1;
        int reqSkillAllMask = 0;
        for (String reqSkill : req_skills) {
            skillsToBinary.put(reqSkill, skillMask);
            reqSkillAllMask |= skillMask;
            skillMask <<= 1;
        }
        System.out.println(Integer.toBinaryString(reqSkillAllMask));

        // Map<peopleSkill, peopleIndex>
        Map<Integer, Integer> skillIndexMap = new HashMap<>();
        for (int i = 0; i < people.size(); i++) {
            List<String> skills = people.get(i);
            int peopleSkill = 0;
            for (String skill : skills) {
                peopleSkill |= skillsToBinary.getOrDefault(skill, 0);
            }
            if (skillIndexMap.containsKey(peopleSkill) || subSet(skillIndexMap, peopleSkill)) {
                peopleSkill = 0;
            }
//            System.out.println("i=" + i + ",peopleSillMask[i]=" + Integer.toBinaryString(peopleSillMask[i]));
            if (peopleSkill != 0) {
                skillIndexMap.put(peopleSkill, i);
            }
        }

        for (Integer exist : skillIndexMap.keySet()) {
            System.out.println(Integer.toBinaryString(exist));
        }
        int[] skills = skillIndexMap.keySet().stream().mapToInt(Integer::intValue).toArray();
        Set<Integer> uniqueSkills = new HashSet<>();
        for (int skill : skills) {
            boolean uniqueSkill = true;
            for (int i = 0; i < skills.length; i++) {
                if (skill != skills[i] && (skill & skills[i]) != 0) {
                    uniqueSkill = false;
                    break;
                }
            }
            if (uniqueSkill) {
                uniqueSkills.add(skill);
            }
        }
        // 问题转换为：从peopleSillMask中最少取哪几个数或的结果 >= reqSkillAllMask
        backtrace(skillIndexMap, skills, new ArrayList<>(), 0, 0, reqSkillAllMask, uniqueSkills);
        return minTeam.stream().mapToInt(Integer::intValue).toArray();
    }

    private boolean subSet(Map<Integer, Integer> skillIndexMap, int skillMask) {
        for (Integer skill : skillIndexMap.keySet()) {
            if ((skill & skillMask) == skillMask) {
                return true;
            }
        }
        return false;
    }

    private void backtrace(Map<Integer, Integer> skillIndexMap, int[] peopleSillMask, List<Integer> result, int i,
                           int currentMask, int reqSkillAllMask, Set<Integer> uniqueSkills) {

//        System.out.println("i=" + i + ",reqSkillAllMask=" + Integer.toBinaryString(currentMask));
        if (reqSkillAllMask == currentMask) {
            if (minTeam == null || minTeam.size() > result.size()) {
//                System.out.println(result);
                minTeam = new ArrayList<>(result);
            }
            return;
        }
        if (i >= peopleSillMask.length) {
            return;
        }
        int s = currentMask;
        for (int j = i; j < peopleSillMask.length; j++) {
            s |= peopleSillMask[j];
        }
        if ((s & reqSkillAllMask) == reqSkillAllMask) {
            if (peopleSillMask[i] != 0) {
                // 包含people[i]
                result.add(skillIndexMap.get(peopleSillMask[i]));
                backtrace(skillIndexMap, peopleSillMask, result, i + 1, currentMask | peopleSillMask[i], reqSkillAllMask, uniqueSkills);
                // 不包含people[i]
                result.remove(result.size() - 1);
                if (!uniqueSkills.contains(peopleSillMask[i])) {
                    backtrace(skillIndexMap, peopleSillMask, result, i + 1, currentMask, reqSkillAllMask, uniqueSkills);
                }
            }
        }
    }

    public static void main(String[] args) {
        SmallestSufficientTeam smallestSufficientTeam = new SmallestSufficientTeam();
        String[][] people = new String[][] {
                {"peaqbonzgl","xtadkauiqwravo"},{"peaqbonzgl","pcfpppaxsxtpixd","zshbwqdhx"},{"x","a"},{"a"},
                {"jmhobexvmmlyyzk","fjubadocdwaygs","xtadkauiqwravo","zshbwqdhx"},{"fjubadocdwaygs","x","zshbwqdhx"},
                {"x","xtadkauiqwravo"},{"x","hyxnrujrqykzhizm"},{"peaqbonzgl","x","pcfpppaxsxtpixd","a"},
                {"peaqbonzgl","pcfpppaxsxtpixd"},{"a"},{"hyxnrujrqykzhizm"},{"jmhobexvmmlyyzk"},
                {"hfkbcrslcdjq","xtadkauiqwravo","a","zshbwqdhx"},{"peaqbonzgl","mf","a","rahimgtlopffbwdg","zshbwqdhx"},
                {"xtadkauiqwravo"},{"fjubadocdwaygs"},{"x","a","ulqocaijhezwfr","zshbwqdhx"},{"peaqbonzgl"},
                {"pcfpppaxsxtpixd","ulqocaijhezwfr","hyxnrujrqykzhizm"},{"a","ulqocaijhezwfr","hyxnrujrqykzhizm"},
                {"a","rahimgtlopffbwdg"},{"zshbwqdhx"},{"fjubadocdwaygs","peaqbonzgl","brgjopmm","x"},
                {"hyxnrujrqykzhizm"},{"jmhobexvmmlyyzk","a","ulqocaijhezwfr"},
                {"peaqbonzgl","x","a","ulqocaijhezwfr","zshbwqdhx"},{"mf","pcfpppaxsxtpixd"},
                {"fjubadocdwaygs","ulqocaijhezwfr"},{"fjubadocdwaygs","x","a"},{"zezdb","hyxnrujrqykzhizm"},
                {"ccwfthnjt","a"},{"fjubadocdwaygs","zezdb","a"},{},{"peaqbonzgl","ccwfthnjt","hyxnrujrqykzhizm"},
                {"xtadkauiqwravo","hyxnrujrqykzhizm"},{"peaqbonzgl","a"},{"x","a","hyxnrujrqykzhizm"},{"zshbwqdhx"},
                {},{"fjubadocdwaygs","mf","pcfpppaxsxtpixd","zshbwqdhx"},{"pcfpppaxsxtpixd","a","zshbwqdhx"},
                {"peaqbonzgl"},{"peaqbonzgl","x","ulqocaijhezwfr"},{"ulqocaijhezwfr"},{"x"},
                {"fjubadocdwaygs","peaqbonzgl"},{"fjubadocdwaygs","xtadkauiqwravo"},{"pcfpppaxsxtpixd","zshbwqdhx"},
                {"peaqbonzgl","brgjopmm","pcfpppaxsxtpixd","a"},{"fjubadocdwaygs","x","mf","ulqocaijhezwfr"},
                {"jmhobexvmmlyyzk","brgjopmm","rahimgtlopffbwdg","hyxnrujrqykzhizm"},{"x","ccwfthnjt","hyxnrujrqykzhizm"},
                {"hyxnrujrqykzhizm"},{"peaqbonzgl","x","xtadkauiqwravo","ulqocaijhezwfr","hyxnrujrqykzhizm"},
                {"brgjopmm","ulqocaijhezwfr","zshbwqdhx"},{"peaqbonzgl","pcfpppaxsxtpixd"},
                {"fjubadocdwaygs","x","a","zshbwqdhx"},{"fjubadocdwaygs","peaqbonzgl","x"},{"ccwfthnjt"}
        };
        List<List<String>> list =  new ArrayList<>();
        for (String[] person : people) {
            list.add(Arrays.stream(person).collect(Collectors.toList()));
        }
        long a = System.currentTimeMillis();
        System.out.println(Arrays.toString(smallestSufficientTeam.smallestSufficientTeam(new String[]{
                "hfkbcrslcdjq","jmhobexvmmlyyzk","fjubadocdwaygs","peaqbonzgl","brgjopmm","x","mf","pcfpppaxsxtpixd",
                "ccwfthnjt","xtadkauiqwravo","zezdb","a","rahimgtlopffbwdg","ulqocaijhezwfr","zshbwqdhx","hyxnrujrqykzhizm"
        }, list)));
        System.out.println("cost:" + (System.currentTimeMillis() - a));

        people = new String[][] {
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
                        {""},{""},{"ylopoifzkacuwp"},{""},{"sdi","bztg"},{"a"},
                        {"bztg","dzsgleocfpl"},{"sdi","bztg"}};
        list =  new ArrayList<>();
        for (String[] person : people) {
            list.add(Arrays.stream(person).collect(Collectors.toList()));
        }
        System.out.println(Arrays.toString(smallestSufficientTeam.smallestSufficientTeam(new String[]{
                "hdbxcuzyzhliwv","uvwlzkmzgis","sdi","bztg","ylopoifzkacuwp","dzsgleocfpl"
        }, list)));
    }
}
