package com.leokongwq.algorithm.leetcode.linkedlist;

import com.leokongwq.algorithm.leetcode.ListNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 2个没有环的单向链表, 查找2个链表的相交点, 如果2个不相交, 则返回null, 相交则返回相交点
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/8/19
 * Time: 下午9:14
 * Email:leokongwq@gmail.com
 * <p>
 * easy
 * <p>
 * <p>
 * 注意：
 * <p>
 * 如果两个链表没有交点，返回 null.
 * 在返回结果后，两个链表仍须保持原有的结构。
 * 可假定整个链表结构中没有循环。
 * 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 * <p>
 * 不能使用 HashMap了
 */
public class Lc160 {

	public ListNode findTwoListJoinPoint(ListNode headA, ListNode headB) {
	    if (headA == null || headB == null) {
	        return null;
        }
        Map<ListNode, ListNode> map = new HashMap<>();
	    ListNode p = headA;

	    while (p != null) {
	        map.put(p, p);
	        p = p.next;
        }
	    ListNode q = headB;
	    while (q != null) {
	        if (map.containsKey(q)) {
	            return q;
            }
	        q = q.next;
        }
	    return null;
	}

	/**
	 * 思路:
	 * 1.查找A链表的最后一个节点endA, 同时记录A链表的长度为lenA
	 * 2.查找B链表的最后一个节点enB, 同时记录B链表的长度为lenB
	 * 3.判断endA 是否等于endB, 如果不相等, 返回null
	 * 4.计算size = lenA - lenB, 如果差值大于0, 则让指针P从A的头节点开始走size步; B节点同理
	 * 5.两个节点同时走, 相遇的节点就是连接的节点
	 */
	public ListNode findTwoListJoinPointV1(ListNode headA, ListNode headB) {
		if (headA == null || headB == null) {
			return null;
		}

		ListNode p = headA;
		ListNode q = headB;
		int lenA = 1;
		int lenB = 1;
		while (p.next != null) {
			lenA++;
			p = p.next;
		}
		while (q.next != null) {
			q = q.next;
			lenB++;
		}
		//2个链表不相交
		if (p != q) {
			return null;
		}
		//相交
		int num = lenA - lenB;
		p = headA;
		q = headB;

		//a链表长, 则a链表先走num 步
		if (num > 0) {
			int i = num;
			while (i-- > 0) {
				p = p.next;
			}
		}
		if (num < 0) {
			int j = -num;
			while (j-- > 0) {
				q = q.next;
			}
		}
		// p q 没有相遇
		while (p != q) {
			p = p.next;
			q = q.next;
		}
		return p;
	}

    public static void main(String[] args) {

    }
}
