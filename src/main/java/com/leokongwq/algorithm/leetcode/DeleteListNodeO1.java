package com.leokongwq.algorithm.leetcode;

/**
 * 题目：给定链表的头指针和一个结点指针，在O(1)时间删除该结点。
 * 这是一道广为流传的Google面试题，能有效考察我们的编程基本功，
 * 还能考察我们的反应速度，更重要的是，还能考察我们对时间复杂度的理解
 *
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/8/19
 * Time: 上午10:49
 * Email:leokongwq@gmail.com
 */
public class DeleteListNodeO1 {

    /**
     * O(1) 时间删除列表节点
     * @param listNode 待删除的列表首节点,
     * @param target 待删除的节点
     */
    public static ListNode deleteNode(ListNode listNode, ListNode target){
        if (listNode == null || target == null){
            return listNode;
        }
        //删除第一个节点
        if (listNode == target){
            ListNode tmp = listNode.next;
            listNode = tmp;
            return listNode;
        }
        //不是最后一个节点
        if (target.next != null){
            ListNode tmp = target.next;
            target.val = tmp.val;
            target.next = tmp.next;
            tmp = null;
            return listNode;
        }
        //查询target的前一个节点
        ListNode cur = listNode;
        while (cur.next != target){
            cur = cur.next;
        }
        cur.next = null;
        target = null;
        return listNode;
    }

    public static ListNode insertNode(ListNode node, ListNode newNode){
        if (node == null){
            return newNode;
        }
        newNode.next = node;
        return newNode;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);

        ListNode head = insertNode(null, node1);
        head = insertNode(head, node2);
        head = insertNode(head, node3);
        head = insertNode(head, node4);

        node1 = deleteNode(head, node1);
        System.out.println(node1);
    }
}
