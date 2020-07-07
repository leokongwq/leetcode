package com.leokongwq.algorithm.leetcode;

/**
 * @author jiexiu
 * created 2018/12/18 - 22:12
 *
 * Write a function to find the longest common prefix string amongst an array of strings.
 *
 * If there is no common prefix, return an empty string "".
 *
 * Example 1:
 *
 * Input: ["flower","flow","flight"]
 * Output: "fl"
 * Example 2:
 *
 * Input: ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 * Note:
 *
 * All given inputs are in lowercase letters a-z.
 */
public class Question14LongestCommonPrefix {

    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }

        String minLenStr = strs[0];
        for (int i = 1; i < strs.length; i++) {
            if (strs[i].length() < minLenStr.length()) {
                minLenStr = strs[i];
            }
        }
        if (minLenStr.length() == 0) {
            return "";
        }

        int endIndex = minLenStr.length();
        while (endIndex > 0) {
            boolean allMathch = true;
            for (String str : strs) {
                if (str.equals(minLenStr)) {
                    continue;
                }
                if (!str.startsWith(minLenStr)) {
                    allMathch = false;
                    break;
                }
            }
            if (allMathch) {
                return minLenStr;
            }
            endIndex--;
            minLenStr = minLenStr.substring(0, endIndex);
        }
        return "";
    }

    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(new String[]{"flower","flow","flight"}));
        System.out.println(longestCommonPrefix(new String[]{"dog","racecar","car"}));
        System.out.println(longestCommonPrefix(new String[]{"a","b","c"}));
        System.out.println(longestCommonPrefix(new String[]{"a","a","c"}));
    }
}

