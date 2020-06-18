package com.leokongwq.leetcode;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author : jiexiu
 * @date : 2020-06-16 23:19
 **/
public class Question102LevelScanBinaryTree {


	private static class TreeNode<T> {

		private T value;

		private TreeNode<T> left;

		private TreeNode<T> right;

		public TreeNode(T value) {
			this.value = value;
		}
	}

	private static <T> List<List<T>> levelOrder(TreeNode<T> root) {
		if (root == null) {
			return Collections.emptyList();
		}

		LinkedList<TreeNode<T>> queue = new LinkedList<>();
		queue.push(root);
		List<List<T>> result = new LinkedList<>();
		LinkedList<TreeNode<T>> currentLevelNodes = new LinkedList<>();

		while (!queue.isEmpty()) {
			TreeNode<T> node = queue.pop();
			currentLevelNodes.push(node);
			if (queue.isEmpty()) {
				List<T> row = new LinkedList<>();
				while (!currentLevelNodes.isEmpty()) {
					TreeNode<T> temp = currentLevelNodes.pop();
					row.add(temp.value);
					if (temp.left != null) {
						queue.add(temp.left);
					}
					if (temp.right != null) {
						queue.add(temp.right);
					}
				}
				result.add(row);
			}
		}
		return result;
	}

	public static void main(String[] args) {
		TreeNode<Integer> root = new TreeNode<>(1);
		root.left = new TreeNode<>(3);
		root.right = new TreeNode<>(2);
		List<List<Integer>> result = levelOrder(root);
		System.out.println(result);
	}
}
