package com.leokongwq.algorithm.leetcode.linkedlist;

import com.leokongwq.algorithm.base.Printer;
import com.leokongwq.algorithm.leetcode.ListNode;

/**
 * @author : jiexiu
 * @date : 2020-08-15 21:50
 * <p>
 * medidum
 * <p>
 * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 * <p>
 * 若算法执行所需要的辅助空间相对于输入数据n而言是一个常数，则称这个算法空间复杂度辅助空间为o（1）；
 * 递归算法空间复杂度：递归深度n*每次递归所要的辅助空间，如果每次递归所需要的辅助空间为常数，则递归空间复杂度o（n）。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 * 示例 2:
 * <p>
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 * <p>
 * 分析：
 * 1. 计数，基数，桶排序，也不可以。
 * 2. 冒泡，选择，插入 不满足时间复杂度要求
 * 3. 基于数组元素交换的排序选择不可以（链表不支持通过下标随机访问）。
 * 4. 必须是原地排序算法。
 * 5. 归并排序时间复杂度满足要求， 空间复杂度，因为是链表，所以不需要开辟新的空间。
 **/
public class Lc148 {

	public ListNode sortList(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		//很重要，否则会死循环
		ListNode fast = head.next, slow = head;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}

		ListNode tmp = slow.next;
		slow.next = null;

		ListNode left = sortList(head);
		ListNode right = sortList(tmp);

		ListNode dummy = new ListNode(0);
		ListNode res = dummy;
		while (left != null && right != null) {
			if (left.val < right.val) {
				dummy.next = left;
				left = left.next;
			} else {
				dummy.next = right;
				right = right.next;
			}
			dummy = dummy.next;
		}
		dummy.next = left != null ? left : right;
		return res.next;
	}

	public ListNode sortListV1(ListNode head) {
		// 归并排序
		int n = 0;
		// 走到null，刚好走链表的长度
		for (ListNode i = head; i != null; i = i.next) {
			n++;
		}

		ListNode dummy = new ListNode(0);
		dummy.next = head;

		// 循环一下
		// 第一层循环，分块，从1个一块，2个一块，4个一块，直到n个一块，
		for (int i = 1; i < n; i = 2 * i) {
			ListNode begin = dummy;
			// 开始归并
			// j + i >= n 表示只有一段就不归并了，因为已经是排好序的
			for (int j = 0; j + i < n; j = j + 2 * i) {
				// 两块，找两块的起始节点
				// 开始都指向第一块的起点
				// 然后second走n步指向第二块的起点
				ListNode first = begin.next;
				ListNode second = first;

				for (int k = 0; k < i; k++) {
					second = second.next;
				}
				// 遍历第一块和第二块进行归并
				// 第一块的数量为i
				// 第二块的数量为i也可能小于i，所以循环条件要加一个second != null
				int f = 0, s = 0;
				while (f < i && s < i && second != null) {
					if (first.val < second.val) {
						begin.next = first;
						begin = begin.next;
						first = first.next;
						f++;
					} else {
						begin.next = second;
						begin = begin.next;
						second = second.next;
						s++;
					}
				}
				// 归并之后可能又多余的没有处理
				while (f < i) {
					begin.next = first;
					begin = begin.next;
					first = first.next;
					f++;

				}
				while (s < i && second != null) {
					begin.next = second;
					begin = begin.next;
					// second已经更新到下一块的起点了
					second = second.next;
					s++;
				}
				// 更新begin
				// begin.next 指向下一块的起点
				begin.next = second;
			}
		}
		return dummy.next;
	}

	public static void main(String[] args) {
		Lc148 lc148 = new Lc148();
		ListNode head = new ListNode(3, new ListNode(2, new ListNode(4, new ListNode(6, new ListNode(5)))));
//		ListNode newHead = lc148.sortList(head);
		ListNode newHead = lc148.sortListV1(head);
		Printer.printList(newHead);
	}
}
