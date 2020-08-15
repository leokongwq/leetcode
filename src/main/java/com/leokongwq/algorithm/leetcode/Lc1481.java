package com.leokongwq.algorithm.leetcode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author : jiexiu
 * @date : 2020-06-16 23:50
 *
 * medium
 *
 * 给你一个整数数组 arr 和一个整数 k 。现需要从数组中恰好移除 k 个元素，请找出移除后数组中不同整数的最少数目。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：arr = [5,5,4], k = 1
 * 输出：1
 * 解释：移除 1 个 4 ，数组中只剩下 5 一种整数。
 * 示例 2：
 *
 * 输入：arr = [4,3,1,1,3,3,2], k = 3
 * 输出：2
 * 解释：先移除 4、2 ，然后再移除两个 1 中的任意 1 个或者三个 3 中的任意 1 个，最后剩下 1 和 3 两种整数。
 *  
 *
 * 提示：
 *
 * 1 <= arr.length <= 10^5
 * 1 <= arr[i] <= 10^9
 * 0 <= k <= arr.length
 *
 **/
public class Lc1481 {

	/**
	 * 题解： 移除k个元素后，不同整数的个数最少，其实就是尽可能保留出现次数最多的整数。也就是优先删除出现次数最少的元素。
	 *
	 * 1. 统计所有整数出现的个数。
	 * 2. 按出现的次数升序排列
	 * 3. 删除前k个数
	 */
	private static int findLeastNumOfUniqueInts(int[] arr, int k) {
		if (arr == null || arr.length <= k) {
			return 0;
		}
		Arrays.sort(arr);
		List<Pair> pairs = new ArrayList<>();
		Pair curNum = new Pair(arr[0]);
		pairs.add(curNum);

		int i = 1;
		while (i < arr.length) {
			if (curNum.getNum() != arr[i]) {
				curNum = new Pair(arr[i]);
				pairs.add(curNum);
			} else {
				curNum.incrTimes();
			}
			i++;
		}

		pairs.sort(new Comparator<Pair>() {
			@Override
			public int compare(Pair o1, Pair o2) {
				return o1.getTimes() - o2.getTimes();
			}
		});

		while (k > 0) {
			Pair currentPair = pairs.get(0);
			if (currentPair.getTimes() == 0) {
				pairs.remove(0);
				currentPair = pairs.get(0);
			}
			currentPair.decrTimes();
			k--;
		}
		if (pairs.get(0).getTimes() == 0) {
			pairs.remove(0);
		}
		return pairs.size();
	}

	private static class Pair {
		private int num;
		private int times;

		public Pair(int num) {
			this.num = num;
			this.times = 1;
		}

		public int getNum() {
			return num;
		}

		public int getTimes() {
			return times;
		}

		public void incrTimes() {
			this.times++;
		}

		public void decrTimes() {
			this.times--;
		}
	}

	private static int findLeastNumOfUniqueIntsV1(int[] arr, int k) {
		// 计算每个数出现次数
		Map<Integer, Integer> map = new HashMap<>();
		Arrays.stream(arr).forEach(num -> map.put(num, map.getOrDefault(num, 0) + 1));
		// 贪心，从数量最少的开始移除
		List<NumCount> numCountList = map
				.entrySet()
				.stream()
				.map(entry -> new NumCount(entry.getKey(), entry.getValue()))
				.sorted(Comparator.comparingInt(o -> o.count))
				.collect(Collectors.toList());
		int sum = numCountList.get(0).count;
		for (int i = 1; i < numCountList.size(); i++) {
			if (sum == k) {
				return numCountList.size() - i;
			} else if (sum > k) {
				return numCountList.size() - i + 1;
			} else {
				sum += numCountList.get(i).count;
			}
		}
		if (sum <= k) {
			return 0;
		} else {
			return 1;
		}
	}

	static class NumCount {
		int num;
		int count;

		public NumCount(int num, int count) {
			this.num = num;
			this.count = count;
		}
	}

	private static int findLeastNumOfUniqueIntsV2(int[] arr, int k) {
		// 计算每个数出现次数
		Map<Integer, Integer> hm = new HashMap<>();
		Arrays.stream(arr).forEach(num -> hm.put(num, hm.getOrDefault(num, 0) + 1));

		if (k == 0) {
			//如果k为0直接返回所有不同整数个数
			return hm.size();
		}
		int[] val = new int[hm.size()];
		int a = 0;
		for (int key : hm.keySet()) {
			val[a++] = hm.get(key);
		}
		//排序后即可从小到大依次用K来减
		Arrays.sort(val);
		int i = 0;
		for (i = 0; i < val.length; i++) {
			if (k - val[i] >= 0) {
				k = k - val[i];
			} else {
				break;
			}
		}
		return val.length - i;
	}

	public static void main(String[] args) {
		System.out.println(findLeastNumOfUniqueInts(new int[]{5, 5, 4}, 1));
//		System.out.println(findLeastNumOfUniqueInts(new int[]{4,3,1,1,3,3,2}, 3));

//		System.out.println(findLeastNumOfUniqueInts(new int[]{2,1,1,3,3,3}, 3));

//		System.out.println(findLeastNumOfUniqueInts(new int[]{1}, 0));
	}
}
