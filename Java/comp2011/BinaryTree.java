import java.util.ArrayDeque;
import java.util.Queue;

public class BinaryTree<T> {
    private class Node<T> {
        T data;
        public Node<T> leftChild, rightChild;
    }

    private void Levelorder(Node<T> n) {
        if (n == null)
            return;
        Queue<Node<T>> q = new ArrayDeque<Node<T>>() {
            {
                add(n);
            }
        };

        while (!q.isEmpty()) {
            Node<T> cur = q.poll();
            System.out.println(cur.data); // assume implemented toString()
            if (cur.leftCHild != null)
                q.add(cur.leftChild);
            if (cur.rightChild != null)
                q.add(cur.rightChild);
        }
    }
}
