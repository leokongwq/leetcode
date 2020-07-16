package com.leokongwq.algorithm.leetcode;

import java.util.*;

/**
 * @author : jiexiu
 * @date : 2020-07-16 08:55
 *
 * 给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。
 *
 * 找到所有在 [1, n] 范围之间没有出现在数组中的数字。
 *
 * 您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。
 *
 * 示例:
 *
 * 输入:
 * [4,3,2,7,8,2,3,1]
 *
 * 输出:
 * [5,6]
 *
 **/
public class Question448FindDisappearedNumbers {

	public List<Integer> findDisappearedNumbers(int[] nums) {
		List<Integer> result = new LinkedList<>();
		if (nums == null || nums.length == 0) {
			return result;
		}
		Map<Integer, Integer> mapping = new HashMap<>();
		Arrays.stream(nums).forEach(num -> mapping.compute(num, (key, value) -> {
			if (value == null) {
				return 1;
			}
			return value + 1;
		}));

		for (int i = 1; i <= nums.length; i++) {
			if (!mapping.containsKey(i)) {
				result.add(i);
			}
		}
		return result;
	}

	public List<Integer> findDisappearedNumbersV1(int[] nums) {
		if (nums == null || nums.length == 0) {
			return Collections.emptyList();
		}
		List<Integer> result = new LinkedList<>();
		Arrays.sort(nums);
		for (int i = 1; i <= nums.length; i++) {
			if (nums[i] != i + 1) {
				result.add(i);
			}
		}
		return result;
	}

	public static void main(String[] args) {
		Question448FindDisappearedNumbers disappearedNumbers = new Question448FindDisappearedNumbers();
		System.out.println(disappearedNumbers.findDisappearedNumbers(new int[]{4, 3, 2, 7, 8, 2, 3, 1}));
		System.out.println(disappearedNumbers.findDisappearedNumbersV1(new int[]{4, 3, 2, 7, 8, 2, 3, 1}));
	}
}
