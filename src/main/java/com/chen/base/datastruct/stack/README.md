# 栈

栈也是一种线性结构

相比数组，栈对应的操作是数组的子集

只能从一端添加元素，也只能从一端取出元素，这一端称为栈顶

栈是一种后进先出的数据结构 LIST IN FIRST OUT(LIFO)

在计算机的世界里，栈拥有着不可思议的作用


## 应用

- 无处不在的Undo操作（撤销）
- 程序调用使用的系统栈
- 括号匹配-编译器

## 时间复杂度分析

ArrayStack<E>
- void push(E)  入栈  O(1)  均摊
- E pop()  出栈   O(1) 均摊
- E peek()  查看栈顶元素   O(1)
- int getSize()  获取栈元素数量  O(1)
- boolean isEmpty()  是否为空   O(1)

## 原理

只允许在栈顶进行操作
- 可使用多种底层数据结构实现，如使用数组实现栈；
- 添加元素时，将元素放置在栈顶，栈的元素数量+1；
- 删除元素时，将栈顶的元素取出，栈的元素数量-1；

## 实现

Stack<E>
- void push(E)  入栈
- E pop()  出栈
- E peek()  查看栈顶元素
- int getSize()  获取栈元素数量
- boolean isEmpty()  是否为空

```java
public interface Stack<E> {

    int getSize();
    boolean isEmpty();
    void push(E e);
    E pop();
    E peek();
}

public class ArrayStack<E> implements Stack<E> {

    private Array<E> array;

    public ArrayStack(int capacity){
        array = new Array<>(capacity);
    }

    public ArrayStack(){
        array = new Array<>();
    }

    @Override
    public int getSize(){
        return array.getSize();
    }

    @Override
    public boolean isEmpty(){
        return array.isEmpty();
    }

    public int getCapacity(){
        return array.getCapacity();
    }

    @Override
    public void push(E e){
        array.addLast(e);
    }

    @Override
    public E pop(){
        return array.removeLast();
    }

    @Override
    public E peek(){
        return array.getLast();
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Stack: ");
        res.append('[');
        for(int i = 0 ; i < array.getSize() ; i ++){
            res.append(array.get(i));
            if(i != array.getSize() - 1)
                res.append(", ");
        }
        res.append("] top");
        return res.toString();
    }
}
```

## 练习

Leetcode练习-20 Valid Parentheses 匹配括号

```
给定一个只包括 '('，')'，'{'，'}'，'['，']'的字符串，判断字符串是否有效。

有效字符串需满足：

左括号必须用相同类型的右括号闭合。
左括号必须以正确的顺序闭合。
注意空字符串可被认为是有效字符串。

示例 1:
输入: "()"
输出: true

示例2:
输入: "()[]{}"
输出: true

示例3:
输入: "(]"
输出: false

示例4:
输入: "([)]"
输出: false

示例5:
输入: "{[]}"
输出: true
```
```java
import java.util.Stack;

class Solution {

    public boolean isValid(String s) {

        Stack<Character> stack = new Stack<>();
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

## 参考

学习刘宇波《玩转数据结构》课程，整理及实践。