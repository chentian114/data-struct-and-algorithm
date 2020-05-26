package com.chen.base.datastruct.map;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author: Chentian
 * @date: Created in 2020/5/26 6:21
 * @desc 349. 两个数组的交集
 */
public class Solution349 {
    class BST<E extends Comparable<E>> {

        private class Node{
            private E e;
            private Node left;
            private Node right;
            private Node(E e){
                this.e = e;
            }
        }

        private Node root;
        private int size;

        public BST(){
            root = null;
            size = 0;
        }

        public int size(){
            return size;
        }

        public boolean isEmpty(){
            return size == 0;
        }

        //添加元素
        public void add(E e){

            root = add(root,e);
        }

        //向以node为根的二分搜索树中插入元素e
        // 返回插入新节点后二分搜索树的根
        private Node add(Node node,E e){
            if(node == null){
                size ++;
                return new Node(e);
            }

            if(node.e.compareTo(e) > 0){
                node.left = add(node.left,e);
            }else if(node.e.compareTo(e) < 0){
                node.right = add(node.right,e);
            }
            return node;
        }

        //看二分搜索树的中是否包含元素
        public boolean contains(E e){
            return contains(root,e);
        }

        //看以node为根的二分搜索树中是否包含元素e
        private boolean contains(Node node, E e) {
            if(node == null){
                return false;
            }
            E value = node.e;
            if(value.compareTo(e) == 0){
                return true;
            }else if(value.compareTo(e) > 0){
                return contains(node.left,e);
            }else {
                return contains(node.right,e);
            }
        }

        public List<E> preOrderNR(){
            if(size == 0){
                return new ArrayList<>(0);
            }
            Stack<Node> stack = new Stack();
            stack.push(root);

            List<E> elements = new ArrayList<>();
            while (!stack.isEmpty()){
                Node node = stack.pop();
                elements.add(node.e);

                if(node.left != null){
                    stack.push(node.left);
                }
                if(node.right != null){
                    stack.push(node.right);
                }
            }
            return elements;
        }
    }

    public int[] intersection(int[] nums1, int[] nums2) {
        if(nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0){
            return new int[0];
        }

        BST<Integer> bst = new BST();
        for (int i = 0; i < nums1.length ; i++){
            bst.add(nums1[i]);
        }

        BST<Integer> bstResult = new BST();
        for (int i = 0 ; i < nums2.length; i++){
            if(bst.contains(nums2[i])){
                bstResult.add(nums2[i]);
            }
        }

        List<Integer> integers = bstResult.preOrderNR();
        int[] result = new int[integers.size()];
        for (int i = 0 ; i < integers.size() ; i++){
            result[i] = integers.get(i);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = {4,3,9,3,1,9,7,6,9,7};
        int[] nums2 = {5,0,8};
        int[] intersection = new Solution349().intersection(nums1, nums2);
        System.out.println(Arrays.toString(intersection));

    }
}
