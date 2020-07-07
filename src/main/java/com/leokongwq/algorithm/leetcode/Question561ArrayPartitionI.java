package com.leokongwq.algorithm.leetcode;

import java.util.Arrays;

/**
 * @author jiexiu
 * created 2019/1/23 - 14:58
 *
 * Given an array of 2n integers, your task is to group these integers into n pairs of integer,
 * say (a1, b1), (a2, b2), ..., (an, bn) which makes sum of min(ai, bi) for all i from 1 to n as large as possible.
 *
 * Example 1:
 * Input: [1,4,3,2]
 *
 * Output: 4
 * Explanation: n is 2, and the maximum sum of pairs is 4 = min(1, 2) + min(3, 4).
 * Note:
 * n is a positive integer, which is in the range of [1, 10000].
 * All the integers in the array will be in the range of [-10000, 10000].
 *
 * 给定一个长度为2n(偶数)的数组，分成n个小组，返回每组中较小值的和sum，使sum尽量大
 * https://blog.csdn.net/whl_program/article/details/70667333
 */
public class Question561ArrayPartitionI {

    /**
     * 分析：每个分组中2个元素的打下关系如下：
     *
     * a 远小于 b ： a 远小于 b, 则 b 这个大数就浪费了，不能增加 sum 的大小
     * a 小于等于 b ： a 和 b 的距离尽可能的小，这样每个组浪费的 增量就小， 结果就是最大的
     */
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int sum  = 0;
        for (int i = 0; i < nums.length; i+=2) {
            sum += nums[i];
        }
        return sum;
    }
}
