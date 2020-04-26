package com.chen.base.datastruct.linked;

/**
 * @author: Chentian
 * @date: Created in 2020/4/26 21:55
 * @desc 链表实现，使用虚拟头节点
 */
public class LinkedList<E> {

    private class Node{
        private E e;
        private Node next;
        public Node(E e,Node next){
            this.e = e;
            this.next = next;
        }
        public Node(){
            this(null,null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private Node dummyHead;
    private int size;

    public LinkedList(){
        dummyHead = new Node();
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void add(int index, E e){
        if(index < 0 || index > size){
            throw new IllegalArgumentException("index is error!");
        }

        Node prev = dummyHead;
        for (int i = 0 ; i < index ; i++){
            prev = prev.next;
        }

        Node node = new Node(e,prev.next);
        prev.next = node;
        size++;
    }

    public void addFirst(E e){
        add(0,e);
    }

    public void addLast(E e){
        add(size,e);
    }

    public void removeElement(E e){
        Node prev = dummyHead;
        while (prev.next != null){
            if(e.equals(prev.next.e)){
                prev.next = prev.next.next;
                size--;
                return;
            }
        }

    }

    public E remove(int index){
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("index is error!");
        }

        Node prev = dummyHead;
        for (int i = 0 ; i < index ; i++){
            prev = prev.next;
        }
        E e = prev.next.e;
        prev.next = prev.next.next;
        size--;
        return e;
    }

    public E removeFirst(){
        return remove(0);
    }

    public E removeLast(){
        return remove(size-1);
    }

    public E get(int index){
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("index is error！");
        }
        Node cur = dummyHead.next;
        for (int i = 0 ; i < index ; i++){
            cur = cur.next;
        }
        return cur.e;
    }

    public E getFirst(){
        return get(0);
    }

    public E getLast(){
        return get(size - 1);
    }

    public boolean contains(E e){
        Node cur = dummyHead.next;
        while (cur != null){
            if(e.equals(cur.e)){
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

        Node cur = dummyHead.next;
        while(cur != null){
            res.append(cur + "->");
            cur = cur.next;
        }
        res.append("NULL");

        return res.toString();
    }


    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        System.out.println(list.isEmpty());
        list.addFirst(0);
        list.add(1,1);
        list.addLast(2);
        System.out.println(list);
        System.out.println(list.getSize());

        System.out.println(list.get(1));
        System.out.println(list.contains(1));
        System.out.println(list.contains(8));
        System.out.println(list.getFirst());
        System.out.println(list.getLast());

        for (int i = 1 ; i < 5; i++){
            list.add(i,i);
        }
        System.out.println(list);

        for (int i = 5; i > 1; i--){
            list.remove(i);
        }
        System.out.println(list);
        System.out.println(list.removeLast());
        System.out.println(list.removeFirst());
        System.out.println(list);
        list.removeElement(1);
        System.out.println(list);


    }
}
