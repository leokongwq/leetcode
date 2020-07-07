package com.leokongwq.algorithm.leetcode;

/**
 * @author : jiexiu
 * @program : leetcode
 * @date : 2020-06-16 23:41
 **/
public class Question1480 {

	private static int[] runningSum(int[] nums) {
		if (nums == null || nums.length == 1) {
			return nums;
		}
		int[] res = new int[nums.length];
		res[0] = nums[0];
		for (int i = 1; i < nums.length; i++) {
			res[i] = res[i - 1] + nums[i];
		}
		return res;
	}

	public static void main(String[] args) {
		int[] arr = new int[] {1, 2, 3, 4};
		int[] res = runningSum(arr);
		System.out.println(res);
	}
}
