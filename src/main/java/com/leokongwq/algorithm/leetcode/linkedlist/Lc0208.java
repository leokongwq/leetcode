package com.leokongwq.algorithm.leetcode.linkedlist;

import com.leokongwq.algorithm.leetcode.ListNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jiexiu
 * created 2020/8/12 - 08:20
 *
 * 给定一个链表，如果它是有环链表，实现一个算法返回环路的开头节点。
 * 有环链表的定义：在链表中某个节点的next元素指向在它前面出现过的节点，则表明该链表存在环路。
 *
 * medium 
 *
 * 示例 1：
 *
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：tail connects to node index 1
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 *  
 *
 * 示例 2：
 *
 * 输入：head = [1,2], pos = 0
 * 输出：tail connects to node index 0
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 *  
 *
 * 示例 3：
 *
 * 输入：head = [1], pos = -1
 * 输出：no cycle
 * 解释：链表中没有环。
 *
 */
public class Lc0208 {

    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }

        Map<ListNode, Integer> map = new HashMap<>();
        ListNode p = head;

        while (p != null) {
            if (map.containsKey(p)) {
                break;
            }
            map.put(p, p.val);
            p = p.next;
        }

        if (p == null) {
            return null;
        }

        return p;
    }


    public ListNode detectCycleV1(ListNode head) {
        ListNode meetingNode = hasCycle(head);
        if (meetingNode == null) {
            return null;
        }

        ListNode p = head;

        while (p != meetingNode) {
            p = p.next;
            meetingNode = meetingNode.next;
        }
        return p;
    }

    /**
     * 返回相遇的点
     *
     * 空间复杂度为 O(1)， 不适用额外的 Map保存出现过的节点
     */
    private ListNode hasCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return slow;
            }
        }
        return null;
    }
}
