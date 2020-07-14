package com.leokongwq.algorithm.geektime.list;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author : jiexiu
 * @date : 2020-06-19 17:39
 *
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 *
 * 示例:
 *
 * 输入:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 *
 **/
public class MergeKSortedList {

	private static class ListNode {
		private int value;

		private ListNode next;

		public ListNode(int x) {
			value = x;
		}
	}

	/**
	 * 暴力解法
	 */
	private static ListNode mergeKListsV1(ListNode[] lists) {
		if (lists == null) {
            return null;
		}
		if (lists.length == 1) {
            return lists[0];
        }
		List<Integer> result = new ArrayList<>();
		for (int i = 0; i < lists.length; i++) {
			ListNode node = lists[i];
			while (node != null) {
				result.add(node.value);
				node = node.next;
			}
		}
		result.sort(Comparator.comparingInt(num -> num));
		ListNode head = new ListNode(Integer.MIN_VALUE);
		ListNode tail = head;
		for (Integer num : result) {
			tail.next = new ListNode(num);
			tail = tail.next;
		}
		return head.next;
	}

	/**
	 * 每次获取每个链表的最小值插入新的链表
	 */
	private static ListNode mergeKListsV2(ListNode[] lists) {
		if (lists == null || lists.length == 1) {
			return lists == null ? null : lists[0];
		}
		ListNode head = new ListNode(Integer.MIN_VALUE);
		ListNode tail = head;

		while (true) {
			ListNode min = null;
			int minIndex = -1;
			for (int i = 0; i < lists.length; i++) {
				if (lists[i] != null) {
					if (min == null || lists[i].value < min.value) {
						min = lists[i];
						minIndex = i;
					}
				}
			}
			if (min == null) {
				break;
			}
			lists[minIndex] = lists[minIndex].next;
			tail.next = new ListNode(min.value);
			tail = tail.next;
		}
		return head.next;
	}

	/**
	 * 逐个合并2个有序链表
	 * 时间复杂度 ： O(k2n)
	 * 空间复杂度为 ：O(1)
	 */
	private static ListNode mergeKListsV3(ListNode[] lists) {
		if (lists == null || lists.length == 1) {
			return lists == null ? null : lists[0];
		}
		ListNode head = null;
		for (int i = 0; i < lists.length; i++) {
			head = mergeSortedList(head, lists[i]);
		}
		return head;
	}

	/**
	 * 采用分治法 两两合并
	 *  时间复杂度 O(kn * logk)
	 *  空间复杂度 O(logk)
	 */
	private static ListNode mergeKListsV4(ListNode[] lists) {
		if (lists == null || lists.length == 1) {
			return lists == null ? null : lists[0];
		}
		return doMergeKListsV4(lists, 0, lists.length - 1);
	}

	/**
	 * 采用分治法 两两合并
	 * 迭代
	 */
	private static ListNode mergeKListsV41(ListNode[] lists) {
		if (lists.length == 0) {
			return null;
		}
		int k = lists.length;
		while (k > 1) {
			int idx = 0;
			for (int i = 0; i < k; i += 2) {
				if (i == k - 1) {
					lists[idx++] = lists[i];
				} else {
					lists[idx++] = mergeSortedList(lists[i], lists[i + 1]);
				}
			}
			k = idx;
		}
		return lists[0];
	}

	/**
	 *  时间复杂度 O(NlogK)
	 *  空间复杂度 O(k)
	 */
	private static ListNode mergeKListsV5(ListNode[] lists) {
		if (lists == null || lists.length == 0) {
			return null;
		}

		PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, new Comparator<ListNode>() {
			@Override
			public int compare(ListNode o1, ListNode o2) {
				if (o1.value < o2.value) {
					return -1;
				}
				else if (o1.value == o2.value) {
					return 0;
				} else {
					return 1;
				}
			}
		});
		//哨兵头结点
		ListNode dummy = new ListNode(0);
		ListNode tail = dummy;
		for (ListNode node : lists) {
			if (node != null) {
				queue.add(node);
			}
		}
		while (!queue.isEmpty()) {
			tail.next = queue.poll();
			tail = tail.next;
			if (tail.next != null) {
				queue.add(tail.next);
			}
		}
		return dummy.next;
	}

	private static ListNode doMergeKListsV4(ListNode[] lists, int left, int right) {
		if (left == right) {
			return lists[left];
		}
		if (left > right) {
			return null;
		}
		int mid = (left + right) >> 1;
		return mergeSortedList(doMergeKListsV4(lists, left, mid), doMergeKListsV4(lists, mid + 1, right));
	}

	/**
	 * 时间复杂度：O(n)O(n)。
	 * 空间复杂度：O(1)O(1)。
	 */
	private static ListNode mergeSortedList(ListNode left, ListNode right) {
		if (left == null) {
			return right;
		}
		if (right == null) {
			return left;
		}

		ListNode p = left;
		ListNode q = right;
		ListNode head = new ListNode(Integer.MIN_VALUE);
		ListNode tail = head;
		while (p != null && q != null) {
			if (p.value <= q.value) {
				tail.next = p;
				p = p.next;
			} else {
				tail.next = q;
				q = q.next;
			}
			tail = tail.next;
		}
		if (p != null) {
			tail.next = p;
		}
		if (q != null) {
			tail.next = q;
		}
		return head.next;
	}


	//[[1,4,5],[1,3,4],[2,6]]
	public static void main(String[] args) {
		ListNode a = new ListNode(1);
		a.next = new ListNode(4);
		a.next.next = new ListNode(5);

		ListNode b = new ListNode(1);
		b.next = new ListNode(3);
		b.next.next = new ListNode(4);

		ListNode c = new ListNode(2);
		c.next = new ListNode(6);


//		ListNode header = mergeSortedList(a, b);
//		printList(header);
//		System.out.println("###################");


		ListNode[] arr = new ListNode[] {a, b, c};
//		ListNode head = mergeKListsV1(arr);
//		ListNode head = mergeKListsV2(arr);
//		ListNode head =	mergeKListsV3(arr);
		ListNode head =	mergeKListsV4(arr);

		printList(head);
	}

	private static void printList(ListNode head) {
		ListNode p = head;
		while (p != null) {
			System.out.println(p.value);
			p = p.next;
		}
	}
}
