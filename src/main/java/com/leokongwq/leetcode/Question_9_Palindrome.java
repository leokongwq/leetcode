package com.leokongwq.leetcode;

/**
 * @author jiexiu
 * created 2018/12/13 - 22:42
 *
 * 判断一个数是否是回文数字
 * 121 true
 * -121 false
 * 10 false
 */
public class Question_9_Palindrome {

    private static boolean isPalindrome(int x) {
        if (x <= 0) {
            return false;
        }
        int reversNum = Question_7_ReversInteger.reverse(x);
        return reversNum == x;
    }

    private static boolean isPalindromeV1(int x) {
        if (x < 0) {
            return false;
        }
        int origin = x;
        long result = 0;
        int i = 0;
        while (origin != 0) {
            int digit = origin % 10;
            origin = origin / 10;
            if (i == 0) {
                result = digit;
            } else {
                result = result * 10 + digit;
            }
            i++;
        }
        return result == x;
    }

    public static void main(String[] args) {
        System.out.println(isPalindromeV1(121));
        System.out.println(isPalindromeV1(-121));
        System.out.println(isPalindromeV1(10));
        System.out.println(isPalindromeV1(Integer.MIN_VALUE));
        System.out.println(isPalindromeV1(Integer.MAX_VALUE));
        System.out.println(isPalindromeV1(1221));
    }
}
