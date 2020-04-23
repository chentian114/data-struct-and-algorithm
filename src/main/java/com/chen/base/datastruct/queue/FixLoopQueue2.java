package com.chen.base.datastruct.queue;

import java.util.Arrays;

/**
 * @author: Chentian
 * @date: Created in 2020/4/24 6:10
 * @desc 固定数组循环队列，使用(front,tail)维护
 * 队列为空：front == tail
 * 队列为满：(tail + 1) % data.length == front,tail指向下一个可存放入队元素的位置
 * 队列容量：data.length - 1
 */
public class FixLoopQueue2<E> implements Queue<E> {
    private E[] data;
    private int front;
    private int tail;

    public FixLoopQueue2(int capacity){
        this.data = (E[]) new Object[capacity];
        this.front = 0 ;
        this.tail = 0;
    }

    public FixLoopQueue2(){
        this(10);
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    @Override
    public int getSize() {
        if( tail == front){
            return 0;
        }
        else if(tail > front){
            return tail - front;
        }else {
            return tail + data.length - front;
        }
    }

    public int getCapacity(){
        //浪费一个tail指向的空间，用于区分队列为空或已满
        return data.length - 1;
    }

    @Override
    public void enqueue(E e) {
        //判断队列是否已满
        if( (tail + 1) % data.length == front ){
            throw new IllegalArgumentException("queue is full!");
        }
        data[tail] = e;
        tail = (tail + 1) % data.length;
    }

    @Override
    public E dequeue() {
        if( isEmpty() ){
            throw new IllegalArgumentException("queue is empty!");
        }
        E e = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        return e;
    }

    @Override
    public E getFront() {
        if(isEmpty()){
            throw new IllegalArgumentException("queue is empty!");
        }
        return data[front];
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue: ");
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
        FixLoopQueue2<Integer> queue = new FixLoopQueue2<>(12);
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
        System.out.println(queue);
        System.out.println(Arrays.toString(queue.data));

//        queue.enqueue(16);
    }
}
