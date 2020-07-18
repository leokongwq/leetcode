package com.leokongwq.algorithm.datastruct;

/**
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/10/31
 * Time: 下午5:49
 * Email:leokongwq@gmail.com
 */
public class SingleLinkedList<T> {

    /**
     * 头节点
     */
    private ListNode head;
    /**
     * 尾节点
     */
    private ListNode tail;

    private int size;

    public SingleLinkedList(){
    }

    public SingleLinkedList(T data){
        this.head = new ListNode(data);
        this.tail = head;
        this.size++;
    }

    public boolean add(T data){
        if (this.head == null){
            ListNode<T> node = new ListNode<T>(data);
            this.head = node;
            this.tail = head;
        }else {
            ListNode<T> node = new ListNode<T>(data);
            this.tail.next = node;
            this.tail = node;
        }
        this.size++;
        return true;
    }

    /**
     * 在target后面插入新元素
     * @param target
     * @param data
     */
    public boolean insertAfter(T target, T data){
        //空链表
        if (isEmpty()){
            this.head = this.tail = new ListNode(data);
            this.size++;
            return true;
        }
        //尾节点
        if (tail.data.equals(target)){
            ListNode newNode = new ListNode(data);
            tail.next = newNode;
            this.tail = newNode;
            this.size++;
            return true;
        }
        //查找目标节点
        ListNode targetNode = findTarget(target);
        ListNode next = targetNode.next;
        ListNode newNode = new ListNode(data, next);
        targetNode.next = newNode;
        this.size++;
        return true;
    }

    /**
     * 查找数据为data的链表节点
     * @param data
     * @return
     */
    private ListNode findTarget(T data){
        if (isEmpty()){
            return null;
        }
        ListNode next = head;
        while (next != null){
            if (next.data.equals(data)){
                return next;
            }
            next = next.next;
        }
        return null;
    }

    public boolean isEmpty(){
        return this.size > 0;
    }

    private static class ListNode<T> {
        private T data;
        private ListNode<T> next;

        public ListNode(T data){
            this(data, null);
        }

        public ListNode(T data, ListNode<T> next) {
            this.data = data;
            this.next = next;
        }

        public T getData() {
            return data;
        }

        public ListNode<T> getNext() {
            return next;
        }
    }

    public static void main(String[] args) {
        SingleLinkedList<Integer> list = new SingleLinkedList<Integer>(0);
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(list);
        list.insertAfter(2, 5);
        System.out.println(list);
    }
}
