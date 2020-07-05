package com.leokongwq.leetcode;

/**
 * @author jiexiu
 * created 2019/1/16 - 17:56
 * <p>
 * Given an integer array nums, find the contiguous subarray (containing at least one number)
 * which has the largest sum and return its sum.
 * <p>
 * Example:
 * <p>
 * Input: [-2, 1, -3, 4, -1, 2, 1, -5, 4]
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 * Follow up:
 * <p>
 * If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach,
 * which is more subtle.
 * <p>
 * 最大子数列问题
 * 最大子数列和
 * https://zh.wikipedia.org/wiki/%E6%9C%80%E5%A4%A7%E5%AD%90%E6%95%B0%E5%88%97%E9%97%AE%E9%A2%98
 */
public class Question53MaximumSubarray {

	/**
	 * Kadane's 算法
	 */
	public int maxSubArrayKadane(int[] nums) {
		int best = Integer.MIN_VALUE;
		int sum = 0;

		for (int i = 0; i < nums.length; i++) {
			sum = Math.max(nums[i], sum += nums[i]);
			best = Math.max(best, sum);
		}

		return best;
	}

	/**
	 * 对于求子数组和的最大值，一般可采用动态规划，动态规划的话一般需要维护一个容器，以保存每个阶段的状态，那么首先要选择要保存的状态，
	 * 并找出各个阶段状态之间的关系。
	 * <p>
	 * 对于这样一个问题，可以取：以当前位置为结尾的子数组和的最大值。
	 * 那么对于任何一个位置而言，它前面的那部分数组总会有一个最大和，如果将这些和保存在dp数组中，有如下关系：
	 * <p>
	 * dp[i] = Math.max(dp[i-1] + cur, cur)
	 * cur是当前位置的值。在一次遍历之后，dp中就存放了以任何一个以当前位置为结尾的子数组的和的最大值，于是就可以确定要求解就在这个数组中，
	 * 且必然在这个数组中，因为子数组的结尾必然是原数组中的某个位置。代码如下：
	 */
	private static int maxSubArray(int[] nums) {
		int n = nums.length;
		if (n == 0) {
			return 0;
		}
		int[] sum = new int[n];
		int max = sum[0] = nums[0];
		for (int i = 1; i < n; i++) {
			if (sum[i - 1] > 0) {
				sum[i] = nums[i] + sum[i - 1];
			} else {
				sum[i] = nums[i];
			}
			if (sum[i] > max) {
				max = sum[i];
			}
		}
		return max;
	}

	/**
	 * dp[i] = Math.max(dp[i-1] + cur, cur)
	 * <p>
	 * dp[i-1] 表示前 i 元素组成的子数组中 子序列和的最大值
	 */
	private static int maxSubArrayV1(int[] nums) {
		int n = nums.length;
		if (n == 0) {
			return 0;
		}
		//假定第一个就是最大的
		int max = nums[0];
		//表示前一个最大值
		int last = nums[0];
		for (int i = 1; i < n; i++) {
			//以当前元素为结尾的子数组 和 最大值
			int cur;
			// 前一个和的最大值大于0，则 当前元素 + 前一个和最大值 肯定 > 当前元素nums[i]
			// 否则 令当前元素为 最新的子序列和最大值
			if (last > 0) {
				cur = nums[i] + last;
			} else {
				cur = nums[i];
			}
			//动态更新和的最大值
			if (cur > max) {
				max = cur;
			}
			last = cur;
		}
		return max;
	}

	/**
	 * 题目中给了一种使用分治法的思路，那么如何使用分治法解本题？分治法的思想呢，一般就是将原数组一分为二，然后在两个部分中各自求解，最后合并起来，
	 * 成为原问题的解。而对于本题而言，如果将原本的数组一分为二，所要求的子数组确实有可能在两个部分之间，不过也有可能两个数组各占一点，
	 * 我们只要考虑到这两种情况，便可以进行解题了：
	 * <p>
	 * 如果子数组在两侧的某一侧，那么只需要求出以两侧的子数组为基础的各自的子数组的最大和就行了，这个最大和必然也是本题的解
	 * 如果子数组在两侧各占一点，那么这个子数组必然是左侧数组的右半部分加上右侧数组的左半部分。那么只要沿着中间的数字向两端延伸，找到一个和最大的子数组就好了
	 * 题目的解必然是上述两种情况的一种，所以上述两种情况都求出来之后的最大的解必然是本题的解。
     * 代码如下：
	 */
	private static int maxSubArrayV2(int[] nums) {
		return max(nums, 0, nums.length - 1);
	}

	private static int max(int[] nums, int start, int end) {
		if (start == end) {
			return nums[start];
		}
		int mid = (start + end) / 2;
		//左半边的最大值
		int left = max(nums, start, mid);
		//右半边的最大值
		int right = max(nums, mid + 1, end);
		int maxL, sumL, maxR, sumR;
		maxL = sumL = nums[mid];
		maxR = sumR = nums[mid + 1];
		//相加得到maxL计算结果的数组元素必然是连续的
		for (int i = mid - 1; i >= start; i--) {
			sumL += nums[i];
			maxL = Math.max(sumL, maxL);
		}
		for (int i = mid + 2; i <= end; i++) {
			sumR += nums[i];
			maxR = Math.max(sumR, maxR);
		}
		// left 有可能等于 maxL, maxR有可能是负数
		return left > right ? Math.max(left, maxL + maxR) : Math.max(right, maxL + maxR);
	}

	public static void main(String[] args) {
		System.out.println(maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
	}
}
