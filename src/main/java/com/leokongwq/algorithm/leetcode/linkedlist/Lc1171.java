package com.leokongwq.algorithm.leetcode.linkedlist;

import com.leokongwq.algorithm.base.Printer;
import com.leokongwq.algorithm.leetcode.ListNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : jiexiu
 * @date : 2020-08-15 17:02
 *
 * medium
 *
 * 给你一个链表的头节点 head，请你编写代码，反复删去链表中由 总和 值为 0 的连续节点组成的序列，直到不存在这样的序列为止。
 *
 * 删除完毕后，请你返回最终结果链表的头节点。
 *
 *  
 *
 * 你可以返回任何满足题目要求的答案。
 *
 * （注意，下面示例中的所有序列，都是对 ListNode 对象序列化的表示。）
 *
 * 示例 1：
 *
 * 输入：head = [1,2,-3,3,1]
 * 输出：[3,1]
 * 提示：答案 [1,2,1] 也是正确的。
 * 示例 2：
 *
 * 输入：head = [1,2,3,-3,4]
 * 输出：[1,2,4]
 * 示例 3：
 *
 * 输入：head = [1,2,3,-3,-2]
 * 输出：[1]
 *
 **/
public class Lc1171 {

	public ListNode removeZeroSumSublists(ListNode head) {
		ListNode dummy = new ListNode(0);
		dummy.next = head;

		Map<Integer, ListNode> node2Sum = new HashMap<>();
		int sum = 0;
		for (ListNode d = dummy; d != null; d = d.next) {
			sum += d.val;
			node2Sum.put(sum, d);
		}

		sum = 0;
		for (ListNode d = dummy; d != null; d = d.next) {
			sum += d.val;
			d.next = node2Sum.get(sum).next;
		}

		return dummy.next;
	}

	public static void main(String[] args) {
		ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(-3, new ListNode(-2)))));

		Lc1171 lc1171 = new Lc1171();
		ListNode newHead = lc1171.removeZeroSumSublists(head);
		Printer.printList(newHead);
	}
}
