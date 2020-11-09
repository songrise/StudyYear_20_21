import java.util.ArrayDeque;


/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class Solution {

    public boolean hasPathSum(TreeNode root, int sum) {
        ArrayDeque<TreeNode> s = new ArrayDeque<>() {
            {
                add(root);
            }
        };

        while (!s.isEmpty()) {
            TreeNode curNode = s.pop();
            if (curNode.left != null) {
                curNode.left.val += curNode.val;
                s.push(curNode.left);
            }
            if (curNode.right != null) {
                curNode.right.val += curNode.val;
                s.push(curNode.right);
            }
            if (curNode.right == null && curNode.left == null && curNode.val == sum) {
                return true;
            }
        }
        return false;
    }

}