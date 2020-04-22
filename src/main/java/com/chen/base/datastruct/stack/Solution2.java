package com.chen.base.datastruct.stack;

import java.util.Stack;

/**
 * @author: Chentian
 * @date: Created in 2020/4/23 5:06
 * @desc  Leetcode 20. 有效的括号
 */
public class Solution2 {

    public boolean isValid(String s) {

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length() ; i++){
            char c = s.charAt(i);
            stack.push(c);
        }

        Stack<Character> outStack = new Stack<>();
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
        System.out.println(new Solution2().isValid(str));

        str = "()[]{}";
        System.out.println(new Solution2().isValid(str));

        str = "(]";
        System.out.println(new Solution2().isValid(str));

        str = "([)]";
        System.out.println(new Solution2().isValid(str));

        str = "{[]}";
        System.out.println(new Solution2().isValid(str));

        str = "([]";
        System.out.println(new Solution2().isValid(str));
    }

}
