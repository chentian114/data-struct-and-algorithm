package com.chen.base.datastruct.queue;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Chentian
 * @date: Created in 2020/4/26 22:53
 * @desc Leetcode 102. Binary Tree Level Order Traversal 层次遍历二叉树，循环队列实现
 */
public class SolutionLoop {

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

    class LoopQueue<E> implements Queue<E>{

        private E[] data;
        private int front;
        private int tail;
        private int size;

        public LoopQueue(int capacity){
            this.data = (E[])new Object[capacity];
            this.front = 0;
            this.tail = 0;
            this.size = 0;
        }

        public LoopQueue(){
            this(10);
        }

        @Override
        public boolean isEmpty() {
            return size == 0;
        }

        @Override
        public int getSize() {
            return size;
        }

        public int getCapacity(){
            return data.length;
        }

        @Override
        public void enqueue(E e) {
            if(size == data.length){
                resize(data.length * 2);
            }

            data[tail] = e;
            tail = (tail + 1) % data.length;
            size ++;
        }

        @Override
        public E dequeue() {
            if(isEmpty()){
                throw new IllegalArgumentException("queue is empty!");
            }
            E e = data[front];
            data[front] = null;
            front = (front + 1) % data.length;
            size --;

            if(size < data.length / 4 && data.length / 2 != 0){
                resize(data.length / 2);
            }
            return e;
        }

        @Override
        public E getFront() {
            if(isEmpty()){
                throw new IllegalArgumentException("queue is empty!");
            }
            return data[front];
        }

        private void resize(int newCapacity) {
            E[] newData = (E[])new Object[newCapacity];
            int index = front;
            for (int i = 0; i < size ; i++){
                newData[i] = data[index];
                index = (index + 1) % data.length;
            }
            this.data = newData;
            this.front = 0;
            this.tail = size;
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root == null){
            return new ArrayList();
        }

        LoopQueue<TreeNode> queue = new LoopQueue<>();
        queue.enqueue(root);

        List<List<Integer>> result = new ArrayList<>();
        LoopQueue<TreeNode> newQueue = new LoopQueue<>();
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
                newQueue = new LoopQueue<>();
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

        List<List<Integer>> lists = new SolutionLoop().levelOrder(root);
        System.out.println(JSON.toJSONString(lists));
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
