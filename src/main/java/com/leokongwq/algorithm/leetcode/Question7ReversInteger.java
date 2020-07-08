package com.leokongwq.algorithm.leetcode;

/**
 * @author jiexiu
 * created 2018/12/13 - 20:30
 * 翻转整数 123 -> 321
 */
public class Question7ReversInteger {

    public static int reverse(int x) {
        if (x == Integer.MAX_VALUE || x == Integer.MIN_VALUE) {
            return 0;
        }
        int num = Math.abs(x);
        long result = num;
        int i = 0;
        while (num != 0) {
            int digit = num % 10;
            num = num / 10;
            if (i == 0) {
                result = digit;
            } else {
                result = result * 10 + digit;
            }
            i++;
        }
        // overflow return zero
        if (result > Integer.MAX_VALUE) {
            return 0;
        }
        if (x < 0) {
            return 0 - (int) result;
        }
        return (int) result;
    }

    public static void main(String[] args) {
        System.out.println(reverse(1));
        System.out.println(reverse(3));
        System.out.println(reverse(12));
        System.out.println(reverse(120));
        System.out.println(reverse(123));
        System.out.println(reverse(1234));
        System.out.println(reverse(-1234));

        System.out.println(reverse(Integer.MAX_VALUE));
    }
}
