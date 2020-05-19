# 二分搜索树

[github地址](https://github.com/chentian114/data-struct-and-algorithm)

树结构本身是一种天然的组织结构。

将数据使用树结构存储后，出奇的高效。

常见的树结构：
- 二分搜索树(Binary Search Tree)
- 平衡二叉树：AVL；红黑树
- 堆；并查集
- 线段树；Trie(字典树/前缀树)

树是一种动态数据结构。

二叉树具有唯一根节点，每个节点最多有两个孩子，每个节点最多有一个父亲；
```java
class Node{
    E e;
    Node left;
    Node right;
}
```
二叉树具有天然递归结构，每个节点的左、右子树也是二叉树；

二叉树类型：
- 满二叉树：除叶子节点外，每个节点都有两个子节点；如果一个二叉树的层数为K，且结点总数是(2^k) -1，则它就是满二叉树
- 完全二叉树：若设二叉树的深度为k，除第 k 层外，其它各层 (1～k-1) 的结点数都达到最大个数，第k 层所有的结点都连续集中在最左边，这就是完全二叉树。
- 二分搜索树：树的每个节点的值大于其左子树的所有节点的值、小于其右子树的所有节点的值、每一棵子树也是二分搜索树；相同值的节点只会存在一个、存储的元素必须有可比较性。
- 平衡二叉树：它或者是一颗空树，或它的左子树和右子树的深度之差(平衡因子)的绝对值不超过1，且它的左子树和右子树都是一颗平衡二叉树。
- 最优二叉树（哈夫曼树）：树的带权路径长度达到最小，称这样的二叉树为最优二叉树，也称为哈夫曼树(Huffman Tree)。哈夫曼树是带权路径长度最短的树，权值较大的结点离根较近。

二叉树特性：
- 满二叉树节点总数与层数k的关系：(2^k) -1；
- 二叉树父子节点与层数据k的关系：一个节点的左节点是其父节点下标的2k+1，右节点是其父节点下标的2k+2；
- 满二叉树第k层一共多少个节点：2^(k-1)。
- 二分搜索树的顺序性，可以用于找前驱和后继、也可以找二叉树中某个元素的floor(),ceil()；也可找排行rank；


## 应用

## 时间复杂度分析
n为树中节点个数，h为树的深度, 在满二叉树的情况下，h = log(n+1)，以2为底的对数

- 添加add:      O(h)  -> O(logn)平均，最差O(n)
- 查找contains：O(h)  -> O(logn)平均，最差O(n)
- 删除remove：  O(h)  -> O(logn)平均，最差O(n)

二分搜索树可能会退化成链表。

## 原理

- 添加新元素：
    - 如果二叉树为空，添加的第一个元素成为根节点； 
    - 添加一个新元素，与根节点比较，如果小，就往其左子树添加；如果大就往其右子树添加；当往左(或右)子树添加且左(或右)子树为空时，新元素即为其左(或右)子树的根节点；否则依此继续递归下；
    - 二分搜索树不包含重复元素，如果想包含重复元素的话，只需要定义：左子树小于等于节点；或者右子树大于等于节点；
    - 二分搜索树添加元素的非递归写法，和链表很像；
    - 改进递归方法：递归终止条件：递归到当前节点为空时，用新元素创建新节点，返回插入新节点后二分搜索树的根；节点左（或右）子树等于将元素插入左（或右）子树返回的结果；
- 查询元素：从根节点递归遍历二叉树是否包含查询的元素，当节点为空时，返回false；与节点比较，相等返回false；小于往左子树寻找；大于往右子树中寻找；
- 二叉树的遍历：把所有节点都访问一遍
  - **前序遍历递归实现**： 从根节点开始递归遍历二叉树，如果节点为空直接返回；访问当前节点元素；递归访问当前节点左子树；递归访问当前节点右子树；
  - **前序遍历非递归实现**：利用栈存放访问的节点；初始将根节点压入栈，将栈顶元素弹出栈，访问该节点元素，并将该节点的右孩子压入栈、再将该节点左孩子压入栈；再将栈顶元素弹出，访问该节点元素，并将该节点的右孩子、再左孩子压入栈，如此循环下去，直接栈为空；左/右孩子为空时不需要压入栈；
  - **中序遍历**：从根节点开始递归遍历二叉树，如果节点为空直接返回；递归访问当前节点左子树；访问当前节点元素；递归访问当前节点右子树；
  - **后序遍历**：从根节点开始递归遍历二叉树，如果节点为空直接返回；递归访问当前节点左子树；递归访问当前节点右子树；访问当前节点元素；
  - **层序遍历**：逐层访问二叉树节点元素，利用队列来存放元素；将根节点放入队列；将队首元素取出，访问该节点元素，再将该节点的左孩子、再将右孩子放入队列；再将队首元素出队，如此循环，直到队列为空；左/右孩子为空时不需要入队；
- 找最小值：从根节点开始，一直往左找左子树，直到左子树为空为止的节点；
- 找最大值：从根节点开始，一直往右找右子树，直到右子树为空为止的节点；
- 删除最小值：找到最小值节点，保存该节点的右子树，并返回作为删除节点子树的根；
- 删除最大值：找到最大值节点，保存该节点的左子树，并返回作为删除节点子树的根；
- 删除任意节点
  - 删除只有左孩子的节点：保存删除节点的左子树，放在要删除节点的位置；
  - 删除只有右孩子的节点：保存删除节点的右子树，放在要删除节点的位置；
  - 删除左右都有孩子的节点(Hibbard)：删除左右都有孩子的节点d，从d节点右子树找其后继(即右子树中最小值)，保存后继，删除右子树中最小值，放在要删除节点的位置；（或找前驱）
  - 1. 删除左右都有孩子的节点d; 
  - 2. 找到 s = min(d->right),s是d的后继; 
  - 3. s->right = delMin(d->right);
  - 4. s->left = d->left;
  - 5. 删除d,s是新的子树的根。
- 二叉搜索树的顺序性-找排行第k的元素：每个节点可维护一个该节点为根的树的size；
- 维护depth的二分搜索树
- 支持重复元素的二分搜索树

## 实现

1. 二叉搜索树实现
2. Leetcode 804. Unique Morse Code Words 测试二叉搜索树
3. Leetcode 144. Binary Tree Preorder Traversal 测试前序遍历的非递归写法
4. Leetcode 94.binary-tree-inorder-traversal
5. Leetcode 145.binary-tree-postorder-traversal

二叉搜索树实现
```java
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BST<E extends Comparable<E>> {

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

```

Leetcode 804. Unique Morse Code Words 测试二叉搜索树
```java
public class Solution {

    private class BST<E extends Comparable<E>> {

        private class Node {
            public E e;
            public Node left, right;

            public Node(E e) {
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

            if(root == null){
                root = new Node(e);
                size ++;
            }
            else
                add(root, e);
        }

        // 向以node为根的二分搜索树中插入元素e，递归算法
        private void add(Node node, E e){
            if(e.equals(node.e))
                return;
            else if(e.compareTo(node.e) < 0 && node.left == null){
                node.left = new Node(e);
                size ++;
                return;
            }
            else if(e.compareTo(node.e) > 0 && node.right == null){
                node.right = new Node(e);
                size ++;
                return;
            }

            if(e.compareTo(node.e) < 0)
                add(node.left, e);
            else //e.compareTo(node.e) > 0
                add(node.right, e);
        }
    }

    public int uniqueMorseRepresentations(String[] words) {

        String[] codes = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        BST<String> bst = new BST<>();
        for(String word: words){
            StringBuilder res = new StringBuilder();
            for(int i = 0 ; i < word.length() ; i ++)
                res.append(codes[word.charAt(i) - 'a']);
            bst.add(res.toString());
        }

        return bst.size();
    }
}
```

Leetcode 144. Binary Tree Preorder Traversal 测试前序遍历的非递归写法
```java
import java.util.List;
import java.util.LinkedList;
import java.util.Stack;

public class Solution {

    // Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<Integer> preorderTraversal(TreeNode root) {

        List<Integer> res = new LinkedList<>();
        if(root == null)
            return res;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode cur = stack.pop();
            res.add(cur.val);

            if(cur.right != null)
                stack.push(cur.right);
            if(cur.left != null)
                stack.push(cur.left);
        }

        return res;
    }
}

// PreOrder Morris Traversal
// Time Complexity: O(n), n is the node number in the tree
// Space Complexity: O(1)
public class PreorderSolution {

    public List<Integer> preorderTraversal(TreeNode root) {

        ArrayList<Integer> res = new ArrayList<Integer>();
        if(root == null)
            return res;

        TreeNode cur = root;
        while(cur != null){
            if(cur.left == null){
                res.add(cur.val);
                cur = cur.right;
            }
            else{
                TreeNode prev = cur.left;
                while(prev.right != null && prev.right != cur)
                    prev = prev.right;

                if(prev.right == null){
                    res.add(cur.val);
                    prev.right = cur;
                    cur = cur.left;
                }
                else{
                    prev.right = null;
                    cur = cur.right;
                }
            }
        }

        return res;
    }
}
```
Leetcode 94.binary-tree-inorder-traversal
```java
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// Classic Non-Recursive algorithm for inorder traversal
// Time Complexity: O(n), n is the node number in the tree
// Space Complexity: O(h), h is the height of the tree
public class Solution1 {

    public List<Integer> inorderTraversal(TreeNode root) {

        ArrayList<Integer> res = new ArrayList<Integer>();
        if(root == null)
            return res;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while(cur != null || !stack.empty()){

            while(cur != null){
               stack.push(cur);
                cur = cur.left;
            }

            cur = stack.pop();
            res.add(cur.val);
            cur = cur.right;
        }
        return res;
    }
}

// Another Classic Non-Recursive algorithm for inorder traversal
// Time Complexity: O(n), n is the node number in the tree
// Space Complexity: O(h), h is the height of the tree
public class Solution2 {

    public List<Integer> inorderTraversal(TreeNode root) {

        ArrayList<Integer> res = new ArrayList<Integer>();
        if(root == null)
            return res;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while(cur != null || !stack.empty()){

            if(cur != null){
                stack.push(cur);
                cur = cur.left;
            }
            else{
                cur = stack.pop();
                res.add(cur.val);
                cur = cur.right;
            }
        }
        return res;
    }
}

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// Inorder Morris Traversal
// Time Complexity: O(n), n is the node number in the tree
// Space Complexity: O(1)
public class InorderSolution {

    public List<Integer> inorderTraversal(TreeNode root) {

        ArrayList<Integer> res = new ArrayList<Integer>();
        if(root == null)
            return res;

        TreeNode cur = root;
        while(cur != null){

            if(cur.left == null){
                res.add(cur.val);
                cur = cur.right;
            }
            else{
                TreeNode prev = cur.left;
                while(prev.right != null && prev.right != cur)
                    prev = prev.right;

                if(prev.right == null){
                    prev.right = cur;
                    cur = cur.left;
                }
                else{
                    prev.right = null;
                    res.add(cur.val);
                    cur = cur.right;
                }
            }
        }
        return res;
    }
}
```

Leetcode 145.binary-tree-postorder-traversal
```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

// Morris PostOrder Traversal
//
// Time Complexity: O(n)
// Space Complexity: O(1)
public class PostorderSolution {

    public List<Integer> postorderTraversal(TreeNode root) {

        ArrayList<Integer> res = new ArrayList<Integer>();
        if(root == null)
            return res;

        TreeNode dummyRoot = new TreeNode(-1);
        dummyRoot.left = root;

        TreeNode cur = dummyRoot;
        while(cur != null){
            if(cur.left == null)
                cur = cur.right;
            else{
                TreeNode pre = cur.left;
                while(pre.right != null && pre.right != cur)
                    pre = pre.right;

                if(pre.right == null){
                    pre.right = cur;
                    cur = cur.left;
                }
                else{
                    pre.right = null;
                    reverseTraversal(cur.left, res);
                    cur = cur.right;
                }
            }
        }
        return res;
    }

    private void reverseTraversal(TreeNode node, ArrayList<Integer> res){
        int start = res.size();
        while(node != null){
            res.add(node.val);
            node = node.right;
        }

        int i = start, j = res.size() - 1;
        while(i < j){
            Integer t = res.get(i);
            res.set(i, res.get(j));
            res.set(j, t);

            i ++;
            j --;
        }
    }
}
```
## 参考

学习刘宇波《玩转数据结构》课程，整理及实践。