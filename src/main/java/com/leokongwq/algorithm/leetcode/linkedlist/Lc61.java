package com.leokongwq.algorithm.leetcode.linkedlist;

import com.leokongwq.algorithm.base.Printer;
import com.leokongwq.algorithm.leetcode.ListNode;

/**
 * @author : jiexiu
 * @date : 2020-08-11 17:59
 *
 * medium
 *
 * 旋转链表， 将链表中每个节点向右移动k个位置, k是非负数
 **/
public class Lc61 {

	public ListNode rotateList(ListNode head, int k) {
		if (head == null || head.next == null || k == 0) {
			return head;
		}

		while (k > 0) {
			ListNode p = head;
			while (p.next != null && p.next.next != null) {
				p = p.next;
			}
			ListNode next = p.next;
			p.next = null;
			next.next = head;
			head = next;
			k--;
		}
		return head;
	}

	public static void main(String[] args) {
		ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
//		ListNode head = new ListNode(0, new ListNode(1, new ListNode(2)));

		Lc61 lc61 = new Lc61();
		ListNode h = lc61.rotateList(head, 2);
		Printer.printList(h);
	}
}
