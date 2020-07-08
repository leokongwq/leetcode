package com.leokongwq.algorithm.leetcode;

/**
 * 查找链表的中间节点
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/8/19
 * Time: 下午1:48
 * Email:leokongwq@gmail.com
 */
public class FindMidNodeInLinkedList {

    /**
     * 查询列表的中间节点
     * 思路1: 遍历链表,计算长度; 再次遍历, 直到计数器 i = lenght / 2 停止;
     * 思路2: 设置2个指针(slow, fast )分别指向head, slow每次前进一步, fast前进2步,
     * fast 到达末尾, 则slow就是中间节点; 原理如下:
     * 假设链表长度为: n  每次一步,从头开始走 n / 2 步就可以找到中点,
     * 如果每次2步, 则走 n / 2 步可以到达最后一个节点.所以fast节点走的同时, slow节点
     * 也在前进, fast节点到达最后节点时, slow节点也刚好走了 n / 2 步, slow节点就是中点
     *
     * @param head
     * @return
     */
    public static ListNode findMidNode(ListNode head){
        if (head == null){
            return null;
        }
        //两个都从头开始走
        ListNode slow = head, fast = head;
        while (fast != null && fast.getNext() != null){
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        return slow;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode mid = findMidNode(head);
        System.out.println(mid);

        head = new ListNode(1, new ListNode(2));
        mid = findMidNode(head);
        System.out.println(mid);

        head = new ListNode(1, new ListNode(2, new ListNode(3)));
        mid = findMidNode(head);
        System.out.println(mid);

        head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, null))));
        mid = findMidNode(head);
        System.out.println(mid);


    }
}
