/**
* -*- coding : utf-8 -*-
* @FileName  : 101_isSymmetric.java
* @Author    : Ruixiang JIANG (Songrise)
* @Time      : 2020-09-19
* @Github    ：https://github.com/songrise
* @Descriptions: Buggy
**/




/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class Solution {
    // public boolean SymNode(TreeNode left, TreeNode right) {
    // if (left != null && right != null) {
    // if (left.val != right.val) {
    // return false;
    // }
    // } else if (!(left == null && right == null)) {
    // return false;
    // }
    // }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        if (root.left != null && root.right != null) {
            if (root.left.val != root.right.val) {
                return false;
            }
        } else if (!(root.left == null && root.right == null)) {
            return false;
        }

        return isSymmetric(root.left) && isSymmetric(root.right);
    }
}