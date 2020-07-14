package com.leokongwq.algorithm.design;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : jiexiu
 * @date : 2020-07-13 20:52
 *
 * LRU 缓存设计
 *
 * 支持操作：
 * 1. 快速插入，查找，删除 O(1)
 * 2. 最近访问的元素移动到头部
 *
 * 分析：
 * 1. 哈希表支持O(1)的查找和删除， 但是无序，不能快速将最近访问的元素移动到头部。
 * 2. 链表支持快速移动元素到头部，但是不支持快速查找和删除(删除中间元素)
 * 3. 将两者结合起来使用
 **/
public class LruCache {

    static class Node {
		private String key;
		private String val;
		private Node next;
		private Node prev;

		public Node(String key, String val) {
			this.key = key;
			this.val = val;
		}
	}

	private static final int DEFAULT_CAPACITY = 100;

	private int capacity;

	private int size;

	private Map<String, Node> container = new HashMap<>();

    private DoubleList doubleList = new DoubleList();

	public LruCache() {
		this(DEFAULT_CAPACITY);
	}

	public LruCache(int capacity) {
		this.capacity = capacity;

	}

	public void put(String key, String value) {
		if (isFull()) {
			return;
		}
		// update
		if (container.containsKey(key)) {
			container.get(key).val = value;
			return;
		}
		Node node = new Node(key, value);
		this.doubleList.addFirst(node);
		container.put(key, node);
		size++;
	}

	public String get(String key) {
		if (!container.containsKey(key)) {
			return null;
		}
		Node node = container.get(key);
		this.doubleList.remove(node);
		this.doubleList.addFirst(node);
		return node.val;
	}

	public void remove(String key) {
	    Node node = container.get(key);
	    if (node != null) {
            container.remove(key);
            this.doubleList.remove(node);
            size--;
        }
	}

	public int getSize() {
		return size;
	}

    private void print() {
        this.doubleList.print();
	}

	private boolean isFull() {
		return size == capacity;
	}

	private static class DoubleList {

	    private int size;

        private Node head;

        private Node tail;

        public DoubleList() {
            //dumb node
            head = new Node(null, null);
            tail = new Node(null, null);
            head.next = tail;
            tail.prev = head;
        }

        /**
         * 在链表头部添加节点 x，时间 O（1）
         */
        public void addFirst(Node x) {
            x.next = head.next;
            head.next.prev = x;
            head.next = x;
            x.prev = head;
        }

        /**
         * 删除链表中的 x 节点（x 一定存在）
         * 由于是双链表且给的是目标 Node 节点，时间O（1）
         */
        public void remove(Node x) {
            Node prev = x.prev;
            x.next.prev = prev;
            x.prev = null;

            prev.next = x.next;
            x.next = null;
        }

        /**
         * 删除链表中最后一个节点，并返回该节点，时间 O（1）
         */
        public Node removeLast() {
            if (tail.prev == head) {
                return null;
            }

            Node node = tail.prev;
            Node last = new Node(node.key, node.val);

            node.key = null;
            node.val = null;
            node.next = null;
            tail.prev = null;
            tail = node;

            return last;
        }

        /**
         *  返回链表长度，时间O（1）
         */
        public int size() {
            return size;
        }

        public void print() {
            Node node = head.next;
            while (node != tail) {
                System.out.println(node.key + " = " + node.val);
                node = node.next;
            }
            System.out.println("*******************");
        }
    }

	public static void main(String[] args) {
		LruCache lruCache = new LruCache();
		lruCache.put("1", "hello");
		lruCache.put("2", "world");
		lruCache.put("3", "hi");

//		lruCache.print();

		lruCache.get("1");
        lruCache.get("1");
        lruCache.get("2");
//        lruCache.get("1");
//        lruCache.get("2");
//		lruCache.get("2");

		lruCache.print();
	}


}
