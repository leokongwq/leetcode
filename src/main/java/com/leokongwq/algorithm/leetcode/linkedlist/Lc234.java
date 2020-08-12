package com.leokongwq.algorithm.leetcode.linkedlist;

import com.leokongwq.algorithm.leetcode.ListNode;

/**
 * @author : jiexiu
 * @date : 2020-07-14 09:08
 *
 * easy
 *
 * 请判断一个链表是否为回文链表。
 *
 * 示例 1:
 *
 * 输入: 1->2
 * 输出: false
 * 示例 2:
 *
 * 输入: 1->2->2->1
 * 输出: true
 *
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 **/
public class Lc234 {

	/**
	 * 1. 通过翻转后的链表和原链表进行比较，如果每个值都相同那么就是回文链表
	 * 2. 借助一个stack也可以，遍历原始链表元素并压入栈中。再次遍历原始链表的同时出栈，比较值是否相同。
	 */
	public boolean isPalindrome(ListNode head) {
		if (head == null) {
			return true;
		}
		ListNode p = head;
		ListNode reverseHead = null;
		while (p != null) {
			if (reverseHead == null) {
				reverseHead = new ListNode(p.val);
			} else {
				ListNode node = new ListNode(p.val);
				node.next = reverseHead;
				reverseHead = node;
			}
			p = p.next;
		}
		p = head;
		ListNode q = reverseHead;
		while (p != null && q != null) {
			if (p.val != q.val) {
				return false;
			}
			p = p.next;
			q = q.next;
		}
		return true;
	}

	// 左侧指针
	private ListNode left;

	public boolean isPalindromeV1(ListNode head) {
		left = head;
		return traverse(head);
	}

	/**
	 * 本质上属于利用了递归函数的堆栈空间
	 */
	boolean traverse(ListNode right) {
		//空链表也是回文链表
		if (right == null) {
			return true;
		}
		boolean res = traverse(right.next);
		// 后序遍历代码（归的时候，right就是右边的指针）
		res = res && (right.val == left.val);
		left = left.next;
		return res;
	}

    /**
     * 算法总体的时间复杂度 O(N)，空间复杂度 O(1)，已经是最优的了。
     */
    public boolean isPalindromeV2(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        //奇数
        if (fast != null) {
            slow = slow.next;
        }

        ListNode left = head;
        ListNode right = reverse(slow);

        while (right != null) {
            if (left.val != right.val) {
                return false;
            }
            left = left.next;
            right = right.next;
        }
        return true;
    }

    ListNode reverse(ListNode head) {
        ListNode pre = null, cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static void main(String[] args) {
		Lc234 isPalindromeLinkedList = new Lc234();
//		ListNode head = new ListNode(1, new ListNode(2));
//		ListNode head = new ListNode(1, new ListNode(2, new ListNode(3)));
//		ListNode head = new ListNode(1, new ListNode(2, new ListNode(1)));
		ListNode head = new ListNode(1, new ListNode(2, new ListNode(2, new ListNode(1))));

		boolean result = isPalindromeLinkedList.isPalindrome(head);
		System.out.println(result);
	}
}
