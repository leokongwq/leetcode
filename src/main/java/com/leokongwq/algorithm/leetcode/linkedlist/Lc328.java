package com.leokongwq.algorithm.leetcode.linkedlist;

import com.leokongwq.algorithm.leetcode.ListNode;

/**
 * @author : jiexiu
 * @date : 2020-08-10 15:00]
 *
 * medium
 *
 * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 *
 * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
 *
 * 示例 1:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 1->3->5->2->4->NULL
 * 示例 2:
 *
 * 输入: 2->1->3->5->6->4->7->NULL
 * 输出: 2->3->6->7->1->5->4->NULL
 * 说明:
 *
 * 应当保持奇数节点和偶数节点的相对顺序。
 * 链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。
 **/
public class Lc328 {

	public ListNode oldEvenList(ListNode head) {
		if (head == null || head.next == null || head.next.next == null) {
			return head;
		}
		ListNode p = head;
		ListNode q = head.next;
		ListNode k = head.next.next;
		q.next = null;

		int num = 0;
		while (k != null) {
			ListNode temp = k.next;
			k.next = null;

			if (++num % 2 == 1) {
				k.next = p.next;
				p.next = k;
				k = temp;
				p = p.next;
			} else {
				q.next = k;
				k = temp;
				q = q.next;
			}
		}
		return head;
	}

	public static void main(String[] args) {
		Lc328 lc328 = new Lc328();
		ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, new ListNode(6))))));
		lc328.oldEvenList(head);
		System.out.println(head);
	}
}
