package com.leokongwq.algorithm.base;

/**
 * 二叉树节点
 */
public class TreeNode {

	public int val;
	public TreeNode left;
	public TreeNode right;

	public TreeNode(int data) {
		this.val = data;
	}

	public TreeNode(int val, TreeNode left, TreeNode right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}
}