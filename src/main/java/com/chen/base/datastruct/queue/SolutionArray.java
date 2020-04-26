package com.chen.base.datastruct.queue;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Chentian
 * @date: Created in 2020/4/26 22:53
 * @desc Leetcode 102. Binary Tree Level Order Traversal 层次遍历二叉树，数组队列实现
 */
public class SolutionArray {
    class Array<E> {
        /**
         * 存放的元素数量
         */
        private int size;
        /**
         * 存放元素数组
         */
        private E[] data;

        public Array(int capacity) {
            size = 0;
            data = (E[]) new Object[capacity];
        }

        public Array() {
            this(10);
        }
        public boolean isEmpty(){
            return size == 0;
        }

        public int getSize(){
            return size;
        }

        public E get(int index){
            if(index < 0 || index >= size){
                throw new IllegalArgumentException("index is error!");
            }
            return data[index];
        }
        /**
         * 在index索引的位置插入一个新元素e
         *
         * @param index
         * @param e
         */
        public void add(int index, E e) {
            if (index < 0 || index > size) {
                throw new IllegalArgumentException("index is error!");
            }

            if (size == data.length) {
                resize(data.length * 2);
            }

            for (int i = size - 1; i >= index; i--) {
                data[i + 1] = data[i];
            }
            data[index] = e;
            size++;
        }

        public void addLast(E e) {
            add(size, e);
        }

        public void addFirst(E e) {
            add(0, e);
        }

        /**
         * 从数组中删除index位置的元素，返回删除的元素
         *
         * @param index
         * @return
         */
        public E remove(int index) {
            if (index < 0 || index >= size) {
                throw new IllegalArgumentException("index is error!");
            }

            E e = data[index];

            for (int i = index + 1; i < size; i++) {
                data[i - 1] = data[i];
            }
            size--;
            data[size] = null;

            if (size == data.length / 4 && data.length / 2 != 0) {
                resize(data.length / 2);
            }
            return e;
        }

        public E removeLast() {
            return remove(size - 1);
        }

        public E removeFirst() {
            return remove(0);
        }

        /**
         * 将数组空间的容量变成newCapacity大小
         * @param newCapacity
         */
        private void resize(int newCapacity) {
            E[] newArray = (E[]) new Object[newCapacity];
            for (int i = 0; i < size ; i++){
                newArray[i] = data[i];
            }
            data = newArray;
        }
    }

    interface Queue<E> {

        boolean isEmpty();

        int getSize();

        /**
         * 入队
         * @param e 元素
         */
        void enqueue(E e);

        /**
         * 出队
         * @return 元素
         */
        E dequeue();

        /**
         * 查看队首元素
         * @return 元素
         */
        E getFront();

    }

    class ArrayQueue<E> implements Queue<E> {
        private Array<E> data;

        public ArrayQueue() {
            this.data = new Array<>();
        }

        public ArrayQueue(int capacity) {
            this.data = new Array<>(capacity);
        }

        @Override
        public boolean isEmpty() {
            return data.isEmpty();
        }

        @Override
        public int getSize() {
            return data.getSize();
        }

        @Override
        public void enqueue(E e) {
            data.addLast(e);
        }

        @Override
        public E dequeue() {
            return data.removeFirst();
        }

        @Override
        public E getFront() {
            return data.get(0);
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root == null){
            return new ArrayList();
        }

        ArrayQueue<TreeNode> queue = new ArrayQueue<>();
        queue.enqueue(root);

        List<List<Integer>> result = new ArrayList<>();
        ArrayQueue<TreeNode> newQueue = new ArrayQueue<>();
        List<Integer> rResult = new ArrayList<>();
        while (!queue.isEmpty()){
            TreeNode node = queue.dequeue();
            rResult.add(node.val);

            if(node.left != null) {
                newQueue.enqueue(node.left);
            }
            if(node.right != null) {
                newQueue.enqueue(node.right);
            }

            if(queue.isEmpty()){
                queue = newQueue;
                newQueue = new ArrayQueue<>();
                result.add(rResult);
                rResult = new ArrayList<>();
            }
        }
        return  result;
    }

    public static void main(String[] args) {
        TreeNode left = new TreeNode(9);
        TreeNode right = new TreeNode(20);
        TreeNode root = new TreeNode(3);
        root.left = left;
        root.right = right;

        TreeNode lleft = new TreeNode(15);
        TreeNode rright = new TreeNode(7);
        right.left = lleft;
        right.right = rright;

        List<List<Integer>> lists = new SolutionArray().levelOrder(root);
        System.out.println(JSON.toJSONString(lists));
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
