package com.leokongwq.algorithm.leetcode.tree;

import com.leokongwq.algorithm.base.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author : jiexiu
 * @date : 2020-09-09 16:56
 **/
public class LevelOrderPrint {

	public List<List<Integer>> levelOrder(TreeNode root) {
		if (root == null) {
			return new LinkedList<>();
		}

		List<List<Integer>> res = new LinkedList<>();

		LinkedList<TreeNode> queue = new LinkedList();
		queue.add(root);

		while (!queue.isEmpty()) {
			int curSize = queue.size();
			List<Integer> level = new ArrayList<>();
			for (int i = 0; i < curSize; i++) {
				TreeNode node = queue.removeFirst();
				level.add(node.val);

				if (node.left != null) {
					queue.add(node.left);
				}
				if (node.right != null) {
					queue.add(node.right);
				}
			}
			res.add(level);
		}

		return res;
	}

	public static void main(String[] args) {
		LevelOrderPrint levelOrderPrint = new LevelOrderPrint();

		TreeNode root = new TreeNode(1, new TreeNode(2, new TreeNode(4), new TreeNode(5)), new TreeNode(3, new TreeNode(6), new TreeNode(7)));
		List<List<Integer>> res = levelOrderPrint.levelOrder(root);

		for (List<Integer> item : res) {
			item.stream().forEach(System.out::print);
			System.out.println();
		}
	}
}
