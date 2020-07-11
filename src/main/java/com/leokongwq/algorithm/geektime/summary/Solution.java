package com.leokongwq.algorithm.geektime.summary;

import com.leokongwq.algorithm.geektime.Printer;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numToPos = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            numToPos.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            Integer pos = numToPos.get(target - nums[i]);
            if (pos != null && pos != i) {
                return new int[]{i, numToPos.get(target - nums[i])};
            }
        }

        return new int[]{-1, -1};
    }

    public int[] twoSumSorted(int[] numbers, int target) {

        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        int[] res = twoSum(new int[]{3, 2, 4}, 6);
        Printer.printArray(res);
    }
}