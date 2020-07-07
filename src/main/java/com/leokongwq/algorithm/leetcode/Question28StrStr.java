package com.leokongwq.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jiexiu
 * created 2018/12/19 - 17:56
 * Implement strStr().
 *
 * Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 *
 * Example 1:
 *
 * Input: haystack = "hello", needle = "ll"
 * Output: 2
 * Example 2:
 *
 * Input: haystack = "aaaaa", needle = "bba"
 * Output: -1
 * Clarification:
 *
 * What should we return when needle is an empty string? This is a great question to ask during an interview.
 *
 * For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr()
 * and Java's indexOf().
 *
 * 其他实现方式参考： https://www.zhihu.com/question/27852656
 */
public class Question28StrStr {

    private static int strStr(String haystack, String needle) {
        if (needle == null || "".equals(needle)) {
            return 0;
        }
        if (haystack == null || haystack.length() == 0) {
            return -1;
        }
        if (needle.length() > haystack.length()) {
            return -1;
        }

        int needleIndex = 0;
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
           int num = i;
           while (num < haystack.length() && needleIndex < needle.length()) {
               if (haystack.charAt(num) != needle.charAt(needleIndex)) {
                   break;
               }
               num++;
               needleIndex++;
           }
           if (needleIndex < needle.length()) {
               needleIndex = 0;
           } else {
                return i;
           }
        }

        return -1;
    }

    /**
     * 这个解法 和上面的解法思路相同，效率有提升，但是利用了JDK提供的API
     */
    private int bruteForce(String haystack, String needle) {
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            if (haystack.substring(i, i + needle.length()).equals(needle)) {
                return i;
            }
        }
        return -1;
    }

    private Map<Character, Integer> constructLastTable(String needle) {
        Map<Character, Integer> result = new HashMap<>();
        for (int i = 0; i < needle.length(); i++) {
            char curChar = needle.charAt(i);
            result.put(curChar, i);
        }
        return result;
    }

    private int boyerMoore(String haystack, String needle) {
        Map<Character, Integer> lastTable = constructLastTable(needle);

        int i = needle.length()-1;
        int j = needle.length()-1;
        while(i < haystack.length()) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                if (j == 0) {
                    return i;
                } else {
                    i--;
                    j--;
                }
            } else {
                if (lastTable.containsKey(haystack.charAt(i))) {
                    if (lastTable.get(haystack.charAt(i)) < j) {
                        i += needle.length() - lastTable.get(haystack.charAt(i)) - 1;
                        j = needle.length()-1;
                    } else {
                        i += needle.length() - j;
                        j = needle.length()-1;
                    }
                } else {
                    i += needle.length();
                    j = needle.length()-1;
                }
            }
        }
        return -1;
    }

    private int kmp(String haystack, String needle) {
        int[] failureTable = constructFailureTable(needle);

        int i = 0;
        int j = 0;
        while (i < haystack.length()) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                if (j == needle.length()-1) {
                    return i - needle.length() + 1;
                }
                i++;
                j++;
            } else {
                if (j == 0) {
                    i++;
                } else {
                    j = failureTable[j-1];
                }
            }
        }
        return -1;
    }

    private int[] constructFailureTable(String needle) {
        int i = 0;
        int j = 1;
        int[] result = new int[needle.length()];
        result[0] = 0;
        while(j < needle.length()) {
            if (needle.charAt(i) == needle.charAt(j)) {
                result[j] = i + 1;
                i++;
                j++;
            } else {
                if (i == 0) {
                    result[j] = 0;
                    j++;
                } else {
                    i = result[i-1];
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(strStr("hello", "ll"));
        System.out.println(strStr("aaaaa", "aab"));
    }
}
