package com.chen.base.datastruct.bst;

/**
 * @author: Chentian
 * @date: Created in 2020/5/18 6:37
 * @desc
 */
public class Solution804 {
    class BST<E extends Comparable<E>> {

        private class Node {
            private E e;
            private Node left;
            private Node right;

            private Node(E e) {
                this.e = e;
            }
        }

        private Node root;
        private int size;

        public BST() {
            root = null;
            size = 0;
        }

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        //添加元素
        public void add(E e) {
            root = add(root, e);
        }

        //向以node为根的二分搜索树中插入元素e
        // 返回插入新节点后二分搜索树的根
        private Node add(Node node, E e) {
            if (node == null) {
                size++;
                return new Node(e);
            }

            if (node.e.compareTo(e) > 0) {
                node.left = add(node.left, e);
            } else if (node.e.compareTo(e) < 0) {
                node.right = add(node.right, e);
            }
            return node;
        }
    }

    public int uniqueMorseRepresentations(String[] words) {
        String[] keys = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};

        if (words == null || words.length == 0 ){
            return 0;
        }
        BST<String> bst = new BST<>();
        for (String word : words){
            StringBuilder sbr = new StringBuilder();
            for (int i = 0; i < word.length(); i++){
                int index = word.charAt(i) - 'a';
                sbr.append(keys[index]);
            }
            bst.add(sbr.toString());
        }
        return bst.size();
    }


    public static void main(String[] args) {
        String[] words = {"gin", "zen", "gig", "msg"};
        Solution804 solution804 = new Solution804();
        System.out.println(solution804.uniqueMorseRepresentations(words));
    }
}
