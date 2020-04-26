package com.chen.base.datastruct.linked;

import com.chen.base.datastruct.queue.Queue;

/**
 * @author: Chentian
 * @date: Created in 2020/4/26 22:39
 * @desc 使用链表实现队列
 */
public class LinkedQueue<E> implements Queue<E> {

    private LinkedList<E> list;

    public LinkedQueue(){
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
        if(isEmpty()){
            throw new IllegalArgumentException("queue is empty");
        }
        return list.removeFirst();
    }

    @Override
    public E getFront() {
        if(isEmpty()){
            throw new IllegalArgumentException("queue is empty");
        }
        return list.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue: ");
        res.append("front [");
        for(int i = 0 ; i < list.getSize() ; i ++){
            res.append(list.get(i));
            if(i != list.getSize() - 1)
                res.append(", ");
        }
        res.append("] tail");
        return res.toString();
    }

    public static void main(String[] args) {

        LinkedQueue<Integer> queue = new LinkedQueue<>();

        System.out.println(queue.isEmpty());

        for (int i = 0 ; i < 10 ; i++){
            queue.enqueue(i);
        }
        System.out.println(queue);
        System.out.println(queue.isEmpty());

        for (int i = 0 ; i < 5 ; i++) {
            System.out.println(queue.dequeue());
        }
        System.out.println(queue);
        System.out.println(queue.getSize());
        System.out.println(queue.isEmpty());

        queue.enqueue(10);
        System.out.println(queue);
    }
}
