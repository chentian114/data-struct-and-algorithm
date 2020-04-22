package com.chen.base.datastruct.queue;

import java.util.Arrays;

/**
 * @author: Chentian
 * @date: Created in 2020/4/23 6:12
 * @desc 固定数组循环队列，使用(front,tail,size)维护
 * 队列为空： size == 0
 * 队列为满： size == data.length
 */
public class FixLoopQueue<E> implements Queue<E> {

    private E[] data ;
    private int front;
    private int tail;
    private int size ;

    public FixLoopQueue(int capacity){
        this.front = 0;
        this.tail = 0;
        this.size = 0;
        this.data = (E[]) new Object[capacity];
    }

    public FixLoopQueue() {
        this(10);
    }

    @Override
    public boolean isEmpty() {
        if(front == tail && size == 0 ){
            return true;
        }
        return false;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void enqueue(E o) {
        if(getSize() == data.length){
            throw new IllegalArgumentException("queue is full");
        }
        data[tail] = o;
        //当tail在末尾位置时，重置到0位置
        tail = (tail == data.length -1) ? 0 : tail + 1;
        size ++;
    }

    @Override
    public E dequeue() {
        if(getSize() == 0 ){
            throw new IllegalArgumentException("queue is empty");
        }

        E e = data[front];
        data[front] = null;
        front = ( front == data.length - 1) ? 0 : front+1;
        size -- ;

        return e;
    }

    @Override
    public E getFront() {
        return data[front];
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue: ");
        res.append("front [");
        int index = front;
        for(int i = 0 ; i < size ; i ++){
            res.append(data[index]);
            if(i != getSize() - 1)
                res.append(", ");
            index = ( index == data.length -1) ? 0 : index + 1;
        }
        res.append("] tail");
        return res.toString();
    }

    public static void main(String[] args) {
        FixLoopQueue<Integer> queue = new FixLoopQueue<>(12);
        System.out.println(queue.isEmpty());

        for (int i = 0 ; i < 10 ; i++){
            queue.enqueue(i);
        }
        System.out.println(queue);
        System.out.println(queue.isEmpty());
        System.out.println(queue.getFront());
        System.out.println(queue.getSize());

        for (int i = 0; i < 5 ; i++){
            System.out.println(queue.dequeue());
        }
        System.out.println(queue);
        System.out.println(queue.getSize());

        for (int i = 10 ; i < 15 ; i++){
            queue.enqueue(i);
        }
        System.out.println(queue);
        System.out.println(queue.getSize());
        System.out.println(Arrays.toString(queue.data));

        queue.enqueue(15);
        queue.enqueue(16);
        System.out.println(queue);
        System.out.println(Arrays.toString(queue.data));
//        queue.enqueue(17);
    }
}
