package com.leokongwq.geektime.algothinking.dp;

/**
 * @author : jiexiu
 * @date : 2020-07-01 18:49
 *
 * 我们有一个数字序列包含 n 个不同的数字，如何求出这个序列中的最长递增子序列长度？
 *
 * 比如 2, 9, 3, 6, 5, 1, 7 这样一组数字序列，它的最长递增子序列就是 2, 3, 5, 7，所以最长递增子序列的长度是 4。
 **/
public class LongestIncreaseSubArray {

	public int longestIncreaseSubArrayDP(int[] array) {
		if (array.length < 2) {
			return array.length;
		}
		int[] state = new int[array.length];
		state[0] = 1;
		//求每个以 i 结尾的子数组中的最长递增子序列
		for (int i = 1; i < array.length; i++) {
			int max = 0;
			for (int j = 0; j < i; j++) {
				if (array[j] < array[i]) {
					if (state[j] > max) {
						max = state[j];
					}
				}
			}
			state[i] = max + 1;
		}
		//选择状态值最大的
		int result = 0;
		for (int i = 0; i < state.length; i++) {
			if (state[i] > result) {
				result = state[i];
			}
		}
		return result;
	}
}
