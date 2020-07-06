package com.leokongwq.geektime.algothinking.backtrace;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : jiexiu
 * @date : 2020-07-02 14:04
 *
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 *
 * 示例:
 *
 * 输入: [1,2,3]
 * 输出:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 **/
public class FullPermute {

	public static List<List<Integer>> permute(int[] nums) {
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
		permute(0, nums, new ArrayList<>(), result);

		return result;
	}

	/**
	 * 回溯算法
	 * @param i 表示第几个阶段
	 */
	private static void permute(int i, int[] nums, List<Integer> item, List<List<Integer>> result) {
		if (i == nums.length) {
			result.add(new ArrayList<>(item));
			return;
		}
		//每个阶段都有多种选择，尝试每种选择
		for (int num : nums) {
			if (!item.contains(num)) {
				item.add(num);
				//下一阶段
				permute(i + 1, nums, item, result);
				item.remove(Integer.valueOf(num));
			}
		}
	}

	public static void main(String[] args) {
//		List<List<Integer>> result = FullPermute.permute(new int[]{1, 2, 3});
		List<List<Integer>> result = FullPermute.permute(new int[]{1});
		for (List<Integer> item : result) {
			for (Integer num : item) {
				System.out.print(num + ",");
			}
			System.out.println();
		}
	}
}
