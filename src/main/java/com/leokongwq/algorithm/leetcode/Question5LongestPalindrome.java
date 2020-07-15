package com.leokongwq.algorithm.leetcode;

/**
 * @author jiexiu
 * created 2020/7/14 - 22:12
 * <p>
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * <p>
 * 示例 1：
 * <p>
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 * <p>
 * 输入: "cbbd"
 * 输出: "bb"
 */
public class Question5LongestPalindrome {

    /**
     * 动态规划
     *
     * dp[i][j]表示s中下标为[i,j]的子串是否是回文串
     * <p>
     * dp[i][j] = true 表示是回文串
     * dp[i][j] = false 表示不是回文串，此时有2中可能
     * 1. i > j 此时 s[i,j] 本身不合法。
     * 2. s[i,j] 本身不是一个回文串
     * <p>
     * 状态转移方程：P（i,j）= P（i+1, j-1）and （Si == Sj）
     * <p>
     * 时间复杂度 O （n^2）
     * 空间复杂度 O （n^2）
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        if (s.length() == 2 && s.charAt(0) == s.charAt(1)) {
            return s;
        }
        int len = s.length();
        //

        boolean[][] dp = new boolean[len][len];
        dp[0][0] = true;
        if (s.charAt(0) == s.charAt(1)) {
            dp[0][1] = true;
        }

        String result = "";
        //二维状态数组，必然是双重循环
        //枚举子串的长度 l + 1
        for (int l = 0; l < len; l++) {
            //枚举子串的起始位置 i，这样可以通过 j = i + l 得到子串的结束位置
            for (int i = 0; i < len; i++) {
                int j = i + l;
                if (j >= len) {
                    break;
                }
                //i == j 单个字母的子串必然是回文的
                if (l == 0) {
                    dp[i][j] = true;
                } else if (l == 1) {
                    dp[i][j] = (s.charAt(i) == s.charAt(j));
                } else {
                    dp[i][j] = dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j);
                }
                //更新结果
                if (dp[i][j] && l + 1 > result.length()) {
                    result = s.substring(i, j + 1);
                }
            }
        }
        return result;
    }

    /**
     * 中心扩展算法
     * 枚举所有的「回文中心」并尝试「扩展」，直到无法扩展为止，此时的回文串长度即为此「回文中心」下的最长回文串长度。
     * 我们对所有的长度求出最大值，即可得到最终的答案。
     *
     * 时间复杂度：O(n^2) 其中 n 是字符串的长度。长度为 1 和 1 的回文中心分别有 n 和 n-1 个，每个回文中心最多会向外扩展 O(n) 次。
     *
     * 空间复杂度：O(1)
     *
     */
    public String longestPalindromeV1(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }

        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }

    public static void main(String[] args) {
        Question5LongestPalindrome longestPalindrome = new Question5LongestPalindrome();
        System.out.println(longestPalindrome.longestPalindrome("babad"));
        System.out.println(longestPalindrome.longestPalindrome("cbbd"));

        System.out.println(longestPalindrome.longestPalindromeV1("aba"));
    }
}
