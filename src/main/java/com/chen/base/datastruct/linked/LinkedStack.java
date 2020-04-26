package com.chen.base.datastruct.linked;

import com.chen.base.datastruct.stack.Stack;

/**
 * @author: Chentian
 * @date: Created in 2020/4/26 22:24
 * @desc 使用链表实现栈
 */
public class LinkedStack<E> implements Stack<E> {
    private LinkedList<E> list;

    public LinkedStack(){
        list = new LinkedList<>();
    }

    @Override
    public void push(E e) {
        list.addFirst(e);
    }

    @Override
    public E pop() {
        if(isEmpty()){
            throw new IllegalArgumentException("stack is empty");
        }
        return list.removeFirst();
    }

    @Override
    public E peek() {
        if(isEmpty()){
            throw new IllegalArgumentException("stack is empty");
        }
        return list.getFirst();
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack: ");
        res.append("top [");
        for(int i = 0 ; i < list.getSize() ; i ++){
            res.append(list.get(i));
            if(i != list.getSize() - 1)
                res.append(", ");
        }
        res.append("]");
        return res.toString();
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new LinkedStack<>();
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
