package com.theonecai.leetcode.sort;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode 1366
 * @Author: theonecai
 * @Date: Create in 2020/9/10 21:38
 * @Description:
 */
public class RankTeam {

    public String rankTeams(String[] votes) {
        Map<Character, int[]> teamRanks = new HashMap<>();
        char ch;
        for (String vote : votes) {
            for (int i = 0; i < vote.length(); i++) {
                ch = vote.charAt(i);
                if (teamRanks.containsKey(ch)) {
                   int[] ranks = teamRanks.get(ch);
                   ranks[i] += 1;
                } else {
                    int[] ranks = new int[26];
                    ranks[i] += 1;
                    teamRanks.put(ch, ranks);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        teamRanks.entrySet().stream().sorted((team, team2) -> {
            int[] rank = team.getValue();
            int[] rank2 = team2.getValue();
            for (int i = 0; i < 26; i++) {
                if (rank[i] != rank2[i]) {
                    return rank2[i] - rank[i];
                }
            }
            return team.getKey().compareTo(team2.getKey());

        }).forEach(entry -> sb.append(entry.getKey()));

        return sb.toString();
    }

    public static void main(String[] args) {
        RankTeam rankTeam = new RankTeam();
        String[] votes = {"ABC","ABC", "BAC", "BAC"};
        Assert.assertEquals("ABC", rankTeam.rankTeams(votes));
    }
}
