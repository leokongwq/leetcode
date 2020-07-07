package com.leokongwq.algorithm.geektime.queue;

/**
 * @author jiexiu
 * created 2020/5/20 - 23:23
 * 数组实现循环队列
 */
public class ArrayCycleQueue {

    private int capacity;
    /**
     * 保存队列元素的数组
     */
    private String[] items;

    /**
     * 队首，待出队的下标
     */
    private int head;

    /**
     * 队尾，也就是待入队的下标
     */
    private int tail;

    public ArrayCycleQueue(int capacity) {
        this.items = new String[capacity];
        this.capacity = capacity;
    }

    /**
     * 入队
     * 重点是如何判断队满， 这里采用的公式是：(tail + 1) % n == head
     */
    public boolean enqueue(String item) {
        //这里需要非常注意：tail 表示的待插入元素的下标，+1 == head 就不能入队，其实是浪费了一个空间的
        //这个空间就是当前tail指向的空间
        if ((tail + 1) % capacity == head) {
            return false;
        }
        items[tail] = item;
        tail = (tail + 1) % capacity;
        return true;
    }

    /**
     * 出队
     * 队列为空的判断条件时 head == tail, 可以理解为：出队下标已经移动到待入队的下标了。
     */
    public String dequeue() {
        if (head == tail) {
            return null;
        }
        String item = items[head];
        head = (head + 1) % capacity;
        return item;
    }

    public static void main(String[] args) {
        ArrayCycleQueue cycleQueue = new ArrayCycleQueue(5);
        for (int i = 0; i < 5; i++) {
            cycleQueue.enqueue("item:" + i);
        }
        System.out.println(cycleQueue.dequeue());
        System.out.println(cycleQueue.dequeue());

        cycleQueue.enqueue("item:5");
        cycleQueue.enqueue("item:6");
    }
}
