package com.chen.base.datastruct.queue;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Chentian
 * @date: Created in 2020/4/26 22:53
 * @desc Leetcode 102. Binary Tree Level Order Traversal 层次遍历二叉树，链表队列实现
 */
public class Solution {

    class LinkedList<E> {

        private class Node {
            private E e;
            private Node next;

            public Node(E e, Node next) {
                this.e = e;
                this.next = next;
            }

            public Node() {
                this(null, null);
            }

            @Override
            public String toString() {
                return e.toString();
            }
        }

        private Node dummyHead;
        private int size;

        public LinkedList() {
            dummyHead = new Node();
            size = 0;
        }

        public int getSize() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void add(int index, E e) {
            if (index < 0 || index > size) {
                throw new IllegalArgumentException("index is error!");
            }

            Node prev = dummyHead;
            for (int i = 0; i < index; i++) {
                prev = prev.next;
            }

            Node node = new Node(e, prev.next);
            prev.next = node;
            size++;
        }

        public void addFirst(E e) {
            add(0, e);
        }

        public void addLast(E e) {
            add(size, e);
        }

        public void removeElement(E e) {
            Node prev = dummyHead;
            while (prev.next != null) {
                if (e.equals(prev.next.e)) {
                    prev.next = prev.next.next;
                    size--;
                    return;
                }
            }

        }

        public E remove(int index) {
            if (index < 0 || index >= size) {
                throw new IllegalArgumentException("index is error!");
            }

            Node prev = dummyHead;
            for (int i = 0; i < index; i++) {
                prev = prev.next;
            }
            E e = prev.next.e;
            prev.next = prev.next.next;
            size--;
            return e;
        }

        public E removeFirst() {
            return remove(0);
        }

        public E removeLast() {
            return remove(size - 1);
        }

        public E get(int index) {
            if (index < 0 || index >= size) {
                throw new IllegalArgumentException("index is error！");
            }
            Node cur = dummyHead.next;
            for (int i = 0; i < index; i++) {
                cur = cur.next;
            }
            return cur.e;
        }

        public E getFirst() {
            return get(0);
        }

        public E getLast() {
            return get(size - 1);
        }

        public boolean contains(E e) {
            Node cur = dummyHead.next;
            while (cur != null) {
                if (e.equals(cur.e)) {
                    return true;
                }
                cur = cur.next;
            }
            return false;
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

    class LinkedQueue<E> implements Queue<E> {

        private LinkedList<E> list;

        public LinkedQueue() {
            list = new LinkedList<>();
        }

        @Override
        public boolean isEmpty() {
            return list.isEmpty();
        }

        @Override
        public int getSize() {
            return list.getSize();
        }

        @Override
        public void enqueue(E e) {
            list.addLast(e);
        }

        @Override
        public E dequeue() {
            if (isEmpty()) {
                throw new IllegalArgumentException("queue is empty");
            }
            return list.removeFirst();
        }

        @Override
        public E getFront() {
            if (isEmpty()) {
                throw new IllegalArgumentException("queue is empty");
            }
            return list.getFirst();
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root == null){
            return new ArrayList<>();
        }

        LinkedQueue<TreeNode> queue = new LinkedQueue<>();
        queue.enqueue(root);

        List<List<Integer>> result = new ArrayList<>();
        LinkedQueue<TreeNode> newQueue = new LinkedQueue<>();
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
                newQueue = new LinkedQueue<>();
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

        List<List<Integer>> lists = new Solution().levelOrder(root);
        System.out.println(JSON.toJSONString(lists));
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
