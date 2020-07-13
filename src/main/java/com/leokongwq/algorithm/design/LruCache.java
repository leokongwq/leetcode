package com.leokongwq.algorithm.design;

import org.w3c.dom.Node;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
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

	private static class Node {
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

	private Node head;

	public LruCache() {
		this(DEFAULT_CAPACITY);
	}

	public LruCache(int capacity) {
		this.capacity = capacity;
	}

	public void add(String key, String value) {
		if (isFull()) {
			return;
		}
		// update
		if (container.containsKey(key)) {
			container.get(key).val = value;
			return;
		}
		Node node = new Node(key, value);
		if (head == null) {
			head = node;
		} else {
			node.next = head;
			head.prev = node;
			head = node;
		}
		container.put(key, head);
		size++;
	}

	public String get(String key) {
		if (!container.containsKey(key)) {
			return null;
		}
		Node node = container.get(key);
		if (node != head) {
			Node pre = node.prev;
			pre.next = node.next;
			if (node.next != null) {
				node.next.prev = pre;
			}
			node.prev = null;
			node.next = head;
			head.prev = node;
		}
		return node.val;
	}

	public void remove(String key) {

		size--;
	}

	public int getSize() {
		return size;
	}

	private boolean isFull() {
		return size == capacity;
	}

	public void print() {
		Node node = head;
		while (node != null) {
			System.out.println(node.key + " = " + node.val);
			node = node.next;
		}
	}

	public static void main(String[] args) {
		LruCache lruCache = new LruCache();
		lruCache.add("1", "hello");
		lruCache.add("2", "world");
		lruCache.add("3", "hi");

		lruCache.print();

		lruCache.get("1");
		lruCache.get("2");

		lruCache.print();
	}
}
