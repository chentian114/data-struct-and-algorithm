package com.chen.base.datastruct.queue;

/**
 * @author: Chentian
 * @date: Created in 2020/4/20 7:21
 * @desc
 */
public interface Queue<E> {

    boolean isEmpty();

    int getSize();

    /**
     * 入队
     * @param e 元素
     */
    void enqueue(E e);

    /**
     * 出队
     * @return 元素
     */
    E dequeue();

    /**
     * 查看队首元素
     * @return 元素
     */
    E getFront();

}
