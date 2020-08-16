package com.leokongwq.algorithm.leetcode.linkedlist;

import com.leokongwq.algorithm.base.Printer;
import com.leokongwq.algorithm.leetcode.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : jiexiu
 * @date : 2020-08-16 16:08
 *
 * medium
 *
 * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
 * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 示例 1:
 *
 * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
 * 示例 2:
 *
 * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
 *
 **/
public class Lc143 {

	private ListNode left;

	public void reorderList(ListNode head) {
		if (head == null) {
			return;
		}
		//存到 list 中去
		List<ListNode> list = new ArrayList<>();
		while (head != null) {
			list.add(head);
			head = head.next;
		}
		//头尾指针依次取元素
		int i = 0, j = list.size() - 1;
		while (i < j) {
			list.get(i).next = list.get(j);
			i++;
			//偶数个节点的情况，会提前相遇
			if (i == j) {
				break;
			}
			list.get(j).next = list.get(i);
			j--;
		}
		list.get(i).next = null;
	}

	public void reorderListV1(ListNode head) {
		if (head == null || head.next == null || head.next.next == null) {
			return;
		}
		//找中点，链表分成两个
		ListNode slow = head;
		ListNode fast = head;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}

		ListNode newHead = slow.next;
		slow.next = null;

		//第二个链表倒置
		newHead = reverseList(newHead);

		//链表节点依次连接
		while (newHead != null) {
			ListNode temp = newHead.next;
			newHead.next = head.next;
			head.next = newHead;
			head = newHead.next;
			newHead = temp;
		}
	}

	private ListNode reverseList(ListNode head) {
		if (head == null) {
			return null;
		}
		ListNode tail = head;
		head = head.next;

		tail.next = null;

		while (head != null) {
			ListNode temp = head.next;
			head.next = tail;
			tail = head;
			head = temp;
		}

		return tail;
	}

	public void reorderListV2(ListNode head) {
		//base case
		if (head == null || head.next == null || head.next.next == null) {
			return;
		}
		int len = 0;
		ListNode h = head;
		//求出节点数
		while (h != null) {
			len++;
			h = h.next;
		}

		reorderListHelper(head, len);
	}

	private ListNode reorderListHelper(ListNode head, int len) {
		if (len == 1) {
			ListNode outTail = head.next;
			head.next = null;
			return outTail;
		}
		if (len == 2) {
			ListNode outTail = head.next.next;
			head.next.next = null;
			return outTail;
		}
		//得到对应的尾节点，并且将头结点和尾节点之间的链表通过递归处理
		ListNode tail = reorderListHelper(head.next, len - 2);
		ListNode subHead = head.next;//中间链表的头结点
		head.next = tail;
		ListNode outTail = tail.next;  //上一层 head 对应的 tail
		tail.next = subHead;
		return outTail;
	}

	public static void main(String[] args) {
		Lc143 lc143 = new Lc143();
		ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
//		ListNode head = new ListNode(1, new ListNode(2));

		lc143.reorderListV2(head);

		Printer.printList(head);
	}
}
