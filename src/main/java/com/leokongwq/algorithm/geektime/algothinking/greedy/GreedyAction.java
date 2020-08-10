package com.leokongwq.algorithm.geektime.algothinking.greedy;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.leokongwq.algorithm.base.Printer;
import lombok.Data;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

/**
 * @author : jiexiu
 * @date : 2020-06-28 09:37
 *
 * 假设我们有一个可以容纳 100kg 物品的背包，可以装各种物品。我们有以下 5 种豆子，每种豆子的总量和总价值都各不相同。为了让背包中所装物品的总价值最大，
 * 我们如何选择在背包中装哪些豆子？每种豆子又该装多少呢？
 *
 * 第一步，当我们看到这类问题的时候，首先要联想到贪心算法：
 * 针对一组数据，我们定义了限制值和期望值，希望从中选出几个数据，在满足限制值的情况下，期望值最大。
 *
 * 类比到刚刚的例子，限制值就是重量不能超过 100kg，期望值就是物品的总价值。这组数据就是 5 种豆子。我们从中选出一部分，满足重量不超过 100kg，并且总价值最大。
 *
 * 第二步，我们尝试看下这个问题是否可以用贪心算法解决：每次选择当前情况下，在对限制值同等贡献量的情况下，对期望值贡献最大的数据。
 * 类比到刚刚的例子，我们每次都从剩下的豆子里面，选择单价最高的，也就是重量相同的情况下，对价值贡献最大的豆子。
 *
 * 第三步，我们举几个例子看下贪心算法产生的结果是否是最优的。大部分情况下，举几个例子验证一下就可以了。
 * 严格地证明贪心算法的正确性，是非常复杂的，需要涉及比较多的数学推理。而且，从实践的角度来说，大部分能用贪心算法解决的问题，贪心算法的正确性都是显而易见的，
 * 也不需要严格的数学推导证明。
 *
 * 实际上，用贪心算法解决问题的思路，并不总能给出最优解。此时可能需要动态规划
 **/
public class GreedyAction {

	/**
	 * 贪心算法实例一：分配糖果
	 *
	 * <p>描述：有 n 个孩子，和 m 个糖果。其中孩子数量大于糖果数量（n > m），每个糖果的大小不一样，每个孩子对糖果大小的需求也不一样， 如何分配糖果可以让分配给更多的孩子呢？
	 *
	 * <p>分析：根据贪心算法分析，将问题看作是，从 n 个孩子中选出一部分孩子，分配糖果。
	 *
	 * <p>限制值是糖果数量 m，期望值是孩子数量，在限制值一定的情况下，同等限制值下贡献量要大，期望值最大，也就是孩子的数量最大。
	 *
	 * <p>方法：按照孩子需求量最小的分配
	 */
	private static void allocationSweets() {
		// 一组孩子
		int childNum = 10;
		int[] children = new int[childNum];
		for (int i = 0; i < childNum; i++) {
			// 孩子对糖果尺寸的大小需求
			children[i] = RandomUtils.nextInt(10, 20);
		}

		// 一组糖果
		int sweetNum = 5;
		List<Integer> sweets = Lists.newArrayListWithCapacity(sweetNum);
		for (int i = 0; i < sweetNum; i++) {
			// 糖果尺寸大小
			sweets.add(RandomUtils.nextInt(1, 20));
		}
		//对孩子的需求从小到大排序
		Arrays.sort(children);
		Printer.printArray(children);

		//对糖果进行排序
		sweets.sort(Comparator.comparingInt(Integer::intValue));
		sweets.forEach(System.out::println);

		Iterator<Integer> iterator = sweets.iterator();
		for (int i = 0; i < childNum; i++) {
			if (sweets.isEmpty()) {
				System.out.println("糖果分配完了.");
				return;
			}
			while (iterator.hasNext()) {
				int candy = iterator.next();
				if (candy >= children[i]) {
					System.out.println("child " + i + " get candy " + candy);
					iterator.remove();
					break;
				}
			}
		}
	}

	/**
	 * 贪心算法实例二：钱币找零
	 *
	 * <p>描述：假设我们有 1 元、2 元、5 元、10 元、20 元、50 元、100 元这些面额的纸币，它们的张数分别是
	 * c1、c2、c5、c10、c20、c50、c100。我们现在要用这些钱来支付 K 元，最少要用多少张纸币呢？
	 *
	 * <p>分析：限制值是 K 元，期望值是钱币张数量，在同等限制值下贡献量要最大的，即钱币的价值最大。
	 *
	 * @param k 需要找零的金额
	 */
	private static void coinChange(int k) {
		// 7中不同面额的纸币
		Coin[] money = new Coin[7];
		int[] unit = new int[]{1, 2, 5, 10, 20, 50, 100};
		for (int i = 0; i < money.length; i++) {
			money[i] = new Coin(unit[i], RandomUtils.nextInt(10, 20));
		}

		Map<Coin, Integer> result = Maps.newHashMap();
		int surplusMoney = k;
		while (surplusMoney > 0) {
			for (int j = money.length - 1; j >=0; j--) {
				//表示该面额的纸币还有剩余
				while (money[j].num > 0 && money[j].value <= surplusMoney) {
					--money[j].num;
					surplusMoney -= money[j].value;
					result.compute(money[j], (key, oldValue) -> {
						if (oldValue == null) {
							return 1;
						}
						return oldValue + 1;
					});
				}
			}
		}

		System.out.println("总价值：" + k);
		for (Map.Entry<Coin, Integer> entry : result.entrySet()) {
			System.out.println("使用面额为:" + entry.getKey().value + " 数量 " + entry.getValue());
		}
	}

	static int methodInvokeCount = 0;

	/**
	 * 贪心算法实例三：区间覆盖
	 *
	 * <p>描述：假设我们有 n 个区间，区间的起始端点和结束端点分别是[l1, r1]，[l2, r2]，[l3, r3]，……，[ln, rn]。我们从这 n
	 * 个区间中选出一部分区间，这部分区间满足两两不相交（端点相交的情况不算相交），最多能选出多少个区间呢？
	 *
	 * <p>分析：我们假设这 n 个区间中最左端点是 lmin，最右端点是 rmax。我们选择几个不相交的区间，从左到右将 [lmin, rmax] 覆盖上。 按照起始端点从小到大的顺序对这 n
	 * 个区间排序。
	 *
	 * <p>每次选择的时，选择左端点跟前面已经覆盖的区间不重复的的区间，右端点又尽量小，这样可以让剩下的未覆盖区间尽可能的大，就可以放置更多的区间。
	 */
	public static void sectionCover() {
		// 将区间按照左端点从小到大排序
		// 例如： [6,8] [2,4] [3,5] [5,9] [8,10]
		// 使用红黑树保存区间信息，自动按照 key 从小到大排序区间信息
		TreeMap<Integer, Integer> section = new TreeMap<>();
		section.put(6, 8);
		section.put(2, 4);
		section.put(3, 5);
		section.put(1, 5);
		section.put(5, 9);
		section.put(8, 10);

		// 遍历每个区间，计算每次循环的最多区间数
		Pair<Integer, List<Map.Entry<Integer, Integer>>> max = Pair.of(0, Collections.emptyList());
		for (Map.Entry<Integer, Integer> entry : section.entrySet()) {
			Pair<Integer, List<Map.Entry<Integer, Integer>>> pair = sectionMaxNum(0, 1, entry, section);
			if (max.getLeft() < pair.getLeft()) {
				max = pair;
			}
		}
		System.out.println("选出的不相交区间的个数：" + max.getLeft() + ", sectionMaxNum 方法一共被调用的次数：" + methodInvokeCount);
		System.out.println("区间信息是：" + max.getRight());
	}

	/**
	 * 递归获取指定区间之后的最长区间数信息
	 *
	 * @param index          当前遍历的索引
	 * @param sectionNum     当前区间最大数量
	 * @param currentSection 当前区间信息
	 * @param section        所有区间
	 * @return pair，left 是区间的最大数量，right 为各个区间信息
	 */
	private static Pair<Integer, List<Map.Entry<Integer, Integer>>> sectionMaxNum(int index,
	                                                                              int sectionNum,
	                                                                              Map.Entry<Integer, Integer> currentSection,
	                                                                              TreeMap<Integer, Integer> section) {

		// 记录方法被调用的次数
		methodInvokeCount++;

		if (index == section.size() - 1) {
			return Pair.of(sectionNum, Collections.singletonList(currentSection));
		}

		// 返回的 List 区间，为当前区间之后，所有可用的区间
		List<Map.Entry<Integer, Integer>> path = Lists.newArrayList();
		path.add(currentSection);
		List<Map.Entry<Integer, Integer>> subPath = Lists.newArrayList();
		int lastSum = 1;

		int maxSectionNum = sectionNum;
		SortedMap<Integer, Integer> tailMap = section.tailMap(currentSection.getKey());
		if (MapUtils.isNotEmpty(tailMap)) {
			Set<Map.Entry<Integer, Integer>> entries = tailMap.entrySet();
			Iterator<Map.Entry<Integer, Integer>> iterator = entries.iterator();
			int i = index + 1;
			while (iterator.hasNext()) {
				Map.Entry<Integer, Integer> nextSection = iterator.next();
				// 前面已经覆盖的区间与下一个左端点与
				if (currentSection.getValue() <= nextSection.getKey()) {
					// 返回一个该区间下，一共满足条件的区间信息
					Pair<Integer, List<Map.Entry<Integer, Integer>>> pair = sectionMaxNum(i, sectionNum + 1,
							nextSection,
							section);
					// 子路径区间数
					Integer subSectionNum = pair.getLeft();
					if (maxSectionNum < subSectionNum) {
						// 子路径区间相差值之和
						int sum = getSectionSum(currentSection, pair);
						subPath = pair.getRight();
						lastSum = sum;
						maxSectionNum = subSectionNum;
					} else if (maxSectionNum == subSectionNum) {
						// 子路径区间相差值之和
						int sum = getSectionSum(currentSection, pair);
						// 区间数相同，比较区间相差值
						if (sum > lastSum) {
							subPath = pair.getRight();
							lastSum = sum;
						}
					}
				}
				i++;
			}
		}
		path.addAll(subPath);
		return Pair.of(maxSectionNum, path);
	}

	private static int getSectionSum(Map.Entry<Integer, Integer> currentSection,
	                                 Pair<Integer, List<Map.Entry<Integer, Integer>>> pair) {
		int sum = 0;
		int lastEnd = currentSection.getValue();
		for (Map.Entry<Integer, Integer> entry : pair.getRight()) {
			sum += (entry.getKey() - lastEnd);
			lastEnd = entry.getValue();
		}
		return sum;
	}

	public static void main(String[] args) {
//		allocationSweets();

		coinChange(96);
	}

	@Data
	static class Coin {

		/**
		 * 价值
		 *
		 * <p>比如。1 元、2 元、5 元等等
		 */
		int value;

		/**
		 * 数量
		 */
		int num;

		public Coin(int value, int num) {
			this.value = value;
			this.num = num;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Coin coin = (Coin) o;
			return value == coin.value;
		}

		@Override
		public int hashCode() {
			return Objects.hash(value);
		}

		@Override
		public String toString() {
			return "{" + "币值=" + value + ", 数量=" + num + '}';
		}

	}
}
