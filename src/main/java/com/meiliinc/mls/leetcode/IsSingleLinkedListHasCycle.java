package com.meiliinc.mls.leetcode;

/**
 * 判断单链表是否存在环
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/8/19
 * Time: 下午2:15
 * Email:jiexiu@mogujie.com
 */
public class IsSingleLinkedListHasCycle {

    /**
     * 算法的原理是: 只有链表有环, 则快的节点肯定会和慢的节点在环上相遇
     * @param head
     * @return
     */
    public static boolean isLinkedListHasCycle(ListNode head){
        if (head == null || head.getNext() == null){
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.getNext() != null){
            slow = slow.getNext();
            fast = fast.getNext().getNext();
            if (fast == slow){
                return true;
            }
        }
        return false;
    }
}
