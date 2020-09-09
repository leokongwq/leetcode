package com.leokongwq.algorithm.leetcode.tree;

import com.leokongwq.algorithm.base.TreeNode;

/**
 * @author : jiexiu
 * @date : 2020-09-09 18:00
 *
 * easy
 *
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例: 
 * 给定如下二叉树，以及目标和 sum = 22，
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \      \
 *         7    2      1
 * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
 *
 **/
public class Lc112 {

	public boolean hasPathSum(TreeNode root, int sum) {
		if (root == null) {
			return false;
		}
		if (root.left == null && root.right == null) {
			return sum == root.val;
		}
		return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
	}

	public static void main(String[] args) {
//		TreeNode root = new TreeNode(1, new TreeNode(2), null);
//		TreeNode root = new TreeNode(1);
		TreeNode root = new TreeNode(1, new TreeNode(2, new TreeNode(3, new TreeNode(4), null),  null), null);

		Lc112 lc112 = new Lc112();

		System.out.println(lc112.hasPathSum(root, 6));
	}
}
