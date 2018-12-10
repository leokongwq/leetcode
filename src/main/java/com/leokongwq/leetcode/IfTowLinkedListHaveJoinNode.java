package com.leokongwq.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * 判断2个单向链表是否相交, ( 假设2个链表都没有环)
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/8/19
 * Time: 下午2:38
 * Email:leokongwq@gmail.com
 */
public class IfTowLinkedListHaveJoinNode {

    /**
     * 算法思路: 2个链表相交,肯定有共同的尾节点, 分别遍历2个链表,记录每个链表的尾节点, 判断2个尾节点是否相等,
     * 相等则相交, 否则不相交 时间复杂度为O(M + N)
     * @param headOne 第一个链表的头节点
     * @param headTwo 第二个链表的头节点
     * @return
     */
    public static boolean isTowSingleLinkedListJoinV1(ListNode headOne, ListNode headTwo){

        ListNode endNodeOne = headOne;
        ListNode endNodeTwo = headTwo;

        while (endNodeOne.getNext() != null){
            endNodeOne = endNodeOne.getNext();
        }
        while (endNodeTwo.getNext() != null){
            endNodeTwo = endNodeTwo.getNext();
        }
        if (endNodeOne == endNodeTwo){
            return true;
        }
        return false;
    }

    /**
     * 算法思路: 首先遍历一个列表,将它的所有节点放入HashSet ;  遍历第二个链表,判断每个节点是否在HashSet中
     * 如果在 : 2个链表相交, 否则不相交
     * @param headOne
     * @param headTwo
     * @return
     */
    public static boolean isTowSingleLinkedListJoinV2(ListNode headOne, ListNode headTwo){
        Set<ListNode> oneListNodes = new HashSet<ListNode>();
        ListNode endNodeOne = headOne;
        while (endNodeOne != null){
            oneListNodes.add(endNodeOne);
            endNodeOne = endNodeOne.getNext();
        }
        ListNode endNodeTwo = headTwo;
        while (endNodeTwo != null){
            if (oneListNodes.contains(endNodeTwo)){
                return true;
            }
        }
        return false;
    }

}
