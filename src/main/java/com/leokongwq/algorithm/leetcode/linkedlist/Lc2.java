package com.leokongwq.algorithm.leetcode.linkedlist;

import com.leokongwq.algorithm.base.Printer;
import com.leokongwq.algorithm.leetcode.ListNode;

/**
 * @author : jiexiu
 * @date : 2020-08-10 16:07
 *
 * medium
 *
 **/
public class Lc2 {

	public ListNode towSum(ListNode head1, ListNode head2) {
		ListNode h1 = head1;
		ListNode h2 = head2;

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
			overFlow = (overFlow + last.val) / 10;
			int num = (overFlow + last.val) % 10;

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

		ListNode h1 = new ListNode(2, new ListNode(4, new ListNode(3)));
		ListNode h2 = new ListNode(5, new ListNode(6, new ListNode(4, new ListNode(3))));

		ListNode head = lc2.towSum(h1, h2);

		Printer.printList(head);
	}
}
