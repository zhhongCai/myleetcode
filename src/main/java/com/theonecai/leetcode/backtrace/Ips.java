package com.theonecai.leetcode.backtrace;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode 93
 *
 * @Author: theonecai
 * @Date: Create in 2020/7/23 20:35
 * @Description:
 */
public class Ips {
    private StringBuilder ip;

    public List<String> restoreIpAddresses(String s) {
        List<String> ips = new ArrayList<>();
        if (s.length() > 12 || s.length() < 4) {
            return ips;
        }

        ip = new StringBuilder();

        resolve(s, new int[3], 0, ips);

        return ips;
    }

    private void resolve(String ipStr, int[] splitIndex, int index, List<String> ips) {
        if (index == splitIndex.length) {
            if (ip.length() > 0) {
                ip.delete(0, ip.length());
            }
            ip.append(ipStr, 0, splitIndex[0]).append(".")
                    .append(ipStr, splitIndex[0], splitIndex[1]).append(".")
                    .append(ipStr, splitIndex[1], splitIndex[2]).append(".")
                    .append(ipStr.substring(splitIndex[2]));
            ips.add(ip.toString());
            return;
        }

        int preIndex;
        for (int i = 1; i < 4; i++) {
            preIndex = index > 0 ? splitIndex[index - 1] : 0;

            if (ipStr.length() == 12 && i < 3) {
                continue;
            }
            if (preIndex + i > ipStr.length() - 1) {
                continue;
            }
            if (index == 2 && inValid(ipStr, preIndex + i, ipStr.length())) {
                continue;
            }
            if (inValid(ipStr, preIndex, preIndex + i)) {
                continue;
            }
            splitIndex[index] = preIndex + i;
            resolve(ipStr, splitIndex, index + 1, ips);
        }
    }

    private boolean inValid(String ipStr, int start, int end) {
        if (end - start > 1 && ipStr.charAt(start) == '0') {
            return true;
        }
        return Integer.parseInt(ipStr.substring(start, end)) > 255;
    }

    public static void main(String[] args) {
        Ips ips = new Ips();
        List<String> list = ips.restoreIpAddresses("25525511135");
        list.forEach(System.out::println);
        list = ips.restoreIpAddresses("102030");
        list.forEach(System.out::println);
    }
}
