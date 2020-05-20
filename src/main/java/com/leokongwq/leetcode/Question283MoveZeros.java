package com.leokongwq.leetcode;

import java.util.Arrays;

/**
 * @author jiexiu
 * created 2019/1/22 - 13:59
 * Given an array nums, write a function to move all 0's to the end of it
 * while maintaining the relative order of the non-zero elements.
 *
 * Example:
 *
 * Input: [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 * Note:
 *
 * You must do this in-place without making a copy of the array.
 * Minimize the total number of operations.
 */
public class Question283MoveZeros {
    /**
     * 将数组中的 0 全部移动到末尾。
     * 注意：
     * 1.原有数字的相对位置不变, 不能进行排序
     * 2.不能开辟额外的空间，原地通过交换来实现
     *
     * 思路：每次遇到 0, 移动后面的元素来覆盖当前为 0 的元素，直到当前元素不为0 或 达到最大的移动次数
     * 记录 移动的总次数 zeroCnt，就是整个数组中元素为0的个数，
     * 将数组尾部 zeroCnt 个元素 设置为0
     *
     * 时间复杂度为 O(n2)
     */
    private static void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 1) {
            return;
        }
        int zeroCnt = 0;
        for (int i = 0; i < nums.length - zeroCnt; i++) {
            //[0,1,0,3,12] i = 0
            //[0, 0, 1] -> [0, 1, 0] -> [1, 0, 0]
            //[0, 0]
            //[1, 0]
            if (nums[i] == 0) {
                //使用while的原因在于，移动元素后，下一个元素还是0，
                //为了防止死循环，下标等于 i 时，剩余 nums.length-1 - i 个元素，所以最多只能循环 nums.length-1 - i 次
                while (nums[i] == 0 && zeroCnt < nums.length - 1 - i) {
                    //不能每次将 i 后面所有的元素进行移动，因为尾部的部分元素已经是从头部移动到尾部的0了
                    for (int j = i; j < nums.length - zeroCnt - 1; j++) {
                        nums[j] = nums[j+1];
                    }
                    zeroCnt++;
                }
            }
        }
        for (int i = nums.length - zeroCnt; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    /**
     * 双指针法
     */
    private static void moveZeroesV1(int[] nums) {
        int left = 0;
        int right = 0;

        while(right <= nums.length - 1) {
            if(nums[right] != 0 && nums[left] != 0) {
                right++;
                left++;
            } else {
                /*
                 * 找到第一个不为0的下标, 则 [left, right-1] 整个区间的元素都是 0
                 */
                while(right < nums.length - 1 && nums[right] == 0){
                    right++;
                }
                //交换 0 和 第一个非0元素
                swap(left, right, nums);
                left++;
                right++;
            }
        }
    }

    private static void swap(int pos1, int pos2, int[] nums) {
        int temp = nums[pos1];
        nums[pos1] = nums[pos2];
        nums[pos2] = temp;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0,1,0,3,12};
        moveZeroes(nums);
        System.out.println(Arrays.toString(nums));

        nums = new int[]{2,1};
        moveZeroes(nums);
        System.out.println(Arrays.toString(nums));

        nums = new int[]{0,1};
        moveZeroes(nums);
        System.out.println(Arrays.toString(nums));

        nums = new int[]{0, 0, 1};
        moveZeroes(nums);
        System.out.println(Arrays.toString(nums));

        nums = new int[]{1, 0};
        moveZeroes(nums);
        System.out.println(Arrays.toString(nums));

        nums = new int[]{0, 0};
        moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }
}
