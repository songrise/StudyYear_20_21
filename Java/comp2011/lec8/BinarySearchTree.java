// package comp2011.lec8;

import java.util.ArrayDeque;
import java.util.Scanner;

/**
 * 
 * @author yixin cao (October 20, 2020)
 *
 *         Binary search tree.
 * 
 *         Elements in a binary search tree need to be comparable. The standard
 *         practice is to require the class T to implement the
 *         {@link Comparable} interface. Then we can call
 *         {@code e1.compareTo​(e2)} to compare elements e1 and e2, both of type
 *         T. See more at
 *         https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/Comparable.html
 * 
 *         To simplify our demonstration, we add an explicit variable key and
 *         use it for comparisons. This is a very bad practice, and please never
 *         use it for programming.
 * 
 */
class Student {
    int id;
    String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String toString() {
        return id + ", " + name;
    }
}

/*
 * Binary search trees
 */
public class BinarySearchTree<T> {
    @SuppressWarnings("hiding")
    private class Node<T> {
        int key; // bad practice; see comment above.
        T data;
        public Node<T> leftChild, rightChild;

        public Node(int key, T data) {
            this.key = key;
            this.data = data;
        }

        public String toString() {
            return data.toString();
        }
    }

    Node<T> root;

    public BinarySearchTree() {
        root = null;
    }

    // the recursive version of insert and its wrapper.
    public void recInsert(int key, T data) {
        Node<T> newNode = insert(root, key, data);
        if (root == null)
            root = newNode;
    }

    private Node<T> insert(Node<T> curRoot, int key, T data) {
        if (curRoot == null) {
            Node<T> newNode = new Node<T>(key, data);
            curRoot = newNode;
            return curRoot;
        }
        if (key <= curRoot.key)
            curRoot.leftChild = insert(curRoot.leftChild, key, data);
        else
            curRoot.rightChild = insert(curRoot.rightChild, key, data);
        return curRoot;
    }

    // the recursive version of preorder traversal and its wrapper.
    public void preorder() {
        preorder(root);
    }

    public void preorder(Node<T> curRoot) {
        if (curRoot == null)
            return;
        System.out.println(curRoot.data);
        preorder(curRoot.leftChild);
        preorder(curRoot.rightChild);
    }

    // the recursive version of inorder traversal and its wrapper.
    public void inorder() {
        inorder(root);
    }

    private void inorder(Node<T> curRoot) {
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

    public void postorder(Node<T> curRoot) {
        if (curRoot == null)
            return;
        postorder(curRoot.leftChild);
        postorder(curRoot.rightChild);
        System.out.println(curRoot.data);
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

    private Node<T> deleteMin(Node<T> x) {
        if (x.leftChild == null)
            return x.rightChild;
        x.leftChild = deleteMin(x.leftChild);
        return x;
    }

    // the recursive version of deletion and its wrapper.
    public void recDelete(int key) {
        if (isEmpty()) {
            System.out.println("Oops...");
            return;
        }
        root = delete(root, key);
    }

    // the trick is on the returned node.
    private Node<T> delete(Node<T> x, int key) {
        if (x == null)
            return null;
        if (key < x.key)
            x.leftChild = delete(x.leftChild, key);
        else if (key > x.key)
            x.rightChild = delete(x.rightChild, key);
        else { // x is the node to be deleted.
            if (x.rightChild == null)
                return x.leftChild;
            if (x.leftChild == null)
                return x.rightChild;
            Node<T> t = x;
            x = recFindMin(t.rightChild);
            x.rightChild = deleteMin(t.rightChild);
            x.leftChild = t.leftChild;
        }
        return x;
    }

    public static void main(String[] args) {
        BinarySearchTree<Student> tree = new BinarySearchTree<Student>();
        int[] ids = { 214, 562, 83, 115, 97, 722, 398, 798, 408, 199, 37 };
        String[] names = { "Chan Eason", "Cheung Jacky", "Winnie", "Ho Denise", "Mickey", "Leung Gigi", "Joey Yung",
                "Teddy", "Peppa", "Tse Kay", "Andy Lau" };
        for (int i = 0; i < 11; i++)
            tree.insert(ids[i], new Student(ids[i], names[i]));
        /*
         * tree.insert(214, new Student(214, "Chan Eason")); tree.insert(562, new
         * Student(562, "Cheung Jacky")); tree.insert( 83, new Student( 83, "Winnie"));
         * tree.insert(115, new Student(115, "Ho Denise")); tree.insert( 97, new
         * Student( 97, "Mickey")); tree.insert(722, new Student(722, "Leung Gigi"));
         * tree.insert(398, new Student(398, "Joey Yung")); tree.insert(798, new
         * Student(798, "Teddy")); tree.insert(408, new Student(408, "Peppa"));
         * tree.insert(199, new Student(199, "Tse Kay")); tree.insert( 37, new Student(
         * 37, "Andy Lau"));
         */

        System.out.println("tree built.");
        // tree.levelDisplay();
        tree.display();

        System.out.println("==========search=========");
        System.out.println("199: " + tree.search(199));
        System.out.println("336: " + tree.search(336));

        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter a number to search");
        System.out.println(tree.search(keyboard.nextInt()));

        System.out.println("Press enter to start insertion");
        keyboard.nextLine();
        keyboard.nextLine();
        System.out.println("inserting two new students.");
        tree.insert(336, new Student(336, "Yung Joey"));
        System.out.println("==========after insertion=========");
        tree.display();
        System.out.println("336: " + tree.search(336));
        System.out.println("377: " + tree.search(377));
        System.out.println("Press enter to start deletion");
        keyboard.nextLine();
        tree.delete(798);
        System.out.println("==========after deleting 798=========");
        tree.levelDisplay();
        tree.delete(722);
        System.out.println("==========after deleting 722=========");
        tree.levelDisplay();
        System.out.println("Enter a number to search");
        System.out.println(tree.search(keyboard.nextInt()));
        keyboard.close();
    }

    // tasks for lab 9
    // try to write both recursive and iterative versions.
    public Node<T> findMin() {
        Node<T> crt = root;
        for (; crt.leftChild != null; crt = crt.leftChild)
            ;
        return crt;
    }

    public Node<T> findMax() {
        Node<T> crt = root;
        for (; crt.rightChild != null; crt = crt.rightChild)
            ;
        return crt;
    }

    private Node<T> recFindMin(Node<T> x) {
        return null;
    }

    public Node<T> search(int key) {
        return recSearch(this.root, key);
    }

    private Node<T> recSearch(Node<T> n, int key) {
        if (n.key == key) {
            return n;
        }
        if (n.leftChild != null)
            recSearch(n.leftChild, key);
        if (n.rightChild != null)
            recSearch(n.rightChild, key);

        return n;
    }

    // A recursive version is given above.
    // Here you are asked to write the iterative version.
    // you're suggested to finish search() before this.
    public void insert(int key, T data) {
        if (root == null) {
            root = new Node(key, data);
            return;
        }

        Node<T> parent = search(key);
        if (key < parent.key) {
            parent.leftChild = new Node(key, data);
        } else {
            parent.rightChild = new Node(key, data);
        }
    }

    public Node<T> predecessor(Node<T> x) {
        return null;
    }

    public Node<T> successor(Node<T> x) {
        return null;
    }

    // Optional tasks
    public void levelDisplay() {

        ArrayDeque<Node<T>> q = new ArrayDeque<>() {
            {
                addFirst(root);
            }
        };
        int max = 1;
        int PLACE_HOLDER = -1;
        while (!q.isEmpty()) {
            for (int i = 0; i < max; i++) {
                Node<T> crtNode = q.pollLast();
                if (crtNode.key != PLACE_HOLDER) {
                    System.out.printf(crtNode.toString() + " ");
                    q.addFirst(crtNode.leftChild == null ? new Node(PLACE_HOLDER, null) : crtNode.leftChild);
                    q.addFirst(crtNode.rightChild == null ? new Node(PLACE_HOLDER, null) : crtNode.rightChild);
                } else {
                    System.out.printf("null, ");
                }
            }
            System.out.println("");
            max <<= 1;
        }
    }

    // non-recursive version: very very very difficult; too many cases to consider.
    public boolean delete(int key) {
        root = deleteNode(root, key);
    }

    Node<T> iterDelNode(Node<T> x, int key) {
        if (x == null) {
            return null;
        }
        ArrayDeque<Node<T>> stack = new ArrayDeque<>() {
            {
                push(x);
            }
        };

        while (!stack.isEmpty()) {
            Node<T> crtNode = stack.pop();
            if (crtNode.key < x.key) {
                stack.push(crtNode.leftChild);
                continue;
            }
            if (crtNode.key > x.key) {
                stack.push(crtNode.leftChild);
                continue;
            }

        }
    }

    Node<T> deleteNode(Node<T> x, int key) {
        if (x == null)
            return null;
        if (key < x.key)
            x.lC = delete(x.lC, key);
        else if (key > x.key)
            x.rC = delete(x.rC, key);
        else { // x is the node to be deleted.
            if (x.rC == null)
                return x.lC;
            if (x.lC == null)
                return x.rC;
            Node<T> t = x;
            x = recFindMin(t.rC);
            x.rC = deleteMin(t.rC);
            x.lC = t.lC;
        }
        return x;
    }

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
