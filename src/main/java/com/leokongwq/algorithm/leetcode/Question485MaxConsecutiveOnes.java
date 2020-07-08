package com.leokongwq.algorithm.leetcode;

/**
 * @author jiexiu
 * created 2019/1/22 - 18:18
 *
 * Given a binary array, find the maximum number of consecutive 1s in this array.
 *
 * Example 1:
 * Input: [1,1,0,1,1,1]
 * Output: 3
 * Explanation: The first two digits or the last three digits are consecutive 1s.
 *     The maximum number of consecutive 1s is 3.
 * Note:
 *
 * The input array will only contain 0 and 1.
 * The length of input array is a positive integer and will not exceed 10,000
 *
 * 题目：给定一个二进制数组，找出最大的连续1的个数
 * 例如：Input: [1,1,0,1,1,1]
 * 前2个元素， 后三个都是连续的， 最大连续1的个数就是3
 *
 * 注意：
 * 1. 数组的元素只有 0 和 1
 * 2. 数组长度是正整数， 并且长度不超过 10000
 */
public class Question485MaxConsecutiveOnes {

    private static int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int len = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                len++;
            } else {
                //说明前面有为1的元素
                if (len > 0) {
                    max = Math.max(max, len);
                    len = 0;
                }
            }
        }
        return Math.max(max, len);
    }

    public static void main(String[] args) {
        System.out.println(findMaxConsecutiveOnes(new int[]{1}));
        System.out.println(findMaxConsecutiveOnes(new int[]{0}));
        System.out.println(findMaxConsecutiveOnes(new int[]{0,0,0,0}));
        System.out.println(findMaxConsecutiveOnes(new int[]{1,1,1,1,1}));
        System.out.println(findMaxConsecutiveOnes(new int[]{1,1,0,1,1,1}));
    }
}
