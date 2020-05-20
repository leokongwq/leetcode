package com.leokongwq.algorithm.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jiexiu
 * created 2019/3/25 - 19:40
 */
public class MinHeap<E extends Comparable<E>>  {

    private List<E> data;

    public MinHeap(int capacity) {
        this.data = new ArrayList<>(capacity);
    }

    public int size() {
        return this.data == null ? 0 : this.data.size();
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    public int leftChild(int index) {
        return index * 2 + 1;
    }

    public int rightChild(int index) {
        return index * 2 + 2;
    }

    private void swap(int i, int j) {
        if (this.data == null) {
            return;
        }
        if (i < 0 || i >= this.data.size() || j < 0 || j >= this.data.size()) {
            throw new IllegalArgumentException("Index is Illegal.");
        }
        E tmp = this.data.get(i);
        this.data.set(i, this.data.get(j));
        this.data.set(j, tmp);
    }

    public void add(E e) {
        this.data.add(e);
        shiftUp(this.data.size() - 1);
    }

    private void shiftUp(int i) {
        //特性2：比较插入值和其父结点的大小关系，小于父结点则用父结点替换当前值，index位置上升为父结点
        // 当上浮元素大于父亲，继续上浮。并且不能上浮到0之上
        // 直到i 等于 0 或 比 父亲节点小了。
        while (i > 0 && data.get(i).compareTo(data.get(parent(i))) > 0) {
                // 数组Array中添加方法swap
                swap(i, parent(i));
                // 这句话让i来到新的位置，使得循环可以查看新的位置是否还要大。
                i = parent(i);
        }
    }
}
