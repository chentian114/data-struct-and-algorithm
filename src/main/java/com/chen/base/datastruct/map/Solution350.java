package com.chen.base.datastruct.map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: Chentian
 * @date: Created in 2020/5/27 5:12
 * @desc 350. 两个数组的交集 II
 */
public class Solution350 {

    class BSTMap<K extends Comparable<K>,V>{

        class Node{
            K key;
            V val;
            Node left;
            Node right;
            public Node(K key, V val){
                this.key = key;
                this.val = val;
            }
        }
        private Node root;
        private int size;

        public int getSize(){
            return size;
        }

        public V getNode(K key){
            Node node = getNode(root, key);
            if(node == null){
                return null;
            }
            return node.val;
        }
        public void put(K key,V val){
            root = put(root,key,val);
        }

        private Node put(Node node, K key, V val) {
            if(node == null){
                size++;
                return new Node(key,val);
            }

            if(node.key.compareTo(key) == 0 ){
                node.val = val;
                return node;
            }
            else if(node.key.compareTo(key) > 0 ){
                node.left = put(node.left,key,val);
            }
            else {
                node.right = put(node.right,key,val);
            }
            return node;
        }

        public V remove(K key){
            Node node = getNode(root,key);
            if(node == null){
                return null;
            }
            root = remove(root,key);

            return node.val;
        }

        private Node remove(Node node, K key) {
            if(node == null){
                return null;
            }

            if(node.key.compareTo(key) > 0 ){
                node.left = remove(node.left, key);
                return node;
            }
            else if(node.key.compareTo(key) < 0){
                node.right = remove(node.right, key);
                return node;
            }
            else {
                if(node.left == null){
                    size--;
                    return node.right;
                }
                else if(node.right == null){
                    size--;
                    return node.left;
                }
                else {
                    Node successor = min(node.right);
                    node.key = successor.key;
                    node.val = successor.val;
                    node.right = removeMin(node.right);
                    return node;
                }
            }
        }

        private Node removeMin(Node node) {
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

        private Node min(Node node) {
            if(node == null){
                return null;
            }
            if(node.left == null){
                return node;
            }
            else {
                return min(node.left);
            }
        }

        private Node getNode(Node node, K key) {
            if(node == null){
                return null;
            }

            if(node.key.compareTo(key) == 0){
                return node;
            }
            else if(node.key.compareTo(key) > 0){
                return getNode(node.left, key);
            }
            else {
                return getNode(node.right, key);
            }
        }


    }

    public int[] intersect(int[] nums1, int[] nums2) {
        if(nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0){
            return new int[0];
        }

        BSTMap<Integer,Integer> bstMap = new BSTMap<>();
        for (int i = 0; i < nums1.length; i++){
            Integer val = bstMap.getNode(nums1[i]);
            if(val != null){
                bstMap.put(nums1[i],val+1);
            }
            else {
                bstMap.put(nums1[i],1);
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums2.length ; i++){
            Integer val = bstMap.getNode(nums2[i]);
            if(val != null){
                list.add(nums2[i]);
                if(val == 1){
                    bstMap.remove(nums2[i]);
                }
                else {
                    bstMap.put(nums2[i],val-1);
                }
            }
        }

        int[] result = new int[list.size()];
        for (int i = 0 ; i < list.size() ; i++){
            result[i] = list.get(i);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = {4,3,9,3,1,9,7,6,9,7};
        int[] nums2 = {5,0,8};
        int[] intersection = new Solution350().intersect(nums1, nums2);
        System.out.println(Arrays.toString(intersection));

        int[] nums3 = {4,9,5};
        int[] nums4 = {9,4,9,8,4};
        int[] intersection2 = new Solution350().intersect(nums3, nums4);
        System.out.println(Arrays.toString(intersection2));
    }
}
