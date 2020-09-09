package com.leokongwq.algorithm.leetcode.tree;

import com.leokongwq.algorithm.base.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * @author : jiexiu
 * @date : 2020-09-09 17:07
 *
 * easy
 *
 * 给你一个树，请你 按中序遍历 重新排列树，使树中最左边的结点现在是树的根，并且每个结点没有左子结点，只有一个右子结点。
 *
 *  
 *
 * 示例 ：
 *
 * 输入：[5,3,6,2,4,null,8,1,null,null,null,7,9]
 *
 *        5
 *       / \
 *     3    6
 *    / \    \
 *   2   4    8
 *  /        / \
 * 1        7   9
 *
 * 输出：[1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
 *
 *  1
 *   \
 *    2
 *     \
 *      3
 *       \
 *        4
 *         \
 *          5
 *           \
 *            6
 *             \
 *              7
 *               \
 *                8
 *                 \
 *                  9
 *  
 *
 * 提示：
 *
 * 给定树中的结点数介于 1 和 100 之间。
 * 每个结点都有一个从 0 到 1000 范围内的唯一整数值。
 *
 **/
public class Lc897 {

	private List<TreeNode> inOrderList = new LinkedList<>();

	public TreeNode increasingBST(TreeNode root) {
		inOrder(root);
		if (inOrderList.isEmpty()) {
			return null;
		}
		TreeNode newRoot = null;
		TreeNode cur = null;
		for (TreeNode node : inOrderList) {
			if (newRoot == null) {
				newRoot = new TreeNode(node.val);
				cur = newRoot;
			} else {
				TreeNode n = new TreeNode(node.val);
				cur.right = n;
				cur = n;
			}
		}
		return newRoot;
	}

	TreeNode newRoot = null;
	TreeNode curNode = null;
	public TreeNode increasingBSTV1(TreeNode root) {
		doIncreasingBST(root);
		return newRoot;
	}

	private void doIncreasingBST(TreeNode root) {
		if (root == null) {
			return;
		}
		doIncreasingBST(root.left);

		if (newRoot == null) {
			newRoot = new TreeNode(root.val);
			curNode = newRoot;
		} else {
			TreeNode treeNode = new TreeNode(root.val);
			curNode.right = treeNode;
			curNode = treeNode;
		}

		doIncreasingBST(root.right);
	}

	private void inOrder(TreeNode root) {
		if (root == null) {
			return;
		}
		inOrder(root.left);
		inOrderList.add(root);
		inOrder(root.right);
	}

	public static void main(String[] args) {
		Lc897 lc897 = new Lc897();
		TreeNode root = new TreeNode(2, new TreeNode(1), new TreeNode(3));
		TreeNode newRoot = lc897.increasingBST(root);
//		TreeNode newRoot = lc897.increasingBSTV1(root);

		while (newRoot != null) {
			System.out.print(newRoot.val + ",");
			newRoot = newRoot.right;
		}
	}
}
