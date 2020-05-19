package com.chen.base.datastruct.bst;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author: Chentian
 * @date: Created in 2020/5/18 6:51
 * @desc
 */
public class Solution144 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    private void preOrder(TreeNode node,List<Integer> valList) {
        if(node == null){
            return;
        }
        valList.add(node.val);
        preOrder(node.left,valList);
        preOrder(node.right,valList);
    }

    private void preOrderNR(TreeNode root,List<Integer> valList){
        if(root == null){
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            valList.add(node.val);
            if(node.right != null){
                stack.push(node.right);
            }
            if(node.left != null){
                stack.push(node.left);
            }
        }
    }


    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> valList = new ArrayList<>();
        preOrder(root,valList);
        return valList;
    }

    public static void main(String[] args) {

    }
}
