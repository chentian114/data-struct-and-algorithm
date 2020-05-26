package com.chen.base.datastruct.map;

import com.chen.base.datastruct.set.FileOperation;

import java.util.ArrayList;

/**
 * @author: Chentian
 * @date: Created in 2020/5/21 7:31
 * @desc
 */
public class BSTMap<K extends Comparable<K>,V> implements Map<K, V>{

    class Node{
        private K key;
        private V val;
        private Node left;
        private Node right;
        public Node(K key, V val){
            this.key = key;
            this.val = val;
        }
    }

    private Node root;
    private int size = 0;

    @Override
    public void add(K key, V val) {
        root = add(root,key,val);
    }

    private Node add(Node node, K key, V val) {
        if(node == null){
            size ++ ;
            return new Node(key,val);
        }

        if(node.key.compareTo(key) == 0){
            node.val = val;
            return node;
        }
        else if(node.key.compareTo(key) > 0){
            node.left = add(node.left,key,val);
        }
        else {
            node.right = add(node.right,key,val);
        }
        return node;
    }

    @Override
    public V remove(K key) {
        Node node = getNode(root, key);
        if(node == null){
            return null;
        }
        root = remove(root,key);
        return node.val;
    }

    private Node getNode(Node node,K key){
        if(node == null){
            return null;
        }
        if(node.key.compareTo(key) == 0){
            return node;
        }
        else if(node.key.compareTo(key) > 0){
            return getNode(node.left,key);
        }
        else {
            return getNode(node.right,key);
        }
    }
    private Node remove(Node node, K key) {
        if(node == null){
            return null;
        }
        if(node.key.compareTo(key) > 0){
            node.left = remove(node.left,key);
            return node;
        }
        else if(node.key.compareTo(key) < 0){
            node.right = remove(node.right,key);
            return node;
        }
        else {
            V val = node.val;
            if(node.left == null){
                node = node.right;
                size --;
            }
            else if(node.right == null){
                node = node.left;
                size--;
            }
            else {
                Node sucesser = getMin(node.right);
                node.key = sucesser.key;
                node.val = sucesser.val;
                node.right = removeMin(node.right);

            }
            return node;
        }
    }

    private Node removeMin(Node node) {
        if(node.left == null && node.right == null){
            size -- ;
            return null;
        }

        if(node.left == null){
            size--;
            return node.right;
        }
        return removeMin(node.left);
    }

    private Node getMin(Node node) {
        Node min = node;
        while (min.left != null){
            min = min.left;
        }
        return min;
    }

    @Override
    public boolean contains(K key) {
        return get(key) != null;
    }

    @Override
    public V get(K key) {
        Node node = get(root,key);
        return node == null ? null : node.val;
    }

    private Node get(Node node, K key) {
        if(node == null){
            return null;
        }
        if(node.key.compareTo(key) == 0){
            return node;
        }
        else if(node.key.compareTo(key) > 0){
            return get(node.left,key);
        }
        else {
            return get(node.right,key);
        }
    }

    @Override
    public void set(K key, V val) {
        set(root,key,val);
    }

    private void set(Node node, K key, V val) {
        if(node == null){
            return;
        }
        if(node.key.compareTo(key) == 0){
            node.val = val;
        }
        else if(node.key.compareTo(key) > 0){
            set(node.left,key,val);
        }
        else {
            set(node.right,key,val);
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    // 生成以node为根节点，深度为depth的描述二叉树的字符串
    private void generateBSTString(Node node, int depth, StringBuilder res){

        if(node == null){
            res.append(generateDepthString(depth) + "null\n");
            return;
        }

        res.append(generateDepthString(depth) + node.key+"-"+node.val +"\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    private String generateDepthString(int depth){
        StringBuilder res = new StringBuilder();
        for(int i = 0 ; i < depth ; i ++)
            res.append("--");
        return res.toString();
    }

    public static void main(String[] args){

        System.out.println("Pride and Prejudice");

        String fileName = System.getProperty("user.dir")+"/files/pride-and-prejudice.txt";

        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile(fileName, words)) {
            System.out.println("Total words: " + words.size());

            BSTMap<String, Integer> map = new BSTMap<>();
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

        BSTMap bst = new BSTMap();
        bst.add(10,"abc");
        bst.add(10,"abcd");
        bst.add(3,"ab");
        bst.add(23,"def");
        bst.add(18,"de");
        bst.add(17,"fg");
        bst.add(19,"db");
        System.out.println(bst);
        bst.remove(23);
        System.out.println(bst);
        bst.remove(18);
        System.out.println(bst);
    }
}
