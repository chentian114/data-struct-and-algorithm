package com.chen.base.datastruct.queue;

import java.util.Arrays;

/**
 * @author: Chentian
 * @date: Created in 2020/4/24 6:52
 * @desc 可变容量循环队列
 */
public class LoopQueue<E> implements Queue<E>{

    private E[] data;
    private int front;
    private int tail;
    private int size;

    public LoopQueue(int capacity){
        this.data = (E[])new Object[capacity];
        this.front = 0;
        this.tail = 0;
        this.size = 0;
    }

    public LoopQueue(){
        this(10);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    public int getCapacity(){
        return data.length;
    }

    @Override
    public void enqueue(E e) {
        if(size == data.length){
            resize(data.length * 2);
        }

        data[tail] = e;
        tail = (tail + 1) % data.length;
        size ++;
    }

    @Override
    public E dequeue() {
        if(isEmpty()){
            throw new IllegalArgumentException("queue is empty!");
        }
        E e = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size --;

        if(size < data.length / 4 && data.length / 2 != 0){
            resize(data.length / 2);
        }
        return e;
    }

    @Override
    public E getFront() {
        if(isEmpty()){
            throw new IllegalArgumentException("queue is empty!");
        }
        return data[front];
    }

    private void resize(int newCapacity) {
        E[] newData = (E[])new Object[newCapacity];
        int index = front;
        for (int i = 0; i < size ; i++){
            newData[i] = data[index];
            index = (index + 1) % data.length;
        }
        this.data = newData;
        this.front = 0;
        this.tail = size;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue: size = %d , capacity = %d\n", size, getCapacity()));
        res.append("front [");
        int index = front;
        int size = getSize();
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
        LoopQueue<Integer> queue = new LoopQueue<>(12);
        System.out.println(queue.isEmpty());
        System.out.println(queue.getCapacity());

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

        queue.enqueue(17);
        System.out.println(queue);
        System.out.println(Arrays.toString(queue.data));

        for (int i = 18 ; i < 29 ; i++){
            queue.enqueue(i);
        }
        System.out.println(queue);
        System.out.println(Arrays.toString(queue.data));

        for (int i = 0 ; i < 20 ; i++ ){
            queue.dequeue();
        }
        System.out.println(queue);
        System.out.println(Arrays.toString(queue.data));
        queue.enqueue(29);
        System.out.println(queue);
        System.out.println(Arrays.toString(queue.data));

    }
}
