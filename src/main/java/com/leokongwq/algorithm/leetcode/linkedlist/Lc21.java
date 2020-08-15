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

	public ListNode mergeTwoSortedList(ListNode l1, ListNode l2) {
		if (l1 == null) {
			return l2;
		}
		if (l2 == null) {
			return l1;
		}
		ListNode dump = new ListNode(Integer.MAX_VALUE);
		ListNode tail = dump;
		while (l1 != null && l2 != null) {
			if (l1.val <= l2.val) {
				tail.next = l1;
				l1 = l1.next;
				tail = tail.next;
			} else {
				tail.next = l2;
				l2 = l2.next;
				tail = tail.next;
			}
		}
		if (l1 != null) {
			tail.next = l1;
		}
		if (l2 != null) {
			tail.next = l2;
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
