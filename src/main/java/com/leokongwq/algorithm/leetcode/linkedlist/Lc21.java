package com.leokongwq.algorithm.leetcode.linkedlist;

import com.leokongwq.algorithm.base.Printer;
import com.leokongwq.algorithm.leetcode.ListNode;

/**
 * @author : jiexiu
 * @date : 2020-08-10 19:50
 * <p>
 * easy
 * <p>
 * 合并2个有序链表
 **/
public class Lc21 {

	public ListNode mergeTwoSortedList(ListNode h1, ListNode h2) {
		if (h1 == null) {
			return h2;
		}
		if (h2 == null) {
			return h1;
		}
		ListNode dump = new ListNode(Integer.MAX_VALUE);
		ListNode tail = dump;
		while (h1 != null && h2 != null) {
			if (h1.val <= h2.val) {
				tail.next = h1;
				h1 = h1.next;
				tail = tail.next;
			} else {
				tail.next = h2;
				h2 = h2.next;
				tail = tail.next;
			}
		}
		if (h1 != null) {
			tail.next = h1;
		}
		if (h2 != null) {
			tail.next = h2;
		}
		return dump.next;
	}

	public static void main(String[] args) {
		Lc21 lc21 = new Lc21();
		ListNode h1 = new ListNode(1, new ListNode(3, new ListNode(5)));
		ListNode h2 = new ListNode(2, new ListNode(4));
		ListNode header = lc21.mergeTwoSortedList(h1, h2);
		Printer.printList(header);
	}
}
