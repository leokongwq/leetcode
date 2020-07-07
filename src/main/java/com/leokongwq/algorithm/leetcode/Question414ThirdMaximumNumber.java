package com.leokongwq.algorithm.leetcode;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * @author jiexiu
 * created 2019/1/22 - 15:45
 * <p>
 * Given a non-empty array of integers, return the third maximum number in this array.
 * If it does not exist, return the maximum number. The time complexity must be in O(n).
 * <p>
 * Example 1:
 * Input: [3, 2, 1]
 * <p>
 * Output: 1
 * <p>
 * Explanation: The third maximum is 1.
 * Example 2:
 * Input: [1, 2]
 * <p>
 * Output: 2
 * <p>
 * Explanation: The third maximum does not exist, so the maximum (2) is returned instead.
 *
 * Example 3:
 * Input: [2, 2, 3, 1]
 * <p>
 * Output: 1
 * <p>
 * Explanation: Note that the third maximum here means the third maximum distinct number.
 * Both numbers with value 2 are both considered as second maximum.
 */
public class Question414ThirdMaximumNumber {

    /**
     * 题目：给定一个非空数组，找出第三大的数， 如果不存在，则返回数组的最大值
     * 要求：时间复杂度为 O(n)
     * 注意：有重复元素
     * 可以认为该题是TopK的变种， 针对本题，就是Top3
     * <p>
     * 解法1：
     * 对整个数组重后，进行排序，查询倒数第3个元素，就是第三大
     * 该解法不符合题目要求，基于比较的排序算法，时间复杂度是 O(N * logN)
     * 非比较的排序算法，空间浪费又很大，
     *
     */
    private static int thirdMaxV1(int[] nums) {
        //也可以不去重，倒序遍历的时候，重复元素只记录一次
        nums = Arrays.stream(nums).distinct().sorted().toArray();
        if (nums.length < 3) {
            return nums[nums.length - 1];
        }
        if (nums.length == 3) {
            return nums[0];
        }
        return nums[nums.length - 3];
    }

    /**
     * 该算法使用的前提条件是 没有重复元素， 如果有重复元素就需要整体排序，
     * 在倒序遍历的时候，重复元素不能多次计数
     */
    private static int thirdMaxV2(int[] nums) {
        int n = nums.length;
        int cnt = 0;
        for (int i = 0; i < n - 1; i++) {
            cnt++;
            for (int j = i + 1; j < n; j++) {
                if (nums[j] < nums[i]) {
                    swap(nums, i, j);
                }
            }
            if (cnt == 3) {
                break;
            }
        }
        if (nums.length < 3) {
            return nums[nums.length - 1];
        }
        if (nums.length == 3) {
            return nums[0];
        }
        return nums[nums.length - 3];
    }

    /**
     * 高效的算法，三个指针。
     * 该是该算法有局限性，如果是求第 7， 8，9 大的数组呢？ 不可能使用那么多的指针的。
     * 扩展：如果要求第二大的数，可以使用2个指针
     */
    private static int  thirdMaxV3(int[] nums) {
        //第一大下标
        int first = 0;
        //第二大下标
        int second = -1;
        //第三大下标
        int third = -1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[first]) {
                third = second;
                second = first;
                first = i;
            }
            else if (nums[i] < nums[first] &&
                    (second == -1 || nums[i] > nums[second])) {
                third = second;
                second = i;
            }
            else if (second >= 0 && nums[i] < nums[second] &&
                    (third == -1 || nums[i] > nums[third])) {
                third = i;
            }
        }
        if (third < 0) {
            return nums[first];
        }
        return nums[third];
    }

    /**
     * 利用TreeSet的有序性和去重功能
     */
    private static int  thirdMaxV4(int[] nums) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int num : nums) {
            set.add(num);
        }
        if (set.size() < 3) {
            int n = 0;
            for (int num : set) {
                n++;
                if (n == set.size()) {
                    return num;
                }
            }
        } else if (set.size() == 3) {
            return set.iterator().next();
        } else {
            int n = 0;
            for (int num : set) {
                n++;
                if (n == set.size() - 2) {
                    return num;
                }
            }
        }
        //unreachable
        return -1;
    }

    private static void swap(int[] nums, int pos1, int pos2) {
        int temp = nums[pos1];
        nums[pos1] = nums[pos2];
        nums[pos2] = temp;
    }

    public static void main(String[] args) {
        System.out.println(thirdMaxV1(new int[]{3, 2, 1}));
        System.out.println(thirdMaxV1(new int[]{1, 2}));
        System.out.println(thirdMaxV1(new int[]{2, 2, 3, 1}));

        System.out.println("###################################");

        System.out.println(thirdMaxV4(new int[]{3, 2, 1}));
        System.out.println(thirdMaxV4(new int[]{1, 2}));
        System.out.println(thirdMaxV4(new int[]{2, 2, 3, 1}));

        System.out.println("###################################");

        System.out.println(thirdMaxV2(new int[]{3, 2, 1}));
        System.out.println(thirdMaxV2(new int[]{1, 2, 3}));
        System.out.println(thirdMaxV2(new int[]{4,3,2,1}));
    }
}
