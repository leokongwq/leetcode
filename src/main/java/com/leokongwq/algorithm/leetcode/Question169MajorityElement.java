package com.leokongwq.algorithm.leetcode;

/**
 * @author jiexiu
 * created 2019/1/21 - 08:20
 * Given an array of size n, find the majority element.
 * The majority element is the element that appears more than ⌊ n/2 ⌋ times.
 * <p>
 * You may assume that the array is non-empty and the majority element always exist in the array.
 * <p>
 * Example 1:
 * <p>
 * Input: [3,2,3]
 * Output: 3
 * Example 2:
 * <p>
 * Input: [2,2,1,1,1,2,2]
 * Output: 2
 *
 * 思路：
 * 1. 遍历数组，将元素和出现的次数保证在HashMap中， 遍历HashMap，查找出现次数大于 n/2 的元素
 * 2.1  假设第一个元素就是出现次数的元素 记作 major ，计数器初始化为：1
 * 2.2  遍历 i = [1, n -1] 之间的元素，如果 和 nums[i] == major， 则 计数器 count + 1
 * 2.3  如果 nums[i] != major, 则 计数器 count - 1
 * 2.4  如果计数器减为0 ，则更新 major， 重新计数 1。
 * 减为0表示已经遍历的元素中， 相同元素的个数 = 其余不同元素的个数的总和，由于题目指出，major元素一定存在
 * 那么， 剩余的元素中一定包含 major元素
 */
public class Question169MajorityElement {

    private static int majorityElement(int[] nums) {
        int major = nums[0], count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                count++;
                major = nums[i];
            } else if (major == nums[i]) {
                count++;
            } else {
                count--;
            }
        }
        return major;
    }

    public static void main(String[] args) {
        System.out.println(majorityElement(new int[]{1}));
        System.out.println(majorityElement(new int[]{3,2,3}));
        //这个case 使得 遍历到 n/2 失效，
        System.out.println(majorityElement(new int[]{2,2,1,1,1,2,2}));
        System.out.println(majorityElement(new int[]{2,2,1,1,1,1,1,2,2}));
        System.out.println(majorityElement(new int[]{6, 5, 5}));
        System.out.println(majorityElement(new int[]{1,2,1,2,1,1}));
    }
}
