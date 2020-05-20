package com.leokongwq.geektime.queue;

/**
 * @author jiexiu
 * created 2020/5/20 - 22:49
 */
public class ArrayQueue {

    /**
     * 当前队列的大小
     */
    private int size;

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

    public ArrayQueue(int capacity) {
        this.items = new String[capacity];
        this.capacity = capacity;
    }

    /**
     * 入队
     */
    public boolean enqueue(String item) {
        if (tail == capacity) {
            //表示前面没有空位
            if (head == 0) {
                return false;
            }
            // 移动元素
            for (int i = head; i < tail; i++) {
                items[i - head] = items[i];
            }
            //修改 head, tail的值, 注意顺序不能颠倒
            tail = tail - head;
            head = 0;
        }
        items[tail++] = item;
        size++;
        return true;
    }

    /**
     * 出队
     */
    public String dequeue() {
        //出队的下标 等于 入队的下标 表示队列空了
        if (head == tail) {
            return null;
        }
        size--;
        return items[head++];
    }

    public int size() {
        return size;
    }


    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(5);
        for (int i = 0; i < 5; i++) {
            queue.enqueue("item:" + i);
        }
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());

        queue.enqueue("item:5");
        queue.enqueue("item:6");

        int n = queue.size();
        for (int i = 0; i < n; i++) {
            System.out.println(queue.dequeue());
        }
    }
}
