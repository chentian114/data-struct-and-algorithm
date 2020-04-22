package com.chen.base.datastruct.stack;


/**
 * @author: Chentian
 * @date: Created in 2020/4/23 5:06
 * @desc  Leetcode 20. 有效的括号
 */
public class Solution {
    class Array<E> {
        /**
         * 存放的元素数量
         */
        private int size;
        /**
         * 存放元素数组
         */
        private E[] data;

        public Array(int capacity) {
            size = 0;
            data = (E[]) new Object[capacity];
        }

        public Array() {
            this(10);
        }

        /**
         * 在index索引的位置插入一个新元素e
         *
         * @param index
         * @param e
         */
        public void add(int index, E e) {
            if (index < 0 || index > size) {
                throw new IllegalArgumentException("index is error!");
            }

            if (size == data.length) {
                resize(data.length * 2);
            }

            for (int i = size - 1; i >= index; i--) {
                data[i + 1] = data[i];
            }
            data[index] = e;
            size++;
        }

        public void addLast(E e) {
            add(size, e);
        }

        public void addFirst(E e) {
            add(0, e);
        }

        /**
         * 从数组中删除index位置的元素，返回删除的元素
         *
         * @param index
         * @return
         */
        public E remove(int index) {
            if (index < 0 || index >= size) {
                throw new IllegalArgumentException("index is error!");
            }

            E e = data[index];

            for (int i = index + 1; i < size; i++) {
                data[i - 1] = data[i];
            }
            size--;
            data[size] = null;

            if (size == data.length / 4 && data.length / 2 != 0) {
                resize(data.length / 2);
            }
            return e;
        }

        public E removeLast() {
            return remove(size - 1);
        }

        public E removeFirst() {
            return remove(0);
        }

        public void removeElement(E e) {
            int index = find(e);
            if (index == -1) {
                return;
            }
            remove(index);
        }

        public int find(E e) {
            for (int i = 0; i < size; i++) {
                if (e.equals(data[i])) {
                    return i;
                }
            }
            return -1;
        }

        public boolean contains(E e) {
            return find(e) != -1;
        }

        public E get(int index) {
            if (index < 0 || index >= size) {
                throw new IllegalArgumentException("index is error!");
            }
            return data[index];
        }

        public void set(int index, E e) {
            if (index < 0 || index >= size) {
                throw new IllegalArgumentException("index is error!");
            }
            data[index] = e;
        }

        /**
         * 将数组空间的容量变成newCapacity大小
         *
         * @param newCapacity
         */
        private void resize(int newCapacity) {
            E[] newArray = (E[]) new Object[newCapacity];
            for (int i = 0; i < size; i++) {
                newArray[i] = data[i];
            }
            data = newArray;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int getSize() {
            return size;
        }
    }

    interface Stack<E> {
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

    class ArrayStack<E> implements Stack<E> {
        private Array<E> data;

        public ArrayStack(int capacity) {
            data = new Array<>(capacity);
        }

        public ArrayStack() {
            data = new Array<>();
        }
        @Override
        public void push(E e) {
            data.addLast(e);
        }

        public E pop() {
            return data.removeLast();
        }

        public E peek() {
            return data.get(data.getSize() - 1);
        }

        public int getSize() {
            return data.getSize();
        }

        public boolean isEmpty() {
            return data.isEmpty();
        }
    }

    public boolean isValid(String s) {

        Stack<Character> stack = new ArrayStack<>(s.length());
        for (int i = 0; i < s.length() ; i++){
            char c = s.charAt(i);
            stack.push(c);
        }

        Stack<Character> outStack = new ArrayStack<>(s.length());
        while (!stack.isEmpty()){
            char c = stack.pop();
            if(c == ')' || c == ']' || c == '}'){
                outStack.push(c);
            }else {
                if(outStack.isEmpty()){
                    return false;
                }
                char o = outStack.pop();
                if( c== '(' && o != ')'){
                    return false;
                }else if( c == '[' && o != ']'){
                    return false;
                }else if (c == '{' && o != '}'){
                    return false;
                }
            }
        }

        return outStack.isEmpty();
    }

    public static void main(String[] args) {
        String str = "()";
        System.out.println(new Solution().isValid(str));

        str = "()[]{}";
        System.out.println(new Solution().isValid(str));

        str = "(]";
        System.out.println(new Solution().isValid(str));

        str = "([)]";
        System.out.println(new Solution().isValid(str));

        str = "{[]}";
        System.out.println(new Solution().isValid(str));

        str = "([]";
        System.out.println(new Solution().isValid(str));
    }

}
