package com.chen.base.datastruct.bst;

import java.util.*;

/**
 * @author: Chentian
 * @date: Created in 2020/5/19 6:56
 * @desc
 */
public class Solution145 {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postorderTraversal(root,list);
        return list;
    }

    private void postorderTraversal(TreeNode node, List<Integer> list) {
        if(node == null){
            return;
        }
        postorderTraversal(node.left,list);
        postorderTraversal(node.right,list);
        list.add(node.val);
        return;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        List<Integer> result = new Solution145().postorderTraversal(root);
        System.out.println(result);
    }
}
