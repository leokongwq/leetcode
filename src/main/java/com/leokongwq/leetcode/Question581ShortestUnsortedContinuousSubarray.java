package com.leokongwq.leetcode;

/**
 * @author jiexiu
 * created 2019/1/24 - 12:00
 *
 * Given an integer array, you need to find one continuous subarray
 * that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order, too.
 *
 * You need to find the shortest such subarray and output its length.
 *
 * Example 1:
 * Input: [2, 6, 4, 8, 10, 9, 15]
 * Output: 5
 * Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
 * Note:
 * Then length of the input array is in range [1, 10,000].
 * The input array may contain duplicates, so ascending order here means <=.
 *
 * 题目：给定一个整数数组，找出元素连续的子数组，对该子数组进行升序排列后，整数数组就都是升序排列的，
 * 找出这样长度最小的数组，返回其长度。
 *
 *
 */
public class Question581ShortestUnsortedContinuousSubarray {

    /**
     * 思路
     * 1. 从右->左 查找第一个不满足升序条件的下标 leftIndex
     * 2. 从左->右 查找第一个不满足升序条件的下标 rightIndex
     * (rightIndex - leftIndex) + 1 就是待排序子数组的长度
     *
     * 注意：数组可能有重复元素
     */
    private static int findUnsortedSubarray(int[] nums) {
        if (nums == null || nums.length == 1) {
            return 0;
        }
        //表示从右到左的子数组中最小的元素， 也是从左到右已经排序的子数组中最大的元素
        int leftMin = nums[nums.length - 1];
        //表示待排序左下标
        int rightIndex = -1;

        for (int i = nums.length - 1; i >= 0; i--) {
            //因为有重复元素，所以必须是 > 不能是 >=
            if (nums[i] > leftMin) {
                rightIndex = i;
            } else {
                leftMin = Math.min(leftMin, nums[i]);
            }
        }
        if (rightIndex == -1) {
            return 0;
        }

        int rigthMax = nums[0];
        int leftIndex = -1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < rigthMax) {
                leftIndex = i;
            } else {
                rigthMax = Math.max(rigthMax, nums[i]);
            }
        }

        return (leftIndex - rightIndex) + 1;
    }

    public static void main(String[] args) {
        System.out.println(findUnsortedSubarray(new int[]{2, 6, 4, 8, 10, 9, 15}));
    }
}
