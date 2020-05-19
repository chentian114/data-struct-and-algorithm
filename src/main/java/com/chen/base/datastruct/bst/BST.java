package com.chen.base.datastruct.bst;

import com.chen.base.datastruct.linked.LinkedQueue;
import com.chen.base.datastruct.linked.LinkedStack;
import com.chen.base.datastruct.queue.Queue;
import com.chen.base.datastruct.stack.Stack;

/**
 * @author: Chentian
 * @date: Created in 2020/5/16 23:10
 * @desc 二叉搜索树
 */
public class BST<E extends Comparable<E>> {

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

    public void preOrder(){
        preOrder(root);
        System.out.println();
    }

    private void preOrder(Node node) {
        if(node == null){
            return;
        }
        System.out.print(node.e+" ");
        preOrder(node.left);
        preOrder(node.right);
    }

    //先序遍历非递归实现
    public void preOrderNR(){
        if(root == null){
            return;
        }
        Stack<Node> stack = new LinkedStack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node node = stack.pop();
            System.out.print(node.e+" ");
            if(node.right != null){
                stack.push(node.right);
            }
            if(node.left != null){
                stack.push(node.left);
            }
        }
        System.out.println();
    }

    public void inOrder(){
        inOrder(root);
        System.out.println();
    }

    private void inOrder(Node node) {
        if(node == null){
            return;
        }
        inOrder(node.left);
        System.out.print(node.e+" ");
        inOrder(node.right);
    }

    public void postOrder(){
        postOrder(root);
        System.out.println();
    }

    private void postOrder(Node node) {
        if(node == null){
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.e+" ");
    }

    //层序遍历
    public void levelOrder(){
        if(root == null){
            return;
        }
        Queue<Node> queue = new LinkedQueue<>();
        queue.enqueue(root);
        while (!queue.isEmpty()){
            Node node = queue.dequeue();
            System.out.print(node.e+" ");
            if(node.left != null){
                queue.enqueue(node.left);
            }
            if(node.right != null){
                queue.enqueue(node.right);
            }
        }
        System.out.println();
    }

    // 寻找最小元素
    public E minimum(){
        if(root == null){
            return null;
        }
        return minimum(root);
    }

    private E minimum(Node node) {
        if(node.left == null){
            return node.e;
        }
        return minimum(node.left);
    }

    // 寻找最大元素
    public E maximum(){
        if (root == null){
            return null;
        }
        return maximum(root);
    }

    private E maximum(Node node) {
        if(node.right == null){
            return node.e;
        }
        return maximum(node.right);
    }

    public E removeMax(){
        E e = maximum();
        removeMax(root);
        return e;
    }
    // 删除掉以node为根的树中最大节点
    private Node removeMax(Node node){
        if(node.right == null){
            size --;
            node = node.left;
            return node;
        }
        node.right = removeMax(node.right);
        return node;
    }

    private Node removeMin(Node node){
        if(node.left == null){
            size --;
            node = node.right;
            return node;
        }
        node.left = removeMin(node.left);
        return node;
    }

    public void remove(E e){
        if (root == null){
            return;
        }
        root = remove(root,e);
    }

    private Node remove(Node node, E e) {
        if(node.e.compareTo(e) == 0){
            if(node.left == null){
                size --;
                node = node.right;
                return node;
            }
            else if(node.right == null){
                size --;
                node = node.left;
                return node;
            }else {
                E sucessor = minimum(node.right);
                node.e = sucessor;
                node.right = removeMin(node.right);
                return node;
            }
        }
        else if(node.e.compareTo(e) > 0){
            node.left = remove(node.left,e);
            return node;
        }
        else{
            node.right = remove(node.right,e);
            return node;
        }

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

        res.append(generateDepthString(depth) + node.e +"\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    private String generateDepthString(int depth){
        StringBuilder res = new StringBuilder();
        for(int i = 0 ; i < depth ; i ++)
            res.append("--");
        return res.toString();
    }

    public static void main(String[] args) {

        BST<Integer> bst = new BST<>();

        bst.add(5);
        System.out.println(bst.size);
        bst.add(3);
        System.out.println(bst.size);

        System.out.println(bst.contains(5));
        System.out.println(bst.contains(2));
        System.out.println(bst.contains(3));

        bst.add(8);
        bst.add(6);
        bst.add(4);
        bst.add(3);
        System.out.println(bst.size());
        System.out.println(bst);
        bst.preOrder();
        bst.preOrderNR();
        bst.inOrder();
        bst.postOrder();
        bst.levelOrder();
        System.out.println(bst.minimum());
        System.out.println(bst.maximum());
        System.out.println("remove max:"+bst.removeMax());
        System.out.println(bst.maximum());
        bst.levelOrder();
        bst.remove(3);
        bst.levelOrder();
        bst.remove(5);
        System.out.println(bst);
        bst.add(3);
        bst.add(5);
        bst.add(20);
        bst.add(30);
        bst.add(28);
        bst.add(29);
        bst.add(19);


        System.out.println(bst);
        bst.remove(20);
        System.out.println(bst);
        System.out.println(bst.size());

    }
}
