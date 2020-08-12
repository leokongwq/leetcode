package com.leokongwq.algorithm.leetcode.linkedlist;

import com.leokongwq.algorithm.leetcode.ListNode;

/**
 * 判断单链表是否存在环
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/8/19
 * Time: 下午2:15
 * Email:leokongwq@gmail.com
 */
public class IsSingleLinkedListHasCycle {

    /**
     * 算法的原理是: 只有链表有环, 则快的节点肯定会和慢的节点在环上相遇
     */
    public static boolean isLinkedListHasCycle(ListNode head){
        if (head == null || head.next == null){
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow){
                return true;
            }
        }
        return false;
    }
}
