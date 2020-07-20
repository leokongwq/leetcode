package com.leokongwq.algorithm.leetcode;

/**
 * @author jiexiu
 * created 2019/1/21 - 20:43
 * <p>
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n,
 * find the one that is missing from the array.
 * <p>
 * Example 1:
 * <p>
 * Input: [3,0,1]
 * Output: 2
 * Example 2:
 * <p>
 * Input: [9,6,4,2,3,5,7,0,1]
 * Output: 8
 * Note:
 * Your algorithm should run in linear runtime complexity.
 * Could you implement it using only constant extra space complexity?
 */
public class Question268MissingNumber {

    /**
     * 题目： 包含 N 个不同的整数（取值范围[0, N]）的数组, 找到缺失的数字
     * 要求： 算法是的时间复杂度为：线性
     * 是否可以只使用常数级别的空间复杂度
     * <p>
     * 思路：
     * note: 数组长度为n, 数组中最大的元素不超过n, 可以利用位图思想
     * 1. 先排序，再遍历数组，找到差值大于1的相邻2个元素，中间的数就是缺失的数字，
     * 时间复杂度O(N*logN + N) 不满足题目要求
     * <p>
     * 2. 用类似位图的思路初始化一个大小为 n+1 的boolean数组，
     * 遍历整数数组，以元素值作为boolean数组的下标，填充boolean数组
     * 遍历boolean数组，元素值为false的下标就是缺失的元素
     */
    private static int missingNumber(int[] nums) {
        boolean[] array = new boolean[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            array[nums[i]] = true;
        }
        for (int i = 0; i < array.length; i++) {
            if (!array[i]) {
                return i;
            }
        }
        return 0;
    }

    /**
     * 思路：根据题目的要求，可知 整个数组(包含缺失的数组)是一个等差数列
     * 利用等差数列的求和公式，计算和 - 整数数组元素的和， 差值就是缺失的数字
     * 前n项和公式为 Sn=n*a1+n(n-1)d/2或Sn=n(a1+an)/2
     */
    private static int missingNumberV1(int[] nums) {
        int len = nums.length;
        int sumExp = len * (len + 1) / 2;

        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        return sumExp - sum;
    }

    /**
     * 利用位操作
     * 1.一个数和本身进行按位异或结果为0
     * 2.一个数和0进行异或操作结果为本身
     * 3.异或操作满足交换律和结合律： 2 ^ 3 ^ 2 = 3 ^ (2 ^ 2) = 3 ^ 0 = 3
     */
    private static int missingNumberV2(int[] nums) {
        int res = 0;

        // 先和新补的索引异或一下
        res = res ^ nums.length;

        for (int i = 0; i < nums.length; i++) {
            res ^= (i ^ nums[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(missingNumber(new int[]{0, 1, 3}));
        System.out.println(missingNumberV1(new int[]{0, 1, 3}));
        System.out.println(missingNumberV2(new int[]{0, 1, 3}));
    }
}
