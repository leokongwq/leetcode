package com.leokongwq.algorithm.leetcode;

/**
 * 查询单向列表中倒数第k的元素, 链表的倒数第0个节点为链表的尾指针
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/8/19
 * Time: 下午12:46
 * Email:leokongwq@gmail.com
 */
public class TheKthNode {

    public static ListNode findK(ListNode head, int k){
        //链表为空
        if (head == null || k < 0){
            return head;
        }
        ListNode slow = head, fast = head;

        //fast 哨兵往前走k步
        int i = 0;
        for (; i < k && fast != null; i++){
            fast = fast.next;
        }
        if (i < k){ //链表长度不够
            return null;
        }
        //同步往前走
        while (fast != null){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, null))));
        ListNode  target = findK(head, 1);
        System.out.println(target);
    }
}
