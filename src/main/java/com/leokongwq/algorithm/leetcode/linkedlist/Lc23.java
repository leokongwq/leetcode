package com.leokongwq.algorithm.leetcode.linkedlist;

import com.leokongwq.algorithm.base.Printer;
import com.leokongwq.algorithm.leetcode.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : jiexiu
 * @date : 2020-08-11 17:29
 * <p>
 * hard
 * <p>
 * 合并k个有序链表
 **/
public class Lc23 {

	public ListNode mergeList(List<ListNode> listNodes) {
		if (listNodes == null || listNodes.size() == 0) {
			return null;
		}
		ListNode head = null;
		for (ListNode listNode : listNodes) {
			Lc21 lc = new Lc21();
			head = lc.mergeTwoSortedList(head, listNode);
		}
		return head;
	}

	public ListNode mergeListV1(List<ListNode> listNodes) {
		if (listNodes == null || listNodes.size() == 0) {
			return null;
		}
		return doMerger(0, listNodes.size() - 1, listNodes);
	}

	private ListNode doMerger(int start, int end, List<ListNode> listNodes) {
		if (start == end) {
			return listNodes.get(start);
		}
		int mid = start + (end - start) / 2;

		ListNode h1 = doMerger(start, mid, listNodes);
		ListNode h2 = doMerger(mid + 1, end, listNodes);

		Lc21 lc = new Lc21();
		return lc.mergeTwoSortedList(h1, h2);
	}

	public static void main(String[] args) {
		Lc23 lc23 = new Lc23();
		List<ListNode> listNodes = new ArrayList<>();
		listNodes.add(new ListNode(1, new ListNode(3, new ListNode(5))));
		listNodes.add(new ListNode(2, new ListNode(4, new ListNode(6, new ListNode(7)))));

//		ListNode head = lc23.mergeList(listNodes);
		ListNode head = lc23.mergeListV1(listNodes);

		Printer.printList(head);
	}
}
