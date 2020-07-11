package com.leokongwq.algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jiexiu
 * created 2020/7/10 - 20:53
 *
 * 找到字符串中所有字母异位词
 *
 * medium
 *
 * 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
 *
 * 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
 *
 * 说明：
 *
 * 字母异位词指字母相同，但排列不同的字符串。
 * 不考虑答案输出的顺序。
 * 示例 1:
 *
 * 输入:
 * s: "cbaebabacd" p: "abc"
 *
 * 输出:
 * [0, 6]
 *
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
 *  示例 2:
 *
 * 输入:
 * s: "abab" p: "ab"
 *
 * 输出:
 * [0, 1, 2]
 *
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。
 *
 */
public class Question438FindAnagrams {

    public List<Integer> findAnagrams(String s, String p) {

        Map<Character, Integer> target = new HashMap<>();
        for (char ch : p.toCharArray()) {
            target.put(ch, target.getOrDefault(ch, 0) + 1);
        }
        Map<Character, Integer> window = new HashMap<>();

        int left = 0;
        int right = 0;
        int valid = 0;

        List<Integer> result = new ArrayList<>();

        while (right < s.length()) {
            char ch = s.charAt(right);
            right++;
            if (target.containsKey(ch)) {
                window.put(ch, window.getOrDefault(ch, 0) + 1);

                if (window.get(ch).intValue() == target.get(ch)) {
                    valid++;
                }
            }

            //窗口的大小和p的长度相同，需要判断是否是异位词
            while (right - left == p.length()) {
                if (validAnagram(window, target)) {
                    result.add(left);
                }
                char leftChar = s.charAt(left);

                if (window.getOrDefault(leftChar, 0) > 0) {
                    if (window.get(leftChar).intValue() == target.get(leftChar)) {
                        valid--;
                    }

                    window.put(leftChar, window.get(leftChar) - 1);
                }
                left++;
            }
        }

        return result;
    }

    private boolean validAnagram(Map<Character, Integer> window, Map<Character, Integer> target) {
        for (Map.Entry<Character, Integer> entry : target.entrySet()) {
            if (!window.containsKey(entry.getKey())
                    || window.getOrDefault(entry.getKey(), 0).intValue() != entry.getValue()) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Question438FindAnagrams solution = new Question438FindAnagrams();
        System.out.println(solution.findAnagrams("cbaebabacd", "abc"));
        System.out.println(solution.findAnagrams("abab", "ab"));
        System.out.println(solution.findAnagrams("baa", "aa"));
    }
}
