import java.util.ArrayList;

/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class Solution {
    public int kthLargest(TreeNode root, int k) {
        ArrayList<Integer> l = new ArrayList<>();
        inorderR(root, l);
        return l.get(k);
    }

    public void inorderR(TreeNode root, ArrayList<Integer> ans) {
        if (root == null) {
            return;
        }

        inorderR(root.right, ans);
        ans.add(root.val);
        inorderR(root.left, ans);
    }
}