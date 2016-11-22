package com.meililinc.mls.datastruct;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/11/1
 * Time: 下午2:48
 * Email:jiexiu@mogujie.com
 */
public class BinaryTree<T extends Comparable> {

    private TreeNode<T> root;

    public BinaryTree(){
    }

    public BinaryTree(TreeNode<T> root){
        this.root = root;
    }

    public BinaryTree(T data){
        this.root = new TreeNode<T>(data, null, null);
    }

    /**
     * 添加节点到树
     * @param data 要添加的数据
     * @return  返回最新添加的树节点
     */
    public void add(T data){
        if (this.root == null){
            this.root = new TreeNode<T>(data, null, null);
            return;
        }
        addInternal(this.root, data);
    }

    private TreeNode<T> addInternal(TreeNode<T> node, T data){
        if (node == null){
            node = new TreeNode<T>(data, null, null);
        }
        if (node.data.compareTo(data) > 0 ){
            node.left = addInternal(node.left, data);
        } else if (node.data.compareTo(data) < 0){
            node.right = addInternal(node.right, data);
        }
        return node;
    }

    /**
     * 范围查询
     * @param min
     * @param max
     * @return
     */
    public List<T> rangeSearch(T min, T max){
        if (this.root == null){
            return Collections.emptyList();
        }
        List<T> result = new LinkedList<T>();
        rangeSearch(this.root, min, max, result);
        return result;
    }

    private void rangeSearch(TreeNode<T> tree, T min, T max, List<T> result){
        if (tree == null){
            return;
        }
        //遍历左子树查询下限
        if (min.compareTo(tree.data) < 0){
            rangeSearch(tree.left, min, max, result);
        }
        //在查找范围内
        if (tree.data.compareTo(min) >= 0 && tree.data.compareTo(max) <= 0){
            result.add(tree.data);
        }
        //遍历右子树（两种情况：1:找min的下限 2：必须在Max范围之内）
        if (min.compareTo(tree.data) > 0 || max.compareTo(tree.data) > 0){
            rangeSearch(tree.right, min, max, result);
        }
    }

    /**
     * 删除元素
     * @param data
     */
    public void remove(T data){
        TreeNode<T> tmp = this.removeInternal(this.root, data);
    }

    private TreeNode<T> removeInternal(TreeNode<T> node, T data){
        if (node == null){
            return null;
        }
        //左子树
        if (data.compareTo(node.data) < 0){
            node.left = removeInternal(node.left, data);
        }
        //右子树
        if (data.compareTo(node.data) > 0){
            node.right = removeInternal(node.right, data);
        }
        //相等
        if (data.compareTo(node.data) == 0){
            if (node.left != null && node.right != null){
                //查找左子树中最大的节点
                TreeNode<T> destNode = findMax(node.left);
                //删除被移动的节点
                removeInternal(node.left, destNode.data);
                //赋值即可
                node.data = destNode.data;
            }else {
                node = node.left != null ? node.left : node.right;
            }
        }
        return node;
    }

    /**
     * 查找以参数root指定节点为根节点的二叉搜索树中值最大的节点
     * @param node
     * @return
     */
    private TreeNode<T> findMax(TreeNode<T> node){
        if (node == null){
            return null;
        }
        while (node.right != null){
            node = node.right;
            findMax(node);
        }
        return node;
    }

    /**
     * 前序遍历(根-》左 -》右)
     */
    public void rootLeftRightPrintTree(TreeNode treeNode){
        if (treeNode == null){
            return;
        }
        //先访问根节点
        System.out.print(treeNode.data + ",");
        //访问左子树
        rootLeftRightPrintTree(treeNode.left);
        //访问右子树
        rootLeftRightPrintTree(treeNode.right);
    }

    /**
     * 中序遍历(左-》根 -》右)
     */
    public void leftRootRightPrintTree(TreeNode treeNode){
        if (treeNode == null){
            return;
        }
        //访问左子树
        leftRootRightPrintTree(treeNode.left);
        //先访问根节点
        System.out.print(treeNode.data + ",");
        //访问右子树
        leftRootRightPrintTree(treeNode.right);
    }

    /**
     * 后序遍历(左-》右 -》根)
     */
    public void leftRightRootPrintTree(TreeNode treeNode){
        if (treeNode == null){
            return;
        }
        //访问左子树
        leftRightRootPrintTree(treeNode.left);
        //访问右子树
        leftRightRootPrintTree(treeNode.right);
        //先访问根节点
        System.out.print(treeNode.data + ",");
    }

    /**
     * 层级遍历
     * @param treeNode
     */
    public void levelPrintTree(TreeNode treeNode){
        System.out.print(treeNode.data + ",");
        System.out.print(treeNode.left.data + ",");
        System.out.print(treeNode.right.data + ",");
        levelPrintTree(treeNode.left);
        levelPrintTree(treeNode.right);
    }

    private static class TreeNode<T extends Comparable> {
        private T data;

        private TreeNode<T> left;

        private TreeNode<T> right;

        public TreeNode(){
        }

        public TreeNode(T data){
            this.data = data;
        }

        public TreeNode(T data, TreeNode<T> left, TreeNode<T> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<Integer>();
        tree.add(20);
        tree.add(15);
        tree.add(10);
        tree.add(18);
        tree.add(12);
        tree.add(25);
        tree.add(30);
        //先序遍历
        tree.rootLeftRightPrintTree(tree.root);
        System.out.println();
        //中序遍历
        tree.leftRootRightPrintTree(tree.root);
        System.out.println();
        //后序遍历
        tree.leftRightRootPrintTree(tree.root);
        System.out.println();
        //范围查询
        List<Integer> result = tree.rangeSearch(10, 20);
        System.out.println(result);
        //删除
        tree.remove(15);
        //中序遍历
        tree.leftRootRightPrintTree(tree.root);
        System.out.println();
    }
}
