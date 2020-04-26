# 链表

线性数据结构：动态数组、栈、队列，底层依托静态数组；靠resize解决固定容量问题；

链表是真正的动态数据结构；

数据存储在节点(Node)中；
```
class Node{
    E e;
    Node next;
}
```
优点：真正的动态，不需要处理固定容量的问题；

缺点：丧失了随机访问的能力；

数组VS链表
- 数组最好用于索引有语意的情况；
- 最大的优点：支持快速查询；
- 链表不适合用于索引有语意的情况；
- 最大的优点：动态；

## 应用

## 时间复杂度分析

添加操作  O(n)
- addLast()  在末尾添加元素  O(n)
- addFirst() 在头部添加元素  O(1)
- add(index,e)  在指定索引添加元素  O(n/2) = O(n)

删除操作  O(n)
- removeLast(e)  在末尾删除元素  O(n)
- removeFirst(e) 在头部删除元素  O(1)
- remove(index,e)  删除指定位置元素  O(n/2) = O(n)

修改操作 O(n)
- set(index,e)  修改指定位置元素 O(n)

查找操作 O(n)
- get(index)  查找指定位置元素  O(n)
- contains(e)  是否存在指定元素  O(n)


## 原理

使用head指向链表第一个节点；需要对第一个元素特殊处理；

添加元素，新节点newNode
- 在尾部添加元素：从head节点开始，遍历到链表最后一个节点(lastNode)，将最后一个节点的下一个结点指向新节点(lastNode.next = newNode)；
- 在头部添加元素：将新节点的下一个节点指向head指向的节点，head指向新节点；newNode.next = head; head = newNode;
- 在中间位置添加元素：找到要添加位置的前一个节点(preNode)，将新节点的下一个节点指向链表中的下一个节点，将前一个节点指向新节点；newNode.next = preNode.next; preNode.next = newNode; 


为链表设立虚拟头节点(dummyHead)，使用head指向虚拟头节点；不需要对第一个元素特殊处理；

删除元素
- 删除头节点元素，将head指向原节点的下一个节点；dummyHead.next = dummyHead.next.next;
- 删除尾节点元素，从head节点开始，遍历到链表最一个节点的前一个节点(prevLastNode)，将其下一个节点指向null;prevLastNode.next = null;
- 删除中间节点元素，找到待删除元素的前一个节点(prevNode),将其下一个节点指向待删除节点(delNode)的下一个节点;prevNode.next = delNode.next;delNode.next = null;

## 链表与递归

递归： 本质上，将原来的问题，转化为更小的同一问题。

举例：数组求和
- Sum(arr[0...n-1]) = arr[0] + Sum(arr[1...n-1])
- Sum(arr[1...n-1]) = arr[1] + Sum(arr[2...n-1])
- ...
- Sum(arr[n-1...n-1]) = arr[n-1];

递归函数实现：
```java
// 计算arr[l...n) 范围的数字和
public static int sum(int[] arr, int l){
    if ( l == arr.length){
        return 0 ;   // 求解最基本问题
    }
    return arr[l] + sum(arr, l + 1);  //把原问题转化成更小的问题
}
```

## 更多链表问题

1. 双链表；每个节点指下一个节点及前一个节点
2. 循环链表

## 实现

1. 链表实现
2. 使用虚拟头节点链表实现
3. 使用链表实现栈
4. 使用链表实现队列
5. Leetcode 20. Valid Parentheses 验证链表及链表实现栈
6. Leetcode 102. Binary Tree Level Order Traversal 验证链表及链表实现队列
7. Leetcode 203. Remove Linked List Elements 验证递归实现链表方法

```java
public class LinkedList<E> {

    private class Node{
        public E e;
        public Node next;

        public Node(E e, Node next){
            this.e = e;
            this.next = next;
        }

        public Node(E e){
            this(e, null);
        }

        public Node(){
            this(null, null);
        }

        @Override
        public String toString(){
            return e.toString();
        }
    }

    private Node head;
    private int size;

    public LinkedList(){
        head = null;
        size = 0;
    }

    // 获取链表中的元素个数
    public int getSize(){
        return size;
    }

    // 返回链表是否为空
    public boolean isEmpty(){
        return size == 0;
    }

    // 在链表头添加新的元素e
    public void addFirst(E e){
//        Node node = new Node(e);
//        node.next = head;
//        head = node;

        head = new Node(e, head);
        size ++;
    }

    // 在链表的index(0-based)位置添加新的元素e
    // 在链表中不是一个常用的操作，练习用：）
    public void add(int index, E e){

        if(index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Illegal index.");

        if(index == 0)
            addFirst(e);
        else{
            Node prev = head;
            for(int i = 0 ; i < index - 1 ; i ++)
                prev = prev.next;

//            Node node = new Node(e);
//            node.next = prev.next;
//            prev.next = node;

            prev.next = new Node(e, prev.next);
            size ++;
        }
    }

    // 在链表末尾添加新的元素e
    public void addLast(E e){
        add(size, e);
    }
}
```

虚拟头节点
```java
public class LinkedList<E> {

    private class Node{
        public E e;
        public Node next;

        public Node(E e, Node next){
            this.e = e;
            this.next = next;
        }

        public Node(E e){
            this(e, null);
        }

        public Node(){
            this(null, null);
        }

        @Override
        public String toString(){
            return e.toString();
        }
    }

    private Node dummyHead;
    private int size;

    public LinkedList(){
        dummyHead = new Node();
        size = 0;
    }

    // 获取链表中的元素个数
    public int getSize(){
        return size;
    }

    // 返回链表是否为空
    public boolean isEmpty(){
        return size == 0;
    }

    // 在链表的index(0-based)位置添加新的元素e
    // 在链表中不是一个常用的操作，练习用：）
    public void add(int index, E e){

        if(index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Illegal index.");

        Node prev = dummyHead;
        for(int i = 0 ; i < index ; i ++)
            prev = prev.next;

        prev.next = new Node(e, prev.next);
        size ++;
    }

    // 在链表头添加新的元素e
    public void addFirst(E e){
        add(0, e);
    }

    // 在链表末尾添加新的元素e
    public void addLast(E e){
        add(size, e);
    }

    // 获得链表的第index(0-based)个位置的元素
    // 在链表中不是一个常用的操作，练习用：）
    public E get(int index){

        if(index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed. Illegal index.");

        Node cur = dummyHead.next;
        for(int i = 0 ; i < index ; i ++)
            cur = cur.next;
        return cur.e;
    }

    // 获得链表的第一个元素
    public E getFirst(){
        return get(0);
    }

    // 获得链表的最后一个元素
    public E getLast(){
        return get(size - 1);
    }

    // 修改链表的第index(0-based)个位置的元素为e
    // 在链表中不是一个常用的操作，练习用：）
    public void set(int index, E e){
        if(index < 0 || index >= size)
            throw new IllegalArgumentException("Set failed. Illegal index.");

        Node cur = dummyHead.next;
        for(int i = 0 ; i < index ; i ++)
            cur = cur.next;
        cur.e = e;
    }

    // 查找链表中是否有元素e
    public boolean contains(E e){
        Node cur = dummyHead.next;
        while(cur != null){
            if(cur.e.equals(e))
                return true;
            cur = cur.next;
        }
        return false;
    }

    // 从链表中删除index(0-based)位置的元素, 返回删除的元素
    // 在链表中不是一个常用的操作，练习用：）
    public E remove(int index){
        if(index < 0 || index >= size)
            throw new IllegalArgumentException("Remove failed. Index is illegal.");

        Node prev = dummyHead;
        for(int i = 0 ; i < index ; i ++)
            prev = prev.next;

        Node retNode = prev.next;
        prev.next = retNode.next;
        retNode.next = null;
        size --;

        return retNode.e;
    }

    // 从链表中删除第一个元素, 返回删除的元素
    public E removeFirst(){
        return remove(0);
    }

    // 从链表中删除最后一个元素, 返回删除的元素
    public E removeLast(){
        return remove(size - 1);
    }

    // 从链表中删除元素e
    public void removeElement(E e){

        Node prev = dummyHead;
        while(prev.next != null){
            if(prev.next.e.equals(e))
                break;
            prev = prev.next;
        }

        if(prev.next != null){
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size --;
        }
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();

        Node cur = dummyHead.next;
        while(cur != null){
            res.append(cur + "->");
            cur = cur.next;
        }
        res.append("NULL");

        return res.toString();
    }
}
```

## 使用链表实现栈
```java
public class LinkedListStack<E> implements Stack<E> {

    private LinkedList<E> list;

    public LinkedListStack(){
        list = new LinkedList<>();
    }

    @Override
    public int getSize(){
        return list.getSize();
    }

    @Override
    public boolean isEmpty(){
        return list.isEmpty();
    }

    @Override
    public void push(E e){
        list.addFirst(e);
    }

    @Override
    public E pop(){
        return list.removeFirst();
    }

    @Override
    public E peek(){
        return list.getFirst();
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Stack: top ");
        res.append(list);
        return res.toString();
    }

    public static void main(String[] args) {

        LinkedListStack<Integer> stack = new LinkedListStack<>();

        for(int i = 0 ; i < 5 ; i ++){
            stack.push(i);
            System.out.println(stack);
        }

        stack.pop();
        System.out.println(stack);
    }
}
```

Leetcode 20. Valid Parentheses
```
/// Leetcode 20. Valid Parentheses
/// https://leetcode.com/problems/valid-parentheses/description/
/// 括号匹配问题
///
/// 使用LinkedListStack测试20号问题的代码在视频中没有提及
/// 该代码主要用于使用Leetcode上的问题测试我们的LinkedListStack：）
class Solution {

    private class LinkedList<E> {

        private class Node{
            public E e;
            public Node next;

            public Node(E e, Node next){
                this.e = e;
                this.next = next;
            }

            public Node(E e){
                this(e, null);
            }

            public Node(){
                this(null, null);
            }

            @Override
            public String toString(){
                return e.toString();
            }
        }

        private Node dummyHead;
        private int size;

        public LinkedList(){
            dummyHead = new Node();
            size = 0;
        }

        // 获取链表中的元素个数
        public int getSize(){
            return size;
        }

        // 返回链表是否为空
        public boolean isEmpty(){
            return size == 0;
        }

        // 在链表的index(0-based)位置添加新的元素e
        // 在链表中不是一个常用的操作，练习用：）
        public void add(int index, E e){

            if(index < 0 || index > size)
                throw new IllegalArgumentException("Add failed. Illegal index.");

            Node prev = dummyHead;
            for(int i = 0 ; i < index ; i ++)
                prev = prev.next;

            prev.next = new Node(e, prev.next);
            size ++;
        }

        // 在链表头添加新的元素e
        public void addFirst(E e){
            add(0, e);
        }

        // 在链表末尾添加新的元素e
        public void addLast(E e){
            add(size, e);
        }

        // 获得链表的第index(0-based)个位置的元素
        // 在链表中不是一个常用的操作，练习用：）
        public E get(int index){

            if(index < 0 || index >= size)
                throw new IllegalArgumentException("Get failed. Illegal index.");

            Node cur = dummyHead.next;
            for(int i = 0 ; i < index ; i ++)
                cur = cur.next;
            return cur.e;
        }

        // 获得链表的第一个元素
        public E getFirst(){
            return get(0);
        }

        // 获得链表的最后一个元素
        public E getLast(){
            return get(size - 1);
        }

        // 修改链表的第index(0-based)个位置的元素为e
        // 在链表中不是一个常用的操作，练习用：）
        public void set(int index, E e){
            if(index < 0 || index >= size)
                throw new IllegalArgumentException("Update failed. Illegal index.");

            Node cur = dummyHead.next;
            for(int i = 0 ; i < index ; i ++)
                cur = cur.next;
            cur.e = e;
        }

        // 查找链表中是否有元素e
        public boolean contains(E e){
            Node cur = dummyHead.next;
            while(cur != null){
                if(cur.e.equals(e))
                    return true;
                cur = cur.next;
            }
            return false;
        }

        // 从链表中删除index(0-based)位置的元素, 返回删除的元素
        // 在链表中不是一个常用的操作，练习用：）
        public E remove(int index){
            if(index < 0 || index >= size)
                throw new IllegalArgumentException("Remove failed. Index is illegal.");

            // E ret = findNode(index).e; // 两次遍历

            Node prev = dummyHead;
            for(int i = 0 ; i < index ; i ++)
                prev = prev.next;

            Node retNode = prev.next;
            prev.next = retNode.next;
            retNode.next = null;
            size --;

            return retNode.e;
        }

        // 从链表中删除第一个元素, 返回删除的元素
        public E removeFirst(){
            return remove(0);
        }

        // 从链表中删除最后一个元素, 返回删除的元素
        public E removeLast(){
            return remove(size - 1);
        }

        // 从链表中删除元素e
        public void removeElement(E e){

            Node prev = dummyHead;
            while(prev.next != null){
                if(prev.next.e.equals(e))
                    break;
                prev = prev.next;
            }

            if(prev.next != null){
                Node delNode = prev.next;
                prev.next = delNode.next;
                delNode.next = null;
                size --;
            }
        }

        @Override
        public String toString(){
            StringBuilder res = new StringBuilder();

            Node cur = dummyHead.next;
            while(cur != null){
                res.append(cur + "->");
                cur = cur.next;
            }
            res.append("NULL");

            return res.toString();
        }
    }

    private interface Stack<E> {

        int getSize();
        boolean isEmpty();
        void push(E e);
        E pop();
        E peek();
    }

    private class LinkedListStack<E> implements Stack<E> {

        private LinkedList<E> list;

        public LinkedListStack(){
            list = new LinkedList<>();
        }

        @Override
        public int getSize(){
            return list.getSize();
        }

        @Override
        public boolean isEmpty(){
            return list.isEmpty();
        }

        @Override
        public void push(E e){
            list.addFirst(e);
        }

        @Override
        public E pop(){
            return list.removeFirst();
        }

        @Override
        public E peek(){
            return list.getFirst();
        }

        @Override
        public String toString(){
            StringBuilder res = new StringBuilder();
            res.append("Stack: top ");
            res.append(list);
            return res.toString();
        }
    }

    public boolean isValid(String s) {

        LinkedListStack<Character> stack = new LinkedListStack<>();
        for(int i = 0 ; i < s.length() ; i ++){
            char c = s.charAt(i);
            if(c == '(' || c == '[' || c == '{')
                stack.push(c);
            else{
                if(stack.isEmpty())
                    return false;

                char topChar = stack.pop();
                if(c == ')' && topChar != '(')
                    return false;
                if(c == ']' && topChar != '[')
                    return false;
                if(c == '}' && topChar != '{')
                    return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {

        System.out.println((new Solution()).isValid("()[]{}"));
        System.out.println((new Solution()).isValid("([)]"));
    }
}
```

## 使用链表实现队列
```java
public class LinkedListQueue<E> implements Queue<E> {

    private class Node{
        public E e;
        public Node next;

        public Node(E e, Node next){
            this.e = e;
            this.next = next;
        }

        public Node(E e){
            this(e, null);
        }

        public Node(){
            this(null, null);
        }

        @Override
        public String toString(){
            return e.toString();
        }
    }

    private Node head, tail;
    private int size;

    public LinkedListQueue(){
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int getSize(){
        return size;
    }

    @Override
    public boolean isEmpty(){
        return size == 0;
    }

    @Override
    public void enqueue(E e){
        if(tail == null){
            tail = new Node(e);
            head = tail;
        }
        else{
            tail.next = new Node(e);
            tail = tail.next;
        }
        size ++;
    }

    @Override
    public E dequeue(){
        if(isEmpty())
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");

        Node retNode = head;
        head = head.next;
        retNode.next = null;
        if(head == null)
            tail = null;
        size --;
        return retNode.e;
    }

    @Override
    public E getFront(){
        if(isEmpty())
            throw new IllegalArgumentException("Queue is empty.");
        return head.e;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Queue: front ");

        Node cur = head;
        while(cur != null) {
            res.append(cur + "->");
            cur = cur.next;
        }
        res.append("NULL tail");
        return res.toString();
    }

    public static void main(String[] args){

        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        for(int i = 0 ; i < 10 ; i ++){
            queue.enqueue(i);
            System.out.println(queue);

            if(i % 3 == 2){
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}
```
Leetcode 102. Binary Tree Level Order Traversal
```java
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

/// Leetcode 102. Binary Tree Level Order Traversal
/// https://leetcode.com/problems/binary-tree-level-order-traversal/description/
/// 二叉树的层序遍历
///
/// 二叉树的层序遍历是一个典型的可以借助队列解决的问题。
/// 该代码主要用于使用Leetcode上的问题测试我们的LinkedListQueue。
/// 对于二叉树的层序遍历，这个课程后续会讲到。
/// 届时，同学们也可以再回头看这个代码。
/// 不过到时，大家应该已经学会自己编写二叉树的层序遍历啦：）

class Solution {

    /// Definition for a binary tree node.
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    private interface Queue<E> {

        int getSize();
        boolean isEmpty();
        void enqueue(E e);
        E dequeue();
        E getFront();
    }

    private class LinkedListQueue<E> implements Queue<E> {

        private class Node{
            public E e;
            public Node next;

            public Node(E e, Node next){
                this.e = e;
                this.next = next;
            }

            public Node(E e){
                this(e, null);
            }

            public Node(){
                this(null, null);
            }

            @Override
            public String toString(){
                return e.toString();
            }
        }

        private Node head, tail;
        private int size;

        public LinkedListQueue(){
            head = null;
            tail = null;
            size = 0;
        }

        @Override
        public int getSize(){
            return size;
        }

        @Override
        public boolean isEmpty(){
            return size == 0;
        }

        @Override
        public void enqueue(E e){
            if(tail == null){
                tail = new Node(e);
                head = tail;
            }
            else{
                tail.next = new Node(e);
                tail = tail.next;
            }
            size ++;
        }

        @Override
        public E dequeue(){
            if(isEmpty())
                throw new IllegalArgumentException("Cannot dequeue from an empty queue.");

            Node retNode = head;
            head = head.next;
            retNode.next = null;
            if(head == null)
                tail = null;
            size --;
            return retNode.e;
        }

        @Override
        public E getFront(){
            if(isEmpty())
                throw new IllegalArgumentException("Queue is empty.");
            return head.e;
        }

        @Override
        public String toString(){
            StringBuilder res = new StringBuilder();
            res.append("Queue: front ");

            Node cur = head;
            while(cur != null)
                res.append(cur + "->");
            res.append("NULL tail");
            return res.toString();
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {

        ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();
        if(root == null)
            return res;

        // 我们使用LinkedList来做为我们的先入先出的队列
        LinkedListQueue<Pair<TreeNode, Integer>> queue = new LinkedListQueue<Pair<TreeNode, Integer>>();
        queue.enqueue(new Pair<TreeNode, Integer>(root, 0));

        while(!queue.isEmpty()){

            Pair<TreeNode, Integer> front = queue.dequeue();
            TreeNode node = front.getKey();
            int level = front.getValue();

            if(level == res.size())
                res.add(new ArrayList<Integer>());
            assert level < res.size();

            res.get(level).add(node.val);
            if(node.left != null)
                queue.enqueue(new Pair<TreeNode, Integer>(node.left, level + 1));
            if(node.right != null)
                queue.enqueue(new Pair<TreeNode, Integer>(node.right, level + 1));
        }

        return res;
    }
}
```
Leetcode 203. Remove Linked List Elements
```java
/// Leetcode 203. Remove Linked List Elements
/// https://leetcode.com/problems/remove-linked-list-elements/description/

class Solution {

    public ListNode removeElements(ListNode head, int val) {

        while(head != null && head.val == val){
            ListNode delNode = head;
            head = head.next;
            delNode.next = null;
        }

        if(head == null)
            return head;

        ListNode prev = head;
        while(prev.next != null){
            if(prev.next.val == val) {
                ListNode delNode = prev.next;
                prev.next = delNode.next;
                delNode.next = null;
            }
            else
                prev = prev.next;
        }

        return head;
    }

    public static void main(String[] args) {

        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = (new Solution()).removeElements(head, 6);
        System.out.println(res);
    }
}
```
## 参考

学习刘宇波《玩转数据结构》课程，整理及实践。
