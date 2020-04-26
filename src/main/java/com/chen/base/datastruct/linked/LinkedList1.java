package com.chen.base.datastruct.linked;

/**
 * @author: Chentian
 * @date: Created in 2020/4/25 17:18
 * @desc 链表实现，不使用虚拟头节点
 */
public class LinkedList1<E> {

    class Node {
        private E e;
        private Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }
    private Node head;
    private int size;


    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0 ;
    }

    public void add(int index,E e){
        if( index < 0 || index > size){
            throw new IllegalArgumentException("index is error!");
        }

        //找插入位置前一个节点
        Node prev = head;
        for (int i = 0 ; i < (index-1); i++){
            prev = prev.next;
        }

        //在head位置添加元素
        if( index == 0 ){
            head = new Node(e,head);
        }else {
            prev.next = new Node(e,prev.next);
        }
        size ++;
    }

    public void addFirst(E e){
        add(0,e);
    }

    public void addLast(E e){
        add(size,e);
    }


    public E remove(int index){
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("index is error!");
        }

        Node prev = head;
        for (int i = 0 ; i < (index-1) ; i++){
            prev = prev.next;
        }

        E e;
        //删除head元素
        if(index == 0){
            e = head.e;
            head = head.next;
        }else {
            e = prev.next.e;
            prev.next = prev.next.next;
        }
        size -- ;
        return e;
    }

    public E removeFirst(){
        return remove(0);
    }

    public E removeLast(){
        return remove(size-1);
    }


    public void set(int index, E e){
        if( index < 0 || index >= size){
            throw new IllegalArgumentException("index is error!");
        }

        Node cur = head;
        for (int i = 0 ; i < index ; i++ ){
            cur = cur.next;
        }
        cur.e = e;
    }

    public E get(int index){
        if( index < 0 || index >= size){
            throw new IllegalArgumentException("index is error!");
        }

        Node cur = head;
        for (int i = 0 ; i < index ; i++){
            cur = cur.next;
        }
        return cur.e;
    }

    public boolean contains(E e){
        Node cur = head;
        while (cur != null){
            if (cur.e.equals(e)){
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();

        Node cur = head;
        while(cur != null){
            res.append(cur + "->");
            cur = cur.next;
        }
        res.append("NULL");

        return res.toString();
    }



    public static void main(String[] args) {
        LinkedList1<Integer> list = new LinkedList1<>();
        list.addFirst(0);
        for (int i = 1 ; i < 5 ; i++){
            list.add(i,i);
        }
        list.addLast(5);
        list.addFirst(8);
        System.out.println(list);

        list.removeFirst();
        System.out.println(list);

        for (int i = 4 ; i >= 1; i --){
            list.remove(i);
            System.out.println(list);
        }
        list.removeLast();
        System.out.println(list);

        list.addLast(1);
        list.addLast(7);
        System.out.println(list);
        list.set(0,9);
        System.out.println(list);
        list.set(1,8);
        System.out.println(list);
        System.out.println(list.get(2));
        System.out.println(list.contains(9));
        System.out.println(list.contains(2));

        System.out.println(list.getSize());
        int size = list.getSize();
        for (int i = 0 ; i < size; i ++){
            list.removeLast();
            System.out.println(list);
        }
        System.out.println(list.getSize());

    }
}
