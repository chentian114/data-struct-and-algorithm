# 集合和映射

[github地址](https://github.com/chentian114/data-struct-and-algorithm)

## 应用

集合：
- 客户统计
- 词汇量统计

## 时间复杂度分析

### 集合

| 方法 | 链表实现 | 二分搜索树实现 |
| :--  |  :-- |  :-- |
| 增add  |      O(n)  |  O(h) -> 平均O(logn),最差O(n)  |
| 查contains |  O(n)  |  O(h) -> 平均O(logn),最差O(n)  |
| 删remove   |  O(n)  |  O(h) -> 平均O(logn),最差O(n)  |

### 映射

| 方法 | 链表实现 | 二分搜索树实现 |
| :--  |  :-- |  :-- |
| 增add  |      O(n)  |  O(h) -> 平均O(logn),最差O(n)  |
| 删remove   |  O(n)  |  O(h) -> 平均O(logn),最差O(n)  |
| 改set |  O(n)  |  O(h) -> 平均O(logn),最差O(n)  |
| 查get   |  O(n)  |  O(h) -> 平均O(logn),最差O(n)  |
| 查contains   |  O(n)  |  O(h) -> 平均O(logn),最差O(n)  |


## 原理

### 集合Set

Set<E>
- void add(E)   不能添加重复元素
- void remove(E)
- boolean contains(E)
- int getSize()
- boolean isEmpty()

使用链表实现集合 

使用二分搜索树实现集合

链表实现的集合VS二分搜索树实现的集合

有序集合中元素具有顺序性，基于搜索树的实现

无序集合中元素没有顺序性，基于哈希表的实现
```java
// 链表
class Node{
    E e;
    Node next;
}

// BST
class Node{
    E e;
    Node left;
    Node right;
}
```

### 映射Map

Map<K,V>
- void add(K,V)
- v remove(K)
- boolean contains(K)
- V get(K)
- void set(K,V)
- int getSize()
- boolean isEmpty()

存储（键，值）数据对的数据结构(key,value)

根据键，寻找值

使用链表实现集合 

使用二分搜索树实现集合

链表实现的集合VS二分搜索树实现的集合

有序集合中元素具有顺序性，基于搜索树的实现

无序集合中元素没有顺序性，基于哈希表的实现

```java
// 链表
class Node{
    K key;
    V value;
    Node next;
}

// BST
class Node{
    K key;
    V value;
    Node left;
    Node right;
}
```

## 实现

- BSTSet
- LinkedListSet
- BSTSet VS LinkedListSet
- Leetcode 804. Unique Morse Code Words
- BSTMap
- LinkedMap
- BSTMap VS LinkedMap
- Leetcode 350. Intersection of Two Arrays II
- Leetcode 349. Intersection of Two Arrays

BSTSet
```java
public interface Set<E> {

    void add(E e);
    boolean contains(E e);
    void remove(E e);
    int getSize();
    boolean isEmpty();
}

public class BSTSet<E extends Comparable<E>> implements Set<E> {

    private BST<E> bst;

    public BSTSet(){
        bst = new BST<>();
    }

    @Override
    public int getSize(){
        return bst.size();
    }

    @Override
    public boolean isEmpty(){
        return bst.isEmpty();
    }

    @Override
    public void add(E e){
        bst.add(e);
    }

    @Override
    public boolean contains(E e){
        return bst.contains(e);
    }

    @Override
    public void remove(E e){
        bst.remove(e);
    }
}
```

LinkedListSet
```java
import java.util.ArrayList;

public class LinkedListSet<E> implements Set<E> {

    private LinkedList<E> list;

    public LinkedListSet(){
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
    public void add(E e){
        if(!list.contains(e))
            list.addFirst(e);
    }

    @Override
    public boolean contains(E e){
        return list.contains(e);
    }

    @Override
    public void remove(E e){
        list.removeElement(e);
    }

    public static void main(String[] args) {

        System.out.println("Pride and Prejudice");

        ArrayList<String> words1 = new ArrayList<>();
        if(FileOperation.readFile("pride-and-prejudice.txt", words1)) {
            System.out.println("Total words: " + words1.size());

            LinkedListSet<String> set1 = new LinkedListSet<>();
            for (String word : words1)
                set1.add(word);
            System.out.println("Total different words: " + set1.getSize());
        }

        System.out.println();


        System.out.println("A Tale of Two Cities");

        ArrayList<String> words2 = new ArrayList<>();
        if(FileOperation.readFile("a-tale-of-two-cities.txt", words2)){
            System.out.println("Total words: " + words2.size());

            LinkedListSet<String> set2 = new LinkedListSet<>();
            for(String word: words2)
                set2.add(word);
            System.out.println("Total different words: " + set2.getSize());
        }
    }
}
```

工具类
```java
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

// 文件相关操作
public class FileOperation {

    // 读取文件名称为filename中的内容，并将其中包含的所有词语放进words中
    public static boolean readFile(String filename, ArrayList<String> words){

        if (filename == null || words == null){
            System.out.println("filename is null or words is null");
            return false;
        }

        // 文件读取
        Scanner scanner;

        try {
            File file = new File(filename);
            if(file.exists()){
                FileInputStream fis = new FileInputStream(file);
                scanner = new Scanner(new BufferedInputStream(fis), "UTF-8");
                scanner.useLocale(Locale.ENGLISH);
            }
            else
                return false;
        }
        catch(IOException ioe){
            System.out.println("Cannot open " + filename);
            return false;
        }

        // 简单分词
        // 这个分词方式相对简陋, 没有考虑很多文本处理中的特殊问题
        // 在这里只做demo展示用
        if (scanner.hasNextLine()) {

            String contents = scanner.useDelimiter("\\A").next();

            int start = firstCharacterIndex(contents, 0);
            for (int i = start + 1; i <= contents.length(); )
                if (i == contents.length() || !Character.isLetter(contents.charAt(i))) {
                    String word = contents.substring(start, i).toLowerCase();
                    words.add(word);
                    start = firstCharacterIndex(contents, i);
                    i = start + 1;
                } else
                    i++;
        }

        return true;
    }

    // 寻找字符串s中，从start的位置开始的第一个字母字符的位置
    private static int firstCharacterIndex(String s, int start){

        for( int i = start ; i < s.length() ; i ++ )
            if( Character.isLetter(s.charAt(i)) )
                return i;
        return s.length();
    }
}
```

BSTSet VS LinkedListSet
```java
import java.util.ArrayList;

public class Main {

    private static double testSet(Set<String> set, String filename){

        long startTime = System.nanoTime();

        System.out.println(filename);
        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile(filename, words)) {
            System.out.println("Total words: " + words.size());

            for (String word : words)
                set.add(word);
            System.out.println("Total different words: " + set.getSize());
        }
        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {

        String filename = "pride-and-prejudice.txt";

        BSTSet<String> bstSet = new BSTSet<>();
        double time1 = testSet(bstSet, filename);
        System.out.println("BST Set: " + time1 + " s");

        System.out.println();

        LinkedListSet<String> linkedListSet = new LinkedListSet<>();
        double time2 = testSet(linkedListSet, filename);
        System.out.println("Linked List Set: " + time2 + " s");

    }
}
```
Leetcode 804. Unique Morse Code Words
```java
// Leetcode 804. Unique Morse Code Words
// https://leetcode.com/problems/unique-morse-code-words/description/

import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

public class BSTSetSolution {

    private class BST<E extends Comparable<E>> {

        private class Node{
            public E e;
            public Node left, right;

            public Node(E e){
                this.e = e;
                left = null;
                right = null;
            }
        }

        private Node root;
        private int size;

        public BST(){
            root = null;
            size = 0;
        }

        public int size(){
            return size;
        }

        public boolean isEmpty(){
            return size == 0;
        }

        // 向二分搜索树中添加新的元素e
        public void add(E e){
            root = add(root, e);
        }

        // 向以node为根的二分搜索树中插入元素e，递归算法
        // 返回插入新节点后二分搜索树的根
        private Node add(Node node, E e){

            if(node == null){
                size ++;
                return new Node(e);
            }

            if(e.compareTo(node.e) < 0)
                node.left = add(node.left, e);
            else if(e.compareTo(node.e) > 0)
                node.right = add(node.right, e);

            return node;
        }

        // 看二分搜索树中是否包含元素e
        public boolean contains(E e){
            return contains(root, e);
        }

        // 看以node为根的二分搜索树中是否包含元素e, 递归算法
        private boolean contains(Node node, E e){

            if(node == null)
                return false;

            if(e.compareTo(node.e) == 0)
                return true;
            else if(e.compareTo(node.e) < 0)
                return contains(node.left, e);
            else // e.compareTo(node.e) > 0
                return contains(node.right, e);
        }

        // 二分搜索树的前序遍历
        public void preOrder(){
            preOrder(root);
        }

        // 前序遍历以node为根的二分搜索树, 递归算法
        private void preOrder(Node node){

            if(node == null)
                return;

            System.out.println(node.e);
            preOrder(node.left);
            preOrder(node.right);
        }

        // 二分搜索树的非递归前序遍历
        public void preOrderNR(){

            Stack<Node> stack = new Stack<>();
            stack.push(root);
            while(!stack.isEmpty()){
                Node cur = stack.pop();
                System.out.println(cur.e);

                if(cur.right != null)
                    stack.push(cur.right);
                if(cur.left != null)
                    stack.push(cur.left);
            }
        }

        // 二分搜索树的中序遍历
        public void inOrder(){
            inOrder(root);
        }

        // 中序遍历以node为根的二分搜索树, 递归算法
        private void inOrder(Node node){

            if(node == null)
                return;

            inOrder(node.left);
            System.out.println(node.e);
            inOrder(node.right);
        }

        // 二分搜索树的后序遍历
        public void postOrder(){
            postOrder(root);
        }

        // 后序遍历以node为根的二分搜索树, 递归算法
        private void postOrder(Node node){

            if(node == null)
                return;

            postOrder(node.left);
            postOrder(node.right);
            System.out.println(node.e);
        }

        // 二分搜索树的层序遍历
        public void levelOrder(){

            Queue<Node> q = new LinkedList<>();
            q.add(root);
            while(!q.isEmpty()){
                Node cur = q.remove();
                System.out.println(cur.e);

                if(cur.left != null)
                    q.add(cur.left);
                if(cur.right != null)
                    q.add(cur.right);
            }
        }

        // 寻找二分搜索树的最小元素
        public E minimum(){
            if(size == 0)
                throw new IllegalArgumentException("BST is empty!");

            return minimum(root).e;
        }

        // 返回以node为根的二分搜索树的最小值所在的节点
        private Node minimum(Node node){
            if(node.left == null)
                return node;
            return minimum(node.left);
        }

        // 寻找二分搜索树的最大元素
        public E maximum(){
            if(size == 0)
                throw new IllegalArgumentException("BST is empty");

            return maximum(root).e;
        }

        // 返回以node为根的二分搜索树的最大值所在的节点
        private Node maximum(Node node){
            if(node.right == null)
                return node;

            return maximum(node.right);
        }

        // 从二分搜索树中删除最小值所在节点, 返回最小值
        public E removeMin(){
            E ret = minimum();
            root = removeMin(root);
            return ret;
        }

        // 删除掉以node为根的二分搜索树中的最小节点
        // 返回删除节点后新的二分搜索树的根
        private Node removeMin(Node node){

            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            }

            node.left = removeMin(node.left);
            return node;
        }

        // 从二分搜索树中删除最大值所在节点
        public E removeMax(){
            E ret = maximum();
            root = removeMax(root);
            return ret;
        }

        // 删除掉以node为根的二分搜索树中的最大节点
        // 返回删除节点后新的二分搜索树的根
        private Node removeMax(Node node){

            if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            }

            node.right = removeMax(node.right);
            return node;
        }

        // 从二分搜索树中删除元素为e的节点
        public void remove(E e){
            root = remove(root, e);
        }

        // 删除掉以node为根的二分搜索树中值为e的节点, 递归算法
        // 返回删除节点后新的二分搜索树的根
        private Node remove(Node node, E e){

            if( node == null )
                return null;

            if( e.compareTo(node.e) < 0 ){
                node.left = remove(node.left , e);
                return node;
            }
            else if(e.compareTo(node.e) > 0 ){
                node.right = remove(node.right, e);
                return node;
            }
            else{   // e.compareTo(node.e) == 0

                // 待删除节点左子树为空的情况
                if(node.left == null){
                    Node rightNode = node.right;
                    node.right = null;
                    size --;
                    return rightNode;
                }

                // 待删除节点右子树为空的情况
                if(node.right == null){
                    Node leftNode = node.left;
                    node.left = null;
                    size --;
                    return leftNode;
                }

                // 待删除节点左右子树均不为空的情况

                // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
                // 用这个节点顶替待删除节点的位置
                Node successor = minimum(node.right);
                successor.right = removeMin(node.right);
                successor.left = node.left;

                node.left = node.right = null;

                return successor;
            }
        }

        @Override
        public String toString(){
            StringBuilder res = new StringBuilder();
            generateBSTString(root, 0, res);
            return res.toString();
        }

        // 生成以node为根节点，深度为depth的描述二叉树的字符串
        private void generateBSTString(Node node, int depth, StringBuilder res){

            if(node == null){
                res.append(generateDepthString(depth) + "null\n");
                return;
            }

            res.append(generateDepthString(depth) + node.e +"\n");
            generateBSTString(node.left, depth + 1, res);
            generateBSTString(node.right, depth + 1, res);
        }

        private String generateDepthString(int depth){
            StringBuilder res = new StringBuilder();
            for(int i = 0 ; i < depth ; i ++)
                res.append("--");
            return res.toString();
        }
    }

    private interface Set<E> {

        void add(E e);
        boolean contains(E e);
        void remove(E e);
        int getSize();
        boolean isEmpty();
    }

    private class BSTSet<E extends Comparable<E>> implements Set<E> {

        private BST<E> bst;

        public BSTSet(){
            bst = new BST<>();
        }

        @Override
        public int getSize(){
            return bst.size();
        }

        @Override
        public boolean isEmpty(){
            return bst.isEmpty();
        }

        @Override
        public void add(E e){
            bst.add(e);
        }

        @Override
        public boolean contains(E e){
            return bst.contains(e);
        }

        @Override
        public void remove(E e){
            bst.remove(e);
        }
    }

    public int uniqueMorseRepresentations(String[] words) {

        String[] codes = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        BSTSet<String> set = new BSTSet<>();
        for(String word: words){
            StringBuilder res = new StringBuilder();
            for(int i = 0 ; i < word.length() ; i ++)
                res.append(codes[word.charAt(i) - 'a']);

            set.add(res.toString());
        }

        return set.getSize();
    }
}
```

Map
```java
public interface Map<K, V> {

    void add(K key, V value);
    V remove(K key);
    boolean contains(K key);
    V get(K key);
    void set(K key, V newValue);
    int getSize();
    boolean isEmpty();
}
```

LinkedListMap
```java
import java.util.ArrayList;

public class LinkedListMap<K, V> implements Map<K, V> {

    private class Node{
        public K key;
        public V value;
        public Node next;

        public Node(K key, V value, Node next){
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(K key, V value){
            this(key, value, null);
        }

        public Node(){
            this(null, null, null);
        }

        @Override
        public String toString(){
            return key.toString() + " : " + value.toString();
        }
    }

    private Node dummyHead;
    private int size;

    public LinkedListMap(){
        dummyHead = new Node();
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

    private Node getNode(K key){
        Node cur = dummyHead.next;
        while(cur != null){
            if(cur.key.equals(key))
                return cur;
            cur = cur.next;
        }
        return null;
    }

    @Override
    public boolean contains(K key){
        return getNode(key) != null;
    }

    @Override
    public V get(K key){
        Node node = getNode(key);
        return node == null ? null : node.value;
    }

    @Override
    public void add(K key, V value){
        Node node = getNode(key);
        if(node == null){
            dummyHead.next = new Node(key, value, dummyHead.next);
            size ++;
        }
        else
            node.value = value;
    }

    @Override
    public void set(K key, V newValue){
        Node node = getNode(key);
        if(node == null)
            throw new IllegalArgumentException(key + " doesn't exist!");

        node.value = newValue;
    }

    @Override
    public V remove(K key){

        Node prev = dummyHead;
        while(prev.next != null){
            if(prev.next.key.equals(key))
                break;
            prev = prev.next;
        }

        if(prev.next != null){
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size --;
            return delNode.value;
        }

        return null;
    }

    public static void main(String[] args){

        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            LinkedListMap<String, Integer> map = new LinkedListMap<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
        }

        System.out.println();
    }
}
```

BSTMap
```java
import java.util.ArrayList;

public class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {

    private class Node{
        public K key;
        public V value;
        public Node left, right;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BSTMap(){
        root = null;
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

    // 向二分搜索树中添加新的元素(key, value)
    @Override
    public void add(K key, V value){
        root = add(root, key, value);
    }

    // 向以node为根的二分搜索树中插入元素(key, value)，递归算法
    // 返回插入新节点后二分搜索树的根
    private Node add(Node node, K key, V value){

        if(node == null){
            size ++;
            return new Node(key, value);
        }

        if(key.compareTo(node.key) < 0)
            node.left = add(node.left, key, value);
        else if(key.compareTo(node.key) > 0)
            node.right = add(node.right, key, value);
        else // key.compareTo(node.key) == 0
            node.value = value;

        return node;
    }

    // 返回以node为根节点的二分搜索树中，key所在的节点
    private Node getNode(Node node, K key){

        if(node == null)
            return null;

        if(key.equals(node.key))
            return node;
        else if(key.compareTo(node.key) < 0)
            return getNode(node.left, key);
        else // if(key.compareTo(node.key) > 0)
            return getNode(node.right, key);
    }

    @Override
    public boolean contains(K key){
        return getNode(root, key) != null;
    }

    @Override
    public V get(K key){

        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    @Override
    public void set(K key, V newValue){
        Node node = getNode(root, key);
        if(node == null)
            throw new IllegalArgumentException(key + " doesn't exist!");

        node.value = newValue;
    }

    // 返回以node为根的二分搜索树的最小值所在的节点
    private Node minimum(Node node){
        if(node.left == null)
            return node;
        return minimum(node.left);
    }

    // 删除掉以node为根的二分搜索树中的最小节点
    // 返回删除节点后新的二分搜索树的根
    private Node removeMin(Node node){

        if(node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size --;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    // 从二分搜索树中删除键为key的节点
    @Override
    public V remove(K key){

        Node node = getNode(root, key);
        if(node != null){
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    private Node remove(Node node, K key){

        if( node == null )
            return null;

        if( key.compareTo(node.key) < 0 ){
            node.left = remove(node.left , key);
            return node;
        }
        else if(key.compareTo(node.key) > 0 ){
            node.right = remove(node.right, key);
            return node;
        }
        else{   // key.compareTo(node.key) == 0

            // 待删除节点左子树为空的情况
            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            }

            // 待删除节点右子树为空的情况
            if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            }

            // 待删除节点左右子树均不为空的情况

            // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
            // 用这个节点顶替待删除节点的位置
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;

            return successor;
        }
    }

    public static void main(String[] args){

        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            BSTMap<String, Integer> map = new BSTMap<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
        }

        System.out.println();
    }
}
```
Leetcode 350. Intersection of Two Arrays II
```java
/// Leetcode 350. Intersection of Two Arrays II
/// https://leetcode.com/problems/intersection-of-two-arrays-ii/description/
///
/// 课程中在这里暂时没有介绍这个问题
/// 该代码主要用于使用Leetcode上的问题测试我们的BSTMap类

import java.util.ArrayList;

public class Solution {

    private interface Map<K, V> {

        void add(K key, V value);
        boolean contains(K key);
        V get(K key);
        void set(K key, V newValue);
        V remove(K key);
        int getSize();
        boolean isEmpty();
    }

    private class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {

        private class Node{
            public K key;
            public V value;
            public Node left, right;

            public Node(K key, V value){
                this.key = key;
                this.value = value;
                left = null;
                right = null;
            }
        }

        private Node root;
        private int size;

        public BSTMap(){
            root = null;
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

        // 向二分搜索树中添加新的元素(key, value)
        @Override
        public void add(K key, V value){
            root = add(root, key, value);
        }

        // 向以node为根的二分搜索树中插入元素(key, value)，递归算法
        // 返回插入新节点后二分搜索树的根
        private Node add(Node node, K key, V value){

            if(node == null){
                size ++;
                return new Node(key, value);
            }

            if(key.compareTo(node.key) < 0)
                node.left = add(node.left, key, value);
            else if(key.compareTo(node.key) > 0)
                node.right = add(node.right, key, value);
            else // key.compareTo(node.key) == 0
                node.value = value;

            return node;
        }

        // 返回以node为根节点的二分搜索树中，key所在的节点
        private Node getNode(Node node, K key){

            if(node == null)
                return null;

            if(key.equals(node.key))
                return node;
            else if(key.compareTo(node.key) < 0)
                return getNode(node.left, key);
            else // if(key.compareTo(node.key) > 0)
                return getNode(node.right, key);
        }

        @Override
        public boolean contains(K key){
            return getNode(root, key) != null;
        }

        @Override
        public V get(K key){

            Node node = getNode(root, key);
            return node == null ? null : node.value;
        }

        @Override
        public void set(K key, V newValue){
            Node node = getNode(root, key);
            if(node == null)
                throw new IllegalArgumentException(key + " doesn't exist!");

            node.value = newValue;
        }

        // 返回以node为根的二分搜索树的最小值所在的节点
        private Node minimum(Node node){
            if(node.left == null)
                return node;
            return minimum(node.left);
        }

        // 删除掉以node为根的二分搜索树中的最小节点
        // 返回删除节点后新的二分搜索树的根
        private Node removeMin(Node node){

            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            }

            node.left = removeMin(node.left);
            return node;
        }

        // 从二分搜索树中删除键为key的节点
        @Override
        public V remove(K key){

            Node node = getNode(root, key);
            if(node != null){
                root = remove(root, key);
                return node.value;
            }
            return null;
        }

        private Node remove(Node node, K key){

            if( node == null )
                return null;

            if( key.compareTo(node.key) < 0 ){
                node.left = remove(node.left , key);
                return node;
            }
            else if(key.compareTo(node.key) > 0 ){
                node.right = remove(node.right, key);
                return node;
            }
            else{   // key.compareTo(node.key) == 0

                // 待删除节点左子树为空的情况
                if(node.left == null){
                    Node rightNode = node.right;
                    node.right = null;
                    size --;
                    return rightNode;
                }

                // 待删除节点右子树为空的情况
                if(node.right == null){
                    Node leftNode = node.left;
                    node.left = null;
                    size --;
                    return leftNode;
                }

                // 待删除节点左右子树均不为空的情况

                // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
                // 用这个节点顶替待删除节点的位置
                Node successor = minimum(node.right);
                successor.right = removeMin(node.right);
                successor.left = node.left;

                node.left = node.right = null;

                return successor;
            }
        }
    }

    public int[] intersect(int[] nums1, int[] nums2) {

        BSTMap<Integer, Integer> map = new BSTMap<>();
        for(int num: nums1){
            if(!map.contains(num))
                map.add(num, 1);
            else
                map.set(num, map.get(num) + 1);
        }

        ArrayList<Integer> res = new ArrayList<>();
        for(int num: nums2){
            if(map.contains(num)){
                res.add(num);
                map.set(num, map.get(num) - 1);
                if(map.get(num) == 0)
                    map.remove(num);
            }
        }

        int[] ret = new int[res.size()];
        for(int i = 0 ; i < res.size() ; i ++)
            ret[i] = res.get(i);

        return ret;
    }
}
```

Leetcode 349. Intersection of Two Arrays
```java
/// Leetcode 349. Intersection of Two Arrays
/// https://leetcode.com/problems/intersection-of-two-arrays/description/

import java.util.ArrayList;
import java.util.TreeSet;

class Solution349 {
    public int[] intersection(int[] nums1, int[] nums2) {

        TreeSet<Integer> set = new TreeSet<>();
        for(int num: nums1)
            set.add(num);

        ArrayList<Integer> list = new ArrayList<>();
        for(int num: nums2){
            if(set.contains(num)){
                list.add(num);
                set.remove(num);
            }
        }

        int[] res = new int[list.size()];
        for(int i = 0 ; i < list.size() ; i ++)
            res[i] = list.get(i);
        return res;
    }
}
```

## 参考

学习刘宇波《玩转数据结构》课程，整理及实践。
