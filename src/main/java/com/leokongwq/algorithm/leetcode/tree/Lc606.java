package com.leokongwq.algorithm.leetcode.tree;

import com.leokongwq.algorithm.base.TreeNode;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * @author : jiexiu
 * @date : 2020-09-10 09:49
 *
 * easy
 *
 * 你需要采用前序遍历的方式，将一个二叉树转换成一个由括号和整数组成的字符串。
 *
 * 空节点则用一对空括号 "()" 表示。而且你需要省略所有不影响字符串与原始二叉树之间的一对一映射关系的空括号对。
 *
 * 示例 1:
 *
 * 输入: 二叉树: [1,2,3,4]
 *        1
 *      /   \
 *     2     3
 *    /
 *   4
 *
 * 输出: "1(2(4))(3)"
 *
 * 解释: 原本将是“1(2(4)())(3())”，
 * 在你省略所有不必要的空括号对之后，
 * 它将是“1(2(4))(3)”。
 * 示例 2:
 *
 * 输入: 二叉树: [1,2,3,null,4]
 *        1
 *      /   \
 *     2     3
 *      \
 *       4
 *
 * 输出: "1(2()(4))(3)"
 *
 * 解释: 和第一个示例相似，
 * 除了我们不能省略第一个对括号来中断输入和输出之间的一对一映射关系。
 *
 **/
public class Lc606 {

	/**
	 * 时间复杂度：O(N)，其中 NN 是二叉树中的节点数目。
	 *
	 * 空间复杂度：O(N)，在最坏情况下，会递归 N 层，需要 O(N) 的栈空间。
	 */
	public String tree2str(TreeNode t) {
		if (t == null) {
			return "";
		}
		if (t.left == null && t.right == null) {
			return t.val + "";
		}
		if (t.right == null) {
			return t.val + "(" + tree2str(t.left) + ")";
		}
		return t.val + "(" + tree2str(t.left) + ")(" + tree2str(t.right) + ")";
	}

	public String tree2strV1(TreeNode t) {
		if (t == null) {
			return "";
		}
		Stack<TreeNode> stack = new Stack<>();
		stack.push(t);
		Set<TreeNode> visited = new HashSet<>();
		StringBuilder s = new StringBuilder();
		while (!stack.isEmpty()) {
			t = stack.peek();
			if (visited.contains(t)) {
				stack.pop();
				s.append(")");
			} else {
				visited.add(t);
				s.append("(" + t.val);
				if (t.left == null && t.right != null) {
					s.append("()");
				}
				if (t.right != null) {
					stack.push(t.right);
				}
				if (t.left != null) {
					stack.push(t.left);
				}
			}
		}
		return s.substring(1, s.length() - 1);
	}
}
