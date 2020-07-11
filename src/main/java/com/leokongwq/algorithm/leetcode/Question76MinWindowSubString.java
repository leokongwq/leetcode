package com.leokongwq.algorithm.leetcode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author jiexiu
 * created 2020/7/8 - 15:23
 * <p>
 * 难度：hard
 * <p>
 * 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字符的最小子串。
 * <p>
 * 示例：
 * <p>
 * 输入: S = "ADOBECODEBANC", T = "ABC"
 * 输出: "BANC"
 * 说明：
 * <p>
 * 如果 S 中不存这样的子串，则返回空字符串 ""。
 * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
 * <p>
 * 注意：子串，说明是连续的, 题目要求包含目标传的所有字母，而不是完全相等
 * <p>
 * <p>
 * 滑动窗口算法的思路是这样：
 * <p>
 * 1、我们在字符串S中使用双指针中的左右指针技巧，初始化left = right = 0，把索引左闭右开区间[left, right)称为一个「窗口」。
 * <p>
 * 2、我们先不断地增加right指针扩大窗口[left, right)，直到窗口中的字符串符合要求（包含了T中的所有字符）。
 * <p>
 * 3、此时，我们停止增加right，转而不断增加left指针缩小窗口[left, right)，直到窗口中的字符串不再符合要求（不包含T中的所有字符了）。同时，每次增加left，我们都要更新一轮结果。
 * <p>
 * 4、重复第 2 和第 3 步，直到right到达字符串S的尽头。
 * <p>
 * 这个思路其实也不难，第 2 步相当于在寻找一个「可行解」，然后第 3 步在优化这个「可行解」，最终找到最优解，也就是最短的覆盖子串。左右指针轮流前进，窗口大小增增减减，窗口不断向右滑动，这就是「滑动窗口」这个名字的来历。
 * <p>
 * 下面画图理解一下，needs和window相当于计数器，分别记录T中字符出现次数和「窗口」中的相应字符的出现次数。
 *
 * 时间复杂度：最坏情况下左右指针对 ss 的每个元素各遍历一遍，哈希表中对 ss 中的每个元素各插入、删除一次，对 tt 中的元素各插入一次。每次检查是否可行会遍历整个 tt 的哈希表，哈希表的大小与字符集的大小有关，设字符集大小为 CC，则渐进时间复杂度为 O(C\cdot |s| + |t|)O(C⋅∣s∣+∣t∣)。
 * 空间复杂度：这里用了两张哈希表作为辅助空间，每张哈希表最多不会存放超过字符集大小的键值对，我们设字符集大小为 CC ，则渐进空间复杂度为 O(C)O(C)。
 *
 */
public class Question76MinWindowSubString {


    public String minWindow(String s, String t) {
        //目标串中字符和个数的映射
        Map<Character, Integer> ori = new HashMap<>();

        // 当前窗口里面的字符和个数
        Map<Character, Integer> cnt = new HashMap<>();

        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            ori.put(c, ori.getOrDefault(c, 0) + 1);
        }

        int l = 0, r = -1;
        int len = Integer.MAX_VALUE, ansL = -1, ansR = -1;
        int sLen = s.length();

        while (r < sLen) {
            //扩展窗口
            ++r;

            //满足条件的值加入窗口，更新窗口值
            if (r < sLen && ori.containsKey(s.charAt(r))) {
                cnt.put(s.charAt(r), cnt.getOrDefault(s.charAt(r), 0) + 1);
            }

            //如果需要，则缩小窗口（窗口可以缩小到0）
            while (check(ori, cnt) && l <= r) {
                //获取最新的满足条件的子串长度，并和之前计算的长度进行对比，更新结果子串的长度和起始位置下标
                if (r - l + 1 < len) {
                    len = r - l + 1;
                    ansL = l;
                    ansR = l + len;
                }
                //缩小满足条件的窗口时，需要更新窗口的值
                //每次缩小窗口都是为了求满足窗口内满足条件的最优值
                //缩小窗口后，如果不满足了，则继续扩大窗口，寻找满足条件的下一个值
                if (ori.containsKey(s.charAt(l))) {
                    cnt.put(s.charAt(l), cnt.getOrDefault(s.charAt(l), 0) - 1);
                }
                //缩小窗口
                ++l;
            }
        }
        return ansL == -1 ? "" : s.substring(ansL, ansR);
    }

    private boolean check(Map<Character, Integer> ori, Map<Character, Integer> cnt) {
        for (Map.Entry<Character, Integer> entry : ori.entrySet()) {
            Character key = entry.getKey();
            Integer val = entry.getValue();
            //当前窗口不包含目标串的所有字符
            if (cnt.getOrDefault(key, 0) < val) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Question76MinWindowSubString solution = new Question76MinWindowSubString();
        String res = solution.minWindow("ADOBECODEBANC", "ABC");
        System.out.println(res);
    }
}
