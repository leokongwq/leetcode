package com.leokongwq.algorithm.leetcode.linkedlist;

/**
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/8/18
 * Time: 下午7:08
 * Email:leokongwq@gmail.com
 */
public class ReverseSingleLinkedList {
	static class ListNode {
		private int val;

		private ListNode next;

		public ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}

	}

	/**
	 * 反转单链表, 最简单, 需要使用数组, 空间占用大, 一般不会推荐使用
	 */
	public static ListNode reverseListV1(ListNode head, int n) {
		ListNode[] arr = new ListNode[n];
		int i = 0;
		ListNode last = head;
		while (last != null) {
			arr[i++] = last;
			last = last.next;
		}
		for (int j = arr.length - 1; j > 0; j--) {
			arr[j].next = arr[j - 1];
			arr[j - 1].next = null;
		}
		return arr[n - 1];
	}

	/**
	 * 反转单链表, 采用p , q 两个指针, 从第二个节点开始, 逐个节点进行反转; 不需要分配额外的空间, 可以采用
	 */
	public static ListNode reverseListV2(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode p = head;
		ListNode q = head.next;
		head.next = null;

		while (q != null) {
			ListNode r = q.next;
			q.next = p;
			p = q;
			q = r;
		}
		head = p;
		return head;
	}

	/**
	 * 反转单链表, 新建一个链表, 将老列表的每一个节点作为新链表的第一个节点, 需要额外的空间;
	 * 新链表建立完成后,可以释放掉老列表的空间
	 */
	public static ListNode reverseListV3(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode newHead = new ListNode(head.val, null);
		ListNode p = head.next;
		while (p != null) {
			ListNode tmp = new ListNode(p.val, null);
			tmp.next = newHead;
			newHead = tmp;
			p = p.next;
		}
		return newHead;
	}

	/**
	 * 反转单链表
	 */
	public static ListNode reverseListV4(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode newHead = new ListNode(head.val, null);
		ListNode tmp;

		while (head.next != null) {
			tmp = head.next;         //tmp指向将要删除的节点
			head.next = head.next.next;     //从list中摘除这个结点
			tmp.next = newHead;       //将该节点放入新列表的第一个节点
			newHead = tmp;        //newHead指向新列表的第一个节点
		}
		head = null; //删除老链表的首节点
		return newHead;
	}

	/**
	 * 递归方式翻转单链表
	 */
	public static ListNode reverseByRecursion(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		//递归逆向返回时: newHead是老链表的最后一个节点; // head 参数是倒数第二个节点
		ListNode newHead = reverseByRecursion(head.next);
		head.next.next = head; // 最后2个节点成环形,
		head.next = null; // 打断原有的正向连接, 只保留逆向的关系
		return newHead;
	}

	public static void printListNode(ListNode listNode) {
		ListNode last = listNode;
		while (last != null) {
			System.out.print(last.val + ",");
			last = last.next;
		}
		System.out.println();
	}

	public static void main(String[] args) {
		ListNode node = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, null))));
		ListNode head = reverseListV1(node, 4);
		printListNode(head);

		node = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, null))));
		head = reverseListV2(node);
		printListNode(head);

		node = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, null))));
		head = reverseListV3(node);
		printListNode(head);

		node = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, null))));
		head = reverseListV4(node);
		printListNode(head);

		node = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, null))));
		head = reverseByRecursion(node);
		printListNode(head);

	}
}
