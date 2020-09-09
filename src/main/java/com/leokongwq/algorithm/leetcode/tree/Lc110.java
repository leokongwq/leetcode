package com.leokongwq.algorithm.leetcode.tree;

import com.leokongwq.algorithm.base.TreeNode;

/**
 * @author : jiexiu
 *
 * easy
 *
 *
 * <p>
 * <p>
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * <p>
 * 本题中，一棵高度平衡二叉树定义为：
 * <p>
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 * <p>
 * easy
 *
 * 示例 1:
 *
 * 给定二叉树 [3,9,20,null,null,15,7]
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回 true 。
 *
 * 示例 2:
 *
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 *
 *        1
 *       / \
 *      2   2
 *     / \
 *    3   3
 *   / \
 *  4   4
 * 返回 false 。
 *
 **/
public class Lc110 {

	public boolean isBalanced(TreeNode root) {
		if (root == null) {
			return true;
		}
		int left = getHeight(root.left, 0);
		int right = getHeight(root.right, 0);
		return Math.abs(left - right) <= 1 && isBalanced(root.left) && isBalanced(root.right);
	}

	public int getHeight(TreeNode treeNode, int height) {
		if (treeNode == null) {
			return height;
		}
		int left = getHeight(treeNode.left, height + 1);
		int right = getHeight(treeNode.right, height + 1);

		return Math.max(left, right);
	}

	public static void main(String[] args) {
		TreeNode left = new TreeNode(9);
		TreeNode right = new TreeNode(20, new TreeNode(15), new TreeNode(7));
		TreeNode root = new TreeNode(3, left, right);

		Lc110 lc110 = new Lc110();

//		System.out.println(lc110.getHeight(root, 0));
//		System.out.println(lc110.isBalanced(root));
//
//
//		TreeNode root1 = new TreeNode(1, new TreeNode(2, new TreeNode(3, new TreeNode(4), new TreeNode(4)), new TreeNode(3)), new TreeNode(2));
//
//
//		System.out.println(lc110.getHeight(root1, 0));
//		System.out.println(lc110.isBalanced(root1));

		TreeNode root2 = new TreeNode(1, null, new TreeNode(2, null, new TreeNode(3)));

		System.out.println(lc110.getHeight(root2, 0));
		System.out.println(lc110.isBalanced(root2));

		//[1,2,2,3,null,null,3,4,null,null,4]/
	}

}
