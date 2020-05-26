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
public class Solution349_2 {
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

        private Node getNode(Node node ,E e){
            if(node == null){
                return null;
            }
            if(node.e.compareTo(e) == 0){
                return node;
            }
            else if(node.e.compareTo(e) > 0){
                return getNode(node.left,e);
            }
            else {
                return getNode(node.right,e);
            }
        }

        private Node min(Node node){
            if(node.left == null){
                return node;
            }
            return min(node.left);
        }

        private Node removeMin(Node node){
            if(node == null){
                return null;
            }
            if(node.left == null){
                size--;
                return node.right;
            }
            node.left = removeMin(node.left);
            return node;
        }

        public void remove(E e){
            root = remove(root,e);
        }

        private Node remove(Node node, E e) {
            if(node == null){
                return null;
            }
            if(node.e.compareTo(e) > 0){
                node.left = remove(node.left,e);
                return node;
            }
            else if(node.e.compareTo(e) < 0){
                node.right = remove(node.right,e);
                return node;
            }
            else {
                if(node.right == null){
                    size--;
                    return node.left;
                }
                else if(node.left == null){
                    size--;
                    return node.right;
                }
                else {
                    Node successor = min(node.right);
                    node.right = removeMin(node.right);
                    node.e = successor.e;
                    return node;
                }
            }
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

        List<Integer> result = new ArrayList<>();
        for (int i = 0 ; i < nums2.length; i++){
            if(bst.contains(nums2[i])){
                bst.remove(nums2[i]);
                result.add(nums2[i]);
            }
        }
        int[] reInt = new int[result.size()];
        for (int i = 0 ; i < result.size(); i++){
            reInt[i] = result.get(i);
        }
        return reInt;
    }

    public static void main(String[] args) {
        int[] nums1 = {4,3,9,3,1,9,7,6,9,7};
        int[] nums2 = {5,0,8};
        int[] intersection = new Solution349_2().intersection(nums1, nums2);
        System.out.println(Arrays.toString(intersection));

        int[] nums3 = {4,9,5};
        int[] nums4 = {9,4,9,8,4};
        int[] intersection2 = new Solution349_2().intersection(nums3, nums4);
        System.out.println(Arrays.toString(intersection2));

    }
}
