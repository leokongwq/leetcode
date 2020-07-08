package com.leokongwq.algorithm.leetcode;

import java.util.Arrays;

/**
 * @author jiexiu
 * created 2019/1/16 - 20:50
 *
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 *
 * Note:
 *
 * The number of elements initialized in nums1 and nums2 are m and n respectively.
 * You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
 * Example:
 *
 * Input:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 *
 * Output: [1,2,2,3,5,6]
 */
public class Question88MergeSortedArray {

    /**
     * 合并有序数组
     * nums1 元素个数 m, nums2 元素个数 n
     * nums 长度 length >= m + n 也就是说不能开辟新的空间
     *
     * 1,2,4,5,6,0
     * 3
     */
    private static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = 0, j = 0;
        int k = m;
        while (i < nums1.length && j < n) {
            if (nums1[i] <= nums2[j]) {
                i++;
            } else {
                //移动元素
                for (int p = k; p > i; p--) {
                    nums1[p] = nums1[p - 1];
                }
                k++;
                nums1[i] = nums2[j];
                //比较下一个元素
                i++;
                j++;
            }
        }
        // nums2 还有剩余元素
        if (j < n) {
            while (j < n) {
                nums1[k++] = nums2[j];
                j++;
            }
        }
    }

    /**
     * 从尾到头进行比较
     */
    private static void mergeV1(int[] nums1, int m, int[] nums2, int n) {
        int tailX = m - 1, tailY = n - 1, mergedTail = m + n - 1;
        while (mergedTail >= 0) {
            // nums1[] runs out
            if (tailX < 0) {
                // num2 有剩余，说明剩余的元素都是最小的部分
                nums1[mergedTail--] = nums2[tailY--];
            } else if (tailY < 0) {
                // nums2 为空，nums1的剩余元素本身就是有序，结束
                break;
            } else if (nums1[tailX] > nums2[tailY]) {
                //最大的肯定在最后
                nums1[mergedTail--] = nums1[tailX--];
            } else {
                nums1[mergedTail--] = nums2[tailY--];
            }
        }
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1,2,3,0,0,0};
        int[] nums2 = new int[]{2,5,6};
        merge(nums1, 3, nums2, 3);

//        int[] nums1 = new int[]{1,2,4,5,6,0};
//        int[] nums2 = new int[]{3};
//        merge(nums1, 5, nums2, 1);

        Arrays.stream(nums1).forEach(num -> System.out.print(num + ","));
        System.out.println();
    }
}
