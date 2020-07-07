package com.leokongwq.algorithm.leetcode;

/**
 * @author jiexiu
 * created 2019/1/16 - 20:23
 *
 * Given a non-empty array of digits representing a non-negative integer, plus one to the integer.
 *
 * The digits are stored such that the most significant digit is at the head of the list,
 * and each element in the array contain a single digit.
 *
 * You may assume the integer does not contain any leading zero, except the number 0 itself.
 *
 * Example 1:
 *
 * Input: [1,2,3]
 * Output: [1,2,4]
 * Explanation: The array represents the integer 123.
 * Example 2:
 *
 * Input: [4,3,2,1]
 * Output: [4,3,2,2]
 * Explanation: The array represents the integer 4321.
 */
public class Question62PlusOne {

    /**
     * 1. 非空数组
     * 2. 不是负数 >= 0
     * 3. 前缀不为0
     */
    private static int[] plusOne(int[] digits) {
        // 判断0
        if (digits[0] == 0) {
            return new int[]{1};
        }
        // 进位数
        int mod = 0;
        for (int i = digits.length - 1; i >= 0; i--) {
            int digit = (i == (digits.length - 1))  ? digits[i] + 1 : digits[i] + mod;
            mod = digit / 10;
            // 能否整除 10
            if (mod == 1) {
                digits[i] = digit % 10;
            } else {
                digits[i] += 1;
                return digits;
            }
        }
        //没有进位产生
        if (mod == 0) {
            return digits;
        }
        int[] result = new int[digits.length + 1];
        result[0] = mod;
        System.arraycopy(digits, 0, result, 1, result.length - 1);
        return result;
    }

    public static void main(String[] args) {
        int[] result1 = plusOne(new int[]{1,2,3});
        int[] result2 = plusOne(new int[]{4,3,2,1});
        int[] result3 = plusOne(new int[]{9,9,9,9});
        System.out.println("ok");
    }
}
