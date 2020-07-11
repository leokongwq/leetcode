package com.leokongwq.algorithm.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jiexiu
 * created 2020/7/10 - 21:14
 *
 * medium
 *
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 */
public class Question3LengthOfLongestSubstring {

    private int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[] state = new int[s.length()];
        state[0] = 1;
        Map<Character, Integer> window = new HashMap<>();
        window.put(s.charAt(0), 1);

        for (int i = 1; i < s.length(); i++) {
            if (window.containsKey(s.charAt(i))) {
                int j = i - 1;
                Map<Character, Integer> temp = new HashMap<>();
                while (j >= 0) {
                    if (s.charAt(j) == s.charAt(i) || temp.containsKey(s.charAt(j))) {
                        break;
                    }
                    temp.put(s.charAt(j), 1);
                    j--;
                }
                state[i] = i - j;
            } else {
                state[i] = state[i - 1] + 1;
                window.put(s.charAt(i), 1);
            }
        }

        return Arrays.stream(state).max().getAsInt();
    }

    /**
     * 还是使用滑动窗口解决
     */
    private int lengthOfLongestSubstringV1(String s) {
        Map<Character, Integer> window = new HashMap<>();

        int left = 0, right = 0;
        // 记录结果
        int res = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            // 进行窗口内数据的一系列更新
            window.put(c, window.getOrDefault(c, 0) + 1);

            // 判断左侧窗口是否要收缩
            while (window.get(c) > 1) {
                char d = s.charAt(left);
                left++;
                // 进行窗口内数据的一系列更新
                window.put(d, window.getOrDefault(d, 0) - 1);
            }
            // 在这里更新答案
            res = Math.max(res, right - left);
        }
        return res;
    }

    public static void main(String[] args) {
        Question3LengthOfLongestSubstring solution = new Question3LengthOfLongestSubstring();

//        System.out.println(solution.lengthOfLongestSubstring("abcabcbb"));
//        System.out.println(solution.lengthOfLongestSubstring("bbbbb"));
//        System.out.println(solution.lengthOfLongestSubstring("pwwkew"));
//
//        System.out.println(solution.lengthOfLongestSubstring("dvdf"));
//        System.out.println(solution.lengthOfLongestSubstring("abba"));


        System.out.println(solution.lengthOfLongestSubstringV1("abcabcbb"));
        System.out.println(solution.lengthOfLongestSubstringV1("bbbbb"));
        System.out.println(solution.lengthOfLongestSubstringV1("pwwkew"));

        System.out.println(solution.lengthOfLongestSubstringV1("dvdf"));
        System.out.println(solution.lengthOfLongestSubstringV1("abba"));
    }
}
