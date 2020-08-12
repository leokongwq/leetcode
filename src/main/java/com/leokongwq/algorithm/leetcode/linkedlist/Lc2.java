package com.leokongwq.algorithm.leetcode.linkedlist;

import com.leokongwq.algorithm.base.Printer;
import com.leokongwq.algorithm.leetcode.ListNode;

/**
 * @author : jiexiu
 * @date : 2020-08-10 16:07
 *
 * medium
 *
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 **/
public class Lc2 {

	public ListNode towSum(ListNode l1, ListNode l2) {
		ListNode h1 = l1;
		ListNode h2 = l2;

		//表示进位
		int overFlow = 0;
		ListNode head = new ListNode(-1);
		ListNode tail = head;
		while (h1 != null && h2 != null) {
			int temp = overFlow;
			overFlow = (h1.val + h2.val + temp) / 10;
			int num = (h1.val + h2.val + temp) % 10;
			tail.next = new ListNode(num);
			tail = tail.next;

			h1 = h1.next;
			h2 = h2.next;
		}
		ListNode last = (h1 == null) ? h2 : h1;

		while (last != null) {
            int temp = overFlow;

			overFlow = (temp + last.val) / 10;
			int num = (temp + last.val) % 10;

			tail.next = new ListNode(num);
			tail = tail.next;

			last = last.next;
		}
		if (overFlow != 0) {
			tail.next = new ListNode(overFlow);
			tail = tail.next;
		}
 		return head.next;
	}

	public static void main(String[] args) {
		Lc2 lc2 = new Lc2();

//		ListNode h1 = new ListNode(2, new ListNode(4, new ListNode(3)));
//		ListNode h2 = new ListNode(5, new ListNode(6, new ListNode(4, new ListNode(3))));

		ListNode h1 = new ListNode(9, new ListNode(8));
		ListNode h2 = new ListNode(1);

		ListNode head = lc2.towSum(h1, h2);

		Printer.printList(head);
	}
}
