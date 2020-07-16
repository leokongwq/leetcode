package com.leokongwq.algorithm.leetcode;

import java.util.Arrays;

/**
 * @author : jiexiu
 * @date : 2020-07-16 08:00
 *
 * 珂珂喜欢吃香蕉。这里有 N 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 H 小时后回来。
 *
 * 珂珂可以决定她吃香蕉的速度 K （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 K 根。如果这堆香蕉少于 K 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。  
 *
 * 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
 *
 * 返回她可以在 H 小时内吃掉所有香蕉的最小速度 K（K 为整数）。
 *
 *  
 *
 * 示例 1：
 *
 * 输入: piles = [3,6,7,11], H = 8
 * 输出: 4
 * 示例 2：
 *
 * 输入: piles = [30,11,23,4,20], H = 5
 * 输出: 30
 * 示例 3：
 *
 * 输入: piles = [30,11,23,4,20], H = 6
 * 输出: 23
 *
 **/
public class Question875MinEatingSpeed {

	public int minEatingSpeed(int[] piles, int H) {
		if (piles == null || piles.length == 0) {
			return 0;
		}
		// piles 数组的最大值
		int max = Arrays.stream(piles).max().getAsInt();
		for (int speed = 1; speed < max; speed++) {
			// 以 speed 是否能在 H 小时内吃完香蕉
			if (canFinish(piles, speed, H)) {
				return speed;
			}
		}
		return max;
	}

	public int minEatingSpeedV1(int[] piles, int H) {
		if (piles == null || piles.length == 0) {
			return 0;
		}
		// 套用搜索左侧边界的算法框架
		int left = 1, right = Arrays.stream(piles).max().getAsInt() + 1;
		while (left < right) {
			// 防止溢出
			int mid = left + (right - left) / 2;
			if (canFinish(piles, mid, H)) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return left;
	}

	// 时间复杂度 O(N)
	private boolean canFinish(int[] piles, int speed, int H) {
		int time = 0;
		for (int n : piles) {
			time += timeOf(n, speed);
		}
		return time <= H;
	}

	/**
	 * 计算 以速度 speed 吃完 n 个香蕉花费的时间
	 */
	private int timeOf(int n, int speed) {
		return (n / speed) + ((n % speed > 0) ? 1 : 0);
	}

	public static void main(String[] args) {
		Question875MinEatingSpeed minEatingSpeed = new Question875MinEatingSpeed();
		System.out.println(minEatingSpeed.minEatingSpeed(new int[]{3, 6, 7, 11}, 8));
		System.out.println(minEatingSpeed.minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 5));
		System.out.println(minEatingSpeed.minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 6));
	}
}
