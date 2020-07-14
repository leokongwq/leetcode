package com.leokongwq.datastruct;

/**
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/11/22
 * Time: 上午11:20
 * Email:leokongwq@gmail.com
 */
public class BinarySearchTree<T extends Comparable<? super T>> {

    /**
     * 根节点
     */
    private BinaryNode<T> root;

    /**
     * 是否是空树
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * 是否包含指定的元素
     */
    public boolean contains(T data) {
        return containsInternal(root, data);
    }

    private boolean containsInternal(BinaryNode<T> node, T data) {
        if (node == null) {
            return false;
        }
        int result = node.data.compareTo(data);
        if (result < 0) {
            return containsInternal(node.right, data);
        } else if (result > 0) {
            return containsInternal(node.left, data);
        } else {
            return true;
        }
    }

    /**
     * 查找最小的数据项
     */
    public T findMin() {
        if (isEmpty()) {
            return null;
        }
        return findMinInternal(root).data;
    }

    /**
     * 最小节点肯定是左子树上的节点
     * @param node 待查找的树节点
     */
    private BinaryNode<T> findMinInternal(BinaryNode<T> node) {
        if (node == null) {
            return null;
        }
        if (node.left == null) {
            return node;
        }
        return findMinInternal(node.left);
    }

    /**
     * 查找最大的数据项
     *
     * @return
     */
    public T findMax() {
        if (isEmpty()) {
            return null;
        }
        return findMaxInternal(root).data;
    }

    /**
     * 最大肯定是右节点
     */
    private BinaryNode<T> findMaxInternal(BinaryNode<T> node) {
        if (node == null) {
            return null;
        }
//        非递归的实现方式
//        if (node != null){
//            while (node.right != null){
//                node = node.right;
//            }
//        }
//        return node;
        if (node.right == null) {
            return node;
        }
        return findMaxInternal(node.right);
    }

    public boolean insert(T data) {
        //插入根节点 root 需要赋值
        this.root = this.insertInternal(root, data);
        return true;
    }

    /**
     * 将元素 val 插入到树中
     */
    private BinaryNode<T> insertInternal(BinaryNode<T> node, T data) {
        if (node == null) {
            //可以是根节点, 或者新插入的节点
            return new BinaryNode<T>(data, null, null);
        }
        int result = data.compareTo(node.data);
        if (result > 0) {
            node.right = insertInternal(node.right, data);
        } else if (result < 0) {
            node.left = insertInternal(node.left, data);
        } else {
            //什么都不做, 返回老的节点(也可以在节点的附加域上加一)
        }
        return node;
    }

    /**
     * 删除参数data指定的数据项
     *
     * @param data
     * @return
     */
    public boolean remove(T data) {
        BinaryNode<T> node = removeInternal(root, data);
        return node != null;
    }

    private BinaryNode<T> removeInternal(BinaryNode<T> node, T data) {
        if (node == null) {
            return null;
        }
        int result = data.compareTo(node.data);
        if (result < 0) {
            //待删除的节点在节点node的左子树, 节点node的左孩子赋值为删除目标元素后的替代节点
            //每次的调用可以将node看做是只有2个叶子节点的树
            node.left = removeInternal(node.left, data);
        } else if (result > 0) {
            node.right = removeInternal(node.right, data);
        } else {
            //有2个子节点
            if (node.left != null && node.right != null) {
                //查找左子树中的最大节点
                BinaryNode<T> leftMaxNode = findMaxInternal(node.left);
                //删除该节点(左子树中的最大节点)
                removeInternal(node.left, leftMaxNode.data);
                node.data = leftMaxNode.data;
            } else {
                // 有一个子节点, 将左孩子或右孩子提升
                node = node.left != null ? node.left : node.right;
            }
        }
        return node;
    }

    private static class BinaryNode<T> {
        private T data;
        private BinaryNode<T> left;
        private BinaryNode<T> right;

        public BinaryNode(T data) {
            this(data, null, null);
        }

        public BinaryNode(T data, BinaryNode<T> left, BinaryNode<T> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> searchTree = new BinarySearchTree<Integer>();
        searchTree.insert(6);
        searchTree.insert(8);
        searchTree.insert(1);
        searchTree.insert(2);
        searchTree.insert(3);
        searchTree.insert(4);
        System.out.println(searchTree.contains(2));
        System.out.println(searchTree.findMin());
        System.out.println(searchTree.findMax());
        searchTree.remove(3);
        System.out.println(searchTree.contains(3));
    }
}
