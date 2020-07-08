package com.leokongwq.algorithm.leetcode;

import com.leokongwq.algorithm.base.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : jiexiu
 * @date : 2020-07-07 20:48
 * 给定一个二叉树，找出其最小深度。
 *
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 *
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最小深度  2.
 *
 **/
public class Question111BinaryTreeMinHeight {

	/**
	 *  求解二叉树的最小深度
	 */
	private int minDepth(TreeNode root) {
		if(root == null) {
			return 0;
		}
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		int minDepth = 1;
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				TreeNode node = queue.poll();
				//叶子节点
				if (node.left == null && node.right == null) {
					return minDepth;
				}
				if (node.left != null) {
					queue.offer(node.left);
				}
				if (node.right != null) {
					queue.offer(node.right);
				}
			}
			minDepth++;
		}
		return minDepth;
	}
}
