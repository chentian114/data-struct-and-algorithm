package com.chen.base.datastruct.stack;

import com.chen.base.datastruct.array.Array;

/**
 * @author: Chentian
 * @date: Created in 2020/4/20 7:01
 * @desc
 */
public class ArrayStack<E> implements Stack<E> {
    private Array<E> data;

    public ArrayStack(int capacity){
        data = new Array<>(capacity);
    }

    public ArrayStack(){
        data = new Array<>();
    }

    @Override
    public void push(E e) {
        data.addLast(e);
    }

    @Override
    public E pop() {
        return data.removeLast();
    }

    @Override
    public E peek() {
        return data.get(data.getSize()-1);
    }

    @Override
    public int getSize() {
        return data.getSize();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack: ");
        res.append('[');
        for(int i = 0 ; i < data.getSize() ; i ++){
            res.append(data.get(i));
            if(i != data.getSize() - 1)
                res.append(", ");
        }
        res.append("] top");
        return res.toString();
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new ArrayStack<>();
        for (int i = 0; i < 10 ; i++ ){
            stack.push(i);
        }
        System.out.println(stack);
        System.out.println(stack.getSize());

        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack);

        System.out.println(stack.isEmpty());
    }
}
