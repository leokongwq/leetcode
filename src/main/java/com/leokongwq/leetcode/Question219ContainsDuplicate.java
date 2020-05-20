package com.leokongwq.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jiexiu
 * created 2019/1/21 - 20:33
 * Given an array of integers and an integer k, find out whether there are two distinct
 * indices i and j in the array such that nums[i] = nums[j] and the absolute difference between i and j is at most k.
 *
 * Example 1:
 * Input: nums = [1,2,3,1], k = 3
 * Output: true
 *
 * Example 2:
 * Input: nums = [1,0,1,1], k = 1
 * Output: true
 *
 * Example 3:
 * Input: nums = [1,2,3,1,2,3], k = 2
 * Output: false
 */
public class Question219ContainsDuplicate {

    private static boolean containsNearbyDuplicate(int[] nums, int k) {
        boolean result = false;
        Map<Integer, Integer> numToCount = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (numToCount.containsKey(nums[i])) {
                int idx = numToCount.get(nums[i]);
                if (i - idx <= k) {
                    return true;
                }
            }
            numToCount.put(nums[i], i);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(containsNearbyDuplicate(new int[]{1,2,3,1}, 3));
        System.out.println(containsNearbyDuplicate(new int[]{1,0,1,1}, 1));
        System.out.println(containsNearbyDuplicate(new int[]{1,2,3,1,2,3}, 2));

        System.out.println(containsNearbyDuplicate(new int[]{99, 99}, 2));
    }
}
