package com.leokongwq.algorithm.leetcode.linkedlist;

import com.leokongwq.algorithm.leetcode.ListNode;

/**
 * @author : jiexiu
 * @date : 2020-07-14 09:15
 **/
public class ReversePrintLinkedList {

	public static void print(ListNode listNode) {
		if (listNode == null) {
			return;
		}
		print(listNode.next);
		System.out.println(listNode.val);
	}

	public static void main(String[] args) {
		ListNode head = new ListNode(1, new ListNode(2, new ListNode(3)));
		ReversePrintLinkedList.print(head);
	}
}
