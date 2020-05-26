package com.chen.base.datastruct.map;

import com.chen.base.datastruct.set.FileOperation;

import java.util.ArrayList;

/**
 * @author: Chentian
 * @date: Created in 2020/5/21 6:55
 * @desc
 */
public class LinkedMap<K,V> implements Map<K,V>{

    class Node {
        K key;
        V val;
        Node next;
        public Node(){}
        public Node(K key, V val,Node next){
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }
    private int size = 0 ;
    private Node dummyNode = new Node();


    private Node getKey(K key){
        Node node = dummyNode.next;
        while (node != null){
            if(node.key.equals(key)){
                return node;
            }
            node = node.next;
        }
        return null;
    }

    @Override
    public void add(K key, V val) {
        Node node = getKey(key);
        if(node != null){
            node.val = val;
            return;
        }

        dummyNode.next = new Node(key,val,dummyNode.next);
        size++;
    }

    @Override
    public V remove(K key) {
        Node prev = dummyNode;
        while (prev.next != null){
            Node node = prev.next;
            if(node.key.equals(key)){
                prev.next = node.next;
                node.next = null;
                size--;
                return node.val;
            }
            prev = prev.next;
        }
        return null;
    }

    @Override
    public boolean contains(K key) {
        return getKey(key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getKey(key);
        return node == null ? null : node.val;
    }

    @Override
    public void set(K key, V val) {
        Node node = getKey(key);
        if(node != null){
            node.val = val;
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    public static void main(String[] args){

        System.out.println("Pride and Prejudice");

        String fileName = System.getProperty("user.dir")+"/files/pride-and-prejudice.txt";

        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile(fileName, words)) {
            System.out.println("Total words: " + words.size());

            LinkedMap<String, Integer> map = new LinkedMap<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
        }

        System.out.println();
    }
}
