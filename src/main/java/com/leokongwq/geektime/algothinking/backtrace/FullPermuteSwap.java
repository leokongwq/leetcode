package com.leokongwq.geektime.algothinking.backtrace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author : jiexiu
 * @date : 2020-07-02 21:35
 *
 * 同样是回溯，但是内存占用很少
 **/
public class FullPermuteSwap {

	public List<List<Integer>> permute(int[] nums) {
		if (nums == null) {
			return null;
		}
		List<List<Integer>> result = new ArrayList<>();
		if (nums.length == 1) {
			List<Integer> item = new ArrayList<>();
			item.add(nums[0]);
			result.add(item);
			return result;
		}
		permute(0, nums, result);

		return result;
	}

	private void permute(int i, int[] nums, List<List<Integer>> result) {
		if (i == nums.length - 1) {
			List<Integer> item = new ArrayList<>();
			Arrays.stream(nums).forEach(item::add);
			result.add(item);
			return;
		}
		//回溯逻辑
		for (int j = i; j < nums.length; j++) {
			if (i == j || nums[i] != nums[j]) {
				swap(nums, i, j);
				permute(i + 1, nums, result);
				//复原
				swap(nums, i, j);
			}
		}
	}

	public void swap(int[] nums, int i, int j) {
		int temp;
		temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

	public static void main(String[] args) {
		FullPermuteSwap fullPermuteSwap = new FullPermuteSwap();
		List<List<Integer>> result = fullPermuteSwap.permute(new int[]{1, 2, 3});
//		List<List<Integer>> result = fullPermuteSwap.permute(new int[]{1});

		for (List<Integer> item : result) {
			for (Integer num : item) {
				System.out.print(num + ",");
			}
			System.out.println();
		}
	}
}
