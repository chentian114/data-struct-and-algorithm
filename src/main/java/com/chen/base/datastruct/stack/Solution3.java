package com.chen.base.datastruct.stack;

/**
 * @author: Chentian
 * @date: Created in 2020/4/23 5:06
 * @desc  Leetcode 20. 有效的括号，使用链表栈
 */
public class Solution3 {
    class LinkedList<E> {
        private class Node {
            private E e;
            private Node next;

            public Node(E e, Node next) {
                this.e = e;
                this.next = next;
            }

            public Node() {
                this(null, null);
            }

            @Override
            public String toString() {
                return e.toString();
            }
        }

        private Node dummyHead;
        private int size;

        public LinkedList() {
            dummyHead = new Node();
            size = 0;
        }

        public int getSize() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void add(int index, E e) {
            if (index < 0 || index > size) {
                throw new IllegalArgumentException("index is error!");
            }

            Node prev = dummyHead;
            for (int i = 0; i < index; i++) {
                prev = prev.next;
            }

            Node node = new Node(e, prev.next);
            prev.next = node;
            size++;
        }

        public void addFirst(E e) {
            add(0, e);
        }

        public void addLast(E e) {
            add(size, e);
        }

        public void removeElement(E e) {
            Node prev = dummyHead;
            while (prev.next != null) {
                if (e.equals(prev.next.e)) {
                    prev.next = prev.next.next;
                    size--;
                    return;
                }
            }

        }

        public E remove(int index) {
            if (index < 0 || index >= size) {
                throw new IllegalArgumentException("index is error!");
            }

            Node prev = dummyHead;
            for (int i = 0; i < index; i++) {
                prev = prev.next;
            }
            E e = prev.next.e;
            prev.next = prev.next.next;
            size--;
            return e;
        }

        public E removeFirst() {
            return remove(0);
        }

        public E removeLast() {
            return remove(size - 1);
        }

        public E get(int index) {
            if (index < 0 || index >= size) {
                throw new IllegalArgumentException("index is error！");
            }
            Node cur = dummyHead.next;
            for (int i = 0; i < index; i++) {
                cur = cur.next;
            }
            return cur.e;
        }

        public E getFirst() {
            return get(0);
        }

        public E getLast() {
            return get(size - 1);
        }

        public boolean contains(E e) {
            Node cur = dummyHead.next;
            while (cur != null) {
                if (e.equals(cur.e)) {
                    return true;
                }
                cur = cur.next;
            }
            return false;
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

    class LinkedStack<E> implements Stack<E> {
        private LinkedList<E> list;

        public LinkedStack() {
            list = new LinkedList<>();
        }

        @Override
        public void push(E e) {
            list.addFirst(e);
        }

        @Override
        public E pop() {
            return list.removeFirst();
        }

        @Override
        public E peek() {
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
    }

    public boolean isValid(String s) {

        Stack<Character> stack = new LinkedStack<>();
        for (int i = 0; i < s.length() ; i++){
            char c = s.charAt(i);
            stack.push(c);
        }

        Stack<Character> outStack = new LinkedStack<>();
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
        System.out.println(new Solution3().isValid(str));

        str = "()[]{}";
        System.out.println(new Solution3().isValid(str));

        str = "(]";
        System.out.println(new Solution3().isValid(str));

        str = "([)]";
        System.out.println(new Solution3().isValid(str));

        str = "{[]}";
        System.out.println(new Solution3().isValid(str));

        str = "([]";
        System.out.println(new Solution3().isValid(str));
    }

}
