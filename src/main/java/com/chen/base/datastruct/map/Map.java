package com.chen.base.datastruct.map;

/**
 * @author: Chentian
 * @date: Created in 2020/5/20 7:34
 * @desc
 */
public interface Map <K,V>{
    void add(K key, V val);

    V remove(K key);

    boolean contains(K key);

    V get(K key);

    void set(K key, V val);

    int getSize();


}
