package com.leokongwq.algorithm.leetcode.linkedlist;

import com.leokongwq.algorithm.leetcode.ListNode;

import java.util.Stack;

/**
 * @author : jiexiu
 * @date : 2020-08-16 01:09
 * <p>
 * medium
 * <p>
 * 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 * <p>
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 * <p>
 *  
 * <p>
 * 进阶：
 * <p>
 * 如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 8 -> 0 -> 7
 **/
public class Lc445 {

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		Stack<Integer> stack1 = new Stack<>();
		Stack<Integer> stack2 = new Stack<>();
		while (l1 != null) {
			stack1.push(l1.val);
			l1 = l1.next;
		}
		while (l2 != null) {
			stack2.push(l2.val);
			l2 = l2.next;
		}

		int carry = 0;
		ListNode head = null;
		while (!stack1.isEmpty() || !stack2.isEmpty() || carry > 0) {
			int sum = carry;
			sum += stack1.isEmpty() ? 0 : stack1.pop();
			sum += stack2.isEmpty() ? 0 : stack2.pop();
			ListNode node = new ListNode(sum % 10);
			node.next = head;
			head = node;
			carry = sum / 10;
		}
		return head;
	}
}
