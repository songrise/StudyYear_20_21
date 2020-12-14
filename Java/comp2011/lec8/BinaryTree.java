package comp2011.lec8;

import java.util.Queue;
import java.util.LinkedList;

class TreeUtil {
    public static int nbrLeaves(BinaryTree.TreeNode n) {
        if (n == null) {
            return 0;
        }

        if (n.left == null && n.right == null) {
            return 1;
        }
        return nbrLeaves(n.left) + nbrLeaves(n.right);
    }

    public static int nbrNodes(BinaryTree.TreeNode n) {
        if (n == null) {
            return 0;
        }
        return 1 + nbrNodes(n.left) + nbrNodes(n.right);
    }

    public static int height(BinaryTree.TreeNode n) {
        if (n == null) {
            return -1;
        }

        return Math.max(height(n.left), height(n.right)) + 1;
    }

    public static boolean isBalanced(BinaryTree.TreeNode n) {
        if (n == null) {
            return true;
        }
        if (Math.abs(height(n.left) - height(n.right)) > 1) {
            return false;
        }
        return isBalanced(n.left) && isBalanced(n.right);
    }
}

class BinaryTree {
    /**
     * TreeNode
     */
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    TreeNode root;

    BinaryTree() {
    }

    BinaryTree(int val) {
        this.root = new TreeNode(val);
    }

    public static String treeNodeToString(TreeNode root) {
        if (root == null) {
            return "[]";
        }

        String output = "";
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            if (node == null) {
                output += "null, ";
                continue;
            }

            output += String.valueOf(node.val) + ", ";
            nodeQueue.add(node.left);
            nodeQueue.add(node.right);
        }
        return "[" + output.substring(0, output.length() - 2) + "]";
    }

    public static TreeNode stringToTreeNode(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return null;
        }

        String[] parts = input.split(",");
        String item = parts[0];
        TreeNode root = new TreeNode(Integer.parseInt(item));
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        int index = 1;
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int leftNumber = Integer.parseInt(item);
                node.left = new TreeNode(leftNumber);
                nodeQueue.add(node.left);
            }

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int rightNumber = Integer.parseInt(item);
                node.right = new TreeNode(rightNumber);
                nodeQueue.add(node.right);
            }
        }
        return root;
    }

    public static void prettyPrintTree(TreeNode node, String prefix, boolean isLeft) {
        if (node == null) {
            System.out.println("Empty tree");
            return;
        }

        if (node.right != null) {
            prettyPrintTree(node.right, prefix + (isLeft ? "│   " : "    "), false);
        }

        System.out.println(prefix + (isLeft ? "└── " : "┌── ") + node.val);

        if (node.left != null) {
            prettyPrintTree(node.left, prefix + (isLeft ? "    " : "│   "), true);
        }
    }

    public static void prettyPrintTree(TreeNode node) {
        prettyPrintTree(node, "", true);
    }

    public static void main(String[] args) {
        String ts = "[1,null,4,5,6,null,7,8,9]";
        TreeNode t = BinaryTree.stringToTreeNode(ts);
        BinaryTree.prettyPrintTree(t);
        System.out.println(TreeUtil.nbrLeaves(t));
        if (TreeUtil.isBalanced(t)) {
            System.out.println("t is balanced");
        }
        System.out.println("====================");
        TreeNode t2 = BinaryTree.stringToTreeNode("[30, 32, 40, 45, 50, 56, 60, 64, 70, 73, 80]");
        BinaryTree.prettyPrintTree(t2);
        if (TreeUtil.isBalanced(t2)) {
            System.out.println("t2 is balanced");
        }
        System.out.println(TreeUtil.height(t2));
        System.out.println(TreeUtil.nbrNodes(t2));

    }

}
