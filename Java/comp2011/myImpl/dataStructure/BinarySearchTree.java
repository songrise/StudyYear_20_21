package comp2011.myImpl.dataStructure;

import java.util.ArrayDeque;
import java.util.Scanner;

public class BinarySearchTree {

    public static void main(String[] args) {
        // !++++++++++build tree++++++++++++++++++
        BinarySearchTree tree = new BinarySearchTree();
        int[] arr = { 214, 562, 83, 115, 97, 722, 398, 798, 408, 199, 37 };
        tree.batchAdd(arr);

        System.out.println("tree built.");
        // tree.levelDisplay();
        tree.prettyPrintTree();
        // !++++++++++build tree++++++++++++++++++

        // tree.display();

        // System.out.println("==========search=========");
        // System.out.println("199: " + tree.search(199));
        // System.out.println("336: " + tree.search(336));

        // Scanner keyboard = new Scanner(System.in);
        // System.out.println("Enter a number to search");
        // System.out.println(tree.search(keyboard.nextInt()));

        // System.out.println("Press enter to start insertion");
        // keyboard.nextLine();
        // keyboard.nextLine();
        // System.out.println("inserting two new students.");
        // tree.insert(336);
        // System.out.println("==========after insertion=========");
        // tree.display();
        // System.out.println("336: " + tree.search(336));
        // System.out.println("377: " + tree.search(377));
        // System.out.println("Press enter to start deletion");
        // keyboard.nextLine();
        // tree.delete(798);
        // System.out.println("==========after deleting 798=========");
        // tree.levelDisplay();
        // tree.delete(722);
        // System.out.println("==========after deleting 722=========");
        // tree.levelDisplay();
        // System.out.println("Enter a number to search");
        // System.out.println(tree.search(keyboard.nextInt()));
        // keyboard.close();
    }

    @SuppressWarnings("hiding")
    private class Node {
        int val; // bad practice; see comment above.
        public Node leftChild, rightChild;

        public Node(int val) {
            this.val = val;

        }

        public String toString() {
            return String.valueOf(val);
        }
    }

    Node root;

    public BinarySearchTree() {
        root = null;
    }

    public void batchAdd(int[] labels) {
        if (labels == null) {
            return;
        }
        for (int i : labels) {
            insert(i);
        }
    }

    // the recursive version of insert and its wrapper.
    public void recInsert(int val) {
        Node newNode = insert(root, val);
        if (root == null)
            root = newNode;
    }

    private Node insert(Node curRoot, int val) {
        if (curRoot == null) {
            Node newNode = new Node(val);
            curRoot = newNode;
            return curRoot;
        }
        if (val <= curRoot.val)
            curRoot.leftChild = insert(curRoot.leftChild, val);
        else
            curRoot.rightChild = insert(curRoot.rightChild, val);
        return curRoot;
    }

    // the recursive version of preorder traversal and its wrapper.
    public void preorder() {
        preorder(root);
    }

    public void preorder(Node curRoot) {
        if (curRoot == null)
            return;
        System.out.println(curRoot);
        preorder(curRoot.leftChild);
        preorder(curRoot.rightChild);
    }

    // the recursive version of inorder traversal and its wrapper.
    public void inorder() {
        inorder(root);
    }

    private void inorder(Node curRoot) {
        if (curRoot == null)
            return;
        inorder(curRoot.leftChild);
        System.out.println(curRoot);
        inorder(curRoot.rightChild);
    }

    // the recursive version of postorder traversal and its wrapper.
    public void postorder() {
        postorder(root);
    }

    public void postorder(Node curRoot) {
        if (curRoot == null)
            return;
        postorder(curRoot.leftChild);
        postorder(curRoot.rightChild);
        System.out.println(curRoot);
    }

    public void display() {
        inorder(root);
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void deleteMin() {
        if (isEmpty()) {
            System.out.println("Oops...");
            return;
        }
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.leftChild == null)
            return x.rightChild;
        x.leftChild = deleteMin(x.leftChild);
        return x;
    }

    // the recursive version of deletion and its wrapper.
    public void recDelete(int val) {
        if (isEmpty()) {
            System.out.println("Oops...");
            return;
        }
        root = delete(root, val);
    }

    private void delete(int val) {
        delete(root, val);
    }

    // the trick is on the returned node.
    private Node delete(Node x, int val) {

        if (val < x.val)
            x.leftChild = delete(x.leftChild, val);
        else if (val > x.val)
            x.rightChild = delete(x.rightChild, val);
        else { // x is the node to be deleted.
            if (x.rightChild == null)
                return x.leftChild;
            if (x.leftChild == null)
                return x.rightChild;
            // else has to children
            Node t = x;
            x = recFindMin(t.rightChild);
            x.rightChild = deleteMin(t.rightChild);
            x.leftChild = t.leftChild;
        }
        return x;
    }

    // tasks for lab 9
    // try to write both recursive and iterative versions.
    public Node findMin() {
        Node crt = root;
        for (; crt.leftChild != null; crt = crt.leftChild)
            ;
        return crt;
    }

    public Node findMax() {
        Node crt = root;
        for (; crt.rightChild != null; crt = crt.rightChild)
            ;
        return crt;
    }

    private Node recFindMin(Node x) {
        return null;
    }

    public Node search(int val) {
        return recSearch(this.root, val);
    }

    private Node recSearch(Node n, int val) {
        if (n.val == val) {
            return n;
        }
        if (n.leftChild != null)
            return recSearch(n.leftChild, val);
        if (n.rightChild != null)
            return recSearch(n.rightChild, val);

        return n;
    }

    // A recursive version is given above.
    // Here you are asked to write the iterative version.
    // you're suggested to finish search() before this.
    public void insert(int val) {
        if (root == null) {
            root = new Node(val);
            return;
        }

        Node parent = search(val);
        if (val < parent.val) {
            parent.leftChild = new Node(val);
        } else {
            parent.rightChild = new Node(val);
        }
    }

    public Node predecessor(Node x) {
        return null;
    }

    public Node successor(Node x) {
        return null;
    }

    public void prettyPrintTree(Node node, String prefix, boolean isLeft) {
        if (node == null) {
            System.out.println("Empty tree");
            return;
        }

        if (node.rightChild != null) {
            prettyPrintTree(node.rightChild, prefix + (isLeft ? "│   " : "    "), false);
        }

        System.out.println(prefix + (isLeft ? "└── " : "┌── ") + node.val);

        if (node.leftChild != null) {
            prettyPrintTree(node.leftChild, prefix + (isLeft ? "    " : "│   "), true);
        }
    }

    public void prettyPrintTree() {
        prettyPrintTree(this.root, "", true);
    }

    // Optional tasks
    public void levelDisplay() {
        if (root == null) {
            return;
        }
        ArrayDeque<Node> q = new ArrayDeque<Node>() {
            {
                addFirst(root);
            }
        };
        int level = 1;
        while (!q.isEmpty()) {
            for (int i = 0; i < level; i++) {
                Node crtNode = q.pollLast();

                System.out.printf(crtNode.toString() + " ");
                if (crtNode.leftChild != null) {
                    q.addFirst(crtNode.leftChild);
                }
                if (crtNode.rightChild != null) {
                    q.addFirst(crtNode.rightChild);
                }
            }
            level <<= 1;
        }
    }

    // // non-recursive version: very very very difficult; too many cases to
    // consider.

    // Node deleteNode(Node x, int val) {
    // if (x == null)
    // return null;
    // if (key < x.key)
    // x.lC = delete(x.lC, key);
    // else if (key > x.key)
    // x.rC = delete(x.rC, key);
    // else { // x is the node to be deleted.
    // if (x.rC == null)
    // return x.lC;
    // if (x.lC == null)
    // return x.rC;
    // Node t = x;
    // x = recFindMin(t.rC);
    // x.rC = deleteMin(t.rC);
    // x.lC = t.lC;
    // }
    // return x;
    // }

    // Other easy exercises
    // the largest distance from the root to a leaf.
    public int height() {
        return -1;
    }

    // the number nodes in the tree.
    public int size() {
        return -1;
    }

    // the number of nodes having two children.
    public int fullNodes() {
        return -1;
    }

}
