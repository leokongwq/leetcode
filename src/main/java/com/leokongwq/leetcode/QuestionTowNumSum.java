package com.leokongwq.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给的一个整形数组和一个目标数组, 求数组中2个元素相加后等于目标数子的2个元素下标
 * 如: [2, 7, 11, 15], target = 9,  取出的元素下标数组为[0, 1]
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/8/17
 * Time: 下午12:23
 * Email:leokongwq@gmail.com
 */
public class QuestionTowNumSum {
    /**
     * 首先能想到的办法, 简单暴力,但是时间复杂度是O(n2)
     * @param nums 待查询数组
     * @param target 目标数字
     * @return int[] 下标数组
     */
    public static int[] twoSumV1(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++){
            for (int j = i + 1; j < nums.length; j++){
                if (nums[j] == target - nums[i]) {
                    return new int[] { i, j };
                }
            }
        }
        return null;
    }

    /**
     * a + b = targe => b = target - a ;  遍历数组中的每一个元素, 然后查询数组中剩余的元素是否包含b, 则可以完成查找
     * 遍历数组时间复杂度是O(n) 如果能将每次查询b的时间复杂度控制在O(1),则整个算法的复杂度降低. HashMap可以满足这样的要求
     * @param nums 待查询数组
     * @param target 目标数字
     * @return int[] 下标数组
     */
    public static int[] twoSumV2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[] { i, map.get(complement) };
            }
        }
        return null;
    }

    /**
     * 不用单独将所有的元素放入map, 边遍历,边插入
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSumV3(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2, 7, 11, 4, 15, 4};
        long start = System.currentTimeMillis();
        int[] result = twoSumV1(arr, 9);
        long end = System.currentTimeMillis();
        System.out.println("耗时: " + (end  - start));
        System.out.println(result);
        start = System.currentTimeMillis();
        int[] result3 = twoSumV2(arr, 8);
        end = System.currentTimeMillis();
        System.out.println("耗时: " + (end  - start));
        System.out.println(result3);
    }
}
