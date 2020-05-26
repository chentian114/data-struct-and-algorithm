package com.chen.base.datastruct.set;

/**
 * @author: Chentian
 * @date: Created in 2020/5/20 6:40
 * @desc
 */
public interface Set<E> {
    void add(E e);

    void remove(E e);

    boolean contains(E e);

    int getSize();

    boolean isEmpty();
}
