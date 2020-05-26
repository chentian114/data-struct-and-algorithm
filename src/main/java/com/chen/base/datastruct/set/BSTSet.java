package com.chen.base.datastruct.set;

import com.chen.base.datastruct.bst.BST;

/**
 * @author: Chentian
 * @date: Created in 2020/5/20 6:42
 * @desc
 */
public class BSTSet<E extends Comparable<E>> implements Set<E> {
    private BST<E> bst;

    public BSTSet(){
        bst = new BST<>();
    }

    @Override
    public void add(E e) {
        if(contains(e)){
            return;
        }
        bst.add(e);
    }

    @Override
    public void remove(E e) {
        bst.remove(e);
    }

    @Override
    public boolean contains(E e) {
        return bst.contains(e);
    }

    @Override
    public int getSize() {
        return bst.size();
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }
}
