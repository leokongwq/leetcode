package com.leokongwq.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jiexiu
 * created 2020/7/8 - 23:05
 *
 * medium
 *
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
 *
 * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
 *
 * 示例1:
 *
 * 输入: s1 = "ab" s2 = "eidbaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 *  
 *
 * 示例2:
 *
 * 输入: s1= "ab" s2 = "eidboaoo"
 * 输出: False
 *  
 *
 * 注意：
 *
 * 输入的字符串只包含小写字母
 * 两个字符串的长度都在 [1, 10,000] 之间
 *
 */
public class Question567StringContains {

    /**
     * 算法的实现思路如下：
     *
     * 1. 扩大窗口知道窗口大小和目标串的长度相同。
     * 2. 判断窗口的字符及其个数是否和目标串中的字符及其个数完全相同
     * 3. 如果不同，则缩小窗口。
     * 4. 继续扩大窗口，重复2，3，4
     */
    private boolean checkInclusion(String s1, String s2) {
        if (s2 == null || s1 == null) {
            return false;
        }
        if (s2.length() < s1.length()) {
            return false;
        }

        Map<Character, Integer> ori = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            char ch = s1.charAt(i);
            ori.put(ch, ori.getOrDefault(ch, 0) + 1);
        }

        int left = 0;
        int right = 0;
        int valid = 0;

        Map<Character, Integer> window = new HashMap<>();

        while (right < s2.length()) {
            char ch = s2.charAt(right);

            right++;
            //判断释放是目标串中的字符，是则加入判断窗口
            if (ori.containsKey(ch)) {
                //更新窗口值
                window.put(ch, window.getOrDefault(ch, 0) + 1);
                //如果相同字符的个数也相同，说明找到一个可以完全匹配的字符
                if (window.get(ch).intValue() == ori.get(ch)) {
                    valid++;
                }
            }
            //如果当前窗口大小和目标的长度相同，则窗口内的子串可能匹配目标串，需要进一步判断
            while ((right - left) >= s1.length()) {
                // 在这里判断是否找到了合法的子串
                if (valid == ori.size()) {
                    return true;
                }
                char c = s2.charAt(left);
                //缩小窗口
                left++;

                // 是目标串的字符，则需要更新计数
                if (ori.getOrDefault(c, 0) > 0) {
                    if (window.get(c).intValue() == ori.get(c)) {
                        valid--;
                    }
                    window.put(c, window.get(c) - 1);
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Question567StringContains solution = new Question567StringContains();
        System.out.println(solution.checkInclusion("ab", "eidbaooo"));
        System.out.println(solution.checkInclusion("ab", "eidboaoo"));
    }
}
