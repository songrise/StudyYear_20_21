/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */

/**
 * -*- coding : utf-8 -*-
 * 
 * @FileName : 226_invertTree.java
 * @Author : Ruixiang JIANG (Songrise)
 * @Time : 2020-09-16
 * @Github ：https://github.com/songrise
 * @Descriptions: None
 **/

class Solution {
    void swap(TreeNode root) {
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;

        } else {
            root.left = invertTree(root.left);
            root.right = invertTree(root.right);
            swap(root);
            return root;
        }
    }
}