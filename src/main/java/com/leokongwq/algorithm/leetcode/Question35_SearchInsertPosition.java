package com.leokongwq.algorithm.leetcode;

/**
 * @author jiexiu
 * created 2018/12/20 - 22:27
 *
 * Given a sorted array and a target value, return the index if the target is found.
 * If not, return the index where it would be if it were inserted in order.
 *
 * You may assume no duplicates in the array.
 *
 * Example 1:
 *
 * Input: [1,3,5,6], 5
 * Output: 2
 * Example 2:
 *
 * Input: [1,3,5,6], 2
 * Output: 1
 * Example 3:
 *
 * Input: [1,3,5,6], 7
 * Output: 4
 * Example 4:
 *
 * Input: [1,3,5,6], 0
 * Output: 0
 */
public class Question35_SearchInsertPosition {

    /**
     * 最简单的解法，没有利用数组的有序特性
     */
    private static int searchInsert(int[] nums, int target) {
        if (nums == null) {
            return -1;
        }
        if (nums.length == 0) {
            return 0;
        }
        int i = 0;
        while (i < nums.length && nums[i] < target) {
            i++;
        }
        //never happens
        return i;
    }

    private static int searchInsertV1(int[] nums, int target) {
        if (nums == null) {
            return -1;
        }
        if (nums.length == 0) {
            return 0;
        }

        int low = 0, high = nums.length - 1;
        int mid = 0;

        while (low <= high) {
            mid = low + (high - low) / 2;
            if (nums[mid] >= target) {
                if (mid == 0) {
                    return 0;
                }
                if (nums[mid - 1] < target) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            } else if (nums[mid] < target) {
                low = mid + 1;
            }
        }
        if (mid == nums.length - 1 ) {
            return mid + 1;
        }
        return mid;
    }

    public static void main(String[] args) {
        System.out.println(searchInsert(new int[]{1,3,5,6}, 5));
        System.out.println(searchInsert(new int[]{1,3,5,6}, 2));
        System.out.println(searchInsert(new int[]{1,3,5,6}, 7));
        System.out.println(searchInsert(new int[]{1,3,5,6}, 0));

//        System.out.println(searchInsertV1(new int[]{1,3,5,6}, 5));
//        System.out.println(searchInsertV1(new int[]{1,3,5,6}, 2));
//        System.out.println(searchInsertV1(new int[]{1,3,5,6}, 7));
//        System.out.println(searchInsertV1(new int[]{1,3,5,6}, 0));

    }
}
