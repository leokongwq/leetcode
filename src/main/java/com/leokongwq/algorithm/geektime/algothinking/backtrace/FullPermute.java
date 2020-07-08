package com.leokongwq.algorithm.geektime.algothinking.backtrace;

import java.util.ArrayList;
import java.util.LinkedList;
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

	/**
	 * 选择结果
	 */
	private List<List<Integer>> result = new LinkedList<>();

	public List<List<Integer>> permute(int[] nums) {
		if (nums == null || nums.length == 0) {
			return null;
		}
		permute(nums, new ArrayList<>());
		return result;
	}

	/**
	 * 回溯算法
	 */
	private void permute(int[] nums, List<Integer> item) {
		// 结束条件
		if (nums.length == item.size()) {
			result.add(new ArrayList<>(item));
			return;
		}
		//每个阶段都有多种选择，尝试每种选择
		for (int num : nums) {
			if (!item.contains(num)) {
				//选择
				item.add(num);
				//下一层决策树
				permute(nums, item);
				//撤销选择
				item.remove(Integer.valueOf(num));
			}
		}
	}

	public static void main(String[] args) {
		FullPermute fullPermute = new FullPermute();
		List<List<Integer>> result = fullPermute.permute(new int[]{1, 2, 3});
//		List<List<Integer>> result = fullPermute.permute(new int[]{1});
		for (List<Integer> item : result) {
			for (Integer num : item) {
				System.out.print(num + ",");
			}
			System.out.println();
		}
	}
}
