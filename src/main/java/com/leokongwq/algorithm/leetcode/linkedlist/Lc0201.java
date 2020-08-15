package com.leokongwq.algorithm.leetcode.linkedlist;

import com.leokongwq.algorithm.base.Printer;
import com.leokongwq.algorithm.leetcode.ListNode;

import java.util.*;

/**
 * @author : jiexiu
 * @date : 2020-08-16 00:22
 * <p>
 * easy
 * <p>
 * 编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。
 * <p>
 * 示例1:
 * <p>
 * 输入：[1, 2, 3, 3, 2, 1]
 * 输出：[1, 2, 3]
 * 示例2:
 * <p>
 * 输入：[1, 1, 1, 1, 2]
 * 输出：[1, 2]
 * 提示：
 * <p>
 * 链表长度在[0, 20000]范围内。
 * 链表元素在[0, 20000]范围内。
 * 进阶：
 * <p>
 * 如果不得使用临时缓冲区，该怎么解决？
 **/
public class Lc0201 {

	public ListNode removeDuplicateNodes(ListNode head) {
		Map<Integer, Boolean> map = new LinkedHashMap<>();
		ListNode p = head;
		while (p != null) {
			map.putIfAbsent(p.val, true);
			p = p.next;
		}
		ListNode dummy = new ListNode(-1);
		p = dummy;
		for (Integer val : map.keySet()) {
			p.next = new ListNode(val);
			p = p.next;
		}
		return dummy.next;
	}

	public ListNode removeDuplicateNodesV1(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		Set<Integer> occurred = new HashSet<Integer>();
		occurred.add(head.val);
		ListNode pos = head;
		// 枚举前驱节点
		while (pos.next != null) {
			// 当前待删除节点
			ListNode cur = pos.next;
			if (occurred.add(cur.val)) {
				pos = pos.next;
			} else {
				pos.next = pos.next.next;
			}
		}
		pos.next = null;
		return head;
	}

	public ListNode removeDuplicateNodesV2(ListNode head) {
		ListNode ob = head;
		while (ob != null) {
			ListNode oc = ob;
			while (oc.next != null) {
				if (oc.next.val == ob.val) {
					oc.next = oc.next.next;
				} else {
					oc = oc.next;
				}
			}
			ob = ob.next;
		}
		return head;
	}

	public static void main(String[] args) {
		Lc0201 lc0201 = new Lc0201();

		ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(3, new ListNode(2, new ListNode(1))))));

//		ListNode head = new ListNode(1, new ListNode(1, new ListNode(3)));

		ListNode h = lc0201.removeDuplicateNodesV1(head);

		Printer.printList(h);
	}
}
