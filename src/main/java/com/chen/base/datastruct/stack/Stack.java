package com.chen.base.datastruct.stack;

/**
 * @author: Chentian
 * @date: Created in 2020/4/20 6:57
 * @desc
 */
public interface Stack<E> {

    /**
     * 入栈
     * @param e element
     */
    void push(E e);

    /**
     * 出栈
     * @return stack top element
     */
    E pop();

    /**
     * 查看栈顶元素
     * @return stack top element
     */
    E peek();

    /**
     * 获取栈元素数量
     */
    int getSize();

    /**
     * 判断队列是否为空
     */
    boolean isEmpty();
}
