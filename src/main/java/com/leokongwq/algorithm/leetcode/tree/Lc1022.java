package com.leokongwq.algorithm.leetcode.tree;

import com.leokongwq.algorithm.base.TreeNode;

/**
 * @author : jiexiu
 * @date : 2020-09-10 10:03
 *
 * easy
 *
 * 给出一棵二叉树，其上每个结点的值都是 0 或 1 。每一条从根到叶的路径都代表一个从最高有效位开始的二进制数。
 *
 * 例如，如果路径为 0 -> 1 -> 1 -> 0 -> 1，那么它表示二进制数 01101，也就是 13 。
 *
 * 对树上的每一片叶子，我们都要找出从根到该叶子的路径所表示的数字。
 *
 * 以 10^9 + 7 为模，返回这些数字之和。
 *
 * 输入：[1,0,1,0,1,0,1]
 * 输出：22
 * 解释：(100) + (101) + (110) + (111) = 4 + 5 + 6 + 7 = 22
 **/
public class Lc1022 {

	/**
	 * 存放结果
	 */
	private int ans = 0;

	/**
	 * 用作取模
	 */
	private static final int mod = 1000000000 + 7;

	public int sumRootToLeaf(TreeNode root) {
		helper(root, 0);
		return ans % mod;
	}

	public void helper(TreeNode root, int sum) {
		if (root != null) {
			//左移 = *2
			sum = sum * 2 + root.val;
			if (root.left == null && root.right == null) {
				// 到达叶子节点，得到一个和，加到结果上
				ans += sum;
			} else {
				// 没有到达叶子节点，继续递归
				helper(root.left, sum);
				helper(root.right, sum);
			}
		}
	}
}
