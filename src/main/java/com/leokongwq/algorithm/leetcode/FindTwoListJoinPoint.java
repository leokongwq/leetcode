package com.leokongwq.algorithm.leetcode;

/**
 * 2个没有环的单向链表, 查找2个链表的相交点, 如果2个不相交, 则返回null, 相交则返回相交点
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/8/19
 * Time: 下午9:14
 * Email:leokongwq@gmail.com
 */
public class FindTwoListJoinPoint {

    /**
     *
     * 思路:
     * 1.查找A链表的最后一个节点endA, 同时记录A链表的长度为lenA
     * 2.查找B链表的最后一个节点enB, 同时记录B链表的长度为lenB
     * 3.判断endA 是否等于endB, 如果不相等, 返回null
     * 4.计算size = lenA - lenB, 如果差值大于0, 则让指针P从A的头节点开始走size步; B节点同理
     * 5.两个节点同时走, 相遇的节点就是连接的节点
     * @param headOne
     * @param headTwo
     * @return
     */
    public ListNode findTwoListJoinPoint(ListNode headOne, ListNode headTwo){
        ListNode endNodeOne = headOne;
        ListNode endNodeTwo = headTwo;
        int lenA = 1;
        int lenB = 1;
        while (endNodeOne.getNext() != null){
            endNodeOne = endNodeOne.getNext();
            lenA++;
        }
        while (endNodeTwo.getNext() != null){
            endNodeTwo = endNodeTwo.getNext();
            lenB++;
        }
        //2个链表不相交
        if (endNodeOne != endNodeTwo){
            return null;
        }
        //相交
        int num = lenA - lenB;
        ListNode p = headOne;
        ListNode q = headTwo;
        if (num > 0){ //a链表长, 则a链表先走num 步
            while (num-- > 0){
                p = p.getNext();
            }
        }
        if (num < 0){
            while (num-- > 0){
                q = q.getNext();
            }
        }
        // p q 没有相遇
        while (p != q){
            p = p.getNext();
            q = q.getNext();
        }
        return p;
    }
}
