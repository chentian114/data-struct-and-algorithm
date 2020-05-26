package com.chen.base.datastruct.set;

import com.chen.base.datastruct.linked.LinkedList;

/**
 * @author: Chentian
 * @date: Created in 2020/5/20 6:42
 * @desc
 */
public class LinkedSet<E> implements Set<E> {

    private LinkedList<E> list ;

    public LinkedSet(){
        list = new LinkedList<>();
    }

    @Override
    public void add(E e) {
        if(contains(e)){
            return;
        }
        list.addLast(e);
    }

    @Override
    public void remove(E e) {
        list.removeElement(e);
    }

    @Override
    public boolean contains(E e) {
        return list.contains(e);
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
