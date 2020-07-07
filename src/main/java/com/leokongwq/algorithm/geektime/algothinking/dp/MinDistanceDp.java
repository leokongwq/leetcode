package com.leokongwq.algorithm.geektime.algothinking.dp;

/**
 * @author : jiexiu
 * @date : 2020-06-30 22:14
 **/
public class MinDistanceDp {

	private int[][] matrix = new int[][]{
			{1, 3, 5, 9},
			{2, 1, 3, 4},
			{5, 2, 6, 7},
			{6, 8, 4, 3}
	};

	private int n = 4;

	private int[][] mem = new int[4][4];

	/**
	 * 递归 + 备忘录 模式的dp实现
	 * 这里可以使用备忘录的原因是，每个节点的值我们都保证了取其最小值
	 */
	public int minDist(int i, int j) {
		if (i == 0 && j == 0) {
			return matrix[0][0];
		}
		if (mem[i][j] > 0) {
			return mem[i][j];
		}
		int minLeft = Integer.MAX_VALUE;
		if (j - 1 >= 0) {
			minLeft = minDist(i, j - 1);
		}
		int minUp = Integer.MAX_VALUE;
		if (i - 1 >= 0) {
			minUp = minDist(i - 1, j);
		}
		int currMinDist = matrix[i][j] + Math.min(minLeft, minUp);
		mem[i][j] = currMinDist;
		return currMinDist;
	}

	public static void main(String[] args) {
		MinDistanceDp minDistanceDp = new MinDistanceDp();
		System.out.println(minDistanceDp.minDist(3, 3));
	}
}
