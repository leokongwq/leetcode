package com.leokongwq.algorithm.leetcode;

import java.util.Arrays;

/**
 * @author jiexiu
 * created 2019/1/21 - 16:09
 * <p>
 * Given an array, rotate the array to the right by k steps, where k is non-negative.
 * <p>
 * Example 1:
 * <p>
 * Input: [1,2,3,4,5,6,7] and k = 3
 * Output: [5,6,7,1,2,3,4]
 * Explanation:
 * rotate 1 steps to the right: [7,1,2,3,4,5,6]
 * rotate 2 steps to the right: [6,7,1,2,3,4,5]
 * rotate 3 steps to the right: [5,6,7,1,2,3,4]
 * Example 2:
 * <p>
 * Input: [-1,-100,3,99] and k = 2
 * Output: [3,99,-1,-100]
 * Explanation:
 * rotate 1 steps to the right: [99,-1,-100,3]
 * rotate 2 steps to the right: [3,99,-1,-100]
 * Note:
 * <p>
 * Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
 * Could you do it in-place with O(1) extra space?
 */
public class Question189RotateArray {

    /**
     * 将数组倒数的k个元素 移动到数组的最前面
     * 时间复杂度 k * (n - k)
     */
    private static void rotateV1(int[] nums, int k) {
        if (k <= 0) {
            return;
        }
        //防止k大于nums.length
        k = k % nums.length;
        int m = 0;
        for (int i = nums.length - k; i < nums.length; i++) {
            int temp = nums[i];
            for (int j = i; j > m; j--) {
                nums[j] = nums[j - 1];
            }
            nums[m++] = temp;
        }
    }

    /**
     * 三次反转，1：反转前部分，2：反转后部分，3：整个反转。
     */
    private static void rotateV2(int[] nums, int k) {
        if (k <= 0) {
            return;
        }
        //防止k大于nums.length
        k = k % nums.length;

        reverse(nums, 0, nums.length - k - 1);
        reverse(nums, nums.length - k, nums.length - 1);
        reverse(nums, 0, nums.length - 1);
    }

    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            ++start;
            --end;
        }
    }

    private static void rotateV3(int[] nums, int k) {
        int size = nums.length;
        int tmp = 0;
        int currentIndex = 0;

        //you need exactly num.length swaps
        for (int i = 0; i < size; i++) {
            //Switch with +k position
            int switchIndex = (currentIndex + k * (i + 1)) % size;
            tmp = nums[switchIndex];
            nums[switchIndex] = nums[currentIndex];
            nums[currentIndex] = tmp;
            if (switchIndex == currentIndex) {
                currentIndex++;
            }
            System.out.println(Arrays.toString(nums));
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};
//        rotateV1(nums, 3);
//        rotateV1(nums, 2);
//        rotateV1(nums, 1);
//        rotateV1(nums, -1);
//          rotateV1(nums, 6);
//        System.out.println(Arrays.toString(nums));

//        rotateV2(nums, 3);
        rotateV3(nums, 3);
        System.out.println(Arrays.toString(nums));
    }
}
