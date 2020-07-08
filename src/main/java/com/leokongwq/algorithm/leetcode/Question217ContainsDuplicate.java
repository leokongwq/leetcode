package com.leokongwq.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jiexiu
 * created 2019/1/21 - 20:28
 * Given an array of integers, find if the array contains any duplicates.
 *
 * Your function should return true if any value appears at least twice in the array,
 * and it should return false if every element is distinct.
 *
 * Example 1:
 *
 * Input: [1,2,3,1]
 * Output: true
 * Example 2:
 *
 * Input: [1,2,3,4]
 * Output: false
 * Example 3:
 *
 * Input: [1,1,1,3,3,4,3,2,4,2]
 * Output: true
 *
 */
public class Question217ContainsDuplicate {

    private static boolean containsDuplicate(int[] nums) {
        boolean result = false;
        Map<Integer, Integer> numToCount = new HashMap<>();
        for (int num : nums) {
            if (numToCount.containsKey(num)) {
                return true;
            }
            numToCount.put(num, 1);
        }
        return result;
    }

    public static void main(String[] args) {

    }
}
