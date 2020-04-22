package com.chen.base.datastruct.queue;

import com.chen.base.datastruct.array.Array;

/**
 * @author: Chentian
 * @date: Created in 2020/4/23 5:58
 * @desc
 */
public class ArrayQueue<E> implements Queue<E> {

    private Array<E> data ;
    public ArrayQueue(){
        this.data = new Array<>();
    }
    public ArrayQueue(int capacity){
        this.data = new Array<>(capacity);
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public int getSize() {
        return data.getSize();
    }

    @Override
    public void enqueue(E e) {
        data.addLast(e);
    }

    @Override
    public E dequeue() {
        return data.removeFirst();
    }

    @Override
    public E getFront() {
        return data.get(0);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue: ");
        res.append("front [");
        for(int i = 0 ; i < data.getSize() ; i ++){
            res.append(data.get(i));
            if(i != data.getSize() - 1)
                res.append(", ");
        }
        res.append("] tail");
        return res.toString();
    }

    public static void main(String[] args) {

        ArrayQueue<Integer> queue = new ArrayQueue<>();

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

    }
}
