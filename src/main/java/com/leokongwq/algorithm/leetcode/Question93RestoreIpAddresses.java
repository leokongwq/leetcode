package com.leokongwq.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jiexiu
 * created 2020/8/9 - 09:45
 * <p>
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 * <p>
 * 有效的 IP 地址正好由四个整数（每个整数位于 0 到 255 之间组成），整数之间用 '.' 分隔。
 * <p>
 * 示例:
 * <p>
 * 输入: "25525511135"
 * 输出: ["255.255.11.135", "255.255.111.35"]
 */
public class Question93RestoreIpAddresses {

    static final int SEG_COUNT = 4;
    List<String> ans = new ArrayList<>();
    int[] segments = new int[SEG_COUNT];

    /**
     * 暴力3重循环解法
     * <p>
     * 判断是否合理：
     * 1. 数字是否小于等于255，
     * 2. 连续0的个数只能为1 && 非0数字不能以0开头
     */
    public List<String> restoreIpAddresses(String s) {
        if (s == null || s.length() == 0 || s.length() > 12) {
            return ans;
        }

        for (int i = 0; i < s.length() - 3; i++) {
            long num1 = Long.parseLong(s.substring(0, i + 1));
            // genLen获取切分出来的数字，去掉0开头的长度
            if (num1 > 255 || getLen(num1) != i + 1) {
                continue;
            }
            for (int j = i + 1; j < s.length() - 2; j++) {
                long num2 = Long.parseLong(s.substring(i + 1, j + 1));
                if (num2 > 255 || getLen(num2) != j - i) {
                    continue;
                }
                for (int k = j + 1; k < s.length() - 1; k++) {
                    long num3 = Long.parseLong(s.substring(j + 1, k + 1));
                    if (num3 > 255 || getLen(num3) != k - j) {
                        continue;
                    }
                    long num4 = Long.parseLong(s.substring(k + 1));
                    if (num4 > 255 || getLen(num4) != s.length() - k - 1) {
                        continue;
                    }
                    String temp = num1 + "." + num2 + "." + num3 + "." + num4;
                    ans.add(temp);
                }
            }
        }
        return ans;
    }

    private static int getLen(long num) {
        //如果当前段的数字的为0，则长度只能是1
        if (num == 0) {
            return 1;
        }

        //去掉前导0的长度
        return (int) Math.floor(Math.log10(num)) + 1;
    }

    public List<String> restoreIpAddressesV1(String s) {
        segments = new int[SEG_COUNT];
        dfs(s, 0, 0);
        return ans;
    }

    public void dfs(String s, int segId, int segStart) {
        // 如果找到了 4 段 IP 地址并且遍历完了字符串，那么就是一种答案
        if (segId == SEG_COUNT) {
            if (segStart == s.length()) {
                StringBuilder ipAddr = new StringBuilder();
                for (int i = 0; i < SEG_COUNT; ++i) {
                    ipAddr.append(segments[i]);
                    if (i != SEG_COUNT - 1) {
                        ipAddr.append('.');
                    }
                }
                ans.add(ipAddr.toString());
            }
            return;
        }

        // 如果还没有找到 4 段 IP 地址就已经遍历完了字符串，那么提前回溯
        if (segStart == s.length()) {
            return;
        }

        // 由于不能有前导零，如果当前数字为 0，那么这一段 IP 地址只能为 0
        if (s.charAt(segStart) == '0') {
            segments[segId] = 0;
            dfs(s, segId + 1, segStart + 1);
        }

        // 一般情况，枚举每一种可能性并递归
        int addr = 0;
        for (int segEnd = segStart; segEnd < s.length(); ++segEnd) {
            addr = addr * 10 + (s.charAt(segEnd) - '0');
            if (addr > 0 && addr <= 0xFF) {
                segments[segId] = addr;
                dfs(s, segId + 1, segEnd + 1);
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) {
        Question93RestoreIpAddresses solution = new Question93RestoreIpAddresses();
//        List<String> result = solution.restoreIpAddresses("25525511135");
        List<String> result = solution.restoreIpAddresses("1231231231234");

        result.forEach(System.out::println);
    }
}
