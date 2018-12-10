package com.leokongwq.blog;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    class Node {
        Node pre;
    	Node next;
    	Integer key;
    	Integer val;
    	
    	Node(Integer k, Integer v) {
    		key = k;
    		val = v;
    	}
    }
    
    Map<Integer, Node> map = new HashMap<Integer, Node>();
    // The head (eldest) of the doubly linked list.
    Node head;
    // The tail (youngest) of the doubly linked list.
    Node tail;

    int cap;

    public LRUCache(int capacity) {
        cap = capacity;
        head = new Node(null, null);
        tail = new Node(null, null);
        head.next = tail;
        tail.pre = head;
    }
    
    public int get(int key) {
        Node n = map.get(key);
        if(n!=null) {
            moveToTail(n);
        	return n.val;
        }
        return -1;
    }

    /**
     * 移动到队列尾部
     * @param node
     */
    private void moveToTail(Node node){
        //已经是最后一个了
        if (node.next == tail){
            return;
        }
        Node pre = node.pre;
        pre.next = node.next;
        node.next.pre = pre;
        node.pre = null;
        node.next = null;
        appendTail(node);
    }

    /**
     * 删除最老的元素
     */
    private void removeOldest(){
        //队列为空
        if (head.next == tail){
            return;
        }
        Node temp = head.next;
        head.next = temp.next;
        temp.next.pre = head;
        temp.next = null;
        temp.pre = null;
        map.remove(temp.key);
    }

    public void set(int key, int value) {
        Node existNode = map.get(key);
        //已经存在,更新值并移动到队列尾部
        if (existNode != null){
            existNode.val = value;
            map.put(key, existNode);
            moveToTail(existNode);
            return;
        }
        // 删除最老的元素
        if(map.size() == cap) {
            removeOldest();
        }
        Node n = new Node(key, value);
        map.put(key, n);
        // youngest node append taill
        appendTail(n);
    }

    private void appendTail(Node n) {
    	n.next = tail;
    	n.pre = tail.pre;
    	tail.pre.next = n;
    	tail.pre = n;
    }

    public Map getMap(){
        return map;
    }

    public Node gethead(){
        return head;
    }

    public Node getTail(){
        return tail;
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(3);
        lruCache.set(1, 1);
        lruCache.set(2, 2);
        lruCache.set(3, 3);
        lruCache.set(1, 4);

        System.out.println(lruCache.getMap());
        System.out.println(lruCache.gethead());
        System.out.println(lruCache.getTail());
    }
}