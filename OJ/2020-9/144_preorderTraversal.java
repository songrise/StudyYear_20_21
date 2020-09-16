import java.util.Deque;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

/**
 * -*- coding : utf-8 -*-
 * 
 * @FileName : 144_preorderTraversal.java
 * @Author : Ruixiang JIANG (Songrise)
 * @Time : 2020-09-16
 * @Github ：https://github.com/songrise
 * @Descriptions: None
 **/

class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<Integer>();
        Deque<TreeNode> s = new ArrayDeque<TreeNode>();
        if (root == null) {
            return ans;
        }

        s.push(root);

        while (!s.isEmpty()) {
            TreeNode crt = s.pop();
            if (crt.right != null) {
                s.push(crt.right);
            }

            if (crt.left != null) {
                s.push(crt.left);
            }

            ans.add(crt.val);
        }
        return ans;

    }
}